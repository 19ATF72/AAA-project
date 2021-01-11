/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;

import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class PatientEntity extends UserEntity {    
    protected StoredProcedures storedStatements = new StoredProcedures();
    protected String address;
    protected int patientType;
    protected int patientId;
    protected String patientName; 
    
    public PatientEntity(){}

    public PatientEntity(int patientId, String patientName, String address, int patientType){
        this.patientId = patientId;
        this.patientName = patientName;
        this.address = address;
        this.patientType = patientType;
    }
    
    public PatientEntity(int uniqueUserId, int patientType, String address){
        this.uniqueUserId = uniqueUserId;
        this.address = address;
        this.patientType = patientType;
    }
    
    public void setPatientEntityFromUser(UserEntity user){
        this.uniqueUserId = user.getUniqueUserId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.dateCreated = user.getDateCreated();
        this.lastAccessed = user.getLastAccessed();
        this.loggedIn = user.getIsLoggedIn();
        this.picture = user.getPicture();
        this.accountStatus = user.getAccountStatus();
    }
    

   
    
    public void createAppointment( ArrayList Params, DynamicDao dynamicDao ){
    ArrayList result = new ArrayList();
        try {
               result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewAppointment), Params.get(0), Params.get(1), Params.get(2), Params.get(3), Params.get(4), Params.get(5), Params.get(6), Params.get(7), Params.get(8));
        } catch (Exception e) {
            result.add("User has no appointments");
        }
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
    
    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
    
    public int getPatientType() {
        return patientType;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
    }

   
   
}
