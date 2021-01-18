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
            int intRepOfIsLoggenIn; 
            if(user.isLoggedIn()){
                intRepOfIsLoggenIn = 1; 
            }
            else{
                intRepOfIsLoggenIn = 0; 
            }
            
            
            int uniqueUserId = (Integer)dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewUser), user.getUserPrefix(), user.getUserFirstname(), user.getUserSurname(), user.getPassword(), user.getEmail(), user.getDateOfBirth(),
                    date, date, intRepOfIsLoggenIn, user.getUserType(), user.getAccountStatus()).get(0);
            user.setUniqueUserId(uniqueUserId);
           
            result = "User created successfully";
           
        } catch (Exception e) {          
            result = "Email already registered ";
        }
        
        return result;
    } 
    
    private void modifyAccountStatus(UserEntity user){
        if(user.getUserType() == "patient") // If a patient
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
              
            Date dateOfBirth = Date.valueOf(tempUserStringArray[6]);
            Date dateCreated = Date.valueOf(tempUserStringArray[7]);
            Date lastAccessed = Date.valueOf(tempUserStringArray[8]);
            
            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], 
                    tempUserStringArray[4], tempUserStringArray[5], dateOfBirth, dateCreated, lastAccessed, Boolean.parseBoolean(tempUserStringArray[9]), tempUserStringArray[10], Integer.parseInt(tempUserStringArray[11]));
                    
            return user;
        } catch (Exception e) { 
            //FIX
            
        }  
        //MAYBE CHANGE
        return null;    
    }
    
    public UserEntity fetchUser(int uniqueUserId)
    {    
        try { 
            ArrayList<String[]> userString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.GetUser), uniqueUserId);
            String[] tempUserStringArray = userString.get(0);
            
            Date dateOfBirth = Date.valueOf(tempUserStringArray[6]);
            Date dateCreated = Date.valueOf(tempUserStringArray[7]);
            Date lastAccessed = Date.valueOf(tempUserStringArray[8]);
            

            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], 
                    tempUserStringArray[4], tempUserStringArray[5], dateOfBirth, dateCreated, lastAccessed, Boolean.parseBoolean(tempUserStringArray[9]), tempUserStringArray[10], Integer.parseInt(tempUserStringArray[11]));
            
            return user;
        } catch (Exception e) { 
            //FIX
            
            //result.add("email or password wrong");
        }  
        //MAYBE CHANGE
        return null;    
    }
    
}
