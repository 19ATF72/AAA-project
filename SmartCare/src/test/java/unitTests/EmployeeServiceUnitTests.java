/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao.DynamicDao;
import model.Entity.EmployeeEntity;
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
    
    private DynamicDao dynamicDaoMock;    
    private EmployeeService employeeService;
    private Connection conn = null;
    
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

        EmployeeEntity employee = new EmployeeEntity(1.0, "Address", 1, 1);

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyObject())).thenReturn(resultArrayList);
        }catch(Exception e){
            
        }
        
        // Act
        String actualResult = employeeService.createEmployee(employee);
       
        //Assert
        Assert.assertEquals("Employee created successfully", actualResult);
    }
    
    
    /*
    public EmployeeEntity getEmployee(int uniqueUserID)
    {
        ArrayList<String[]> result = new ArrayList();
        try {  
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployee), uniqueUserID);
            
            String[] tempEmployeeEntityString = result.get(0);
            EmployeeEntity employee = new EmployeeEntity(Integer.parseInt(tempEmployeeEntityString[0]), Double.parseDouble(tempEmployeeEntityString[1]), 
                    tempEmployeeEntityString[2], Integer.parseInt(tempEmployeeEntityString[3]), Integer.parseInt(tempEmployeeEntityString[4]));
            
            return employee; 
        } catch (Exception e) {
           //THROW ERROR
        }
        return null;
    }
    */
    @Test
    public void getEmployee_Success(){
        
        // Arrange
        ArrayList<String[]> employeeArrayList = new ArrayList<>();
        String[] patientStringArray = {"1", "1.0", "Address", "1", "1"};
        employeeArrayList.add(patientStringArray);
        
        EmployeeEntity expectedEmployee = new EmployeeEntity(1, 1.0, "Address", 1, 1);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        EmployeeEntity actualEmployee = employeeService.getEmployee(1);
        
        // Assert
        Assert.assertThat(actualEmployee, new ReflectionEquals(expectedEmployee));
    }
    
    
    @Test
    public void retrieveEmployeeDisplayableAppointments_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"Duration", "Notes", "Charge", "Date", "1", "61", "Appointment status", "1", "1", "Username"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"Duration", "Notes", "Charge", "Date", "00:00:01", "00:01:01", "Appointment status", "1", "1", "Username"};
        expectedEmployeeAppointments.add(expectedEmployeeStringArray);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList actualEmployeeAppointments = employeeService.retrieveEmployeeDisplayableAppointments(employee);
        
        // Assert
        Assert.assertThat(actualEmployeeAppointments, new ReflectionEquals(expectedEmployeeAppointments));
    }
    
    
    @Test
    public void retrieveEmployeeDailyDisplayableAppointments_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"Duration", "Notes", "Charge", "Date", "1", "601", "Appointment status", "1", "1", "Username"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"Duration", "Notes", "Charge", "Date", "00:00:01", "00:01:01", "Appointment status", "1", "1", "Username"};
        expectedEmployeeAppointments.add(expectedEmployeeStringArray);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList actualEmployeeAppointments = employeeService.retrieveEmployeeDailyDisplayableAppointments(employee);
        
        // Assert
        Assert.assertThat(actualEmployeeAppointments, new ReflectionEquals(expectedEmployeeAppointments));
    }
    
    /*
    public void UpdateAppointment(ArrayList params, DynamicDao dynamicDao){

        ArrayList result = new ArrayList();
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.newPrescription), params.get(0), params.get(1), params.get(2));
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateAppointment), params.get(3), result.get(0), params.get(4));
        } catch (Exception e) {
        }

    }
    */
    @Test
    public void UpdateAppointment_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        
        //"INSERT INTO patient_prescriptions (patient_pid, medicine, repeat) VALUES(?,?,?)");
        //"UPDATE appointment SET notes=?,patient_prescriptions_prid=?,appointment_status_asid=2 WHERE aid=?");
        ArrayList params = new ArrayList();
        params.add(1);
        params.add("Medicine");
        params.add(false);
        params.add("Notes");
        params.add(1);
        params.add(1);
        
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", 1, 1);
        
        ArrayList expecetedUpdatedAppointment = new ArrayList();
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString(), anyBoolean())).thenReturn(employeeAppointmentsArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyInt(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        
        employeeService.UpdateAppointment(params, dynamicDaoMock);
        
        ArrayList actualUpdatedAppointment = employeeService.retrieveEmployeeDisplayableAppointments(employee);
        
        // Assert
        
        Assert.assertThat(actualUpdatedAppointment, new ReflectionEquals(expecetedUpdatedAppointment));

    }
}
