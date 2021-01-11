/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Dao.DynamicDao;
import model.Entity.OrganisationEntity;

import model.Service.OrganisationService;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rob
 */

@WebServlet(name = "OrganisationServlet", urlPatterns = {"/WEB-INF/OrganisationServlet.do"})
public class OrganisationServlet extends HttpServlet {
    
    private OrganisationService organisationDao;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             
        String action = request.getServletPath();
      
       // THIS IS JUST FOR TESTING \TODO: REMOVE AFTER TESTING
        HttpSession session = request.getSession(); // TODO: Remove line after testing done
        DynamicDao dynamicDao = new DynamicDao();
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dynamicDao", dynamicDao);
        // END OF TESTING BLOCK 
        
        organisationDao  = new OrganisationService(dynamicDao);
             
        try
        {
        // call methods that might throw SQLException
    
        switch (action) {
        case "/OrganisationServlet.do/new_organisation":
            showNewForm(request, response);
            break;
        case "/OrganisationServlet.do/insert_organisation":
            insertOrganisation(request, response);
            break;
        case "/OrganisationServlet.do/delete_organisation":
            deleteOrganisation(request, response);
            break;
        case "/OrganisationServlet.do/edit_organisation":
            showEditForm(request, response);
            break;
        default:
            listOrganisation(request, response);
            break;
        }
        
        }
        catch(Exception e){
           e.printStackTrace();
        }
       
       //request.setAttribute("message", result);
       //request.getRequestDispatcher("/WEB-INF/OrganisationList.jsp").forward(request, response);
       
       
    }
    
    private void listOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<OrganisationEntity> organisationList = organisationDao.listAllOrganisations();
        request.setAttribute("organisationList", organisationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/OrganisationList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/OrganisationAddNew.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String oId = request.getParameter("id");
        OrganisationEntity exisitingOrganisation = organisationDao.getOrganisation(oId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/OrganisationAddNew.jsp");
        request.setAttribute("organisation", exisitingOrganisation);
        dispatcher.forward(request, response);
 
    }
    
    // TODO: NEEDS FIXING
    
    private void insertOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        
        String name = request.getParameter("name");
        String type = request.getParameter("orgType");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String phonenum = request.getParameter("phoneNum");
 
        OrganisationEntity organisation = new OrganisationEntity(name, type, address, postcode, phonenum);
        
        organisationDao.insertOrganisation(organisation);
        
        response.sendRedirect("/SmartCare/OrganisationServlet.do");
    }
    
    // TODO: NEEDS FIXING
    
    private void deleteOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int oId = Integer.parseInt(request.getParameter("oId"));
        String oIdd = "";
        OrganisationEntity organisation = new OrganisationEntity(oIdd);
  
        organisationDao.deleteOrganisation(organisation);
        response.sendRedirect("/SmartCare/OrganisationServlet.do");
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
