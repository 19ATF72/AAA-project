package controller;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Entity.EmployeeEntity;
import model.Service.ListService;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.pricingUpdateModel;

/**
 *
 * @author atf1972
 */

@WebServlet(name = "prescriptionController", urlPatterns = {"/WEB-INF/prescriptionController.do"})
public class prescriptionController extends HttpServlet {
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
        
        // Reactivate after testing
        HttpSession session = request.getSession(false);
        DynamicDao dynamicDao = (DynamicDao)session.getAttribute("dynamicDao");
        
        //HttpSession session = request.getSession(false); // UNCOMMENT
        response.setContentType("text/html;charset=UTF-8");        
  
        ListService ListHandler = (ListService)session.getAttribute("ListHandler");
        UserEntity user = (UserEntity)session.getAttribute("user");

        ArrayList<Integer> params = new ArrayList();
        params.add(user.getUniqueUserId());
        
        ArrayList repeatPrescriptions = ListHandler.getPrescriptionsForDisplay(params, dynamicDao);
        ArrayList prescriptions = ListHandler.getRepeatPrescriptionsForDisplay(params, dynamicDao);
        
        request.setAttribute("repeatPrescriptions", repeatPrescriptions);
        request.setAttribute("prescriptions", prescriptions);
        
        String[] requestRepeat = new String[3];
        requestRepeat[0] = (String)request.getParameter("requestRepeat");
        
        if (request.getParameter("requestRepeat") != null){ 
            
            request.setAttribute("message", "Sent request to admin");

        }
        request.getRequestDispatcher("/WEB-INF/prescriptionListPage.jsp").forward(request, response);
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