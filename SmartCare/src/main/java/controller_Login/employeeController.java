/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_Login;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import dao.DynamicDao;
import dao.StoredData;
import dao.StoredData.SqlQueryEnum;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.*;
/**
 *
 * @author me-aydin
 */
@WebServlet(name = "employeeController", urlPatterns = {"/WEB-INF/employeeController.do"})
public class employeeController extends HttpServlet {

   

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
        
              
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        DynamicDao dynamicDao = (DynamicDao)session.getAttribute("dynamicDao");
        UserModel user = (UserModel)session.getAttribute("User");
        EmployeeModel employee = (EmployeeModel)session.getAttribute("Employee");
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        String query;
        ListModel listHandler = (ListModel)session.getAttribute("ListHandler");
        query = (String)request.getParameter("employeeOperation");
        AppointmentModel apointment_handling = new AppointmentModel();
        
        switch(query){
                case "recordAppointment":
                    request.getSession().setAttribute("appointmentToRecord", request.getParameter("appointmentToUpdate"));
                    request.getRequestDispatcher("/WEB-INF/prescriptionPage.jsp").forward(request, response);
                    break;
                case "recorded":
                    String notes = (String)request.getParameter("notes");
                    Boolean isRepeat = ((String)request.getParameter("repeat") == null);
                    String medication = (String)request.getParameter("prescription");
                    String[] ptidAndAid = ((String)request.getSession().getAttribute("appointmentToRecord")).split(",");
                    Integer AppointmentID = Integer.parseInt(ptidAndAid[0]);
                    Integer PatientID = Integer.parseInt(ptidAndAid[1]);
                    
                    ArrayList prescriptionParams = new ArrayList(Arrays.asList(PatientID, medication, isRepeat, notes, AppointmentID));
                    
                    employee.UpdateAppointment(prescriptionParams, dynamicDao);
                    request.setAttribute("message", "appointment updated successfully");
                    ArrayList employee_appointments = employee.retrieveEmployeeDisplayableAppointments(dynamicDao);
                    request.setAttribute("schedule", employee_appointments);
                    ArrayList employeeDailyAppointments = employee.retrieveEmployeeDailyDisplayableAppointments(dynamicDao);
                    request.setAttribute("dailySchedule", employeeDailyAppointments);
                    request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/employeePage.jsp").forward(request, response);
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
    