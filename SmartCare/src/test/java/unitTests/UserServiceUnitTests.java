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
import model.Entity.UserEntity;
import model.Service.UserService;
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


@RunWith(MockitoJUnitRunner.class)
public class UserServiceUnitTests {
    
    @Mock
    private DynamicDao dynamicDaoMock;
    private UserService userServiceMock;
    private UserService userService;
    private Connection conn = null;
    private Date dateMock;
    
    public UserServiceUnitTests() {
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
        dateMock = Date.valueOf("2000-01-01");

    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void createUser_Success(){
        
        // Arrange
        ArrayList resultArrayList = new ArrayList();
        resultArrayList.add(1);
        
        Date dateOfBirth = Date.valueOf("2000-01-01");
        Date dateCreated = Date.valueOf("2000-01-01");
        Date lastAccessed = Date.valueOf("2000-01-01");
        UserEntity user = new UserEntity(1, "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "User", 5551234, "2");

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), dateMock, dateMock, dateMock, anyInt(), anyString(), anyInt())).thenReturn(resultArrayList);
        }catch(Exception e){
            
        }

        // Act
        String actualResult = userService.createUser(user);
       
        //Assert
        Assert.assertEquals("User created successfully", actualResult);
    }

    
    @Test
    public void loginUser_Success(){
        
        // Arrange
        ArrayList<String[]> userArrayList = new ArrayList<>();
        String[] userStringArray = {"1", "Mr", "John", "Smith", "root", "root@admin.com",
            "2000-01-01", "2000-01-01", "2000-01-01", "0", "User", "2", "5551234"};
        userArrayList.add(userStringArray);
       
        Date dateOfBirth = Date.valueOf("2000-01-01");
        Date dateCreated = Date.valueOf("2000-01-01");
        Date lastAccessed = Date.valueOf("2000-01-01");
        UserEntity expectedUser = new UserEntity(1, "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "User", 5551234, "2");
                
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString())).thenReturn(userArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        UserEntity actualUser = userService.loginUser("root@admin.com", "root");     
        
        // Assert
        Assert.assertThat(actualUser, new ReflectionEquals(expectedUser));
    }

    
    @Test
    public void fetchUser_Success(){
        
        // Arrange
        ArrayList<String[]> userArrayList = new ArrayList<>();
        String[] userStringArray = {"1", "Mr", "John", "Smith", "root", "root@admin.com",
            "2000-01-01", "2000-01-01", "2000-01-01", "0", "User", "2", "5551234"};
        userArrayList.add(userStringArray);
        
        Date dateOfBirth = Date.valueOf("2000-01-01");
        Date dateCreated = Date.valueOf("2000-01-01");
        Date lastAccessed = Date.valueOf("2000-01-01");
        UserEntity expectedUser = new UserEntity(1, "Mr", "John", "Smith", "root", "root@admin.com",
                dateOfBirth, dateCreated, lastAccessed, false, "User", 5551234, "2");

        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(userArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        UserEntity actualUser = userService.fetchUser(1);
        
        // Assert
       Assert.assertThat(actualUser, new ReflectionEquals(expectedUser));
    }
}
