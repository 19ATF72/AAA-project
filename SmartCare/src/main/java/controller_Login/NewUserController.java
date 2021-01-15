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
import model.Entity.EmployeeEntity;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Dao.DynamicDao;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Service.PatientService;
import model.Service.UserService;
import model.Service.EmployeeService;
/**
 *
 * @author me-aydin
 */
@WebServlet(name = "NewUser", urlPatterns = {"/NewUserController.do"})
public class NewUserController extends HttpServlet {

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
        UserEntity newUser = (UserEntity)session.getAttribute("User");
       
        UserService userService = new UserService(dynamicDao);
        
        // DO WE NEED TO BE CASTING IT TO STRING?? Example: (String)request.getParameter("Address")
        newUser.setUsername(request.getParameter("username"));
        newUser.setPassword(request.getParameter("password"));
        newUser.setEmail(request.getParameter("email"));
        newUser.setPicture(request.getParameter("picUrl"));
        newUser.setUserRole(request.getParameter("Role"));
        
        
        // TODO discuss if we are adding address
        //newUser.set(request.getParameter("Address"));
       
        // TODO are these needed?
//        Date date = Date.valueOf(LocalDate.now());
//        int login = 1;
//        int user_status = 1;
//        
//        if(newUser.getAccountStatus() == 0){
//            user_status = 2;
//        }
        
        String result = userService.createUser(newUser);
        
        // TODO: MOVE INTO USER MODEL
        String address = request.getParameter("Address");
   
        if("User created successfully".equals(result)) {
            switch(newUser.getUserRole()) {
            case "0":
                PatientService patientService = new PatientService(dynamicDao);
                
                int patientType = Integer.parseInt((String)request.getParameter("patientType"));
                

                PatientEntity patient = new PatientEntity(newUser.getUniqueUserId(), patientType, address);
                
                String patientReturnResult = patientService.createPatient(patient);
    
                if("Patient created".equals(patientReturnResult)){
                    request.setAttribute("message", "patient "+ patient.getUsername() +" created successfully" );
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "1":
            case "2":
                double salary;
                
                EmployeeService employeeService = new EmployeeService(dynamicDao);
                
                if("1".equals(newUser.getUserRole())){
                    salary = (Double)session.getAttribute("docSalary");
                }
                else{
                    salary = (Double)session.getAttribute("nurseSalary");
                }
                
                // TODO: ADD ORGANISATION
                EmployeeEntity employee = new EmployeeEntity(salary, address, Integer.parseInt(newUser.getUserRole()), 0);
                String employeeReturnResult = employeeService.createEmployee(employee);
                
                if("Employee created successfully".equals(employeeReturnResult)) { 
                    if("1".equals(newUser.getUserRole())){

                        request.setAttribute("message", "doctor "+ employee.getUsername() +" created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }  
                    else
                    {
                        request.setAttribute("message", "nurse "+ employee.getUsername() +" created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    }
                }
                break; 
           
            default:
                int p = 0;
                //TODO: ??


            }
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
    