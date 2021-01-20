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
public class AppointmentSlotsEntity {
    
    protected int appointmentSlotId;
    protected String startTime;
    protected String endTime;

    public AppointmentSlotsEntity(int appointmentSlotId, String startTime, String endTime) {
        this.appointmentSlotId = appointmentSlotId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getAppointmentSlotId() {
        return appointmentSlotId;
    }

    public void setAppointmentSlotId(int appointmentSlotId) {
        this.appointmentSlotId = appointmentSlotId;
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
    
}
