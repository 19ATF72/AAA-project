/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.StoredData;
import java.util.ArrayList;
import dao.DynamicDao;
import dao.StoredData;
/**
 *
 * @author rob
 */

//POSSIBLY NEED TO CHANGE
enum Status {
    active,
    idle
}


public class UserModel {
    private StoredData storedStatements = new StoredData();   
    private String username;
    private String password;
    private String email; 
    
    private String dateCreated;
    private String lastAccessed;
    
    private boolean loggedIn;
    
    private int accountStatus;
           
    private int uniqueUserId; 
    
    private String picture;
    
public UserModel(){}
    
public ArrayList create_User(ArrayList params, DynamicDao dynamicDao)
{    
    ArrayList result = new ArrayList();

    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {
           params.set(7,2);   
           uniqueUserId = (Integer)dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewUser), params.get(0),params.get(1),params.get(2), params.get(3),params.get(4),params.get(5),params.get(6),params.get(7)).get(0);
           result.add("User created successfully");
           result.add(uniqueUserId);
           
    } catch (Exception e) {
        result.add("Email already registered ");
    }
    return result;
}    
public ArrayList login_User(ArrayList params, DynamicDao dynamicDao)
{    
    ArrayList result = new ArrayList();
    
    try { 
           ArrayList<String[]> user_string = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.LoginUser), params.get(0), params.get(1));
           String[] user = user_string.get(0);
           setUniqueUserId(Integer.parseInt(user[0]));
           setUsername(user[1]);
           setPassword(user[2]);
           setEmail(user[3]);
           setDateCreated(user[4]);
           setLastAccessed(user[5]);
           setIsLoggedIn(user[6].equals("1"));
           setPicture(user[7]);
           setAccountStatus(Integer.parseInt(user[8]));
           ArrayList role = get_user_role(dynamicDao);
           if (role!=null) {
                result.add(user);
                result.add(role.get(0));
                result.add(role.get(1));
            }
           else{
               result.add("email or password wrong");
           }
    } catch (Exception e) {
        result.add("email or password wrong");
    }
    return result;
}
public ArrayList get_user_role(DynamicDao dynamicDao){   
    ArrayList role = new ArrayList();
    try {
       role = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatient), uniqueUserId);
       role.add("patient");
    } catch (Exception p) {
        
        try {
            role = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployee), uniqueUserId);
            role.add("employee");
        } catch (Exception e) {
            
            try {
                role = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployee), uniqueUserId);
                role.add("admin");
            } catch (Exception a) {
                
                role = null;
                
            }
        }
    } 
    return role;
}
    
    public void setIsLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }
    
    public boolean getIsLoggedIn(){
        return loggedIn;
    }
    
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getusername(){
        return username; 
    }
 
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getPassword(){
        return password; 
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEmail(){
        return email; 
    }   
    
    public void setDateCreated(String dateCreated){
        this.dateCreated = dateCreated;
    }
    
    public String getDateCreated(){
        return dateCreated; 
    }
    
    public void setLastAccessed(String lastAccessed){
        this.lastAccessed = lastAccessed;
    }
    
    public String getLastAccessed(){
        return lastAccessed; 
    }
    
    public void setAccountStatus(int accountStatus){
        this.accountStatus = accountStatus;
    }
    
    public int getAccountStatus(){
        return accountStatus; 
    }
    
    public void setUniqueUserId(int uuid){
        this.uniqueUserId = uuid;
    }
    
    public int getUniqueUserId(){
        return uniqueUserId; 
    }
    
    public void setPicture(String picture){
        this.picture = picture;
    }
    
    public String getPicture(){
        return picture; 
    }
    
    
    
}
