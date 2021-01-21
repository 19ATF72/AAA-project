/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.time.LocalTime;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import model.Dao.DynamicDao;
import model.Entity.AppointmentEntity;
import model.Entity.EmployeeEntity;
import model.Entity.UserEntity;
import model.Helper.StoredProcedures;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import model.Entity.AppointmentSlotsEntity;
/**
 *
 * @author rob
 */
public class AppointmentService {
    
    protected DynamicDao dynamicDao;
    private final StoredProcedures storedProcedures = new StoredProcedures();  
    
    public AppointmentService(DynamicDao dynamicDao){
        this.dynamicDao = dynamicDao;
    }
    
    public ArrayList<AppointmentSlotsEntity> getAppointmentTimeSlots(int practitionerId, String date){
        
        ArrayList<AppointmentSlotsEntity> appointmentTimes = fetchAvaialbleAppointmentsForPractitioner(practitionerId, date);
        
        return appointmentTimes;  
    }
    
    public int[] getStartAndEndOfAppointmentTimes(String[] chosenSlots){
      
        ArrayList<String[]> formatedSlots = new ArrayList<>();
        for (String chosenSlot : chosenSlots) {
            formatedSlots.add(chosenSlot.split(","
                    + ""));
        }
        
        int first_index = Integer.parseInt(formatedSlots.get(0)[0]);
        int last_index = Integer.parseInt( formatedSlots.get((chosenSlots.length - 1))[0] );

        if ((last_index - first_index) != chosenSlots.length - 1){
            return null;
        }

        ArrayList slotIds = new ArrayList();
        
        for (int slot_id = 0; slot_id < formatedSlots.size(); slot_id++) {
            slotIds.add(Integer.parseInt(formatedSlots.get(slot_id)[0]));
        }
        
        int start = LocalTime.parse((String)formatedSlots.get(0)[1]).toSecondOfDay();
        int end = LocalTime.parse((String)formatedSlots.get(formatedSlots.size()- 1)[1]).toSecondOfDay();
        
        int[] startAndEndTime = {start, end};
        
        return startAndEndTime;
    }
    
