/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;

/**
 *
 * @author rob
 */
public class OrganisationEntity {
    
    protected  int oId; 
    protected  String name;
    protected  String orgType;
    protected  String address;
    protected  String postcode;
    protected  String phoneNum;    
    
    public OrganisationEntity(int oId, String name, String orgType, String address, String postcode, String phoneNum){
        this.oId = oId;
        this.name = name;
        this.orgType = orgType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNum = phoneNum; 
    }
       
    public OrganisationEntity(String name, String orgType, String address, String postcode, String phoneNum){
        this.name = name;
        this.orgType = orgType;
        this.address = address;
        this.postcode = postcode;
        this.phoneNum = phoneNum; 
    }
   
    public OrganisationEntity(int oid){
        this.oId = oid;
    }
     
    public int getOId() {
        return oId;
    }

    public void setOId(int oid){
        this.oId = oId;
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
