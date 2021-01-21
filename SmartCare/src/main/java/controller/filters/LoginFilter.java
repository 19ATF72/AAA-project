/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filters;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Dao.DynamicDao;
import model.Entity.AppointmentEntity;
import model.Entity.EmployeeEntity;
import model.Entity.OrganisationEntity;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Helper.Enums;
import model.Helper.StoredProcedures;
import model.Service.AppointmentService;
import model.Service.EmployeeService;
import model.Service.ListService;
import model.Service.OrganisationService;
import model.Service.PatientService;
import model.Service.UserService;


public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig config) throws ServletException {
        // If you have any <init-param> in web.xml, then you could get them
        // here by config.getInitParameter("name") and assign it as field.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        
        StoredProcedures storedData = new StoredProcedures(); 
        
        DynamicDao dynamicDao = new DynamicDao();
        
        ListService listHandler = new ListService();
        UserEntity user = new UserEntity();
        UserService userService = new UserService(dynamicDao);
        //set database object connection 
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        //check if connection was stablished only needs to be done here TODO remove from other classes
         if (dynamicDao.getCon() == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        //saves data objects in the session
        session.setAttribute("dynamicDao", dynamicDao);
        session.setAttribute("ListHandler", listHandler);
        session.setAttribute("user", user);     
        session.setAttribute("storedData", storedData);
        session.setAttribute("docSalary", (Double)request.getServletContext().getAttribute("docSalary"));
        session.setAttribute("nurseSalary", (Double)request.getServletContext().getAttribute("nurseSalary"));
        session.setAttribute("clientCharge", (Double)request.getServletContext().getAttribute("clientCharge"));
        //uncoment to populate appointment slots type table
        //dynamicDao.addTimeSlots();
        String query = (String)request.getParameter("LoginOperation");
        if(query == null)
            query = "Error";
        switch(query) {
            case "NewUser":
                OrganisationService orgService = new OrganisationService(dynamicDao);
                try{
                    ArrayList<OrganisationEntity> organisations = orgService.listAllOrganisations();
                    request.setAttribute("organisations", (ArrayList<OrganisationEntity>)organisations);
                }catch(SQLException e){
                    String sqlError = "Sql Error";
                    // Only using error messages for breakpoints... Not enough time to implment verbose logging.
                }
                request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
                break;
            case "Login":
                loginUser(request, response, session, dynamicDao, user, userService);
                break;
                    
            case "Error": 
                request.getRequestDispatcher("/errorPage.jsp").forward(request, response);
                break;
           
        }
    }

    private void listPatientAppointments(DynamicDao dynamicDao, HttpServletRequest request, int patientId){
        AppointmentService appointmentService = new AppointmentService(dynamicDao);
        ArrayList<AppointmentEntity> patientsAppointments = new ArrayList();
           
        patientsAppointments = appointmentService.getPatientsAppointments(patientId);
        
        ArrayList<AppointmentEntity> patientsActiveAppointments = new ArrayList();
        ArrayList<AppointmentEntity> patientsInvoicedAppointments = new ArrayList();
        ArrayList<AppointmentEntity> patientsPaidAppointments = new ArrayList();
        ArrayList<AppointmentEntity> patientsCancelledAppointments = new ArrayList();
  
        for (int i = 0; i < patientsAppointments.size(); i++) {
            if(patientsAppointments.get(i).getStatus() == 1){
                patientsActiveAppointments.add(patientsAppointments.get(i));
            }    
            else if(patientsAppointments.get(i).getStatus() == 4){
                patientsInvoicedAppointments.add(patientsAppointments.get(i));
            }
            else if(patientsAppointments.get(i).getStatus() == 5){
                patientsPaidAppointments.add(patientsAppointments.get(i));
            }      
            else if(patientsAppointments.get(i).getStatus() == 3){
                patientsCancelledAppointments.add(patientsAppointments.get(i));
            }
          
        }
        request.setAttribute("patientsActiveAppointments", patientsActiveAppointments);
        request.setAttribute("patientsInvoicedAppointments", patientsInvoicedAppointments);
        request.setAttribute("patientsPaidAppointments", patientsPaidAppointments);
        request.setAttribute("patientsCancelledAppointments", patientsCancelledAppointments);
    }
        
    private void loginUser(HttpServletRequest request, HttpServletResponse response, HttpSession session, DynamicDao dynamicDao,
        UserEntity user, UserService userService) throws IOException, ServletException {
        String email = (String) request.getParameter("mail");
        String password = (String) request.getParameter("password");
        //retrieves user from database if it exists  
        user = userService.loginUser(email, password);
        dynamicDao.addTimeSlots();
        if (user != null) {
            int userStatus = user.getAccountStatus();
            switch (userStatus) {
                case Enums.APPROVED:
                    session.setAttribute("user", user);
                    switch (user.getUserType()) {
                        case "patient":
                            PatientService patientService = new PatientService(dynamicDao);
                            PatientEntity patient = patientService.getPatient(user);

                            session.setAttribute("Patient", patient);
                            //                                //retrieve appointment for display and senthem to the page
                            //                                //ArrayList appointments = patientService.retrievePatientDisplayableAppointments(patient);
                            //                                
                            listPatientAppointments(dynamicDao, request, patient.getPatientId());
                            request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
                            break;
                        case "doctor":
                        case "nurse:":
                            AppointmentService appointmentService = new AppointmentService(dynamicDao);
                            EmployeeService employeeService = new EmployeeService(dynamicDao);
                            EmployeeEntity employee = employeeService.fetchEmployee(user);

                            session.setAttribute("Employee", employee);

                            ArrayList employeeAppointments = appointmentService.retrieveEmployeeDisplayableAppointments(employee);
                            request.setAttribute("schedule", employeeAppointments);

                            ArrayList employeeDailyAppointments = appointmentService.retrieveEmployeeDailyDisplayableAppointments(employee);
                            request.setAttribute("dailySchedule", employeeDailyAppointments);

                            request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);
                            break;
                        case "admin":
                            ArrayList<UserEntity> fetchedUsers = userService.fetchAllUsers(userStatus);
                            
                            ArrayList<UserEntity> patients = new ArrayList<>();
                            ArrayList<UserEntity> employees = new ArrayList<>();
                            
                            for(int i = 0; i < fetchedUsers.size(); i++){
                                if("patient".equals(fetchedUsers.get(i).getUserType())){
                                    patients.add(fetchedUsers.get(i));
                                }
                                if("employee".equals(fetchedUsers.get(i).getUserType()) || "doctor".equals(fetchedUsers.get(i).getUserType()) ){
                                    employees.add(fetchedUsers.get(i));
                                }
                            }  
                            request.setAttribute("listOfPatients", patients);
                            request.setAttribute("listOfEmployees", employees);
                            
                            request.getRequestDispatcher("/WEB-INF/adminPage.jsp").forward(request, response);
                            break;
                        default:
                    }
                case Enums.PENDING:
                    request.setAttribute("message", "User has not been approved by admin yet");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("message", "User has been blocked by the admin");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                    break;
            }
        } else {
            request.setAttribute("badPw", true);
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

 
    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}