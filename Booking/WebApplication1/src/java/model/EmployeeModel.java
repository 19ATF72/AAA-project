/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

enum Role {  //TODO: RENAME?? 
    doctor,
    nurse,
    admin
}


/**
 *
 * @author rob
 */
public class EmployeeModel extends UserModel {
    
   private String calander;
    
   private Role employmentRole;
   private String[] bookedAppointments;
   private double salarayRate;
   //TODO add more but need to think
  
   public void setCalander(String calander){
        this.calander = calander;
   }
    
   public String getCalander(){
        return calander; 
   }
   
   public void setEmploymentRole(Role employmentRole){
        this.employmentRole = employmentRole;
   }
    
   public Role getemploymentRole(){
        return employmentRole; 
   }
   
   public void setBookedAppointments(String[] bookedAppointments){
        this.bookedAppointments = bookedAppointments;
   }
    
   public String[] getBookedAppointments(){
        return bookedAppointments; 
   }
   
   public void setSalaryRate(double salarayRate){
        this.salarayRate = salarayRate;
   }
    
   public double getSalaryRate(){
        return salarayRate; 
   }
    
}
