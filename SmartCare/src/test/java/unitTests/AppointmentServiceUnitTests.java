/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao.DynamicDao;
import model.Entity.AppointmentEntity;
import model.Entity.AppointmentSlotsEntity;
import model.Entity.EmployeeEntity;
import model.Entity.UserEntity;
import model.Service.AppointmentService;
import model.Helper.StoredProcedures;
import model.Service.EmployeeService;
import model.Service.UserService;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import static org.mockito.Matchers.*;

/**
 *
 * @author James
 */
public class AppointmentServiceUnitTests {
    @Mock
    private DynamicDao dynamicDaoMock;    
    private AppointmentService appointmentService;
    private UserService userService;
    private UserService userServiceMock;
    private EmployeeService employeeService;
    private EmployeeService employeeServiceMock;
    private Connection conn = null;
    private Date dateMock;
    private Date dateMockService;

    
    public AppointmentServiceUnitTests() {
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
        userService = new UserService(dynamicDaoMock);  
        userServiceMock = mock(UserService.class);
        employeeService = new EmployeeService(dynamicDaoMock);  
        employeeServiceMock = mock(EmployeeService.class);
        appointmentService = new AppointmentService(dynamicDaoMock); 
        dateMock = Date.valueOf("2000-01-01");
        dateMockService = mock(Date.class);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void getAppointmentTimeSlots_Success(){
        
        // Arrange
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray1 = {"1", "3600", "4200"};
        appointmentsArrayList.add(appointmentStringArray1);

        AppointmentSlotsEntity appointmentSlots = new AppointmentSlotsEntity(1, "3600", "4800");
       
        ArrayList<AppointmentSlotsEntity> expectedAppointmentSlots = new ArrayList<>();
        expectedAppointmentSlots.add(appointmentSlots);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString())).thenReturn(appointmentsArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<AppointmentSlotsEntity> actualAppointmentSlots = new ArrayList<>();
        actualAppointmentSlots = appointmentService.getAppointmentTimeSlots(1, "2020-05-12");     
        
        // Assert
        Assert.assertThat(actualAppointmentSlots, new ReflectionEquals(expectedAppointmentSlots));
    }

    
    @Test
    public void getStartAndEndOfAppointmentTimes_Success(){
        
        // Arrange
        String[] chosenSlots = {"0,00:01:00", "1,00:02:00"};
        int[] expectedStartAndEndTime = {60, 120};
        
        // Act
        int[] actualStartAndEndTime = appointmentService.getStartAndEndOfAppointmentTimes(chosenSlots);
        
        // Assert
        Assert.assertArrayEquals(actualStartAndEndTime, expectedStartAndEndTime);
    }
    
    
    @Test
    public void retrieveEmployeeDisplayableAppointments_Success(){

        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"600", "Notes", "50.0", "2000-01-01", "1", "601", "1", "1", "1", "Username", "1"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"600", "Notes", "50.0", "2000-01-01", "00:00:01", "00:01:01", "1", "1", "1", "Username", "1"};
        expectedEmployeeAppointments.add(expectedEmployeeStringArray);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList actualEmployeeAppointments = appointmentService.retrieveEmployeeDisplayableAppointments(employee);
        
        // Assert
        Assert.assertThat(actualEmployeeAppointments, new ReflectionEquals(expectedEmployeeAppointments));
    }
    
    
    @Test
    public void retrieveEmployeeDailyDisplayableAppointments_Success(){

        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"600", "Notes", "50.0", "2000-01-01", "1", "601", "1", "1", "1", "Username", "1"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"600", "Notes", "50.0", "2000-01-01", "00:00:01", "00:01:01", "1", "1", "1", "Username", "1"};
        expectedEmployeeAppointments.add(expectedEmployeeStringArray);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList actualEmployeeAppointments = appointmentService.retrieveEmployeeDailyDisplayableAppointments(employee);
        
        // Assert
        Assert.assertThat(actualEmployeeAppointments, new ReflectionEquals(expectedEmployeeAppointments));
    }
    
    
    @Test
    public void getPatientsAppointments_Success(){
        
        // Arrange        
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"1", "600", "Notes", "50", "2020-12-12", "2021-01-01", "00:00:01", "00:10:01", "1", "2", "1", "1", "1"};
        appointmentsArrayList.add(appointmentStringArray);
        
        ArrayList<AppointmentEntity> expectedAppointments = new ArrayList<>();

        Date dateOfBirth = Date.valueOf("2000-01-01");
        Date dateCreated = Date.valueOf("2000-01-01");
        Date lastAccessed = Date.valueOf("2000-01-01");
        UserEntity user = new UserEntity(1, "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "User", 5551234, "2");
        
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1, 
                "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "Employee", 1, "5551234");
        
        when(employeeServiceMock.fetchEmployee_EId(anyInt())).thenReturn(employee);
        when(userServiceMock.fetchUser(anyInt())).thenReturn(user);
        

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<AppointmentEntity> actualAppointments = appointmentService.getPatientsAppointments(1);
        
        
        // Assert
        Assert.assertArrayEquals(expectedAppointments.toArray(), actualAppointments.toArray()); 
    }
    
    
    @Test
    public void createAppointment_Success(){
        
        // Arrange
        ArrayList resultArrayList = new ArrayList();
        resultArrayList.add(1);
        
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"1", "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221"};
        appointmentsArrayList.add(appointmentStringArray);

  
        AppointmentEntity appointment = new AppointmentEntity(600, "Notes", 50.0, 
                "2000-01-01", "1", "601", 1, 2, 1, 1, 1);
        
        ArrayList<String> chosenSlots = new ArrayList<>();
        
        String[] expectedAppointment = {};
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString(), anyDouble(), 
                    dateMock, anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(resultArrayList);
        }catch(SQLException e){
            
        }

        // Act
      //  appointmentService.createAppointment(appointment, chosenSlots);
        
      //  ArrayList<String[]> listOfAppointments = appointmentService.getAppointmentTimeSlots(1, "");
       // String[] actualAppointment = listOfAppointments.get(listOfAppointments.size() - 1);
        
        
        // Assert
      //  Assert.assertThat(actualAppointment, new ReflectionEquals(expectedAppointment));
    }
    

    @Test
    public void cancelAppointment_Success(){
        
        // Arrange
        ArrayList resultArrayList = new ArrayList();
        resultArrayList.add(1);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(resultArrayList);
        }catch(SQLException e){
            
        }

        // Act       
        String actualResult = appointmentService.cancelAppointment(1);        
        
        // Assert
        Assert.assertEquals("Cancelled", actualResult);
    }
}
