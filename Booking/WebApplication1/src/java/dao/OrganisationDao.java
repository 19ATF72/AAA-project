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
        
        String sql = "SELECT * FROM organisation";
       
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql); 
        
        while (resultSet.next()){
           String name = resultSet.getString("name");
           String orgType = resultSet.getString("orgnaistion_type_oid");
           String address = resultSet.getString("address");
           String postcode = resultSet.getString("postcode");
           String phoneNum = resultSet.getString("phoneNum");     
           
           OrganisationEntity organisation = new OrganisationEntity(name, orgType, address, postcode, phoneNum); 
           organisationList.add(organisation); 
        }
        resultSet.close();
        statement.close();   
        
        return organisationList;    
    }
    
    public void insertOrganisation(OrganisationEntity organisation) throws SQLException {
        sd = new StoredData();
       
        agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.insertOrganisation),  organisation.getName(), "1", 
                organisation.getAddress(), organisation.getPostcode(), organisation.getPhoneNum());
    }
    
    public void deleteOrganisation(OrganisationEntity organisation) throws SQLException {
        
        agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.deleteOrganisation), organisation.getName());
        
        //need to turn to bool 
        //return rowDeleted;     
    }
    
    public OrganisationEntity getOrganisation(String name) throws SQLException {
        OrganisationEntity organisation = null;
        
        sd = new StoredData();
       
        ArrayList<String[]> result = agnostic_query(sd.sqlQueryMap.get(SqlQueryEnum.getOrganisation), name);
        String[] orgString = result.get(0);
        
        organisation = new OrganisationEntity(orgString[1], orgString[2], orgString[3], orgString[4], orgString[5]);
        
        return organisation;
    }
    
    
    
}
