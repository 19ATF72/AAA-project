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

    }
    
    @After
    public void tearDown() {
    }
    
    
    @Test
    public void createUser_Success(){
        
        // Arrange
        ArrayList resultArrayList = new ArrayList();
        resultArrayList.add(1);

        UserEntity user = new UserEntity(1, "Rob", "password", "email@email.com", "01/01/2021", "02/01/2021", false, "pic string", 2);

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString(), anyString(), anyString(), anyString(), anyBoolean(), anyString(), anyInt())).thenReturn(resultArrayList);
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
        String[] userStringArray = {"1", "root", "root", "root@admin.com", 
            "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", "0", "default.png", "2"};
        userArrayList.add(userStringArray);
       

        UserEntity expectedUser = new UserEntity(1, "root", "root", "root@admin.com", 
                "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", false, "default.png", 2);
        expectedUser.setUserRole("");
                
        Assert.assertThat(expectedUser, new ReflectionEquals(actualUser));
    }

    
    @Test
    public void getUserRole_Success(){ //Seems to just work even though it shouldn't be able to query properly yet?
        
        // Arrange
        ArrayList<String[]> roleArrayList = new ArrayList<>();
        String[] roleStringArray = {"1", "root", "root", "root@admin.com", 
            "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", "0", "default.png", "2", "0"};
        roleArrayList.add(roleStringArray);
        
        String expectedRole = "patient";

        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(roleArrayList); //Should need 'getRole' in model.Helper.StoredProcedures to be developed first?
        }catch(SQLException e){
            
        }
        
        // Act
        String actualRole = userService.getUserRole(1);
        
        // Assert
       Assert.assertThat(expectedRole, new ReflectionEquals(actualRole));
    }
    
    /*
    @Test
    public void getUserRole_Failure(){
        
        // Arrange
        
        // Act
        
        // Assert
    }
    */
}
