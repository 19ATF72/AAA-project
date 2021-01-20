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
import model.Entity.PatientEntity;
import model.Entity.UserEntity;
import model.Service.PatientService;

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

public class PatientServiceUnitTests {
    
    @Mock
    private DynamicDao dynamicDaoMock;
    private PatientService patientService;
    private Connection conn = null;
    
    public PatientServiceUnitTests() {
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
        patientService = new PatientService(dynamicDaoMock);  
    }
  
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void createPatient_Success(){
        
        // Arrange
        ArrayList<String[]> resultArrayList = new ArrayList<>();
        
        //public PatientEntity(int patientId, String patientName, String address, int patientType)
        String[] patientStringArray = {"1", "John Smith", "Address", "1"};
        resultArrayList.add(patientStringArray);

        PatientEntity patient = new PatientEntity(1, "John Smith", "Address", 1);

        try{
            //dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewPatient), patient.getAddress(), patient.getPatientType(), patient.getUniqueUserId());
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString(), anyInt())).thenReturn(resultArrayList);
        }catch(Exception e){
            
        }
        
        // Act
        String actualResult = patientService.createPatient(patient);
       
        //Assert
        Assert.assertEquals("Patient created", actualResult);
    }
    
    /*
    public PatientEntity getPatient(int uniqueUserID)
    {
        ArrayList<String[]> result = new ArrayList();
        try {  
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatient), uniqueUserID); 
           
            String[] tempPatientEntityString = result.get(0);    
            PatientEntity patient = new PatientEntity(Integer.parseInt(tempPatientEntityString[0]), tempPatientEntityString[1],
                    tempPatientEntityString[2], Integer.parseInt(tempPatientEntityString[3]));
            
            return patient; 
        } catch (Exception e) {
           //THROW ERROR
        }
        return null;
    }
    */
    @Test
    public void getPatient_Success(){
        
        // Arrange
        ArrayList<String[]> patientArrayList = new ArrayList<>();
        String[] patientStringArray = {"1", "John Smith", "Address", "1"};
        patientArrayList.add(patientStringArray);
        
        //uniqueUserId, String username, String password, String email, String dateCreated, String lastAccessed, Boolean loggedIn, String picture, int accountStatus, String userRole)
        //PatientEntity expectedPatient = new PatientEntity(1, "username", "pass", "email@email.com", "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", false, "pic.png", 2, "0", 1, "John Smith", "Address", 1);
        
        PatientEntity expectedPatient = new PatientEntity(1, "John Smith", "Address", 1);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(patientArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        PatientEntity actualPatient = patientService.getPatient(1);
        
        // Assert
        Assert.assertThat(actualPatient, new ReflectionEquals(expectedPatient));
        //Assert.assertSame(expectedPatient, actualPatient);
    }
    
    
    @Test
    public void retrievePatientDisplayableAppointments_Success(){
        
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"Duration", "Notes", "Charge", "2020-12-12", "1", "61", "1"};
        appointmentsArrayList.add(appointmentStringArray);
        ArrayList expectedAppointments = new ArrayList();
        String[] expectedAppointmentsStringArray = {"Duration", "Notes", "Charge", "2020-12-12", "00:00:01", "00:01:01", "1"};
        expectedAppointments.add(expectedAppointmentsStringArray);
        
        PatientEntity patient = new PatientEntity(1, "John Smith", "Address", 1);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        

        ArrayList actualAppointments = patientService.retrievePatientDisplayableAppointments(patient);
        
        // Assert
        Assert.assertArrayEquals(expectedAppointments.toArray(), actualAppointments.toArray()); 

    }
}
