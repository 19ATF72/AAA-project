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
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.*;
/**
 *
 * @author me-aydin
 */
@WebServlet(name = "NewUser", urlPatterns = {"/NewUserController.do"})
public class NewUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                  
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        DynamicDao dynamicDao = (DynamicDao)session.getAttribute("dynamicDao");
        UserModel newUser = (UserModel)session.getAttribute("User");
        
        
        String [] query = new String[6];
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("email");
        query[3] = (String)request.getParameter("picUrl");
        query[4] = (String)request.getParameter("Role");
        query[5] = (String)request.getParameter("Address");
        
                
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date date = Date.valueOf(LocalDate.now());
                
                
                // THIS WILL TAKE STRING MAKE IT SQL DATE INSERTABLE
                //String dateCreate = request.getParameter("dateCreate");
                
                //SimpleDateFormat formater = new SimpleDateFormat("yyyy-mm-dd");
                //Date dateCreated = formater.parse(dateCreate);
                //Date dateCreated = formater.parse();
                //newDate = dateCreated;
                    
                //pst.setDate(6, new java.sql.Date(newDate.getTime()));
                
                int login = 1;
                int user_status = 1;
                if(query[4].equals("0")){
                    user_status = 2;
                }

                ArrayList params = new ArrayList(Arrays.asList(query[0], query[1], query[2], date, date, login, query[3], user_status));
                ArrayList result  = newUser.create_User(params, dynamicDao);
                
                if(result.size() > 1) {
                    switch(query[4]) {
                    case "0":
                        int patientType = Integer.parseInt((String)request.getParameter("patientType"));
                        ArrayList patient_params = new ArrayList(Arrays.asList(query[5],patientType, result.get(1)));
                        PatientModel patient = new PatientModel();
                        patient.create_patient(patient_params,dynamicDao);
                        request.setAttribute("message", "patient "+ query[0] +" created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                      break;
                    case "1":
                        ArrayList doc_params = new ArrayList(Arrays.asList((Double)session.getAttribute("docSalary"), query[5], Integer.parseInt(query[4]), "testpital", result.get(1)));
                        EmployeeModel employee_doc = new EmployeeModel();
                        employee_doc.create_Employee(doc_params,dynamicDao);
                        request.setAttribute("message", "doctor "+ query[0] +" created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                      break;
                    case "2":
                        ArrayList nurse_params = new ArrayList(Arrays.asList((Double)session.getAttribute("nurseSalary"), query[5], Integer.parseInt(query[4]), (String)request.getParameter("organizationName"), result.get(1)));
                        EmployeeModel employee_nurse = new EmployeeModel();
                        employee_nurse.create_Employee(nurse_params,dynamicDao);
                        request.setAttribute("message", "nurse "+ query[0] +" created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                      break;
                    default:
                        int p = 0;
                    

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
    