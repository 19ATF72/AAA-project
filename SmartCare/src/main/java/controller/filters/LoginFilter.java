/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.filters;

import java.io.IOException;
import java.sql.Connection;
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
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Helper.Enums;
import model.Helper.StoredProcedures;
import model.Service.AppointmentService;
import model.Service.EmployeeService;
import model.Service.ListService;
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
        switch(query) {
            case "NewUser":
                request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
                break;
            case "Login":
                String email = (String)request.getParameter("mail");
                String password = (String)request.getParameter("password");
                //retrieves user from database if it exists  
                user = userService.loginUser(email, password);
                
                if (user != null) {
                    int userStatus = user.getAccountStatus();
                switch (userStatus) {
                    case Enums.APPROVED:
                        session.setAttribute("user", user);
                        switch(user.getUserType()) {
                            case "Patient":
                                PatientService patientService = new PatientService(dynamicDao);
                                PatientEntity patient = patientService.getPatient(user);
                                
                                session.setAttribute("Patient", patient); 
//                                //retrieve appointment for display and senthem to the page
//                                //ArrayList appointments = patientService.retrievePatientDisplayableAppointments(patient);
//                                
                                listPatientAppointments(dynamicDao, request, patient.getPatientId());   
                                request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);                         
                                break;
                            case "Doctor":
                            case "Nurse:":
                                EmployeeService employeeService = new EmployeeService(dynamicDao);
                                EmployeeEntity employee = employeeService.fetchEmployee(user);
                                
                                session.setAttribute("Employee", employee);

                                ArrayList employeeAppointments = employeeService.retrieveEmployeeDisplayableAppointments(employee);
                                request.setAttribute("schedule", employeeAppointments);

                                ArrayList employeeDailyAppointments = employeeService.retrieveEmployeeDailyDisplayableAppointments(employee);
                                request.setAttribute("dailySchedule", employeeDailyAppointments);
                                
                                request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);
                                break;
                            case "Admin":
                                break;
                            default:
                        }
                        break;
                    case Enums.PENDING:
                        request.setAttribute("message","User has not been approved by admin yet");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                        break;
                    default:
                        request.setAttribute("message","User has been blocked by the admin");
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                        break;
                    }
                }
                else{
                    request.setAttribute("badPw", true);
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
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
        request.setAttribute("patientsActiveAppointments", patientsAppointments);
        request.setAttribute("patientsInvoicedAppointments", patientsInvoicedAppointments);
        request.setAttribute("patientsPaidAppointments", patientsPaidAppointments);
        request.setAttribute("patientsCancelledAppointments", patientsCancelledAppointments);
    }
        

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

}