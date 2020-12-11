/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.ArrayList;
import java.util.HashMap; 
import java.util.UUID;

/**
 *
 * @author rob
 */
public class AppointmentModel implements EmployeeModelInterface, PatientModelInterface {
    
    private EmployeeModel employeeModel;
    private PatientModel patientModel;
    
    
    public AppointmentModel(EmployeeModel newEmployeeModel, PatientModel newPatientModel){
        employeeModel = newEmployeeModel;
        patientModel = newPatientModel;
    }
    
    public AppointmentModel(EmployeeModel newEmployeeModel){
        employeeModel = newEmployeeModel;
    }
    
    public AppointmentModel(PatientModel newPatientModel){
        patientModel = newPatientModel;
    }
    
    private UUID uniqueAppointmentId;
    private String appointmentDateTime; 
    private String appointmentDescription; 
    private String doctorsName;
    
    
    HashMap<UUID, String> bookedAppointments = new HashMap<UUID, String>();
    ArrayList<String> availableAppointments = new ArrayList<String>();
    
    public AppointmentModel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
 
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
    
    @Override
    public String getCalander() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Role getemploymentRole() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAddress(String address) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAddress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCurrentPrescriptions(String[] currentPrescriptions) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getCurrentPrescriptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBookedAppointments(String[] bookedAppointments) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
