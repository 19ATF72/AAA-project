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
import model.Service.ListService;
import model.Entity.AppointmentEntity;
import model.Entity.EmployeeEntity;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Dao.DynamicDao;

import model.Helper.StoredProcedures;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.*;
import model.Service.AppointmentService;
import model.Service.PatientService;
/**
 *
 * @author me-aydin
 */
@WebServlet(name = "PatientController", urlPatterns = {"/PatientController.do"})
public class PatientController extends HttpServlet {
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
        
        // TODO is it needed
        UserEntity user = (UserEntity)session.getAttribute("User");
        
        PatientEntity patient = (PatientEntity)session.getAttribute("Patient");
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        String query;
        ListService listHandler = (ListService)session.getAttribute("ListHandler");
        query = (String)request.getParameter("patientOperation");
        AppointmentService appointmentService = new AppointmentService(dynamicDao);
        PatientService patientService = new PatientService(dynamicDao);
        switch(query){
            case "bookAppointment":
                request.getSession().setAttribute("doctors", listHandler.getDoctorsForDisplay(dynamicDao));
                request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                break;
            case "choosen":
                request.getSession().setAttribute("chosenDoctor", Integer.parseInt(request.getParameter("docChoice")));
                request.getSession().setAttribute("chosenDate", (String)request.getParameter("bookingDate"));

                String dateSelected = (String)request.getParameter("bookingDate");

                Calendar c = Calendar.getInstance();
                c.setTime(Date.valueOf(dateSelected));
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);  
                request.setAttribute("dayOfWeek", dayOfWeek);      
                
                ArrayList<String[]> lengths = appointmentService.getAppointmentTimeSlots((int)session.getAttribute("chosenDoctor"),(String)session.getAttribute("chosenDate"));
               
                request.setAttribute("lengths", lengths);
                request.getSession().setAttribute("lengths", lengths);
                request.setAttribute("dateSelected", true);
                request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                break;
            case "checkPrescription":
            case "booked":
                String[] chosenSlots = (String[])request.getParameterValues("chosenTime");
                int[] startAndFinishTimes = appointmentService.getStartAndEndOfAppointmentTimes(chosenSlots);

                int employeeId = (int)session.getAttribute("chosenDoctor");
                String dateChosen = (String)session.getAttribute("chosenDate");
                String appointmentNotes = (String)request.getParameter("appointmentNotes");
                
                ArrayList chosenTimeSlots = (ArrayList)session.getAttribute("lengths");
                
                if(startAndFinishTimes == null){
                    request.setAttribute("message", "please select consecutive times");
                    request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                }
                
                int oneHourInSeconds = 3600;
                if ((startAndFinishTimes[0] - startAndFinishTimes[1]) > oneHourInSeconds ){
                    request.setAttribute("message", "you cannot book more than hour");
                    request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                }
                
                double charge = (Double)session.getAttribute("clientCharge")*chosenSlots.length;
                int durationInt = chosenSlots.length * 10;
                
                //NOT USED
                String durationStr = durationInt + " Minutes"; 
                
                AppointmentEntity newAppointment = new AppointmentEntity(durationInt, appointmentNotes, charge, dateChosen, String.valueOf(startAndFinishTimes[0]), String.valueOf(startAndFinishTimes[1]), patient.getPatientId(), employeeId, patient.getPatientType(), 0, 0);
                
                appointmentService.createAppointment(newAppointment, chosenTimeSlots);
                
                request.getSession().setAttribute("lengths", null);
                request.getSession().setAttribute("choosenDoctor", null);
                request.getSession().setAttribute("choosenDate", null);
                request.setAttribute("message", "booked successfully");


                ArrayList<AppointmentEntity> patientsAppointments = new ArrayList();
                patientsAppointments = appointmentService.getPatientsAppointments(patient.getPatientId());


                request.setAttribute("patientsAppointments", patientsAppointments);
                request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
                break;
             case "list":
                request.getRequestDispatcher("/WEB-INF/List.jsp").forward(request, response);
                 break;
            case "turnover":
                request.getRequestDispatcher("/WEB-INF/Turnover.jsp").forward(request, response);
                break;
            case "Cancel":
                String appointmentIdToCancel = request.getParameter("id");
                break;
            default:
                request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
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
    