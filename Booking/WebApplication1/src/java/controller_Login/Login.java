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
import dao.DynamicDao;
import dao.StoredData;
import dao.StoredData.SqlQueryEnum;
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
import org.hibernate.validator.internal.util.logging.Log;

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
@WebServlet(name = "Login", urlPatterns = {"/Login.do"})
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
        StoredData storedData = new StoredData(); 
        DynamicDao dynamicDao = new DynamicDao();
        ListModel listHandler = new ListModel();
        UserModel User = new UserModel();
        PatientModel patient;
        EmployeeModel employee;
        //set database object connection 
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        //check if connection was stablished only needs to be done here TODO remove from other classes
         if (dynamicDao.getCon() == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        //saves data objects in the session
        session.setAttribute("dynamicDao", dynamicDao);
        session.setAttribute("ListHandler", listHandler);
        session.setAttribute("User", User);     
        session.setAttribute("storedData", storedData);
        //uncoment to populate appointment slots type table
        //dynamicDao.addTimeSlots();
        
        
        String query = (String)request.getParameter("LoginOperation");
        
 
        switch(query) {
                        case "NewUser":
                            request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
                            break;
                        case "Login":
                                String mail = (String)request.getParameter("mail");
                                String password = (String)request.getParameter("password");
                                ArrayList params = new ArrayList(Arrays.asList(mail, password));
                                //retrieves user from data base if it exists 
                                ArrayList result = User.login_User(params, dynamicDao);
                                String UserType = (String)result.get(1);
                                String[] PatientDetails = (String[])result.get(0);
                                if ( result.size() > 1 ) {
                                    int userStatus = User.getAccountStatus();
                                    
                                    if (userStatus == storedData.approved) {
                                        session.setAttribute("User", User);
                                        switch(UserType) {
                                             case "patient":
                                                 //patient login
                                                 patient = new PatientModel();  
                                                 patient.login_patient(PatientDetails, dynamicDao);
                                                 session.setAttribute("Patient", patient);
                                                 //patient page set up   
                                                 //retrieve appointment for display and senthem to the page
                                                 ArrayList appointments = patient.retrievePatientDisplayableAppointments(dynamicDao);
                                                 request.setAttribute("schedule", appointments);
                                                 request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
                                                break;
                                            case "employee":
                                                   //TODO to be implemented
                    //                             EmployeeModel employee = new EmployeeModel();
                    //                             employee.((ArrayList<String[]>)result.get(1), dynamicDao);
                    //                             session.setAttribute("Employee", employee);
                    //                             request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);   
                                                break;
                                            case "admin":
                                                break;
                                            default:
                                    }
                                    }
                                    else if (userStatus == storedData.pending)
                                    {
                                        request.setAttribute("message","User has not been approved by admin yet");
                                        request.getRequestDispatcher("/login.jsp").forward(request, response);  
                                    }
                                    else
                                    {
                                        request.setAttribute("message","User has been blocked by the admin");
                                        request.getRequestDispatcher("/login.jsp").forward(request, response);  
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
