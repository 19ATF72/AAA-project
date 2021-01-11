/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;
import dao.StoredData;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author rob
 */
public class PatientModel {    
    private StoredData storedStatements = new StoredData();
    private int patientID;
    private String address;
    private int patientType;
    
public PatientModel(){}
    
public void create_patient(ArrayList params, DynamicDao dynamicDao){
    String result = "";
    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {  
       dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewPatient), params.get(0), params.get(1), params.get(2) );
   } catch (Exception e) {
      result = "";
  }
}
public void login_patient(String[] params, DynamicDao dynamicDao){
           setPatientID(Integer.parseInt(params[0]));
           setAddress(params[1]);
           setPatientType(Integer.parseInt(params[2]));
}

public ArrayList get_patient(ArrayList params, DynamicDao dynamicDao){
    ArrayList result = new ArrayList();
    try { 
           ArrayList<String[]> patientString = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatient), params.get(0) );
           String[] patient = patientString.get(0);   
           setAddress(patient[0]);
           setPatientID(Integer.parseInt(patient[1]));
           setPatientType(Integer.parseInt(patient[1]));
           result = null;
    } catch (Exception e) {
        result.add("patient doesnt exist");
    }
    return result;
}
public ArrayList retrievePatientAppointments( DynamicDao dynamicDao ){
        
    ArrayList result = new ArrayList();
    try {
           result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatientAppointments), patientID);
    } catch (Exception e) {
        result.add("User has no appointments");
    }
    return result;
}
public ArrayList retrievePatientDisplayableAppointments( DynamicDao dynamicDao ){
        
    ArrayList result = new ArrayList();
    try {
           result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatientDisplayableAppointments), patientID);
    } catch (Exception e) {
        result.add("User has no appointments");
    }
        for (int appointment = 0; appointment < result.size(); appointment++) {
            ((String [])result.get(appointment))[5] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[5])).toString();
            ((String [])result.get(appointment))[4] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[4])).toString();
        }
    return result;
}  
public void createAppointment( ArrayList Params, DynamicDao dynamicDao ){
    ArrayList result = new ArrayList();
    try {
           result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewAppointment), Params.get(0), Params.get(1), Params.get(2), Params.get(3), Params.get(4), Params.get(5), Params.get(6), Params.get(7), Params.get(8));
    } catch (Exception e) {
        result.add("User has no appointments");
    }
}   

       public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPatientType() {
        return patientType;
    }

    public void setPatientType(int patientType) {
        this.patientType = patientType;
    }

   
   
}
