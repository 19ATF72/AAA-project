/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

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
import java.util.Base64;
import model.Dao.DynamicDao;
import model.Entity.AppointmentEntity;
import model.Entity.EmployeeEntity;
import model.Entity.UserEntity;
import model.Helper.StoredProcedures;

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
    
    public ArrayList<String[]> retrieveAvaialbleAppointmentsForDoctor(int doctorId, String date){   
        ArrayList doctor_appointments = new ArrayList();
        try {
            doctor_appointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctorId, date);
            if (doctor_appointments.size() == 0) {
                try {
                    doctor_appointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getAllPossibleAppointments));
                } catch (Exception p) {
                    doctor_appointments.add("Somthing is very very veeeery DEEPLY wrong time to start crying the slots type table does not exist");
                }
            }
        } catch (Exception e) {           
        }
        return doctor_appointments;
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
                    
                    appointmentEntity.setUniqueAppointmentId(Integer.parseInt(patientsAppointmentsArray[0]));
                    appointmentEntity.setDuration(patientsAppointmentsArray[1]);
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
    
    /**
     *
     * @param params
     */
    public void CreateAppointment(ArrayList params){   
        try {
            ArrayList slot_ids = (ArrayList)params.get(9);                                  
            dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewAppointment), params.get(0), params.get(1),params.get(2),params.get(3),params.get(4),params.get(5),params.get(6),params.get(7),params.get(8));
            for (int slot = 0; slot < slot_ids.size(); slot++) {
                           dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.NewEmployeeAppointmentSlot), slot_ids.get(slot), params.get(7),params.get(3));
            }
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
