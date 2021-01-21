/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;  
import java.sql.SQLException;
import java.time.LocalDate;  
import java.util.ArrayList;
import java.util.Formatter;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import model.Entity.UserEntity;
import org.apache.commons.codec.digest.DigestUtils;

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
        
        String hashedPassword = hashPassword(user.getPassword());
        
        user.setPassword(hashedPassword);

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
        
        String hashedInputPassword = hashPassword(password);
        
        try { 
            ArrayList<String[]> userString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.LoginUser), email, hashedInputPassword);
            String[] tempUserStringArray = userString.get(0);
              
            Date dateOfBirth = Date.valueOf(tempUserStringArray[6]);
            Date dateCreated = Date.valueOf(tempUserStringArray[7]);
            Date lastAccessed = Date.valueOf(tempUserStringArray[8]);
            
            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], 
                    tempUserStringArray[4], tempUserStringArray[5], dateOfBirth, dateCreated, lastAccessed, Boolean.parseBoolean(tempUserStringArray[9]), tempUserStringArray[10], Integer.parseInt(tempUserStringArray[12]),tempUserStringArray[11]);
                    
            return user;
        } catch (Exception e) { 
            System.out.println("Exception " + e);
            return null;
        }  
        
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
                    tempUserStringArray[4], tempUserStringArray[5], dateOfBirth, dateCreated, lastAccessed, Boolean.parseBoolean(tempUserStringArray[9]), tempUserStringArray[10],  Integer.parseInt(tempUserStringArray[12]),tempUserStringArray[11]);
            
            return user;
        } catch (Exception e) { 
            //FIX
            
            //result.add("email or password wrong");
        }  
        //MAYBE CHANGE
        return null;    
    }
    
    public void updateUserStatus(Integer user_id, Integer status)
    {
        try {
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateUserStatus), status, user_id);
        } catch (Exception e) {
        }
    }
    
    private String hashPassword(String password){
        return (String)DigestUtils.sha1Hex(password);
    }
   
    
}
