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
public class PatientModel extends UserModel {
    
    private String address; //TODO possibly change..   
    private String appointment;
    private String[] currentPrescriptions;
    
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
