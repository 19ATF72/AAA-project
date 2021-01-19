/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;
import java.sql.Date;  
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
    
    protected String userPrefix;
    protected String userFirstname;
    protected String userSurname;
    protected String password;
    protected String email; 
    
    protected Date dateOfBirth;
    
    protected Date dateCreated;
    protected Date lastAccessed;
    
    protected boolean loggedIn;
   
    
    protected String userType; 
    
    protected int accountStatus;
 

    
    public UserEntity() {
    }

    public UserEntity(int uniqueUserId, String userPrefix, String userFirstname, String userSurname, String password, String email, Date dateOfBirth, Date dateCreated, Date lastAccessed, boolean loggedIn, String userType, int accountStatus) {
        this.uniqueUserId = uniqueUserId;
        this.userPrefix = userPrefix;
        this.userFirstname = userFirstname;
        this.userSurname = userSurname;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.dateCreated = dateCreated;
        this.lastAccessed = lastAccessed;
        this.loggedIn = loggedIn;
    
        this.userType = userType;
        this.accountStatus = accountStatus;
    }

    
 
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    
    
    public int getUniqueUserId() {
        return uniqueUserId;
    }

    public void setUniqueUserId(int uniqueUserId) {
        this.uniqueUserId = uniqueUserId;
    }

    public String getUserPrefix() {
        return userPrefix;
    }

    public void setUserPrefix(String userPrefix) {
        this.userPrefix = userPrefix;
    }

    public String getUserFirstname() {
        return userFirstname;
    }

    public void setUserFirstname(String userFirstname) {
        this.userFirstname = userFirstname;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(Date lastAccessed) {
        this.lastAccessed = lastAccessed;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(int accountStatus) {
        this.accountStatus = accountStatus;
    } 
}
