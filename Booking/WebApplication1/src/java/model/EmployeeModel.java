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
   private int EmployeeId = 0;
   private double Salary = 0.0;
   private String address = "";
   private int Type = 0;
   private int Organization = 0;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double Salary) {
        this.Salary = Salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return Type;
    }

    public void setType(int Type) {
        this.Type = Type;
    }

    public int getOrganization() {
        return Organization;
    }

    public void setOrganization(int Organization) {
        this.Organization = Organization;
    }
  
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
public void loginEmployee(String[] params, DynamicDao dynamicDao){
           setEmployeeId(Integer.parseInt(params[0]));
           setSalary(Double.parseDouble(params[1]));
           setAddress(params[2]);
           setType(Integer.parseInt(params[3]));
           
//           setOrganization(Organization) does not exist yet; 
}
public Double getEmployeeSalary(Integer eid, DynamicDao dynamicDao)
{    
    Double result = 0.0;
    try {  
           result = Double.parseDouble(((ArrayList<String[]>)dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployeeSalary), eid)).get(0)[0]);
    } catch (Exception e) {
        
    }
    return result;
}
public ArrayList retrieveEmployeeDisplayableAppointments( DynamicDao dynamicDao ){
        
    ArrayList result = new ArrayList();
    try {
        result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployeeDisplayableAppointments), EmployeeId);
    } catch (Exception e) {
        result.add("User has no appointments");
    }
        for (int appointment = 0; appointment < result.size(); appointment++) {
            ((String [])result.get(appointment))[5] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[5])).toString();
            ((String [])result.get(appointment))[4] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[4])).toString();
        }
    return result;
}
public ArrayList retrieveEmployeeDailyDisplayableAppointments( DynamicDao dynamicDao ){
        
    ArrayList result = new ArrayList();
    try {
        result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployeeDisplayableDailyAppointments), EmployeeId);
    } catch (Exception e) {
        result.add("User has no appointments");
    }
        for (int appointment = 0; appointment < result.size(); appointment++) {
            ((String [])result.get(appointment))[5] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[5])).toString();
            ((String [])result.get(appointment))[4] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[4])).toString();
        }
    return result;
}
public void UpdateAppointment(ArrayList params,DynamicDao dynamicDao){

    ArrayList result = new ArrayList();
    try {
        result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.newPrescription), params.get(0), params.get(1), params.get(2));
        result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.updateAppointment), params.get(3), result.get(0), params.get(4));
    } catch (Exception e) {
    }
    
}

}