    public ArrayList<AppointmentSlotsEntity> fetchAvaialbleAppointmentsForPractitioner(int doctorId, String date){   
        
        
        ArrayList<String[]> employesCurrentSlots = new ArrayList();
        ArrayList<String[]> tempAllPractitionersAppointments = new ArrayList();
        ArrayList<AppointmentSlotsEntity>  practitionerAppointments = new ArrayList<AppointmentSlotsEntity>();
        
        try {
           
            employesCurrentSlots = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctorId, date);
            tempAllPractitionersAppointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getAllPossibleAppointments), "");
            
            for(int j = 0; j < employesCurrentSlots.size(); j++){
                  for (int i = 0; i < tempAllPractitionersAppointments.size(); i++) {
                    
                    if(Integer.parseInt(employesCurrentSlots.get(j)[0]) != Integer.parseInt(tempAllPractitionersAppointments.get(i)[0])){
                        AppointmentSlotsEntity appointmentSlots = new AppointmentSlotsEntity(Integer.parseInt(tempAllPractitionersAppointments.get(i)[0]), tempAllPractitionersAppointments.get(i)[1], tempAllPractitionersAppointments.get(i)[2]); 
                    
                        practitionerAppointments.add(appointmentSlots);
                    }
                }
            }   
           
        } catch (Exception e) {           
        }
        return practitionerAppointments;
    }
    
    public ArrayList<AppointmentEntity> retrieveEmployeeDisplayableAppointments(EmployeeEntity employee){

        ArrayList<AppointmentEntity> appointmentList  = new ArrayList<AppointmentEntity>();
        
        ArrayList<String[]> result = new ArrayList();
        
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeDisplayableAppointments), employee.getUniqueUserId());
        } catch (Exception e) {
            
            //TODO THROW
            return null;
        }
        for (int i = 0; i < result.size(); i++) {
             AppointmentEntity appointment = new AppointmentEntity(Integer.parseInt(result.get(i)[0]), result.get(i)[1], Double.parseDouble(result.get(i)[2]), result.get(i)[3], result.get(i)[4], result.get(i)[5], Integer.parseInt(result.get(i)[6]), Integer.parseInt(result.get(i)[7]), Integer.parseInt(result.get(i)[8]), result.get(i)[9], result.get(i)[10]);
             appointmentList.add(appointment);
        } 
        return appointmentList;
    }
    
    public ArrayList<AppointmentEntity> retrieveEmployeeDailyDisplayableAppointments(EmployeeEntity employee){

        ArrayList<AppointmentEntity> appointmentList  = new ArrayList<AppointmentEntity>();
        
        ArrayList<String[]> result = new ArrayList();
        
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeDisplayableDailyAppointments), employee.getUniqueUserId());
        } catch (Exception e) {
            
            //TODO THROW
            return null;
        }
        for (int i = 0; i < result.size(); i++) {
             AppointmentEntity appointment = new AppointmentEntity(Integer.parseInt(result.get(i)[0]), result.get(i)[1], Double.parseDouble(result.get(i)[2]), result.get(i)[3], result.get(i)[4], result.get(i)[5], Integer.parseInt(result.get(i)[6]), Integer.parseInt(result.get(i)[7]), Integer.parseInt(result.get(i)[8]), result.get(i)[9], result.get(i)[10]);
             appointmentList.add(appointment);
        } 
        return appointmentList;
    }
    
     public ArrayList<String[]> parseAppointmentsToTimeSlots(ArrayList<AppointmentSlotsEntity> slots){
        
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
                ArrayList composed = new ArrayList(Arrays.asList(LocalTime.ofSecondOfDay(Integer.parseInt(slots.get(i).getStartTime())).toString(), tempLengths));
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
    
    /**
     *
     * @param patientId
     * @return patientsAppointmentsArrayLstEntity
     */
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
                            

                    appointmentEntity.setStartTime(LocalTime.ofSecondOfDay(Integer.parseInt(patientsAppointmentsArray[6])).toString()); 
                    appointmentEntity.setEndTime(LocalTime.ofSecondOfDay(Integer.parseInt(patientsAppointmentsArray[7])).toString()); 
                
                    appointmentEntity.setUniqueAppointmentId(Integer.parseInt(patientsAppointmentsArray[0]));
                    appointmentEntity.setDuration(Integer.parseInt(patientsAppointmentsArray[1]));
                    appointmentEntity.setCharge(Double.parseDouble(patientsAppointmentsArray[3]));
                    appointmentEntity.setDateStr(patientsAppointmentsArray[4]);
                    appointmentEntity.setDatePaidStr(patientsAppointmentsArray[5]);
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
    
    public void createAppointment(AppointmentEntity appointment, ArrayList<String> chosenSlots){   
        try {
            

            
            int appointmentEndTime = Integer.parseInt(appointment.getEndTime()) + 600;
            
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewAppointment), appointment.getDuration(), appointment.getNotes(), appointment.getCharge(), Date.valueOf(appointment.getDateStr()), 
                 Integer.parseInt(appointment.getStartTime()), appointmentEndTime, appointment.getPatientId(), appointment.getEmployeeId(), appointment.getType(), 1);

         } catch (Exception e) {
            e.printStackTrace();
            String test = e.toString();
        }    
        ArrayList<AppointmentSlotsEntity> timeSlots = fetchAvaialbleAppointmentsForPractitioner(appointment.getEmployeeId(), appointment.getDateStr());
            
        try {
            int slotId = 0;
            for (AppointmentSlotsEntity times : timeSlots) {
                if (times.getStartTime().equals(appointment.getStartTime())) {
                   slotId = times.getAppointmentSlotId();
                   Date date = Date.valueOf(appointment.getDateStr());
                   int EmployeeId = appointment.getEmployeeId();
                   ArrayList agnosticQuery = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewEmployeeAppointmentSlot), slotId, EmployeeId, date);
                }
            }
            } catch (Exception e) {
            e.printStackTrace();
            String test = e.toString();
        }
            
          
    }
    
    public String cancelAppointment(int idToCancel){
        String result; 
        try {
            
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.cancelAppointment), idToCancel);
            result = "Cancelled";
        }catch (Exception e) {
            e.printStackTrace();
            String test = e.toString();
            result = "Error";
        }
        return result;
    }
    
    public void UpdateAppointment(ArrayList params,DynamicDao dynamicDao){

        ArrayList result = new ArrayList();
        try {
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.newPrescription), params.get(0), params.get(1), params.get(2));
            result = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateAppointment), params.get(3), result.get(0), params.get(4));
        } catch (Exception e) {
        }

    }
    
    public void sendSMSReminder() throws IOException{
        String myURI = "https://api.bulksms.com/v1/messages";

        String myUsername = "robertbarclay1";
        String myPassword = "robert!23";

        String myData = "{to: \"+447928582641\", body: \"Hello Robert. This is a reminder that you have an appointment at 14:00 on the 21/01/2021 with Dr Best. Thanks, The SmartCare Team.\"}";

        // if your message does not contain unicode, the "encoding" is not required:
        // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

        // build the request based on the supplied settings
        URL url = new URL(myURI);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);

        // supply the credentials
        String authStr = myUsername + ":" + myPassword;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        // we want to use HTTP POST
        request.setRequestMethod("POST");
        request.setRequestProperty( "Content-Type", "application/json");

        // write the data to the request
        OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
        out.write(myData);
        out.close();

        // try ... catch to handle errors nicely
        try {
            // make the call to the API
            InputStream response = request.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(response));
            String replyText;
            while ((replyText = in.readLine()) != null) {
              System.out.println(replyText);
            }
            in.close();
        } catch (IOException ex) {
            System.out.println("An error occurred:" + ex.getMessage());
            BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            // print the detail that comes with the error
            String replyText;
            while ((replyText = in.readLine()) != null) {
              System.out.println(replyText);
            }
            in.close();
        }
            request.disconnect();
    }

    

}
