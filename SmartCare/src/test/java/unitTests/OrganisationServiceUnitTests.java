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
    }
    
    @After
    public void tearDown() {
        dynamicDaoMock = mock(DynamicDao.class);
        organisationService = new OrganisationService(dynamicDaoMock); 
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    /*
    public ArrayList<OrganisationEntity> listAllOrganisations() throws SQLException {
        ArrayList<OrganisationEntity> organisationList = new ArrayList<>();
        sp = new StoredProcedures();

        ArrayList<String[]> result = dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.getOrganisation), "");
        
        for(int i=0; i<result.size(); i++)
        {
            String[] tempOrganisationStringArray = result.get(i);
            OrganisationEntity organisation = new OrganisationEntity(tempOrganisationStringArray[0], tempOrganisationStringArray[1], tempOrganisationStringArray[2],
                    tempOrganisationStringArray[3], tempOrganisationStringArray[4], tempOrganisationStringArray[5]);
            organisationList.add(organisation); 
        }
         
        return organisationList;    
    }
    */
    @Test
    public void listAllOrganisations_Success(){ //NOT possible yet? 'getOrganisation' SQL query has multiple possibilities
        
        // Arrange
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        //"INSERT INTO organisation (name, organisation_type_oid, address, postcode, phone_number) VALUES (?, ?, ?, ?, ?)");
        String[] organisationStringArray = {"Name", "ID", "Address", "Postcode", "1"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity organisation = new OrganisationEntity("Name", "ID", "Address", "Postcode", "1");
        ArrayList<OrganisationEntity> expectedListOfOrganisation = new ArrayList<>();
        expectedListOfOrganisation.add(organisation);
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<OrganisationEntity> actualListOfOrganisation = new ArrayList<>(); 
        try{
            actualListOfOrganisation = organisationService.listAllOrganisations(); 
        }catch(SQLException e){
            
        } 
        
        // Assert
        Assert.assertThat(expectedListOfOrganisation, new ReflectionEquals(actualListOfOrganisation));
    }
    
    
    /*
    public void insertOrganisation(OrganisationEntity organisation) throws SQLException {
        sp = new StoredProcedures();
       
        dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.insertOrganisation),  organisation.getName(), organisation.getOrgType(), 
                organisation.getAddress(), organisation.getPostcode(), organisation.getPhoneNum());
    }
    */
    @Test
    public void insertOrganisation_Success(){
        
        // Arrange
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {};
        appointmentsArrayList.add(appointmentStringArray);
       
        //public OrganisationEntity(String name, String orgType, String address, String postcode, String phoneNum)
        OrganisationEntity organisation = new OrganisationEntity("Name", "Type", "Address", "Postcode", "1");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        try {
            organisationService.insertOrganisation(organisation);
        }catch(SQLException e){
        
        }
        
        // Assert 
        ArrayList<OrganisationEntity> organisationList = new ArrayList<>(); 
        try{
            organisationList = organisationService.listAllOrganisations(); 
        }catch(SQLException e){
            
        }
        
        OrganisationEntity resultOrganisation = organisationList.get(organisationList.size()-1);
      
        Assert.assertThat(organisation, new ReflectionEquals(resultOrganisation));  
    }
    
    /*
    public void deleteOrganisation(OrganisationEntity organisation) throws SQLException {
        
        dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.deleteOrganisation), organisation.getOId());
        
        //need to turn to bool 
        //return rowDeleted;     
    }
    */
    @Test
    public void deleteOrganisation_Success(){ //TODO
        
        // Arrange
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {};
        appointmentsArrayList.add(appointmentStringArray);
       
        //public OrganisationEntity(String name, String orgType, String address, String postcode, String phoneNum)
        OrganisationEntity organisation = new OrganisationEntity("Name", "Type", "Address", "Postcode", "1");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        
        // Assert         
        /*
        public void remove(String fruit) {
            if (!lstFruits.contains(fruit)) {
                throw new NoSuchElementException();
            }
            lstFruits.remove(fruit);
        }
        public void testRemove() {
            lstTest.remove("Orange");
            assertEquals("Removing 1 fruit from list", 2, lstTest.size());
        }
        */
    }
    

    /*
    public OrganisationEntity getOrganisation(String oId) throws SQLException {
        OrganisationEntity organisation = null;
        
        sp = new StoredProcedures();
       
        ArrayList<String[]> result = dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.getOrganisation), oId);
        String[] orgString = result.get(0);
        
        organisation = new OrganisationEntity(orgString[0], orgString[1], orgString[2], orgString[3], orgString[4], orgString[5]);
        
        return organisation;
    }
    */
    @Test
    public void getOrganisation_Success(){
        
        // Arrange
        ArrayList<String[]> organisationArrayList = new ArrayList<>();
        String[] organisationStringArray = {"Name", "Type", "Address", "Postcode", "1"};
        organisationArrayList.add(organisationStringArray);
       
        OrganisationEntity expectedOrganisation = new OrganisationEntity("Name", "Type", "Address", "Postcode", "1");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(organisationArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        OrganisationEntity actualOrganisation = new OrganisationEntity("", "", "", "", ""); 
        try{
            actualOrganisation = organisationService.getOrganisation("1"); 
        }catch(SQLException e){
            
        } 
        
        // Assert
        Assert.assertThat(expectedOrganisation, new ReflectionEquals(actualOrganisation));
    }
}

