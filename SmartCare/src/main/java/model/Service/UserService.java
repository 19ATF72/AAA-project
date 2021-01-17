/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;
import java.text.DateFormat;
import java.text.SimpleDateFormat;  
import java.sql.Date;  
import java.time.LocalDate;  
import java.util.ArrayList;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import model.Entity.UserEntity;
import model.Entity.EmployeeEntity;

/**
 *
 * @author rob
 */
public class UserService{
    
    protected DynamicDao dynamicDao;
    private final StoredProcedures storedProcedures = new StoredProcedures();  
    
    public UserService(DynamicDao dynamicDao){
        this.dynamicDao = dynamicDao;
    }
    
    
    public String createUser(UserEntity user)
    {    
        
        modifyAccountStatus(user);
        
        String result = "";
        
        Date date;
        date = Date.valueOf(LocalDate.now());
        
        try {
            int uniqueUserId = (Integer)dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewUser), user.getUserName(), user.getPassword(), user.getEmail(), 
                    date, date, user.getIsLoggedIn(), user.getPicture(), user.getAccountStatus()).get(0);
            user.setUniqueUserId(uniqueUserId);
           
            result = "User created successfully";
           
        } catch (Exception e) {          
            result = "Email already registered ";
        }
        
        return result;
    } 
    
    private void modifyAccountStatus(UserEntity user){
        if("0".equals(user.getUserRole())) // If a patient
        {
            user.setAccountStatus(2);
        }
        else
        {
            user.setAccountStatus(1);
        }
    }
    
    public UserEntity loginUser(String email, String password)
    {    
        
        try { 
            ArrayList<String[]> userString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.LoginUser), email, password);
            String[] tempUserStringArray = userString.get(0);
              
            String userRole = getUserRole(Integer.parseInt(tempUserStringArray[0]));
            
            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], tempUserStringArray[4], tempUserStringArray[5], tempUserStringArray[6].equals("1"), tempUserStringArray[7],  Integer.parseInt(tempUserStringArray[8]), userRole);
            
            String role = getUserRole(user.getUniqueUserId());
            user.setUserRole(role);
            
            return user;
        } catch (Exception e) { 
            //FIX
            
            //result.add("email or password wrong");
        }  
        //MAYBE CHANGE
        return null;    
    }
    
    public UserEntity getUser(int uniqueUserId)
    {    
        try { 
            ArrayList<String[]> userString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.GetUser), uniqueUserId);
            String[] tempUserStringArray = userString.get(0);
              
            String userRole = getUserRole(Integer.parseInt(tempUserStringArray[0]));
            
            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], tempUserStringArray[4], tempUserStringArray[5], tempUserStringArray[6].equals("1"), tempUserStringArray[7],  Integer.parseInt(tempUserStringArray[8]), userRole);
            
            String role = getUserRole(user.getUniqueUserId());
            user.setUserRole(role);
            
            return user;
        } catch (Exception e) { 
            //FIX
            
            //result.add("email or password wrong");
        }  
        //MAYBE CHANGE
        return null;    
    }
    
    public String getUserRole(int uniqueUserId){    
        StoredProcedures.SqlQueryEnum[] userRoleEnums = {StoredProcedures.SqlQueryEnum.getPatient_Uuid, StoredProcedures.SqlQueryEnum.getEmployee_Uuid};
        String[] userRoleStringArray = {"patient", "employee"};
        EmployeeService employeeService = new EmployeeService(dynamicDao);
        ArrayList roleArrayLst = new ArrayList();
        
        String result = ""; 
        
        try {
            // Admins UUID is always/only 1
            if(uniqueUserId == 1){
                result = "admin";
            }
            
            for (int i = 0; i < 2; i++) {
                roleArrayLst = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(userRoleEnums[i]), uniqueUserId);  
                
                if(roleArrayLst.size() != 0){
                    result = userRoleStringArray[i];
                }  
            }
           
            if(result.isEmpty()){
                throw new Exception("Result is empty");
            }
        } catch (Exception p) {
            //THROW EXCEPTION
        } 
        
        return result;
    }
}
