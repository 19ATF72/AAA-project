/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Service;

import model.Helper.StoredProcedures;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Entity.OrganisationEntity;
import model.Helper.Enums.SqlQueryEnum;
import model.Dao.DynamicDao;


/**
 *
 * @author rob
 */
public class OrganisationService{
    
    protected DynamicDao dynamicDao;
    
    public OrganisationService(DynamicDao dynamicDao){
        this.dynamicDao = dynamicDao;
    }
    
    StoredProcedures sp;
    
    public ArrayList<OrganisationEntity> listAllOrganisations() throws SQLException {
        ArrayList<OrganisationEntity> organisationList = new ArrayList<>();
        sp = new StoredProcedures();

        ArrayList<String[]> result = dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.getOrganisation), "");
        
        for(int i=0; i<result.size(); i++)
        {
            String[] tempOrganisationStringArray = result.get(i);
            OrganisationEntity organisation = new OrganisationEntity(tempOrganisationStringArray[0], tempOrganisationStringArray[1], tempOrganisationStringArray[2],
                    tempOrganisationStringArray[3], tempOrganisationStringArray[4], tempOrganisationStringArray[5]);
            organisationList.add(organisation); 
        }
         
        return organisationList;    
    }
    
    public void insertOrganisation(OrganisationEntity organisation) throws SQLException {
        sp = new StoredProcedures();
       
        dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.insertOrganisation),  organisation.getName(), organisation.getOrgType(), 
                organisation.getAddress(), organisation.getPostcode(), organisation.getPhoneNum());
    }
    
    public void deleteOrganisation(OrganisationEntity organisation) throws SQLException {
        
        dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.deleteOrganisation), organisation.getOId());
        
        //need to turn to bool 
        //return rowDeleted;     
    }
    
    public OrganisationEntity getOrganisation(String oId) throws SQLException {
        OrganisationEntity organisation = null;
        
        sp = new StoredProcedures();
       
        ArrayList<String[]> result = dynamicDao.agnosticQuery(sp.sqlQueryMap.get(SqlQueryEnum.getOrganisation), oId);
        String[] orgString = result.get(0);
        
        organisation = new OrganisationEntity(orgString[0], orgString[1], orgString[2], orgString[3], orgString[4], orgString[5]);
        
        return organisation;
    }
    
    
    
}
