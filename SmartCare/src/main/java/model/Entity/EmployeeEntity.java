/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;

import java.sql.Date;

/**
 *
 * @author rob
 */
public class EmployeeEntity extends UserEntity{
    
    private  int employeeId;
    private  double salary;
    private  String address;
    private  String postcode;
    private  int organisation;

    public EmployeeEntity(){
    }

    public EmployeeEntity(double salary, String address, String postcode, int organisation) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.address = address;
        this.postcode = postcode;
        this.organisation = organisation;
    }
    
    public EmployeeEntity(int employeeId, double salary, String address, String postcode, int organisation, int uniqueUserId) {
        this.employeeId = employeeId;
        this.salary = salary;
        this.address = address;
        this.postcode = postcode;
        this.organisation = organisation;
        this.uniqueUserId = uniqueUserId;
    }

    public EmployeeEntity(int employeeId, double salary, String address, String postcode, int organisation, int uniqueUserId, String userPrefix, String userFirstname, String userSurname, String password, String email, Date dateOfBirth, Date dateCreated, Date lastAccessed, boolean loggedIn, String userType, int accountStatus, String phoneNumber) {
        super(uniqueUserId, userPrefix, userFirstname, userSurname, password, email, dateOfBirth, dateCreated, lastAccessed, loggedIn, userType, accountStatus, phoneNumber);
        this.employeeId = employeeId;
        this.salary = salary;
        this.address = address;
        this.postcode = postcode;
        this.organisation = organisation;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public int getOrganisation() {
        return organisation;
    }

    public void setOrganisation(int organisation) {
        this.organisation = organisation;
    }

}
