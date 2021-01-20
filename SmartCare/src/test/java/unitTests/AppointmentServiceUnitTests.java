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
    }
    
    @After
    public void tearDown() {
    }


    /*
    private ArrayList<String[]> parseAppointmentsToTimeSlots(ArrayList<AppointmentSlotsEntity> slots){
        
        ArrayList lengths  = new ArrayList();
        ArrayList tempLengths  = new ArrayList();
        Integer lengthIndex = 1;
        for (int i = 0; i < slots.size(); i++) {

            int startTime = Integer.parseInt(slots.get(i).getStartTime());
           
            if (startTime%3600 == 0 && tempLengths.size() != 0){
                String start = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i).getStartTime())).toString();
                String end = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i).getEndTime())).toString();
                String[] times = {String.valueOf(slots.get(i).getAppointmentSlotId()), start, end,"slot_" + lengthIndex.toString()};
                tempLengths.add(times);
                ArrayList composed = new ArrayList(Arrays.asList(LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i-6).getStartTime())).toString(), tempLengths));
                lengths.add(composed);
                tempLengths  = new ArrayList();
                lengthIndex = 1;
            }
            else{
                String start = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i).getStartTime())).toString();
                String end = LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i).getEndTime())).toString();
                String[] times = {String.valueOf(slots.get(i).getAppointmentSlotId()), start, end,"slot_" + lengthIndex.toString()};
                tempLengths.add(times);
                lengthIndex++;
            }
        }
        return lengths;
    }
    
    private ArrayList<AppointmentSlotsEntity> fetchAvaialbleAppointmentsForPractitioner(int doctorId, String date){   
        
        
        ArrayList<String[]> tempPractitionersAppointments = new ArrayList();
        ArrayList<AppointmentSlotsEntity>  practitionerAppointments = new ArrayList<AppointmentSlotsEntity>();
        
        
        try {
            tempPractitionersAppointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctorId, date);
            
            if(tempPractitionersAppointments.size() != 0){
                for (int i = 0; i < tempPractitionersAppointments.size(); i++) {
                    
                    AppointmentSlotsEntity appointmentSlots = new AppointmentSlotsEntity(Integer.parseInt(tempPractitionersAppointments.get(i)[0]), tempPractitionersAppointments.get(i)[1], tempPractitionersAppointments.get(i)[2]); 
                    
                    practitionerAppointments.add(appointmentSlots);
                }
            }
            else{
                try {
                    tempPractitionersAppointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getAllPossibleAppointments), "");
                    
                    for (int i = 0; i < tempPractitionersAppointments.size(); i++) {

                        AppointmentSlotsEntity appointmentSlots = new AppointmentSlotsEntity(Integer.parseInt(tempPractitionersAppointments.get(i)[0]), tempPractitionersAppointments.get(i)[1], tempPractitionersAppointments.get(i)[2]); 
                    
                        practitionerAppointments.add(appointmentSlots);
                    }
                } catch (Exception p) {                
                }
            }   

        } catch (Exception e) {           
        }
        return practitionerAppointments;
    }
    
    public ArrayList<String[]> getAppointmentTimeSlots(int practitionerId, String date){
        
        ArrayList<AppointmentSlotsEntity> appointmentTimes = fetchAvaialbleAppointmentsForPractitioner(practitionerId, date);
        
        return parseAppointmentsToTimeSlots(appointmentTimes);  
    }
    
    */
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
        
        //dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctorId, date)
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString())).thenReturn(appointmentsArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<String[]> actualAppointmentSlots = appointmentService.getAppointmentTimeSlots(1, "2020-05-12");     
        
        // Assert
        Assert.assertArrayEquals(actualAppointmentSlots.toArray(), expectedAppointmentSlots.toArray());
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
    
    /*
    public void createAppointment(AppointmentEntity appointment, ArrayList<String> chosenSlots){   
        try {
            
            fetchAvaialbleAppointmentsForPractitioner(appointment.getEmployeeId(), appointment.getDateStr());
            
            
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewAppointment), appointment.getDuration(), appointment.getNotes(), appointment.getCharge(), Date.valueOf(appointment.getDateStr()), 
                Integer.parseInt(appointment.getStartTime()), Integer.parseInt(appointment.getEndTime()), appointment.getPatientId(), appointment.getEmployeeId(), appointment.getType(), 1);
           // for (int i = 0; i < chosenSlots.length; i++) {
               // dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewEmployeeAppointmentSlot), slot_ids.get(slot), params.get(7),params.get(3));
           // }
        } catch (Exception e) {
            e.printStackTrace();
            String test = e.toString();
        }
    }
    */
    
    /*
     *
     * @param patientId
     * @return patientsAppointmentsArrayLstEntity
     
    public ArrayList<AppointmentEntity> getPatientsAppointments(int patientId){
        ArrayList<String[]> patientsAppointmentsArrayLstString = new ArrayList();
        ArrayList<AppointmentEntity> patientsAppointmentsArrayLstEntity = new ArrayList();
        
        EmployeeService employeeService = new EmployeeService(dynamicDao);
        UserService userService = new UserService(dynamicDao);
        
        try {
            patientsAppointmentsArrayLstString = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientAppointments), patientId);
            if (patientsAppointmentsArrayLstString.size() == 0) {
                // DO something
            }
            else{
                for (int i = 0; i < patientsAppointmentsArrayLstString.size(); i++) {
                    String[] patientsAppointmentsArray = patientsAppointmentsArrayLstString.get(i);
                    
                    AppointmentEntity appointmentEntity = new AppointmentEntity();
                    
                    appointmentEntity.setUniqueAppointmentId(Integer.parseInt(patientsAppointmentsArray[0]));
                    appointmentEntity.setDuration(Integer.parseInt(patientsAppointmentsArray[1]));
                    appointmentEntity.setCharge(Double.parseDouble(patientsAppointmentsArray[3]));
                    appointmentEntity.setDateStr(patientsAppointmentsArray[4]);
                    appointmentEntity.setDatePaidStr(patientsAppointmentsArray[5]);
                    appointmentEntity.setStartTime(patientsAppointmentsArray[6]);
                    appointmentEntity.setEndTime(patientsAppointmentsArray[7]);
                    appointmentEntity.setPatientId(Integer.parseInt(patientsAppointmentsArray[8]));
                    appointmentEntity.setEmployeeId(Integer.parseInt(patientsAppointmentsArray[9]));
                    appointmentEntity.setType(Integer.parseInt(patientsAppointmentsArray[10]));
                    appointmentEntity.setStatus(Integer.parseInt(patientsAppointmentsArray[12])); 
                    
                    if(patientsAppointmentsArray[2] != null)
                        appointmentEntity.setNotes(patientsAppointmentsArray[2]);
                    else
                        appointmentEntity.setNotes(" ");
                    if(patientsAppointmentsArray[11] != null)
                        appointmentEntity.setPerscriptionId(Integer.parseInt(patientsAppointmentsArray[11]));
                    
                    
                    EmployeeEntity tempEmployee = employeeService.fetchEmployee_EId(appointmentEntity.getEmployeeId()); 
                    UserEntity tempUser = userService.fetchUser(tempEmployee.getUniqueUserId());
                           
                    String employeeePrefixAndName = tempUser.getUserPrefix() + " " + tempUser.getUserSurname();
                    
                    appointmentEntity.setDoctorsName(employeeePrefixAndName);

                    patientsAppointmentsArrayLstEntity.add(appointmentEntity);  
                   
                }
            }
        } catch (Exception e) {
            //PRINT SOMETHING
        }
        
        return patientsAppointmentsArrayLstEntity;
    }
    */
    
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
        
        
        /*
        dynamicDaoMock = mock(DynamicDao.class);
        userService = new UserService(dynamicDaoMock);  
        userServiceMock = mock(UserService.class);
        employeeService = new EmployeeService(dynamicDaoMock);  
        employeeServiceMock = mock(EmployeeService.class);
        appointmentService = new AppointmentService(dynamicDaoMock); 
        employeeService = new EmployeeService(dynamicDaoMock); 
        userService = new UserService(dynamicDaoMock);
        */
        
        when(employeeServiceMock.fetchEmployee_EId(anyInt())).thenReturn(employee);
        when(userServiceMock.fetchUser(anyInt())).thenReturn(user);
        

        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act
        ArrayList<AppointmentEntity> actualAppointments = appointmentService.getPatientsAppointments(1);
        
        
        // Assert
        //Assert.assertThat(actualAppointments, new ReflectionEquals(expectedAppointments));
        Assert.assertArrayEquals(expectedAppointments.toArray(), actualAppointments.toArray()); 
    }
    
    
    /*
    public void createAppointment(AppointmentEntity appointment, ArrayList<String> chosenSlots){   
        try {
            
            fetchAvaialbleAppointmentsForPractitioner(appointment.getEmployeeId(), appointment.getDateStr());
            
            
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewAppointment), appointment.getDuration(), appointment.getNotes(), appointment.getCharge(), Date.valueOf(appointment.getDateStr()), 
                Integer.parseInt(appointment.getStartTime()), Integer.parseInt(appointment.getEndTime()), appointment.getPatientId(), appointment.getEmployeeId(), appointment.getType(), 1);
           // for (int i = 0; i < chosenSlots.length; i++) {
               // dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewEmployeeAppointmentSlot), slot_ids.get(slot), params.get(7),params.get(3));
           // }
        } catch (Exception e) {
            e.printStackTrace();
            String test = e.toString();
        }
    }
    */
    
    
    @Test
    public void retrieveEmployeeDailyDisplayableAppointments_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        String[] employeeAppointmentsStringArray = {"Duration", "Notes", "Charge", "Date", "1", "601", "Appointment status", "1", "1", "Username"};
        employeeAppointmentsArrayList.add(employeeAppointmentsStringArray);
                
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        ArrayList expectedEmployeeAppointments = new ArrayList();
        String[] expectedEmployeeStringArray = {"Duration", "Notes", "Charge", "Date", "00:00:01", "00:01:01", "Appointment status", "1", "1", "Username"};
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
    public void UpdateAppointment_Success(){
        
        // Arrange
        ArrayList<String[]> employeeAppointmentsArrayList = new ArrayList<>();
        
        //"INSERT INTO patient_prescriptions (patient_pid, medicine, repeat) VALUES(?,?,?)");
        //"UPDATE appointment SET notes=?,patient_prescriptions_prid=?,appointment_status_asid=2 WHERE aid=?");
        ArrayList params = new ArrayList();
        params.add(1);
        params.add("Medicine");
        params.add(false);
        params.add("Notes");
        params.add(1);
        params.add(1);
        
        EmployeeEntity employee = new EmployeeEntity(1, 1.0, "Address", "Postcode", 1, 1);
        
        ArrayList expecetedUpdatedAppointment = new ArrayList();
        
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString(), anyBoolean())).thenReturn(employeeAppointmentsArrayList);
            when(dynamicDaoMock.agnosticQuery(anyString(), anyString(), anyInt(), anyInt())).thenReturn(employeeAppointmentsArrayList);
        }catch(SQLException e){
            
        }
        
        // Act      
        appointmentService.UpdateAppointment(params, dynamicDaoMock);
        
        ArrayList actualUpdatedAppointment = appointmentService.retrieveEmployeeDisplayableAppointments(employee);
        
        // Assert
        Assert.assertThat(actualUpdatedAppointment, new ReflectionEquals(expecetedUpdatedAppointment));

    }
    
    @Test
    public void createAppointment_Success(){
        
        // Arrange
        ArrayList resultArrayList = new ArrayList();
        resultArrayList.add(1);
        
        ArrayList<String[]> appointmentsArrayList = new ArrayList<>();
        String[] appointmentStringArray = {"1", "2020-12-12 15:42:50.221"};
        appointmentsArrayList.add(appointmentStringArray);
        
        String[] expectedAppointment = {};
        
        // int duration, String notes, Double charge, String dateStr, String startTime, 
        // String endTime, int patientId, int employeeId, int type, int perscriptionId, int status)
        
        //int uniqueAppointmentId, int duration, String notes, Double charge, String dateStr, String datePaidStr, String startTime, 
        //String endTime, int patientId, int employeeId, int type, int perscriptionId, int status) {
        AppointmentEntity appointment = new AppointmentEntity();
        
        ArrayList<String> chosenSlots = new ArrayList<>();
        
        /*
        try{
            when(dynamicDaoMock.agnosticQuery(anyString(), anyInt(), anyString(), anyDouble(), dateMock, anyInt(), anyInt(), anyInt(), anyInt(), anyInt(), anyInt())).thenReturn(appointmentsArrayList);
        }catch(SQLException e){
            
        }
        */
        
        // Act
        appointmentService.createAppointment(appointment, chosenSlots);
        
        ArrayList<String[]> listOfAppointments = appointmentService.getAppointmentTimeSlots(1, "");
        String[] actualAppointment = listOfAppointments.get(listOfAppointments.size() - 1);
        
        
        // Assert
        Assert.assertThat(actualAppointment, new ReflectionEquals(expectedAppointment));
    }
}
