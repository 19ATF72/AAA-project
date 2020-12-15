/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.EnumMap;

/**
 *
 * @author rob
 */

public class StoredData {
    
    public static final int pending =  1;
    public static final int approved = 2;
    public static final int blocked =  3;
    
    public enum SqlQueryEnum{
        fetchAppointment,
        deleteUser,
        LoginUser,
        NewUser,
        CheckForUsername,
        NewEmployee,
        NewPatient,
        GetOrganizationByName,
        getPatientAppointments,
        getEmployeeAppointments,
        getPatient,
        getEmployee,
        NewAppointment,
        getAllPossibleAppointments,
        getEmployeeAppointmentsInDay,
        getEmployeeFreeAppointmentsInDay,
        NewEmployeeAppointmentSlot,
        getEmployeeSalary,
    }
    
    public EnumMap<SqlQueryEnum, String> sqlQueryMap = new EnumMap<>(SqlQueryEnum.class);
    
    public StoredData() {
        sqlQueryMap.put(SqlQueryEnum.fetchAppointment, "Test");
        sqlQueryMap.put(SqlQueryEnum.deleteUser, "DELETE FROM users WHERE mail=?");
        sqlQueryMap.put(SqlQueryEnum.LoginUser, "SELECT * FROM users WHERE email=? AND pass=?");
        sqlQueryMap.put(SqlQueryEnum.NewUser, "INSERT INTO users ( username, pass, email, created, last_access, logged_in, picture, user_status_usid ) VALUES (?,?,?,?,?,?,?,?)");
        sqlQueryMap.put(SqlQueryEnum.CheckForUsername, "SELECT name FROM users WHERE name=?");
        sqlQueryMap.put(SqlQueryEnum.NewEmployee, "INSERT INTO employee ( salary, address, employee_type_tid, organization_oid, users_uuid ) VALUES (?,?,?,?,?)");
        sqlQueryMap.put(SqlQueryEnum.NewPatient, "INSERT INTO patient ( address, patient_type_ptid, users_uuid ) VALUES ( ?,?,? )");
        sqlQueryMap.put(SqlQueryEnum.GetOrganizationByName, "SELECT oid FROM organization WHERE name=? LIMIT 1");
        sqlQueryMap.put(SqlQueryEnum.getPatientAppointments, "SELECT * FROM appointment WHERE patient_pid=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeAppointmentsInDay, "SELECT * FROM appointment WHERE employee_eid=? AND date=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeFreeAppointmentsInDay, "SELECT * FROM employee_has_appointment_slots WHERE employee_eid=? AND date=?");
        sqlQueryMap.put(SqlQueryEnum.getAllPossibleAppointments, "SELECT * FROM timeslots");
        sqlQueryMap.put(SqlQueryEnum.getPatient, "SELECT * FROM patient WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployee, "SELECT * FROM employee WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.NewAppointment, "INSERT INTO appointment (duration, notes, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, patient_prescriptions_prid, appointment_status_asid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeSalary, "SELECT salary FROM employee WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.NewEmployeeAppointmentSlot, "INSERT INTO employee_has_appointment_slots (employee_eid, date) VALUES (?, ?)");

    }   
}
