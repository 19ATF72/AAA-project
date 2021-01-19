/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import model.Entity.EmployeeEntity;
import model.Entity.UserEntity;

/**
 *
 * @author rob
 */
public class EmployeeService {
    
    protected DynamicDao dynamicDao;
    private final StoredProcedures storedProcedures = new StoredProcedures();  
    
    public EmployeeService(DynamicDao dynamicDao){
        this.dynamicDao = dynamicDao;
    }
    
    public String createEmployee(EmployeeEntity employee)
    {    
        String result = "";
        try {  
            //TODO: FIX ORG
            //TODO ArrayList resultSet = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.GetOrganizationByName), params.get(3));

            //TODO CHANGE ORG from 0
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewEmployee), employee.getSalary(), employee.getAddress(), employee.getOrganisation(), employee.getUniqueUserId());

            result = "Employee created successfully";
        } catch (Exception e) {
          result = "Unable to create employee";
        }
        return result; 
    }

    public EmployeeEntity fetchEmployee(UserEntity user)
    {
        ArrayList<String[]> result = new ArrayList();
        try {  
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployee_Uuid), user.getUniqueUserId());
            
            String[] tempEmployeeEntityString = result.get(0);
            EmployeeEntity employee = new EmployeeEntity(Integer.parseInt(tempEmployeeEntityString[0]), Double.parseDouble(tempEmployeeEntityString[1]), 
                    tempEmployeeEntityString[2], tempEmployeeEntityString[3], Integer.parseInt(tempEmployeeEntityString[4]), user.getUniqueUserId(), user.getUserPrefix(),
                    user.getUserFirstname(), user.getUserSurname(), user.getPassword(), user.getEmail(), user.getDateOfBirth(), user.getDateCreated(), 
                    user.getLastAccessed(), user.isLoggedIn(), user.getUserType(), user.getAccountStatus());
            
            return employee; 
        } catch (Exception e) {
           //THROW ERROR
        }
        return null;
    }
    
    public EmployeeEntity fetchEmployee_EId(int eId)
    {
        ArrayList<String[]> result = new ArrayList();
        try {  
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployee_Eid), eId);
            
            String[] tempEmployeeEntityString = result.get(0);
             EmployeeEntity employee = new EmployeeEntity(Integer.parseInt(tempEmployeeEntityString[0]), Double.parseDouble(tempEmployeeEntityString[1]), 
                    tempEmployeeEntityString[2], tempEmployeeEntityString[3], Integer.parseInt(tempEmployeeEntityString[4]), Integer.parseInt(tempEmployeeEntityString[5]));
            
            return employee; 
        } catch (Exception e) {
           //THROW ERROR
        }
        return null;
    }
    

    public ArrayList retrieveEmployeeDisplayableAppointments(EmployeeEntity employee){

        ArrayList result = new ArrayList();
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeDisplayableAppointments), employee.getUniqueUserId());
        } catch (Exception e) {
            result.add("User has no appointments");
        }
            for (int appointment = 0; appointment < result.size(); appointment++) {
                ((String [])result.get(appointment))[5] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[5])).toString();
                ((String [])result.get(appointment))[4] = LocalTime.ofSecondOfDay( Integer.parseInt(((String [])result.get(appointment))[4])).toString();
            }
        return result;
    }
    
    public ArrayList retrieveEmployeeDailyDisplayableAppointments(EmployeeEntity employee){

        ArrayList result = new ArrayList();
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeDisplayableDailyAppointments), employee.getUniqueUserId());
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
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.newPrescription), params.get(0), params.get(1), params.get(2));
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateAppointment), params.get(3), result.get(0), params.get(4));
        } catch (Exception e) {
        }

    }
    
}
