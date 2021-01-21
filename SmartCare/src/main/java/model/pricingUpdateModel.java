/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;

/**
 *
 * @author Rodrigo
 */
public class pricingUpdateModel {
    private StoredProcedures storedStatements = new StoredProcedures();   
    public ArrayList updateDoctorBaseSalary(DynamicDao dynamicDao, Double newSalary){
        ArrayList result = new ArrayList();
        try {
          dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateDoctorBaseSalary),newSalary);
        } catch (Exception e) {
          result.add("Could not update doctor base salary.");
        }
        return result;
    }
    public ArrayList updateNurseBaseSalary(DynamicDao dynamicDao, Double newSalary){
        ArrayList result = new ArrayList();
        try {
          dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updateNurseBaseSalary),newSalary);
        } catch (Exception e) {
          result.add("Could not update nurses base salary.");
        }
        return result;
    }
    public ArrayList updatePatientCostSalary(DynamicDao dynamicDao, Double newCost){
        ArrayList result = new ArrayList();
        try {
          dynamicDao.agnosticQuery(storedStatements.sqlQueryMap.get(StoredProcedures.SqlQueryEnum.updatePatientCost),newCost);
        } catch (Exception e) {
          result.add("Could not update patients charge rate.");
        }
        return result;
    }
}
