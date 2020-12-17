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
    
    public ArrayList getPatientsBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatientsBetweenDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getPatientsByTypeBetweenDates(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getPatientsByTypeBetweenDates), Integer.parseInt(params.get(0)), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }

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
        
    public ArrayList getInvoicesBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getInvoicesBetweenDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getInvoicesByTypeBetweenDates(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getInvoicesByTypeBetweenDates), Integer.parseInt(params.get(0)), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }

    public ArrayList getInvoicesByType(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getInvoicesByType), Integer.parseInt(params.get(0)));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getInvoices(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getInvoices));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getDoctorsForDisplay(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnostic_query(storedStatements.sqlQueryMap.get(StoredData.SqlQueryEnum.getAllEmployeesNamesTypesAndIds));
        } catch (Exception e) {
          result.add("Could not retrieve doctors.");
        }
        return result;
    } 
    
}


