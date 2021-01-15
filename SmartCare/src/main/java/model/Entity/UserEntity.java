/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.time.LocalDate;
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
    
    protected String username;
    protected String password;
    protected String email; 
    
    protected Date dateCreated;
    protected Date lastAccessed;
    
    protected boolean loggedIn;
    
    protected String picture;
    
    protected int accountStatus;
    
    protected String userRole;
   
    
    public UserEntity(){
    }
    
    public UserEntity(int uniqueUserId, String username, String password, String email, Date dateCreated, Date lastAccessed, Boolean loggedIn, String picture, int accountStatus){
        this.uniqueUserId = uniqueUserId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dateCreated = dateCreated;
        this.lastAccessed = lastAccessed;
        this.loggedIn = loggedIn;
        this.picture = picture;
        this.accountStatus = accountStatus;
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
    
    public String getUsername(){
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
    
    public void setDateCreated(){
        Date date;
        date = Date.valueOf(LocalDate.now());
        this.dateCreated = date;
    }
    
    public Date getDateCreated(){
        return dateCreated; 
    }
    
    public void Date(Date lastAccessed){
        this.lastAccessed = lastAccessed;
    }
    
    public Date getLastAccessed(){
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
