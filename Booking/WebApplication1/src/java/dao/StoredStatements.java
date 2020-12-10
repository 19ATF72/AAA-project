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

public class StoredStatements {
    
    public enum SqlQueryEnum{
        fetchAppointment,
        deleteUser
    }
    
    public EnumMap<SqlQueryEnum, String> sqlQueryMap = new EnumMap<>(SqlQueryEnum.class);

    public StoredStatements() {
        sqlQueryMap.put(SqlQueryEnum.fetchAppointment, "Test");
        sqlQueryMap.put(SqlQueryEnum.deleteUser, "DELETE FROM Users WHERE username =");
    }
   
    
    
}
