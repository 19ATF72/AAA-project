/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Dao.DynamicDao;
import model.Entity.AppointmentEntity;
import model.Entity.EmployeeEntity;
import model.Service.AppointmentService;
import model.Helper.StoredProcedures;

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
    private Connection conn = null;
    //private EmployeeEntity employeeModel;
    
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
        appointmentService = new AppointmentService(dynamicDaoMock);  
    }
    
    @After
    public void tearDown() {
    }


    @Test
    public void retrieveAvaialbleAppointmentsForDoctor_Success(){
        
        // Arrange
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"1", "2020-12-12 15:42:50.221"};
        appointmentsArrayList.add(appointmentStringArray);
        
        ArrayList<String[]> expectedAppointments = new ArrayList<>();
        String[] expectedAppointmentsStringArray = {"1", "2020-12-12 15:42:50.221"};
        expectedAppointments.add(expectedAppointmentsStringArray);
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<String[]> actualAppointments = appointmentService.retrieveAvaialbleAppointmentsForDoctor(1, "");     
        
        // Assert
        Assert.assertArrayEquals(actualAppointments.toArray(), expectedAppointments.toArray());
    }
    
    
    @Test
    public void CreateAppointment_Success(){
        
        // Arrange
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"1", "2020-12-12 15:42:50.221"};
        appointmentsArrayList.add(appointmentStringArray);
       
        //"INSERT INTO appointment (duration, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, appointment_status_asid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        ArrayList params = new ArrayList();
        params.add(1); //duration
        params.add(1); //charge
        params.add("2020-12-12"); //date
        params.add("00:00:00.000"); //start time
        params.add("01:00:00.000"); //end time
        params.add(1); //patient id
        params.add(1); //employee id
        params.add(1); //appointment type id
        params.add(1); // appointment status id
        
        String[] expectedAppointment = {"1", "1", "2020-12-12", "00:00:00.000", "01:00:00.000", "1", "1", "1", "1"};
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        appointmentService.CreateAppointment(params);
        
        ArrayList<String[]> listOfAppointments = appointmentService.retrieveAvaialbleAppointmentsForDoctor(1, "");
        
        String[] actualAppointment = listOfAppointments.get(listOfAppointments.size() - 1);
        
        
        // Assert
        Assert.assertThat(actualAppointment, new ReflectionEquals(expectedAppointment));
    }
}
