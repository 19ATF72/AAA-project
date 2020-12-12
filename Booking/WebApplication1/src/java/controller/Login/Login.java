///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package controller.Login;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//import dao.DynamicDao;
//import dao.StoredStatements;
//import dao.StoredStatements.SqlQueryEnum;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// *
// * @author me-aydin
// */
//@WebServlet(name = "Delete", urlPatterns = {"/Delete.do"})
//public class Login extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        DynamicDao dynamicDao = new DynamicDao();
//        dynamicDao.tryConnect();
//        StoredStatements storedStatements = new StoredStatements();
//                
//        response.setContentType("text/html;charset=UTF-8");
//        HttpSession session = request.getSession(false);
//        
//        String [] query = new String[2];
//        query[0] = (String)request.getParameter("username");
//        query[1] = (String)request.getParameter("password");
//        //String insert = "INSERT INTO `Users` (`username`, `password`) VALUES ('";
// 
//        if (dynamicDao == null)
//            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
//        
//        if(query[0] == null) {
//            request.setAttribute("message", "Username cannot be NULL");
//        }
//        else if(query[1] == null) {
//            request.setAttribute("message", "Password cannot be NULL");
//        }
//        else {
//            
//            try {
//                ArrayList p = dynamicDao.string_retrieve(storedStatements.sqlQueryMap.get(SqlQueryEnum.LoginUser), "rod", 5);
//            } catch (Exception e) {
//                request.setAttribute("message", query[0]+" does not exist");
//            }
//            
//            request.setAttribute("message", "User with "+query[0]+" loggedin");      
//        }
//         
//        request.getRequestDispatcher("/WEB-INF/user.jsp").forward(request, response);
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
