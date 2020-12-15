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
        request.setAttribute("message", user.getusername());
        if (dynamicDao == null)
            request.getRequestDispatcher("/WEB-INF/conErr.jsp").forward(request, response);
        
        String query;
        query = (String)request.getParameter("patientOperation");
        AppointmentModel apointment_handling = new AppointmentModel();
        switch(query){
                case "bookAppointment":
                    ArrayList<String[]> slots = apointment_handling.retrieveAvaialbleAppointmentsForDoctor(1,"c", dynamicDao);
                    ArrayList hours  = new ArrayList();
                    ArrayList lengths  = new ArrayList();
                    ArrayList temp_lengths  = new ArrayList();
                    int length = 10;
                    hours.add(LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(0)[1])));
                    for (int i = 0; i < slots.size(); i++) {                     
                        if (Integer.parseInt(slots.get(i)[0])%6 == 0 && lengths.size() > 0){
                            hours.add(LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i)[1])));
                            lengths.add(temp_lengths);
                            temp_lengths  = new ArrayList();
                        }
                        else{
                            String[] times = {slots.get(i)[1], slots.get(i)[2]};
                            temp_lengths.add(times);
                        }
                    }
                    
                    request.setAttribute("hours", hours);
                    request.setAttribute("lengths", lengths);
                    request.getRequestDispatcher("/WEB-INF/book.jsp").forward(request, response);
                        
                case "checkPrescription":
                case "booked":
                      //parameters to be used when creating appointment
                      EmployeeModel doctor = new EmployeeModel();
                      Double doc_salary = doctor.getEmployeeSalary(Integer.parseInt(request.getParameter("employee_eid")), dynamicDao);
                      double charge = doc_salary*Integer.parseInt(request.getParameter("duration"));
                      ArrayList appointment_params = new ArrayList(Arrays.asList(Integer.parseInt(request.getParameter("duration")), request.getParameter("notes"), charge, request.getParameter("date"), Integer.parseInt(request.getParameter("start_time")), Integer.parseInt(request.getParameter("end_time")), Integer.parseInt(request.getParameter("patient_pid")), Integer.parseInt(request.getParameter("employee_eid")), Integer.parseInt(request.getParameter("appointment_type_atid")), Integer.parseInt(request.getParameter("patient_prescriptions_prid")), Integer.parseInt(request.getParameter("appointment_status_asid"))));
                      apointment_handling.CreateAppointment(appointment_params, dynamicDao);
                default:
        }
        request.getRequestDispatcher("/WEB-INF/patientPage.jsp").forward(request, response);

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
    