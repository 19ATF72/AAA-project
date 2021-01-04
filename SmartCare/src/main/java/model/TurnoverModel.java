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
public class TurnoverModel {
    private StoredData storedStatements = new StoredData();   
    private String type; //TODO possibly change..   
    private String start_date;
    private String end_date;
    
    public TurnoverModel(){}
    
    public ArrayList getTurnoverByDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getTurnoverByDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getIncomeByDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getIncomeByDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getOutgoingsByDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getOutgoingsByDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
        
    public ArrayList getTurnoverByTypeBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getTurnoverByTypeBetweenDates), (String)params.get(0), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getIncomeByTypeBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getIncomeByTypeBetweenDates), (String)params.get(0), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getOutgoingsByTypeBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getOutgoingsByTypeBetweenDates), (String)params.get(0), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
}


