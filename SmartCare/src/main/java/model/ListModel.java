/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.util.ArrayList;

/**
 *
 * @author atf1972
 */
public class ListModel {
    private StoredProcedures storedStatements = new StoredProcedures();   
    private String type; //TODO possibly change..   
    private String start_date;
    private String end_date;
    
    public ListModel(){}
    
    public ArrayList getPatientsBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientsBetweenDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getPatientsByTypeBetweenDates(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientsByTypeBetweenDates), Integer.parseInt(params.get(0)), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }

    public ArrayList getPatientsByType(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatientsByType), Integer.parseInt(params.get(0)));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getPatients(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getPatients));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }   
        
    public ArrayList getInvoicesBetweenDates(ArrayList params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getInvoicesBetweenDates), params.get(0), params.get(1));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getInvoicesByTypeBetweenDates(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getInvoicesByTypeBetweenDates), Integer.parseInt(params.get(0)), params.get(1), params.get(2));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }

    public ArrayList getInvoicesByType(ArrayList<String> params, DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getInvoicesByType), Integer.parseInt(params.get(0)));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getInvoices(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getInvoices));
        } catch (Exception e) {
          result.add("Could not retrieve patients with that type.");
        }
        return result;
    }
    
    public ArrayList getDoctorsForDisplay(DynamicDao dynamicDao){
        ArrayList result = new ArrayList();
        try {  
          result = dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.getAllEmployeesNamesTypesAndIds));
        } catch (Exception e) {
          result.add("Could not retrieve doctors.");
        }
        return result;
    } 
    
}


