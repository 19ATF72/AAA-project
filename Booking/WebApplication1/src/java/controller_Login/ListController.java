/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_Login;

import dao.DynamicDao;
import dao.StoredData;
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
import model.EmployeeModel;
import model.ListModel;
import model.PatientModel;
import model.UserModel;

/**
 *
 * @author atf1972
 */

@WebServlet(name = "ListController", urlPatterns = {"/WEB-INF/ListController.do"})
public class ListController extends HttpServlet {
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
        
        // THIS IS JUST FOR TESTING \TODO: REMOVE AFTER TESTING
        HttpSession session = request.getSession(); // TODO: Remove line after testing done
        DynamicDao dynamicDao = new DynamicDao();
        dynamicDao.connect((Connection)request.getServletContext().getAttribute("connection"));
        session.setAttribute("dynamicDao", dynamicDao);
        // END OF TESTING BLOCK
        
        //HttpSession session = request.getSession(false); // UNCOMMENT
        response.setContentType("text/html;charset=UTF-8");
        
        ListModel list = new ListModel();
        
        //DynamicDao dynamicDao = (DynamicDao)session.getAttribute("dynamicDao"); //UNCOMMENT
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        String [] query = new String[4];
        query[0] = (String)request.getParameter("patientType");
        query[1] = (String)request.getParameter("startTime");
        query[2] = (String)request.getParameter("endTime");
        query[3] = (String)request.getParameter("recordType");
        
//        Date startTime = null;
//        Date endTime = null;
//        try {
//            SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
//            startTime = formater.parse(query[1]);
//            endTime = formater.parse(query[2]);
//            //newDate = dateCreated;
//        } catch (ParseException e ) {
//            e.printStackTrace();
//        } 
        //java.sql.Date startTimeSQL = new java.sql.Date(startTime.getTime());
        //java.sql.Date endTimeSQL = new java.sql.Date(endTime.getTime());
        //setDate(6, new java.sql.Date(newDate.getTime()));
        
        //ArrayList params = new ArrayList(Arrays.asList(query[0], startTime, endTime));
        //ArrayList resultPatients = list.getPatientsByType(params, dynamicDao);
        ArrayList resultPatients = new ArrayList();
        ArrayList resultInvoices = new ArrayList();
        
        if (query[0] == null || query[3] == null) {
            resultPatients = null;
            resultInvoices = null;
            request.setAttribute("message", "Please make a selection");
        } 
        else if ( (!(query[1].equals("")) && query[2].equals("")) 
               || (query[1].equals("") && !(query[2].equals(""))) ) {
                resultPatients = null;
                resultInvoices = null;
                request.setAttribute("message", "Please enter Start & end Date");
        } else {
            switch (query[3]) {
                case "retrievePatients":
                    if (query[0].equals("0") && !(query[1].equals(""))) {
                        ArrayList params = new ArrayList(Arrays.asList(query[1], query[2]));
                        resultPatients = list.getPatientsBetweenDates(params, dynamicDao);
                    } else if (query[0].equals("0")) {
                        resultPatients = list.getPatients(dynamicDao);
                    } else {                 
                        if (!(query[1].equals(""))) {
                            ArrayList params = new ArrayList(Arrays.asList(query[0], query[1], query[2]));
                            resultPatients = list.getPatientsByTypeBetweenDates(params, dynamicDao); //TODO
                        } else {
                            ArrayList params = new ArrayList(Arrays.asList(query[0]));
                            resultPatients = list.getPatientsByType(params, dynamicDao);
                        }   
                    }
                    request.setAttribute("resultPatients", resultPatients);
                    break;
                case "retrieveInvoices":
                    if (query[0].equals("0") && !(query[1].equals(""))) {
                        ArrayList params = new ArrayList(Arrays.asList(query[1], query[2]));
                        resultInvoices = list.getInvoicesBetweenDates(params, dynamicDao);
                    } else if (query[0].equals("0")) {
                        resultInvoices = list.getInvoices(dynamicDao);
                    } else {                 
                        if (!(query[1].equals(""))) {
                            ArrayList params = new ArrayList(Arrays.asList(query[0], query[1], query[2]));
                            resultInvoices = list.getInvoicesByTypeBetweenDates(params, dynamicDao); //TODO
                        } else {
                            ArrayList params = new ArrayList(Arrays.asList(query[0]));
                            resultInvoices = list.getInvoicesByType(params, dynamicDao);
                        }   
                    }
                    request.setAttribute("resultInvoices", resultInvoices);
                    break;
            }
        }
        
        request.getRequestDispatcher("/WEB-INF/List.jsp").forward(request, response);
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