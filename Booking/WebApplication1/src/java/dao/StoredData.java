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
        getPatient,
        getPatientsByType,
        getPatients,
        getAppointment,
        getEmployee,
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
        sqlQueryMap.put(SqlQueryEnum.getAppointment, "SELECT * FROM appointment WHERE patient_pid=?");
        sqlQueryMap.put(SqlQueryEnum.getPatientsByType, "SELECT * FROM patient WHERE patient_type_ptid = ? LIMIT 10");
        sqlQueryMap.put(SqlQueryEnum.getPatients, "SELECT * FROM patient LIMIT 10");
        sqlQueryMap.put(SqlQueryEnum.getPatient, "SELECT * FROM patient WHERE users_uuid=?");
        sqlQueryMap.put(SqlQueryEnum.getEmployee, "SELECT * FROM employee WHERE users_uuid=?");
        
    }   
}
