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
        String[] appointmentStringArray2 = {"2", "4200", "4800"};
        String[] appointmentStringArray3 = {"3", "4800", "5400"};
        String[] appointmentStringArray4 = {"4", "5400", "6000"};
        String[] appointmentStringArray5 = {"5", "6000", "6600"};
        String[] appointmentStringArray6 = {"6", "6600", "7200"};
        String[] appointmentStringArray7 = {"7", "7200", "7800"};
        appointmentsArrayList.add(appointmentStringArray1);
        appointmentsArrayList.add(appointmentStringArray2);
        appointmentsArrayList.add(appointmentStringArray3);
        appointmentsArrayList.add(appointmentStringArray4);
        appointmentsArrayList.add(appointmentStringArray5);
        appointmentsArrayList.add(appointmentStringArray6);
        appointmentsArrayList.add(appointmentStringArray7);
        
        
        ArrayList<String[]> appointments = new ArrayList<>();
        String[] expectedAppointmentsStringArray1 = {"1", "01:00", "01:10", "slot_1"};
        String[] expectedAppointmentsStringArray2 = {"2", "01:10", "01:20", "slot_2"};
        String[] expectedAppointmentsStringArray3 = {"3", "01:20", "01:30", "slot_3"};
        String[] expectedAppointmentsStringArray4 = {"4", "01:30", "01:40", "slot_4"};
        String[] expectedAppointmentsStringArray5 = {"5", "01:40", "01:50", "slot_5"};
        String[] expectedAppointmentsStringArray6 = {"6", "01:50", "02:00", "slot_6"};
        String[] expectedAppointmentsStringArray7 = {"7", "02:00", "02:10", "slot_7"};
        appointments.add(expectedAppointmentsStringArray1);
        appointments.add(expectedAppointmentsStringArray2);
        appointments.add(expectedAppointmentsStringArray3);
        appointments.add(expectedAppointmentsStringArray4);
        appointments.add(expectedAppointmentsStringArray5);
        appointments.add(expectedAppointmentsStringArray6);
        appointments.add(expectedAppointmentsStringArray7);
          
        String appointmentsStartTime = "01:00";
        ArrayList appointmentSlots = new ArrayList();
        appointmentSlots.add(appointmentsStartTime);
        appointmentSlots.add(appointments);
        
        ArrayList expectedAppointmentSlots = new ArrayList();
        expectedAppointmentSlots.add(appointmentSlots);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString())).thenReturn(appointmentsArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<String[]> actualAppointmentSlots = appointmentService.getAppointmentTimeSlots(1, "2020-05-12");     
        
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
        appointmentService.createAppointment(appointment, chosenSlots);
        
        ArrayList<String[]> listOfAppointments = appointmentService.getAppointmentTimeSlots(1, "");
        String[] actualAppointment = listOfAppointments.get(listOfAppointments.size() - 1);
        
        
        // Assert
        Assert.assertThat(actualAppointment, new ReflectionEquals(expectedAppointment));
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
