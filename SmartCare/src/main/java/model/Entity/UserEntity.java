/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;
/**
 *
 * @author rob
 */

//POSSIBLY NEED TO CHANGE
enum Status {
    active,
    idle
}


public class UserEntity {
    
    protected int uniqueUserId; 
    
    protected String userName;
    protected String password;
    protected String email; 
    
    protected String dateCreated;
    protected String lastAccessed;
    
    protected boolean loggedIn;
    
    protected String picture;
    
    protected int accountStatus;
    
    protected String userRole;
   
    
    public UserEntity(){
    }
    
    public UserEntity(int uniqueUserId, String userName, String password, String email, String dateCreated, String lastAccessed, Boolean loggedIn, String picture, int accountStatus, String userRole){
        this.uniqueUserId = uniqueUserId;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.lastAccessed = lastAccessed;
        this.loggedIn = loggedIn;
        this.picture = picture;
        this.accountStatus = accountStatus;
        this.userRole = userRole;
    }
    
    public void setIsLoggedIn(boolean loggedIn){
        this.loggedIn = loggedIn;
    }
    
    public boolean getIsLoggedIn(){
        return loggedIn;
    }
    
    
    public void setUserName(String userName){
        this.userName = userName;
    }
    
    public String getUserName(){
        return userName; 
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
    
    public void Date(String lastAccessed){
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
    
    public void setUserRole(String userRole){
        this.userRole = userRole;
    }
    
    public String getUserRole(){
        return userRole; 
    }
    
    
}
