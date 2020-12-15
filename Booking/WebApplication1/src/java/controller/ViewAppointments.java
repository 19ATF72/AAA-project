/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.DynamicDao;
import dao.StoredData;
import dao.StoredData.SqlQueryEnum;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AppointmentModel;
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
        StoredData storedStatements = new StoredData();
        
        AppointmentModel appointmentModel = new AppointmentModel();
        
        //appointmentModel = exampleDao.retrieveAppointment(storedStatements.sqlQueryMap.get(SqlQueryEnum.fetchAppointment));
        
        
        
        //ArrayList<String> availableAppointments = appointmentEngine.fetchAvailableAppointments();
    }
    }
}
