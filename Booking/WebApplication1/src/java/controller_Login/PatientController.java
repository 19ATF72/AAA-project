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
@WebServlet(name = "PatientController", urlPatterns = {"/WEB-INF/PatientController.do"})
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
        UserModel user = (UserModel)session.getAttribute("User");
        PatientModel patient = (PatientModel)session.getAttribute("Patient");
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        String query;
        ListModel listHandler = (ListModel)session.getAttribute("ListHandler");
        query = (String)request.getParameter("patientOperation");
        AppointmentModel apointment_handling = new AppointmentModel();
        
        switch(query){
                case "bookAppointment":
                    request.getSession().setAttribute("doctors", listHandler.getDoctorsForDisplay(dynamicDao));
                    request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                    break;
                case "choosen":
                    request.getSession().setAttribute("choosenDoctor", Integer.parseInt(request.getParameter("docChooice")));
                    request.getSession().setAttribute("choosenDate", (String)request.getParameter("bookingDate"));
                    ArrayList<String[]> slots = apointment_handling.retrieveAvaialbleAppointmentsForDoctor((Integer)session.getAttribute("choosenDoctor"),(String)session.getAttribute("choosenDate"), dynamicDao);
                    ArrayList lengths  = new ArrayList();
                    ArrayList temp_lengths  = new ArrayList();
                    Integer lengthIndex = 1;
                    for (int i = 1; i < slots.size(); i++) {
                        if(Integer.parseInt(slots.get(i)[1]) == 36000){
                            int p = 0;}
                        if (Integer.parseInt(slots.get(i)[1])%3600 == 0 && temp_lengths.size() != 0){
                            String start = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i)[1])).toString();
                            String end = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i)[2])).toString();
                            String[] times = {slots.get(i)[0], start, end,"slot_" + lengthIndex.toString()};
                            temp_lengths.add(times);
                            ArrayList composed = new ArrayList(Arrays.asList(LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i-6)[1])).toString(), temp_lengths));
                            lengths.add(composed);
                            temp_lengths  = new ArrayList();
                            lengthIndex = 1;
                        }
                        else{
                            String start = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i)[1])).toString();
                            String end = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i)[2])).toString();
                            String[] times = {slots.get(i)[0], start, end,"slot_" + lengthIndex.toString()};
                            temp_lengths.add(times);
                            lengthIndex++;
                        }
                    }
                    request.setAttribute("lengths", lengths);
                    request.getSession().setAttribute("lengths", lengths);
                    request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                    break;
                case "checkPrescription":
                case "booked":
                      //parameters to be used when creating appointment
                      String[] ChosenSlots = (String[])request.getParameterValues("book_choice");
                      ArrayList<String[]> formatedSlots = new ArrayList<String[]>();
                      for (int slot = 0; slot < ChosenSlots.length; slot++) {
                          formatedSlots.add(ChosenSlots[slot].split(","
                                  + ""));
                        }
                      int first_index = Integer.parseInt(formatedSlots.get(0)[0]);
                      int last_index = Integer.parseInt( formatedSlots.get((ChosenSlots.length - 1))[0] );
                      
                      if( (last_index - first_index) != ChosenSlots.length - 1){
                          request.setAttribute("message", "please select consecutive times");
                          request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                      }
                      
                      EmployeeModel doctor = new EmployeeModel();
                      
                      ArrayList slot_ids = new ArrayList();
                      for (int slot_id = 0; slot_id < formatedSlots.size(); slot_id++) {
                          slot_ids.add(Integer.parseInt(formatedSlots.get(slot_id)[0]));
                        }
                      Integer eid = (Integer)session.getAttribute("choosenDoctor");
                      ArrayList length = (ArrayList)session.getAttribute("lengths");
                      int start = LocalTime.parse((String)formatedSlots.get(0)[1]).toSecondOfDay();
                      int end = LocalTime.parse((String)formatedSlots.get(formatedSlots.size()- 1)[1]).toSecondOfDay();
                      int one_hour = 3600;
                      if( (end - start) > 3600 ){
                          request.setAttribute("message", "you cannot book more than hour");
                          request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                      }
                      Double doc_salary = doctor.getEmployeeSalary(eid, dynamicDao);
                      double charge = (Double)session.getAttribute("clientCharge")*ChosenSlots.length;
                      ArrayList appointment_params = new ArrayList(Arrays.asList(ChosenSlots.length, (Double)charge, session.getAttribute("choosenDate"), start, end, patient.getPatientID(), eid, patient.getPatientType(),1, slot_ids));
                      apointment_handling.CreateAppointment(appointment_params, dynamicDao);
                      request.getSession().setAttribute("lengths", null);
                      request.getSession().setAttribute("choosenDoctor", null);
                      request.getSession().setAttribute("choosenDate", null);
                      request.setAttribute("message", "booked successfully");
                      ArrayList appointments = patient.retrievePatientDisplayableAppointments(dynamicDao);
                      request.setAttribute("schedule", appointments);
                      request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);
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
    