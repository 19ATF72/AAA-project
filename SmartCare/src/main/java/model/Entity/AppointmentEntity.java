/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Entity;
import model.Entity.EmployeeEntity;
import model.Entity.Role;
import model.Entity.PatientEntity;
import model.Dao.DynamicDao;
import model.Helper.StoredProcedures;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap; 
import model.PatientModelInterface;

/**
 *
 * @author rob
 */
public class AppointmentEntity {
    
    protected int uniqueAppointmentId;
    protected String doctorsName;
    protected String duration; 
    protected String notes; 
    protected Double charge;
    protected String dateStr;
    protected String datePaidStr;
    protected String startTime;
    protected String endTime;
    protected int patientId; 
    protected int employeeId; 
    protected int type;
    protected int perscriptionId; 
    protected int status;

    public AppointmentEntity() {
    }

    public AppointmentEntity(int uniqueAppointmentId, String duration, String notes, Double charge, String dateStr, String datePaidStr, String startTime, 
            String endTime, int patientId, int employeeId, int type, int perscriptionId, int status) {
        this.uniqueAppointmentId = uniqueAppointmentId;
        this.duration = duration;
        this.notes = notes;
        this.charge = charge;
        this.dateStr = dateStr;
        this.datePaidStr = datePaidStr;
        this.startTime = startTime;
        this.endTime = endTime;
        this.patientId = patientId;
        this.employeeId = employeeId;
        this.type = type;
        this.perscriptionId = perscriptionId;
        this.status = status;
    }

    public int getUniqueAppointmentId() {
        return uniqueAppointmentId;
    }

    public void setUniqueAppointmentId(int uniqueAppointmentId) {
        this.uniqueAppointmentId = uniqueAppointmentId;
    }

    public String getDatePaidStr() {
        return datePaidStr;
    }

    public void setDatePaidStr(String datePaidStr) {
        this.datePaidStr = datePaidStr;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int emplyoeeId) {
        this.employeeId = emplyoeeId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPerscriptionId() {
        return perscriptionId;
    }

    public void setPerscriptionId(int perscriptionId) {
        this.perscriptionId = perscriptionId;
    }
    

//public AppointmentEntity() {}

//
//public void getDailyAppointment(){
//        
//    }
//    
//    public AppointmentEntity(EmployeeEntity newEmployeeModel, PatientEntity newPatientModel){
//        employeeModel = newEmployeeModel;
//        patientModel = newPatientModel;
//    }
//    
//    public AppointmentEntity(EmployeeEntity newEmployeeModel){
//        employeeModel = newEmployeeModel;
//    }
//    
//    public AppointmentEntity(PatientEntity newPatientModel){
//        patientModel = newPatientModel;
//    }


    public String getDoctorsName() {
        return doctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        this.doctorsName = doctorsName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getCharge() {
        return charge;
    }

    public void setCharge(Double charge) {
        this.charge = charge;
    }

    public String getDateStr() {
        return dateStr;
    }

    public void setDateStr(String dateStr) {
        this.dateStr = dateStr;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
