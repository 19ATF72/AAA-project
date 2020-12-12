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
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author me-aydin
 */
@WebServlet(name = "NewUser", urlPatterns = {"/WEB-INF/NewUser.do"})
public class NewUser extends HttpServlet {

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
        
        DynamicDao dynamicDao = new DynamicDao();
        dynamicDao.tryConnect();
        StoredStatements storedStatements = new StoredStatements();
                
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        
        String [] query = new String[4];
        query[0] = (String)request.getParameter("username");
        query[1] = (String)request.getParameter("password");
        query[2] = (String)request.getParameter("email");
        query[3] = (String)request.getParameter("picUrl");
        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";
 
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);  
            try {
                //dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(SqlQueryEnum.CheckForUsername), query[0]);
                //trmporary for testing todo delete after program can get this info alone
                Date date= new Date();
                //getTime() returns current time in milliseconds
                long time = date.getTime();
                int uid = 2;
                Timestamp created = new Timestamp(time);
                Timestamp access = new Timestamp(time);
                int login = 1;
                int status = 1;
                int admin_signupid = 1;
                //
                dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(SqlQueryEnum.NewUser) , query[0], query[1], query[2], created, access, login, query[3], admin_signupid);
                request.setAttribute("message", query[0]+" is added"); 
            } catch (Exception e) {
                request.setAttribute("message","Username already exists");
            }
        
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