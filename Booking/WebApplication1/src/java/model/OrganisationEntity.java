/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.DynamicDao;

/**
 *
 * @author rob
 */
public class OrganisationEntity {
    
    private String name;
    private String orgType;
    private String address;
    private String postcode;
    private String phoneNum;    
       
    public OrganisationEntity(String name, String orgType, String address, String postcode, String phoneNum){
        this.name = name;
        this.orgType = orgType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNum = phoneNum; 
    }
    
    public OrganisationEntity(String name){
        this.name = name;
    }
     
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
   
}
