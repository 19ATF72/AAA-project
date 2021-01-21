/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.TurnoverModel;

/**
 *
 * @author atf1972
 */

@WebServlet(name = "TurnoverController", urlPatterns = {"/WEB-INF/TurnoverController.do"})
public class TurnoverController extends HttpServlet {
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
        
        String [] query = new String[3];
        query[0] = (String)request.getParameter("turnoverOperation");
        query[1] = (String)request.getParameter("startTime");
        query[2] = (String)request.getParameter("endTime");
        
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
        
        ArrayList<String[]> resultIncome = new ArrayList();
        ArrayList<String[]> resultOutgoings = new ArrayList();
        ArrayList<String[]> resultTurnover = new ArrayList();
        ArrayList<String[]> params = new ArrayList();
        
        if (query[0] == null || query[1].equals("") || query[2].equals("")) {
            resultTurnover = null;
            request.setAttribute("message", "Please enter all fields");
        } else {
            switch (query[0]) {
                case "turnover":
                    calculateProfit(request, dynamicDao, query, params, resultTurnover, resultIncome, resultOutgoings);
                case "private":
                case "nhs":
                    calculateProfit(request, dynamicDao, query, params, resultTurnover, resultIncome, resultOutgoings);
            }
        }
        request.getRequestDispatcher("/WEB-INF/Turnover.jsp").forward(request, response);
    }
    
    public void calculateProfit(HttpServletRequest request, DynamicDao dynamicDao, String[] query, ArrayList<String[]> params, ArrayList<String[]> resultTurnover , ArrayList<String[]> resultIncome, ArrayList<String[]> resultOutgoings){
        params = new ArrayList(Arrays.asList(query[0], query[1], query[2]));
        TurnoverModel TurnoverHandler = new TurnoverModel();
        
        resultTurnover = TurnoverHandler.getTurnoverByTypeBetweenDates(params, dynamicDao);
        resultIncome = TurnoverHandler.getIncomeByTypeBetweenDates(params, dynamicDao);
        resultOutgoings = TurnoverHandler.getOutgoingsByTypeBetweenDates(params, dynamicDao);
        if(resultIncome.get(0)[0] == null || resultOutgoings.get(0)[0] == null){
            request.setAttribute("message", "No paid appointments in time period found. ");   
        }
        else{
            double resultProfit = (Double.parseDouble(((String[])resultIncome.get(0))[0]) - Double.parseDouble(((String[])resultOutgoings.get(0))[0]));
            request.setAttribute("resultTurnover", resultTurnover);
            request.setAttribute("resultIncome", resultIncome);
            request.setAttribute("resultOutgoings", resultOutgoings);
            request.setAttribute("resultProfit", resultProfit);
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