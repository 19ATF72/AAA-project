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

@WebServlet(name = "pricingUpdateController", urlPatterns = {"/WEB-INF/pricingUpdateController.do"})
public class pricingUpdateController extends HttpServlet {
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
        pricingUpdateModel pricingUpdateHandler = new pricingUpdateModel();
        String[] pricings = new String[3];
        pricings[0] = (String)request.getParameter("nurseSalary");
        pricings[1] = (String)request.getParameter("doctorSalary");
        pricings[2] = (String)request.getParameter("patientCharge");
        
        
        Double[] pricing_values =  new Double[3];
        String message = "Values have been updated";
        if (request.getParameter("adminOperation") != null){ 
            for (int pricing = 0; pricing < pricings.length; pricing++) {
                if (pricings[pricing] == null ) {
                    continue;
                }
                else{

                    try {
                        pricing_values[pricing] = Double.parseDouble(pricings[pricing]);
                    } catch (Exception e) {
                        
                        message = "Please enter only numbers";
             
                    }

                }   
            }
            if (message.equals("Values have been updated") ) {
                pricingUpdateHandler.updateNurseBaseSalary(dynamicDao, pricing_values[0]);
                pricingUpdateHandler.updateDoctorBaseSalary(dynamicDao, pricing_values[1]);
                pricingUpdateHandler.updatePatientCostSalary(dynamicDao, pricing_values[2]);
                request.getServletContext().setAttribute("nurseSalary", pricing_values[0]);
                request.getServletContext().setAttribute("docSalary", pricing_values[1]);
                request.getServletContext().setAttribute("clientCharge", pricing_values[2]);

                
            }
            request.setAttribute("message", message);
            
        }
        request.getRequestDispatcher("/WEB-INF/pricingUpdatePage.jsp").forward(request, response);
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