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
        getPatientsByType,
        getPatients,
        getPatientsByTypeBetweenDates,
        getPatientsBetweenDates,
        getAppointment,
        getEmployee,
        getAllEmployees,
        getEmployeeAppointments,
        getEmployeeAppointmentsInDay,
        getEmployeeFreeAppointmentsInDay,
        getAllEmployeesNamesTypesAndIds,
        getEmployeeSalary,
        NewEmployeeAppointmentSlot,
        getPatientAppointments,
        getPatient,
        NewAppointment,
        getAllPossibleAppointments,
        getInvoicesByType,
        getInvoices,
        getInvoicesByTypeBetweenDates,
        getInvoicesBetweenDates,
        getPatientDisplayableAppointments,

    }
    
    public EnumMap<SqlQueryEnum, String> sqlQueryMap = new EnumMap<>(SqlQueryEnum.class);
    
    public StoredData() {
        sqlQueryMap.put(SqlQueryEnum.fetchAppointment, "Test");
        sqlQueryMap.put(SqlQueryEnum.deleteUser, "DELETE FROM users WHERE mail=?");
        sqlQueryMap.put(SqlQueryEnum.LoginUser, "SELECT * FROM users WHERE email=? AND pass=?");
        sqlQueryMap.put(SqlQueryEnum.NewUser, "INSERT INTO users ( username, pass, email, created, last_access, logged_in, picture, user_status_usid ) VALUES (?,?,?,?,?,?,?,?)");
        sqlQueryMap.put(SqlQueryEnum.CheckForUsername, "SELECT name FROM users WHERE name=?");
        //Employee calls
        sqlQueryMap.put(SqlQueryEnum.NewEmployee, "INSERT INTO employee ( salary, address, employee_type_tid, organization_oid, users_uuid ) VALUES (?,?,?,?,?)");
        sqlQueryMap.put(SqlQueryEnum.getEmployee, "SELECT * FROM employee WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.getAllEmployees, "SELECT * FROM employee");
        sqlQueryMap.put(SqlQueryEnum.getAllEmployeesNamesTypesAndIds, "SELECT e.eid, et.type_name, u.username FROM users u INNER JOIN employee e ON e.users_uuid = u.uuid INNER JOIN employee_type et ON e.EMPLOYEE_TYPE_TID = et.etid");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeSalary, "SELECT salary FROM employee WHERE eid=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeAppointmentsInDay, "SELECT * FROM appointment WHERE employee_eid=? AND date=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeFreeAppointmentsInDay, "SELECT * FROM employee_has_appointment_slots WHERE employee_eid=? AND date=?");
        sqlQueryMap.put(SqlQueryEnum.NewEmployeeAppointmentSlot, "INSERT INTO employee_has_appointment_slots (employee_eid, date) VALUES (?, ?)");
        sqlQueryMap.put(SqlQueryEnum.NewPatient, "INSERT INTO patient ( address, patient_type_ptid, users_uuid ) VALUES ( ?,?,? )");
        sqlQueryMap.put(SqlQueryEnum.GetOrganizationByName, "SELECT oid FROM organization WHERE name=? FETCH FIRST 1 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getAppointment, "SELECT * FROM appointment WHERE patient_pid=?");
        sqlQueryMap.put(SqlQueryEnum.getPatientsByType, "SELECT u.uuid,u.username,p.address,pt.type_name,u.created FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID WHERE p.patient_type_ptid = ? FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getPatients, "SELECT u.uuid,u.username,p.address,pt.type_name,u.created FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getPatientsByTypeBetweenDates, "SELECT u.uuid, u.username, p.address, pt.type_name, u.created FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID WHERE pt.PTID = ? AND (u.created BETWEEN ? AND ?)  FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getPatientsBetweenDates, "SELECT u.uuid, u.username, p.address, pt.type_name, u.created FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID WHERE (u.created BETWEEN ? AND ?) FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getPatientAppointments, "SELECT * FROM appointment WHERE patient_pid=?");
        sqlQueryMap.put(SqlQueryEnum.getPatientDisplayableAppointments, "SELECT duration,notes,charge,date,start_time,end_time,appointment_status_asid FROM appointment WHERE patient_pid=?");
        

        sqlQueryMap.put(SqlQueryEnum.getAllPossibleAppointments, "SELECT * FROM appointment_slots");
        sqlQueryMap.put(SqlQueryEnum.getPatient, "SELECT * FROM patient WHERE users_uuid=?");
        
        sqlQueryMap.put(SqlQueryEnum.NewAppointment, "INSERT INTO appointment (duration, charge, date, start_time, end_time, patient_pid, employee_eid, appointment_type_atid, appointment_status_asid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        sqlQueryMap.put(SqlQueryEnum.getEmployeeSalary, "SELECT salary FROM employee WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.NewEmployeeAppointmentSlot, "INSERT INTO employee_has_appointment_slots (employee_eid, date) VALUES (?, ?)");
        sqlQueryMap.put(SqlQueryEnum.getInvoicesByType, "SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.duration,a.date,a.charge,aps.appointment_status FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID INNER JOIN appointment a ON p.PID = a.PATIENT_PID INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid WHERE aps.asid = 4 AND p.PATIENT_TYPE_PTID = ? FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getInvoices, "SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.duration,a.date,a.charge,aps.appointment_status FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID INNER JOIN appointment a ON p.PID = a.PATIENT_PID INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid WHERE aps.asid = 4 FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getInvoicesByTypeBetweenDates, "SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.duration,a.date,a.charge,aps.appointment_status FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID INNER JOIN appointment a ON p.PID = a.PATIENT_PID INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid WHERE aps.asid = 4 AND p.PATIENT_TYPE_PTID = ? AND (a.date BETWEEN ? AND ?) FETCH FIRST 10 ROWS ONLY");
        sqlQueryMap.put(SqlQueryEnum.getInvoicesBetweenDates, "SELECT u.uuid,u.username,p.address,pt.type_name,a.aid,a.duration,a.date,a.charge,aps.appointment_status FROM patient p INNER JOIN patient_type pt ON p.patient_type_ptid = pt.ptid INNER JOIN users u ON p.USERS_UUID = u.UUID INNER JOIN appointment a ON p.PID = a.PATIENT_PID INNER JOIN appointment_status aps ON a.APPOINTMENT_STATUS_ASID = aps.asid WHERE aps.asid = 4 AND (a.date BETWEEN ? AND ?) FETCH FIRST 10 ROWS ONLY");
        


    }   
}
