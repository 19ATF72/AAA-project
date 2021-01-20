/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao.DynamicDao;
import model.Entity.EmployeeEntity;
import model.Entity.UserEntity;
import model.Service.AppointmentService;
import model.Service.EmployeeService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

/**
 *
 * @author James
 */
 
public class EmployeeServiceUnitTests {
    
    @Mock
    private DynamicDao dynamicDaoMock;    
    private EmployeeService employeeService;
    private Connection conn = null;
    private AppointmentService appointmentService; 
    
    public EmployeeServiceUnitTests() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dynamicDaoMock = mock(DynamicDao.class);
        employeeService = new EmployeeService(dynamicDaoMock);  
    }
    
    @After
    public void tearDown() {
    }    
    

    @Test
    public void createEmployee_Success(){
        
        // Arrange
        ArrayList<String> resultArrayList = new ArrayList();
        resultArrayList.add("1");

        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyObject())).thenReturn(resultArrayList);
        }catch(Exception e){
            
        }
        
        // Act
        String actualResult = employeeService.createEmployee(employee);
       
        //Assert
        Assert.assertEquals("Employee created successfully", actualResult);
    }
    
    
    @Test
    public void fetchEmployee_Success(){
        
        // Arrange
        ArrayList<String[]> employeeArrayList = new ArrayList<>();
        String[] patientStringArray = {"1", "1.0", "Address", "Postcode", "1", 
            "1", "Mr", "John", "Smith", "password", "email@email.com", 
        "2020-01-01", "2020-01-01", "2020-01-01", "0", "Employee", "5551234", "1"};
        employeeArrayList.add(patientStringArray);
        
        Date dateOfBirth = Date.valueOf("2000-01-01");
        Date dateCreated = Date.valueOf("2000-01-01");
        Date lastAccessed = Date.valueOf("2000-01-01");
        UserEntity user = new UserEntity(1, "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "Employee", 1, "5551234");
        
        EmployeeEntity expectedEmployee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1, 
                "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "Employee", 1, "5551234");
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        EmployeeEntity actualEmployee = employeeService.fetchEmployee(user);
        
        // Assert
        Assert.assertThat(actualEmployee, new ReflectionEquals(expectedEmployee));
    }

    
    @Test
    public void fetchEmployee_EId_Success(){
        
        // Arrange
        ArrayList<String[]> employeeArrayList = new ArrayList<>();
        String[] patientStringArray = {"1", "1.0", "Address", "Postcode", "1", "1"};
        employeeArrayList.add(patientStringArray);
        
        EmployeeEntity expectedEmployee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        EmployeeEntity actualEmployee = employeeService.fetchEmployee_EId(1);
        
        // Assert
        Assert.assertThat(actualEmployee, new ReflectionEquals(expectedEmployee));
    }
    
    @Test
    public void retrieveEmployeeDisplayableAppointments_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"Duration", "Notes", "Charge", "Date", "1", "61", "Appointment status", "1", "1", "Username"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"Duration", "Notes", "Charge", "Date", "00:00:01", "00:01:01", "Appointment status", "1", "1", "Username"};
        expectedEmployeeAppointments.add(expectedEmployeeStringArray);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
//        ArrayList actualEmployeeAppointments = employeeService.retrieveEmployeeDisplayableAppointments(employee);
        
        // Assert
    //    Assert.assertThat(actualEmployeeAppointments, new ReflectionEquals(expectedEmployeeAppointments));
    }
    
    
}
