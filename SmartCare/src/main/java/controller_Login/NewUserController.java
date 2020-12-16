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
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        
        

            
       

                Date date= new Date();
                long time = date.getTime();
                Timestamp created = new Timestamp(time);
                Timestamp access = new Timestamp(time);
                int login = 1;
                int status = 1;
                int user_status = 1;
                int uuid = 0;
                if(query[4].equals("0")){
                    user_status = 2;
                }

                ArrayList params = new ArrayList(Arrays.asList(query[0], query[1], query[2], created, access, login, query[3], user_status));
                ArrayList result  = newUser.create_User(params, dynamicDao);
                



                switch(query[4]) {
                    case "0":
                        int patientType = Integer.parseInt((String)request.getParameter("patientType"));
                        ArrayList patient_params = new ArrayList(Arrays.asList(query[5],patientType, result.get(1)));
                        PatientModel patient = new PatientModel();
                        patient.create_patient(patient_params,dynamicDao);
                      break;
                    case "1":
                        ArrayList employee_params = new ArrayList(Arrays.asList(0, query[5], Integer.parseInt(query[4]), (String)request.getParameter("organizationName"), result.get(1)));
                        EmployeeModel employee = new EmployeeModel();
                        employee.create_Employee(employee_params,dynamicDao);
                      break;
                    default:
                        int p = 0;

                    }

                   request.setAttribute("message", result.get(0));
                   request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);

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
    