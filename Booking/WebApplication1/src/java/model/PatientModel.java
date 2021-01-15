/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;


/**
 *
 * @author rob
 */
public class PatientModel {    
    
    private int patientID;
    private String address;
    private int patientType;
    
public PatientModel(){}
    
//public void createAppointment( ArrayList Params, DynamicDao dynamicDao ){
//    ArrayList result = new ArrayList();
//    try {
//           result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewAppointment), Params.get(0), Params.get(1), Params.get(2), Params.get(3), Params.get(4), Params.get(5), Params.get(6), Params.get(7), Params.get(8));
//    } catch (Exception e) {
//        result.add("User has no appointments");
//    }
//}   

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
