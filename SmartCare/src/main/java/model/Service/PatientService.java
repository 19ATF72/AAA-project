/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import model.Dao.DynamicDao;
import model.Entity.UserEntity;
import model.Entity.PatientEntity;
import model.Helper.StoredProcedures;

/**
 *
 * @author rob
 */
public class PatientService {
    
    protected DynamicDao dynamicDao;
    private final StoredProcedures storedProcedures = new StoredProcedures();  
    
    public PatientService(DynamicDao dynamicDao){
        this.dynamicDao = dynamicDao;
    }
    
    public String createPatient(PatientEntity patient){
        String result = "";
        
        try {  
            ArrayList agnosticQuery = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewPatient), 
                    patient.getAddress(), patient.getPostcode(), patient.getPatientType(), patient.getPatientId());
           result = "Patient created";
        } catch (Exception e) {
          result = "Unable to create patient";
        }
        return result; 
    }
    
    public PatientEntity getPatient(UserEntity user)
    {
        ArrayList<String[]> result = new ArrayList();
        try {  
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatient_Uuid), user.getUniqueUserId()); 
           
            String[] tempPatientEntityString = result.get(0);    
            PatientEntity patient = new PatientEntity(Integer.parseInt(tempPatientEntityString[0]), tempPatientEntityString[1],
                    tempPatientEntityString[2], Integer.parseInt(tempPatientEntityString[3]), user.getUniqueUserId(), user.getUserPrefix(),
                    user.getUserFirstname(), user.getUserSurname(), user.getPassword(), user.getEmail(), user.getDateOfBirth(), user.getDateCreated(), 
                    user.getLastAccessed(), user.isLoggedIn(), user.getUserType(), user.getAccountStatus(), user.getPhoneNumber());
            
            return patient; 
        } catch (Exception e) {
           //THROW ERROR
        }
        return null;
    }
    
    
    // TODO: OLD 
    public ArrayList retrievePatientDisplayableAppointments(PatientEntity patient){

        ArrayList result = new ArrayList();
        try {
               result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientDisplayableAppointments), patient.getUniqueUserId());
        } catch (Exception e) {
            result.add("User has no appointments");
        }
            for (int appointment = 0; appointment < result.size(); appointment++) {
                ((String [])result.get(appointment))[5] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[5])).toString();
                ((String [])result.get(appointment))[4] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[4])).toString();
            }
        return result;
    }  
    
//    public ArrayList get_patient(ArrayList params, DynamicDao dynamicDao){
//        ArrayList result = new ArrayList();
//        try { 
//               ArrayList<String[]> patientString = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatient_Uuid), params.get(0) );
//               String[] patient = patientString.get(0);   
//               setAddress(patient[0]);
//               setPatientID(Integer.parseInt(patient[1]));
//               setPatientType(Integer.parseInt(patient[1]));
//               result = null;
//        } catch (Exception e) {
//            result.add("patient doesnt exist");
//        }
//        return result;
//    }
    
//        public ArrayList retrievePatientAppointments( DynamicDao dynamicDao ){
//
//        ArrayList result = new ArrayList();
//        try {
//               result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientAppointments), patientId);
//        } catch (Exception e) {
//            result.add("User has no appointments");
//        }
//        return result;
//    }
}
