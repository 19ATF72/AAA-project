/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.UUID;

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
      
    private String username;
    private String password;
    private String email; 
    private String name;
    
    private String dateCreated;
    private String lastAccessed;
    
    private boolean loggedIn;
    
    private Status accountStatus;
           
    private UUID uniqueUserId; 
    
    private String picture;
    
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
    
    public void setUniqueUserId(){
        UUID uuid = UUID.randomUUID();
        this.uniqueUserId = uuid;
    }
    
    public UUID getUniqueUserId(){
        return uniqueUserId; 
    }
    
    public void setPicture(String picture){
        this.picture = picture;
    }
    
    public String getPicture(){
        return picture; 
    }
    
    
    
}
