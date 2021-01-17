/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.util.ArrayList;
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
                    appointmentEntity.setNotes(patientsAppointmentsArray[2]);
                    appointmentEntity.setCharge(Double.parseDouble(patientsAppointmentsArray[3]));
                    appointmentEntity.setDateStr(patientsAppointmentsArray[4]);
                    appointmentEntity.setDatePaidStr(patientsAppointmentsArray[5]);
                    appointmentEntity.setStartTime(patientsAppointmentsArray[6]);
                    appointmentEntity.setEndTime(patientsAppointmentsArray[7]);
                    appointmentEntity.setPatientId(Integer.parseInt(patientsAppointmentsArray[8]));
                    appointmentEntity.setEmployeeId(Integer.parseInt(patientsAppointmentsArray[9]));
                    appointmentEntity.setType(Integer.parseInt(patientsAppointmentsArray[10]));
                    appointmentEntity.setPerscriptionId(Integer.parseInt(patientsAppointmentsArray[11]));
                    appointmentEntity.setStatus(Integer.parseInt(patientsAppointmentsArray[12])); 
                    
                    EmployeeEntity tempEmployee = employeeService.fetchEmployee_EId(appointmentEntity.getEmployeeId()); 
                    UserEntity tempUser = userService.getUser(tempEmployee.getUniqueUserId());
                           
                    appointmentEntity.setDoctorsName(tempUser.getUserName());

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

}
