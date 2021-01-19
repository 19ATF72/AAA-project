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
 * @author rob
 */
public class PatientServiceUnitTests {
    
   
    private DynamicDao dynamicDaoMock;
    private PatientService patientServiceMock;
    
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
        patientServiceMock = mock(PatientService.class);

    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void loginUser_Success(){
        
        // Arrange
        ArrayList<String[]> resultArrayList = new ArrayList<>();
        String[] patientStringArray = {"1", "John Smith", "Address","1"};
        resultArrayList.add(patientStringArray);
       
        PatientEntity patient = new PatientEntity(1, "John Smith", "Address", 1);
                
        try{
            //when(dynamicDao.agnosticQuery(anyString(), anyObject())).thenReturn(userArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(resultArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        //PatientEntity resultPatient = patientService.getPatient(1);
        
        // Assert
        //Assert.assertThat(patient, new ReflectionEquals(resultPatient));
    }
}
