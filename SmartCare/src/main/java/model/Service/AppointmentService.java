/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import java.util.ArrayList;
import model.Dao.DynamicDao;
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
    
    public ArrayList<String[]> retrieveAvaialbleAppointmentsForDoctor(Integer doctor_id, String Date){   
        //get all free appointments for chosen doctor
        ArrayList doctor_appointments = new ArrayList();
        try {
            doctor_appointments = dynamicDao.agnosticQuery(storedProcedures.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getEmployeeFreeAppointmentsInDay), doctor_id, Date);
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
