/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Dao.DynamicDao;
import model.Entity.UserEntity;

import java.io.IOException;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author rob
 */
@WebServlet("/apointments")
public class Book extends HttpServlet {
    private DynamicDao bookingDao;
    
    public void init(){
        bookingDao = new DynamicDao();
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        String time = request.getParameter("time");

        try {
            request.setAttribute("time", time);
            RequestDispatcher dispatcher = request.getRequestDispatcher("page.jsp");
            dispatcher.forward(request, response);  
              
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        response.sendRedirect("BookingSucessful.jsp");           
    }
    
    
    
}
