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
import model.Entity.UserEntity;
import model.Helper.StoredProcedures;
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


/**
 *
 * @author rob
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
    public void tearDown(){
    }
    
//    @Test
//    public void creatUser_Success(){
//        
//        ArrayList arraylst = new ArrayList();
//
//        ArrayList<String> arrayLstToReturn = new ArrayList();
//        arrayLstToReturn.add("1");
//
//        UserEntity user = new UserEntity(01, "Rob", "password", "email@email.com", 
//                "01/01/2021", "02/01/2021", false, "pic string", 2);
//
//        try{
//            when(dynamicDao.agnosticQuery(anyString(), anyObject())).thenReturn(arrayLstToReturn);
//        }catch(Exception e){
//            
//        }
//        
//        String result = userService.createUser(user);
//       
//        assertEquals("User created successfully", result);
//    }
    
    @Test
    public void loginUser_Success(){
        
        // Arrange
        ArrayList<String[]> userArrayList = new ArrayList<>();
        String[] userStringArray = {"1","root","root","root@admin.com","2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", "0", "default.png", "2", "0"};
        userArrayList.add(userStringArray);
       
//        UserEntity user = new UserEntity(1, "root", "root", "root@admin.com", 
//                "2020-12-12 15:42:50.221", "2020-12-12 15:42:50.221", false, "default.png", 2);
//        user.setUserRole("");
//                
        try{
            //when(dynamicDao.agnosticQuery(anyString(), anyObject())).thenReturn(userArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyString())).thenReturn(userArrayList);
            when(userServiceMock.getUserRole(anyInt())).thenReturn("");
        }catch(SQLException e){
            
        }
        
        // Act
        UserEntity resultUser = userService.loginUser("root@admin.com", "root");     
        
        // Assert
      //  Assert.assertThat(user, new ReflectionEquals(resultUser));
    }
    
    
    @Test
    public void getUserRole_Success(){
        
//        // Arrange
//        ArrayList<String[]> roleArrayList = new ArrayList<>();
//        String[] userStringArray = {};
//        roleArrayList.add(userStringArray);
//        
//        // Act
//        try{
//            //when(dynamicDao.agnosticQuery(anyString(), anyObject())).thenReturn(userArrayList);
//            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn;
//        }catch(SQLException e){
//            
//        }
//        
//        // Assert
       
    }
}
