/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;
import dao.StoredData;
import java.util.ArrayList;

/**
 *
 * @author atf1972
 */
public class ListModel {
    private StoredData storedStatements = new StoredData();   
    private String type; //TODO possibly change..   
    private String start_date;
    private String end_date;
    
    public ListModel(){}

    public ArrayList getPatientsByType(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatientsByType), Integer.parseInt(params.get(0)));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getPatients(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatients));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }    
}


