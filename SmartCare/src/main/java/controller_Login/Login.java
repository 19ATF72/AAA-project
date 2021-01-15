/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.Entity.EmployeeEntity;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import model.Helper.Enums;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.*;
import model.Service.EmployeeService;
import model.Service.UserService;
import model.Service.PatientService;

/*
 * 
 * @class Login 
 *
 * @brief Handles login users into the system and redirection to new user creation page.
          On login operation it will redirect user to appropriate page depending if the user is a doctor or a patient.
 * 
 * @brief atribute_1 description
 *      atribute_2 description
 *        atribute_3 description
 * 
 * @brief method_1
 *       method_2
 *        method_3
 *
 * @brief last reviewed by: your_nmae
 * 
 * @date  last_review date
 */
@WebServlet(name = "Login", urlPatterns = {"/"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods for login and new user.
     * @brief  Upon login creates a new session and retrieves
     * a connection to the database from the servelet context. It generates a user object 
     * based on login details and sets it as the user for the current session.
     * Generates either a patient or a doctor object for the session based on 
     * the user object attributes and redirects the user to the appropriate page. 
     * All Model objects  which are used more than once across pages are generated here.
     *   
     *
     *  
     * @param request servlet request
     * @param response servlet response
     * @param[out] HttpSession session A containing all attributes which will be used in other pages  
     * @param[out] ListModel listHandler Object set to the current session so other pages can list data from the database 
     * @param[out] ListModel user Object set to the current session so data base does not need to be called to retrieve user info
     * 
     * @param[in] input_name input_description 
     *
     * @returns returned_variable returned_description
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        //data objects to be used in every page
        //TODO delete this from other controller it is now saved on the session 
        StoredProcedures storedData = new StoredProcedures(); 
        
        DynamicDao dynamicDao = new DynamicDao();
        
        
        
        ListModel listHandler = new ListModel();
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
        session.setAttribute("User", user);     
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
                String UserType = user.getUserRole();

                if (user != null) {
                    int userStatus = user.getAccountStatus();

                switch (userStatus) {
                    case Enums.APPROVED:
                        session.setAttribute("User", user);
                        switch(UserType) {
                            case "patient":
                                PatientService patientService = new PatientService(dynamicDao);
                                PatientEntity patient = patientService.getPatient(user.getUniqueUserId());
                                patient.setPatientEntityFromUser(user);
                                session.setAttribute("Patient", patient);

                                //retrieve appointment for display and senthem to the page
                                ArrayList appointments = patientService.retrievePatientDisplayableAppointments(patient);
                                request.setAttribute("schedule", appointments);
                                request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
                                break;
                            case "employee":
                                EmployeeService employeeService = new EmployeeService(dynamicDao);
                                EmployeeEntity employee = employeeService.getEmployee(user.getUniqueUserId());
                                employee.setEmployeeEntityFromUser(user);
                                session.setAttribute("Employee", employee);

                                ArrayList employeeAppointments = employeeService.retrieveEmployeeDisplayableAppointments(employee);
                                request.setAttribute("schedule", employeeAppointments);

                                ArrayList employeeDailyAppointments = employeeService.retrieveEmployeeDailyDisplayableAppointments(employee);
                                request.setAttribute("dailySchedule", employeeDailyAppointments);
                                request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);
                                break;
                            case "admin":
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
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
