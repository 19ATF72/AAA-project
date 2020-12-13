/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.StoredStatements;
import java.util.ArrayList;
import dao.DynamicDao;
import dao.StoredStatements;
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
    private DynamicDao dynamicDao = new DynamicDao();
    private StoredStatements storedStatements = new StoredStatements();   
    private String username;
    private String password;
    private String email; 
    private String name;
    
    private String dateCreated;
    private String lastAccessed;
    
    private boolean loggedIn;
    
    private Status accountStatus;
           
    private int uniqueUserId; 
    
    private String picture;
    
public UserModel(){}
    
public ArrayList create_User(ArrayList params)
{    
    ArrayList result = new ArrayList();
    dynamicDao.tryConnect();
    if (dynamicDao == null){ 
        result.add("conFail"); 
    }
    else{
    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {
           params.set(7,2);   
           uniqueUserId = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredStatements.SqlQueryEnum.NewUser), params.get(0),params.get(1),params.get(2), params.get(3),params.get(4),params.get(5),params.get(6),params.get(7));
           result.add("User created successfully");
           result.add(uniqueUserId);
           
    } catch (Exception e) {
        result.add("Email already registered ");
    }
    }
    return result;
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
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(){
        return name; 
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
    
    public void setAccountStatus(Status accountStatus){
        this.accountStatus = accountStatus;
    }
    
    public Status getAccountStatus(){
        return accountStatus; 
    }
    
//    public void setUniqueUserId(){
//        UUID uuid = UUID.randomUUID();
//        this.uniqueUserId = uuid;
//    }
    
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
