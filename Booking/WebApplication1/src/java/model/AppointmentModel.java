/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import dao.DynamicDao;
import dao.StoredData;
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
    private StoredData storedStatements = new StoredData();
                
public ArrayList<String[]> retrieveAvaialbleAppointmentsForDoctor(Integer doctor_id, String Date, DynamicDao dynamicDao ){   
    //get all free appointments for chosen doctor
    ArrayList doctor_appointments = new ArrayList();
        try {
            
            doctor_appointments = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctor_id, Date);
        
        } catch (Exception e) {
            try {
                doctor_appointments = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getAllPossibleAppointments));
            } catch (Exception p) {
                doctor_appointments.add("Somthing is very very veeeery DEEPLY wrong time to start crying the slots type table does not exist");
            }
            
        }
   
        return doctor_appointments;
}
public void CreateAppointment(ArrayList params, DynamicDao dynamicDao ){   
        try {
            
           dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewAppointment), params.get(0), params.get(1),params.get(2),params.get(3),params.get(4),params.get(5),params.get(6),params.get(7),params.get(8),params.get(9),params.get(10));
           dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.NewEmployeeAppointmentSlot), params.get(0), params.get(1),params.get(2));

        } catch (Exception e) {

            
        }
}

public void getDailyAppointment(){
        
    }
    
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
