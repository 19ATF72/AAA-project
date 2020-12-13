/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;
import dao.StoredStatements;
import java.util.ArrayList;

/**
 *
 * @author atf1972
 */
public class ListModel {
    private StoredStatements storedStatements = new StoredStatements();   
    private String type; //TODO possibly change..   
    private String start_date;
    private String end_date;
    
    public ListModel(){}

    public ArrayList getPatientsByType(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnostic_retrieve(storedStatements.sqlQueryMap.get(StoredStatements.SqlQueryEnum.getPatientsByType), params.get(0));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getPatients(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnostic_retrieve(storedStatements.sqlQueryMap.get(StoredStatements.SqlQueryEnum.getPatientsByType));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }    
}


