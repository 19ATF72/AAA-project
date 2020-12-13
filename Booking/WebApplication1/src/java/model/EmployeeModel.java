/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;
import dao.StoredData;
import java.util.ArrayList;

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
   private StoredData storedStatements = new StoredData();    
   private String calander;
   private Role employmentRole;
   private String[] bookedAppointments;
   private double salarayRate;
   //TODO add more but need to think
  
   public void create_Employee(ArrayList params, DynamicDao dynamicDao)
{    
    String result = "";
    //    , query[0], query[1], query[2], created, access, login, query[3], user_status
    try {  
            ArrayList resultSet = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.GetOrganizationByName), params.get(3));
            params.set(3,(Integer)resultSet.get(0));
            dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewEmployee), params.get(0), params.get(1), params.get(2), params.get(3), params.get(4));
            
           result = "User created successfully";
    } catch (Exception e) {
      result = "";
    }
}

                    
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
