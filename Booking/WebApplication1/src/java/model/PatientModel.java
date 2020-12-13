/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;
import dao.StoredStatements;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class PatientModel {
    private StoredStatements storedStatements = new StoredStatements();   
    private String address; //TODO possibly change..   
    private String appointment;
    private String[] currentPrescriptions;
    
public PatientModel(){}
    
public void create_patient(ArrayList params, DynamicDao dynamicDao){
    String result = "";
    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {  
       dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredStatements.SqlQueryEnum.NewPatient), params.get(0), params.get(1), params.get(2) );
   } catch (Exception e) {
      result = "";
  }
}

public void get_patient(ArrayList params, DynamicDao dynamicDao){
    String result = "";

    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {  
       dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredStatements.SqlQueryEnum.getPatient), params.get(0) );
   } catch (Exception e) {
      result = "";
  }
}
    
   public void setAddress(String address){
        this.address = address;
   }
    
   public String getAddress(){
        return address; 
   }
   
//   public void setAppointment(String appointment){
//        this.appointment = appointment;
//   }
//    
//   public String getAppointment(){
//        return appointment; 
//   }
   
   public void setCurrentPrescriptions(String[] currentPrescriptions){
        this.currentPrescriptions = currentPrescriptions;
   }
    
   public String[] getCurrentPrescriptions(){
        return currentPrescriptions; 
   }
   
   
   
}
