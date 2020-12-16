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
     * @brief Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods for login and new user. Upon login creates a new session and retrieves
     * a connection to the database from the servelet context. It generates a user object 
     * based on login details and sets it as the user for the current session.
     * Generates either a patient or a doctor object for the session based on 
     * the user object attributes and redirects the user to the appropriate page. 
       All Model objects  which are used more than once across pages are generated here. 
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        StoredData storedData = new StoredData(); 
        DynamicDao dynamicDao = new DynamicDao();
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dynamicDao", dynamicDao);
        
        //uncoment to populate appointment slots type table
        //dynamicDao.addTimeSlots();
        
        ListModel listHandler = new ListModel();
        session.setAttribute("ListHandler", listHandler);
        
        String [] query = new String[4];
        query[0] = (String)request.getParameter("NewUser");
        query[1] = (String)request.getParameter("Login");
        
        UserModel User = new UserModel();
        session.setAttribute("User", User); 
 
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        if(query[0] != null){
            request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
        }
        else if (query[1] != null){
            query[2] = (String)request.getParameter("mail");
            query[3] = (String)request.getParameter("password");

            ArrayList params = new ArrayList(Arrays.asList(query[2], query[3]));
            //retrieves user from data base if it exists 
            ArrayList result = User.login_User(params, dynamicDao);
            if ( result.size() > 1 ) {
                
                int userStatus = User.getAccountStatus();
                if (userStatus == storedData.approved) {
                    session.setAttribute("User", User);
                    switch((String)result.get(2)) {
                         case "patient":
                             //patient login
                             PatientModel patient = new PatientModel();
                             ArrayList<String[]> patient_details = new ArrayList<String[]>();
                             patient_details.add((String[])result.get(1));
                             patient.login_patient(patient_details, dynamicDao);
                             session.setAttribute("Patient", patient);
                             //patient page set up   
                             //retrieve appointment for display and senthem to the page
                             //ArrayList appointments = patient.retrieveAppointments( dynamicDao );
                             //request.setAttribute("message", appointments);
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
    else{
                
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
