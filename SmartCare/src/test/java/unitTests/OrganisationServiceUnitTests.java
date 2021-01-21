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
import model.Entity.OrganisationEntity;
import model.Service.OrganisationService;

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


public class OrganisationServiceUnitTests {
    
    private DynamicDao dynamicDaoMock;    
    private OrganisationService organisationService;
    private Connection conn = null;
    
    public OrganisationServiceUnitTests() {
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
        organisationService = new OrganisationService(dynamicDaoMock);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void listAllOrganisations_Success(){ //NOT possible yet? 'getOrganisation' SQL query has multiple possibilities
        
        // Arrange
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        String[] organisationStringArray = {"1", "Name", "ID", "Address", "Postcode", "5550123"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity organisation = new OrganisationEntity(1, "Name", "ID", "Address", "Postcode", "5550123");
        ArrayList<OrganisationEntity> expectedOrganisationList = new ArrayList<>();
        expectedOrganisationList.add(organisation);
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }

        // Act
        ArrayList<OrganisationEntity> actualOrganisationList = new ArrayList<>();
        try{
            actualOrganisationList = organisationService.listAllOrganisations(); 
        }catch(SQLException e){
            
        } 
        
        // Assert
        Assert.assertThat(expectedOrganisationList, new ReflectionEquals(actualOrganisationList));

    }
    
    
    @Test
    public void insertOrganisation_Success(){
        
        // Arrange
        ArrayList resultArray = new ArrayList();
        resultArray.add(1);
        
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        String[] organisationStringArray = {"1", "Name", "ID", "Address", "Postcode", "5550123"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity organisation = new OrganisationEntity(1, "Name", "ID", "Address", "Postcode", "5550123");
        
        OrganisationEntity expectedOrganisation = new OrganisationEntity(1, "Name", "ID", "Address", "Postcode", "5550123");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString(), anyString(), anyString(), anyString())).thenReturn(resultArray);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<OrganisationEntity> organisationList = new ArrayList<>();
        try {
            organisationService.insertOrganisation(organisation);
            organisationList = organisationService.listAllOrganisations();
        }catch(SQLException e){
        
        }
        
        OrganisationEntity actualOrganisation = organisationList.get(organisationList.size()-1);
        
        // Assert
        Assert.assertThat(actualOrganisation, new ReflectionEquals(expectedOrganisation));  
    }
    

    @Test
    public void deleteOrganisation_Success(){ //TODO
        
        // Arrange
        ArrayList resultArray = new ArrayList();
        resultArray.add(1);
        
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        String[] organisationStringArray = {"1", "Name", "ID", "Address", "Postcode", "5550123"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity organisation = new OrganisationEntity("Name", "Type", "Address", "Postcode", "1");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(resultArray);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        boolean actualResult = false;
        try {
            organisationService.insertOrganisation(organisation);
            actualResult = organisationService.deleteOrganisation(organisation);
        }catch(SQLException e){
        
        }        
        
        // Assert 
        assertEquals(true, actualResult);
        
    }
    
    
    @Test
    public void getOrganisation_Success(){
        
        // Arrange
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        String[] organisationStringArray = {"1", "Name", "Type", "Address", "Postcode", "5550123"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity expectedOrganisation = new OrganisationEntity(1, "Name", "Type", "Address", "Postcode", "5550123");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        OrganisationEntity actualOrganisation = new OrganisationEntity(1, "", "", "", "", ""); 
        try{
            actualOrganisation = organisationService.getOrganisation("1"); 
        }catch(SQLException e){
            
        } 
        
        // Assert
        Assert.assertThat(expectedOrganisation, new ReflectionEquals(actualOrganisation));
    }
}

