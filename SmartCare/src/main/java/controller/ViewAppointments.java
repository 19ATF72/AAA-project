/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Entity.AppointmentEntity;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class ViewAppointments {
    
    public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Do your Java code thing here.
        
        
        
        //request.setAttribute("message"); // Will be available in ${message}.

        // And then forward the request to a JSP file.
        request.getRequestDispatcher("page2.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
        
        DynamicDao exampleDao = new DynamicDao();
        StoredProcedures storedStatements = new StoredProcedures();
        
        AppointmentEntity appointmentModel = new AppointmentEntity();
        
        //appointmentModel = exampleDao.retrieveAppointment(storedStatements.sqlQueryMap.get(SqlQueryEnum.fetchAppointment));
        
        
        
        //ArrayList<String> availableAppointments = appointmentEngine.fetchAvailableAppointments();
    }
    }
}
