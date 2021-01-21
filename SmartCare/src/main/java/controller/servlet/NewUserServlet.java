/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.servlet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import model.Entity.EmployeeEntity;
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Entity.OrganisationEntity;
import model.Dao.DynamicDao;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Service.PatientService;
import model.Service.UserService;
import model.Service.EmployeeService;
import model.Service.OrganisationService;
/**
 *
 * @author me-aydin
 */
@WebServlet(name = "NewUser", urlPatterns = {"/NewUserController.do"})
public class NewUserServlet extends HttpServlet {

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
        UserEntity newUser = new UserEntity();
       
        newUser.setAccountStatus(1);
        
        

        UserService userService = new UserService(dynamicDao);
        
        newUser.setEmail(request.getParameter("email"));
        newUser.setPassword(request.getParameter("password"));
        
        String userPrefix = request.getParameter("userPrefix");
        if(userPrefix == "None" || userPrefix == "Choose...")
            newUser.setUserPrefix("");
        else
            newUser.setUserPrefix(userPrefix);
        
        newUser.setUserFirstname(request.getParameter("userFirstName"));
        newUser.setUserSurname(request.getParameter("userSurname"));
        
        newUser.setDateOfBirth(Date.valueOf(request.getParameter("dateOfBirth")));
        
        // Was going to return tuple but they dont exist in java... 
        String telephone = request.getParameter("telephone");
        String telephoneResult = userService.telephoneValidation(telephone);
        
        if(telephoneResult == "Please enter a valid UK mobile number.")
        {     
            request.setAttribute("message", "Please enter a valid UK mobile number." );
            return;
        }
        
        newUser.setPhoneNumber(telephoneResult);

        String houseNumber = request.getParameter("houseNumber");
        String line2 = request.getParameter("line2");
        String town = request.getParameter("town");
        String county = request.getParameter("county");
        
        String address = houseNumber + ", " + line2 + "," + town + ", " + county;  
        String postcode = request.getParameter("postcode");
        
        newUser.setUserType(request.getParameter("role"));
        
        String result = userService.createUser(newUser);

        if("User created successfully".equals(result)) {
            switch(newUser.getUserType()) {
            case "patient":
                PatientService patientService = new PatientService(dynamicDao);
                
                int patientType = Integer.parseInt((String)request.getParameter("patientType"));
               
                PatientEntity patient = new PatientEntity(newUser.getUniqueUserId(), address, postcode, patientType);
                
                String patientReturnResult = patientService.createPatient(patient);
    
                if("Patient created".equals(patientReturnResult)){
                    request.setAttribute("message", "patient "+ newUser.getUserPrefix()+ " " + newUser.getUserSurname() + " created successfully" );
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "nurse":
            case "doctor":
                
                EmployeeService employeeService = new EmployeeService(dynamicDao);
                
//                if("1".equals(newUser.getUserRole())){
//                    salary = (Double)session.getAttribute("docSalary");
//                }
//                else{
//                    salary = (Double)session.getAttribute("nurseSalary");
//                }
                //TODO REMOVE ONCE RODRIGO ADDS HIS CHANGE
                double defaultSalary = 20;      
                if(newUser.getUserType() == "nurse"){
                    defaultSalary = defaultSalary / 2;
                }
                
                // TODO: ADD ORGANISATION
                EmployeeEntity employee = new EmployeeEntity(defaultSalary, address, postcode, 0); //IMPLEMENT ORG!
                String employeeReturnResult = employeeService.createEmployee(employee);
                
                if("Employee created successfully".equals(employeeReturnResult)) { 
                        request.setAttribute("message", newUser.getUserType() + " " + newUser.getUserSurname() + " created successfully" );
                        request.getRequestDispatcher("/login.jsp").forward(request, response);
                    
                }
                break; 
           
            default:
                request.setAttribute("message", "error" );
                request.getRequestDispatcher("/NewUser.jsp").forward(request, response);
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
    