/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;
import model.Entity.EmployeeEntity;
import model.Entity.Role;
import model.Entity.PatientEntity;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap; 
import java.util.UUID;
import model.PatientModelInterface;

/**
 *
 * @author rob
 */
public class AppointmentEntity {
    
    private EmployeeEntity employeeModel;
    private PatientEntity patientModel;
    private StoredProcedures storedStatements = new StoredProcedures();

public AppointmentEntity() {}


public void getDailyAppointment(){
        
    }
    
    public AppointmentEntity(EmployeeEntity newEmployeeModel, PatientEntity newPatientModel){
        employeeModel = newEmployeeModel;
        patientModel = newPatientModel;
    }
    
    public AppointmentEntity(EmployeeEntity newEmployeeModel){
        employeeModel = newEmployeeModel;
    }
    
    public AppointmentEntity(PatientEntity newPatientModel){
        patientModel = newPatientModel;
    }
    
    private UUID uniqueAppointmentId;
    private String appointmentDateTime; 
    private String appointmentDescription; 
    private String doctorsName;
    
    
    HashMap<UUID, String> bookedAppointments = new HashMap<UUID, String>();
    ArrayList<String> availableAppointments = new ArrayList<String>();
    

    
 
    public void setUniqueAppointmentId(){
        UUID uuid = UUID.randomUUID();
        this.uniqueAppointmentId = uuid;
    }
    
    public UUID getUniqueAppointmentId(){
        return uniqueAppointmentId; 
    }
    
    public String getAppointmentDateTime(){
        return appointmentDateTime; 
    }
    
    public void setAppointmentDateTime(String dateTime){
        this.appointmentDateTime = appointmentDateTime;
    }
    
    public String getAppointmentDescription(){
        return appointmentDescription; 
    }
    
    public void setAppointmentDescription(String dateTime){
        this.appointmentDescription = appointmentDescription;
    }
    
    
   public void LocalsetBookedAppointments(HashMap<UUID, String> bookedAppointments){
        this.bookedAppointments = bookedAppointments;
   }
  
    public HashMap<UUID, String> getBookedAppointments(){
        return bookedAppointments; 
    }
    
    public void setAvailableAppointments(ArrayList<String> availableAppointments){
        this.availableAppointments = availableAppointments;
    }
  
    public ArrayList<String> getAvailableAppointments(){
        return availableAppointments; 
    }
    
  

}
