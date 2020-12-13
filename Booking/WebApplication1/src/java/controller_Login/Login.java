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
import dao.StoredStatements;
import dao.StoredStatements.SqlQueryEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.UserModel;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "Login", urlPatterns = {"/Login.do"})
public class Login extends HttpServlet {

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
        


        StoredStatements storedStatements = new StoredStatements();       
        HttpSession session = request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        
        DynamicDao dynamicDao = new DynamicDao();
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dynamicDao", dynamicDao);    
        
        String [] query = new String[4];
        query[0] = (String)request.getParameter("NewUser");
        query[1] = (String)request.getParameter("Login");
        
        UserModel User = new UserModel();
        session.setAttribute("User", User); 
        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";
 
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        if(query[0] != null){
            request.getRequestDispatcher("/WEB-INF/NewUser.jsp").forward(request, response);
        }
        else if (query[1] != null){
        query[2] = (String)request.getParameter("mail");
        query[3] = (String)request.getParameter("password");
        try {
            ArrayList params = new ArrayList(Arrays.asList(query[2], query[3]));
            User.login_User(params, dynamicDao);
            session.setAttribute("User", User);
            request.setAttribute("message", "User with "+query[2]+" loggedin");
            request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response); //todo replace by forward to user page
        } catch (Exception e) {
            request.setAttribute("message","Incorrect user or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
        }
        //request.setAttribute("message", "Welcome " + query[0].toString())
        //request.getRequestDispatcher("/user.jsp").forward(request, response);
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
