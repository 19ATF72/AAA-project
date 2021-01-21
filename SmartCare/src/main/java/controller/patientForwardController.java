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
import model.Service.OrganisationService;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Service.PatientService;
import model.pricingUpdateModel;

/**
 *
 * @author atf1972
 */

@WebServlet(name = "patientForwardController", urlPatterns = {"/WEB-INF/patientForwardController.do"})
public class patientForwardController extends HttpServlet {
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
        PatientService patientHandler = new PatientService(dynamicDao);
        OrganisationService organizationHandler = new OrganisationService(dynamicDao);
        ArrayList allPatients = patientHandler.getAllPatientsNames();
        if (request.getParameter("forward") != null) {
            String organization = request.getParameter("organization");
            request.setAttribute("message", "Details about " + organization + " have been forwarded to patient email. Organisation has been informed of patient");
        }
        try {
            ArrayList allOrganizations = organizationHandler.listAllOrganisations();
            request.setAttribute("allOrganizations", allOrganizations);
        } catch (Exception e) {
        }
        request.setAttribute("allPatients", allPatients);    
        request.getRequestDispatcher("/WEB-INF/patientForwardPage.jsp").forward(request, response);
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