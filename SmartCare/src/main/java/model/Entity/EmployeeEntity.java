/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;

import model.Entity.UserEntity;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
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
public class EmployeeEntity extends UserEntity {
    
    private StoredProcedures storedProcedures = new StoredProcedures();    
    
    private int employeeId;
    private double salary;
    private String address = "";
    private int employeeRole;
    private int organisation;
    
    public EmployeeEntity() {
    }
    
    public EmployeeEntity(int employeeId, double salary, String address, int organisation, int employeeRole) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.address = address; 
        this.organisation = organisation;
        this.employeeRole = employeeRole;
    }
    
    public EmployeeEntity(double salary, String address, int employeeRole, int organisation) {
        this.salary = salary;
        this.address = address; 
        this.employeeRole = employeeRole;
        this.organisation = organisation;
    }
    
    public void setEmployeeEntityFromUser(UserEntity user) {
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
    
    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.employeeId = EmployeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double Salary) {
        this.salary = Salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(int employeeRole) {
        this.employeeRole = employeeRole;
    }

    public int getOrganisation() {
        return organisation;
    }

    public void setOrganisation(int Organization) {
        this.organisation = Organization;
    }

}
