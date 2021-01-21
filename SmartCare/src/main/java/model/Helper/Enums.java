/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Helper;

/**
 *
 * @author rob
 */
public class Enums { 
    
    public static final int PENDING =  1;
    public static final int APPROVED = 2;
    public static final int BLOCKED =  3;
    
    public enum SqlQueryEnum{
        fetchAppointment,
        deleteUser,
        LoginUser,
        GetUser,
        NewUser,
        CheckForUsername,
        NewEmployee,
        NewPatient,
        GetOrganizationByName,
        getPatientsByType,
        getPatients,
        getPatientsByTypeBetweenDates,
        getPatientsBetweenDates,
        getAppointment,
        getEmployee_Eid,
        getEmployee_Uuid,
        getAllEmployees,
        getEmployeeAppointments,
        getEmployeeAppointmentsInDay,
        getEmployeeFreeAppointmentsInDay,
        getAllEmployeesNamesTypesAndIds,
        getEmployeeSalary,
        NewEmployeeAppointmentSlot,
        getPatientAppointments,
        getPatient_Uuid,
        NewAppointment,
        getAllPossibleAppointments,
        getInvoicesByType,
        getInvoices,
        getInvoicesByTypeBetweenDates,
        getInvoicesBetweenDates,
        getPatientDisplayableAppointments,
        getTurnoverByDates,
        getIncomeByDates,
        getOutgoingsByDates,
        getTurnoverByTypeBetweenDates,
        getIncomeByTypeBetweenDates,
        getOutgoingsByTypeBetweenDates,
        getEmployeeDisplayableAppointments,
        getEmployeeDisplayableDailyAppointments,
        updateAppointment,
        newPrescription,      
        insertOrganisation,
        getOrganisations,
        getOrganisation,
        deleteOrganisation,
        listAllOrganisations,
        getNurseBaseSalary,
        getDoctorBaseSalary,
        getPatientCost,
        cancelAppointment,
        getPatientRepeatPrescriptions
    }
}
