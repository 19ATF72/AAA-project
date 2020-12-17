/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author rob
 */
public interface PatientModelInterface {
    
    public void setAddress(String address);
    public String getAddress();
    
    public void setCurrentPrescriptions(String[] currentPrescriptions);
    public String[] getCurrentPrescriptions();
}
