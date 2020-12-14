/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DynamicDao;
import model.OrganisationEntity;

import dao.OrganisationDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rob
 */
public class OrganisationServerlet {
    
    private OrganisationDao dao;
    
    public void init() {
 
        dao = new OrganisationDao();
 
    }
//    // THIS IS JUST FOR TESTING \TODO: REMOVE AFTER TESTING
//        HttpSession session = request.getSession(); // TODO: Remove line after testing done
//        DynamicDao dynamicDao = new DynamicDao();
//        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
//        session.setAttribute("dynamicDao", dynamicDao);
//        // END OF TESTING BLOCK
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
 
       
        switch (action) {
        case "/new_organisation":
            showNewForm(request, response);
            break;
        case "/insert_organisation":
            insertOrganisation(request, response);
            break;
        case "/delete_organisation":
            deleteOrganisation(request, response);
            break;
        case "/edit_organisation":
            showEditForm(request, response);
            break;
        default:
            listOrganisation(request, response);
            break;
        }
        
    }
    
    private void listOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<OrganisationEntity> organisationList = dao.listAllOrganisations();
        request.setAttribute("listOrganisation", organisationList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrganisationList.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrganisationAddNew.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String name = request.getParameter("name");
        OrganisationEntity exisitingOrganisation = dao.getOrganisation(name);
        RequestDispatcher dispatcher = request.getRequestDispatcher("OrganisationAddNew.jsp");
        request.setAttribute("organisation", exisitingOrganisation);
        dispatcher.forward(request, response);
 
    }
    
    private void insertOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        
        OrganisationDao organisationDao = new OrganisationDao();
        
        String name = request.getParameter("name");
        String type = request.getParameter("orgType");
        String address = request.getParameter("address");
        String postcode = request.getParameter("postcode");
        String phonenum = request.getParameter("phonenum");
 
        OrganisationEntity organisation = new OrganisationEntity(name, type, address, postcode, phonenum);
        
        organisationDao.insertOrganisation(organisation);
        
        response.sendRedirect("list");
    }

    private void deleteOrganisation(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String name = request.getParameter("name");
        
        OrganisationDao organisationDao = new OrganisationDao();
        OrganisationEntity organisation = new OrganisationEntity(name);
  
        organisationDao.deleteOrganisation(organisation);
        response.sendRedirect("list");
    }
    
}
