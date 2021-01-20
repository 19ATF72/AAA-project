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
public class PatientEntity extends UserEntity{    
    protected String address;
    protected int patientType;
    protected int patientId;
    protected String postcode; 
    
    public PatientEntity(){
    }

    public PatientEntity(int patientId, String address, String postcode, int patientType){
        this.patientId = patientId;
        this.address = address;
        this.postcode = postcode;
        this.patientType = patientType;
    }
    
    public PatientEntity(int uniqueUserId, int patientType, String address){
        this.uniqueUserId = uniqueUserId;
        this.address = address;
        this.patientType = patientType;
    }

    public PatientEntity(int patientId, String address, String patientPostCode, int patientType, int uniqueUserId, String userPrefix, String userFirstname, String userSurname, String password, String email, Date dateOfBirth, Date dateCreated, Date lastAccessed, boolean loggedIn, String userType, int accountStatus, String phoneNumber) {
        super(uniqueUserId, userPrefix, userFirstname, userSurname, password, email, dateOfBirth, dateCreated, lastAccessed, loggedIn, userType, accountStatus, phoneNumber);
        this.address = address;
        this.patientType = patientType;
        this.patientId = patientId;
        this.postcode = postcode;
    }

    
    
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public int getPatientType() {
        return patientType;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
    }

   
   
}
