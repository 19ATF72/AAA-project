/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.util.ArrayList;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import model.Entity.UserEntity;

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
        String result = "";
        
        try {
            int uniqueUserId = (Integer)dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewUser), user.getUsername(), user.getPassword(), user.getEmail(), 
                   user.getDateCreated(), user.getLastAccessed(), user.getIsLoggedIn(), user.getPicture(), user.getAccountStatus()).get(0);
            user.setUniqueUserId(uniqueUserId);
           
            result = "User created successfully";
            //result.add(uniqueUserId);
           
        } catch (Exception e) {          
            result = "Email already registered ";
        }
        
        return result;
    } 
    
    public UserEntity loginUser(String email, String password)
    {    
        try { 
            ArrayList<String[]> userString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.LoginUser), email, password);
            String[] tempUserStringArray = userString.get(0);

            UserEntity user = new UserEntity(Integer.parseInt(tempUserStringArray[0]), tempUserStringArray[1], tempUserStringArray[2], tempUserStringArray[3], tempUserStringArray[4], tempUserStringArray[5], tempUserStringArray[6].equals("1"), tempUserStringArray[7],  Integer.parseInt(tempUserStringArray[8]));

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
        StoredProcedures.SqlQueryEnum[] userRoleEnums = {StoredProcedures.SqlQueryEnum.getPatient, StoredProcedures.SqlQueryEnum.getEmployee, StoredProcedures.SqlQueryEnum.getEmployee};
        String[] userRoleStringArray = {"patient", "employee", "admin"};
            
        ArrayList roleArrayLst = new ArrayList();
        
        String result = ""; 
        
        try {
        
            // TODO: FIX .getEmployee to getAdmin for admin
        
            for (int i = 0; i < 3; i++)
            {
                roleArrayLst = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(userRoleEnums[i]), uniqueUserId);
                if(roleArrayLst.size() != 0){
                    result = userRoleStringArray[0];
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
