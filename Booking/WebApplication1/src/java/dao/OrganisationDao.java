/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.OrganisationEntity;
import dao.StoredData.SqlQueryEnum;


/**
 *
 * @author rob
 */
public class OrganisationDao extends DynamicDao{
    
    StoredData sd;
    
    public ArrayList<OrganisationEntity> listAllOrganisations() throws SQLException {
        ArrayList<OrganisationEntity> organisationList = new ArrayList<>();
        sd = new StoredData();

        ArrayList<String[]> result = agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.getOrganisation), "");
        
        for(int i=0; i<result.size(); i++)
        {
            String[] tempOrganisationString = result.get(i);
            OrganisationEntity organisation = new OrganisationEntity(tempOrganisationString[0], tempOrganisationString[1], tempOrganisationString[2],
                    tempOrganisationString[3], tempOrganisationString[4], tempOrganisationString[5]);
            organisationList.add(organisation); 
        }
         
        return organisationList;    
    }
    
    public void insertOrganisation(OrganisationEntity organisation) throws SQLException {
        sd = new StoredData();
       
        agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.insertOrganisation),  organisation.getName(), organisation.getOrgType(), 
                organisation.getAddress(), organisation.getPostcode(), organisation.getPhoneNum());
    }
    
    public void deleteOrganisation(OrganisationEntity organisation) throws SQLException {
        
        agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.deleteOrganisation), organisation.getOId());
        
        //need to turn to bool 
        //return rowDeleted;     
    }
    
    public OrganisationEntity getOrganisation(String oId) throws SQLException {
        OrganisationEntity organisation = null;
        
        sd = new StoredData();
       
        ArrayList<String[]> result = agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.getOrganisation), oId);
        String[] orgString = result.get(0);
        
        organisation = new OrganisationEntity(orgString[0], orgString[1], orgString[2], orgString[3], orgString[4], orgString[5]);
        
        return organisation;
    }
    
    
    
}
