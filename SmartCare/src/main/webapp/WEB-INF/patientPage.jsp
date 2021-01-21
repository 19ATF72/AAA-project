<%-- 
    Document   : driver
    Created on : 16-Jan-2020, 15:18:08
    Author     : micah
--%>
<!DOCTYPE html>
<html lang="en">
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='/html/scripts.html'%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page import="java.util.ArrayList"%>
<%@include file='/html/scripts.html'%>
<style><%@include file="/css/style.css"%></style>
<link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />

    <head>
        <link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />
    </head>

    <body>
        <%@include file='/html/headerSignOut.html'%>
        
        <%! int i=0; String str="PatientController"; String url = "./app/book_appointment"; %>
        <% str="PatientController"; url = "./app/book_appointment"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/patientSidebar.html'%>
             </form>
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">Patient Dashboard</h1>
                        </div>
                        
                        <div class="alert alert-success ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <h2>Scheduled appointments</h2>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>Practitioner</th>
                                        <th>Duration</th>
                                        <th>Notes</th>
                                        <th>Charge</th>
                                        <th>Date</th>
                                        <th>Starting time</th>
                                        <th>End time</th>
                                        <th>Update Appointment</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${patientsActiveAppointments}" var="AppointmentEntity">
                                        <tr>
                                           <td><c:out value="${AppointmentEntity.doctorsName}" /></td>
                                           <td><c:out value="${AppointmentEntity.duration}" /> Minutes</td>
                                           <td><c:out value="${AppointmentEntity.notes}" /></td>
                                           <td><c:out value="${AppointmentEntity.charge}" /></td>
                                           <td><c:out value="${AppointmentEntity.dateStr}" /></td>
                                           <td><c:out value="${AppointmentEntity.startTime}" /></td>
                                           <td><c:out value="${AppointmentEntity.endTime}" /></td>                
                                           <td>
                                                <form action="./app/cancel?id=<c:out value='${AppointmentEntity.uniqueAppointmentId}'/>" method="post">
                                                    <button class="btn btn-danger" type="submit" name="patientOperation" value="cancel" >Cancel</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <h2>Invoiced appointments</h2>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>Practitioner</th>
                                        <th>Duration</th>
                                        <th>Notes</th>
                                        <th>Charge</th>
                                        <th>Date</th>
                                        <th>Starting time</th>
                                        <th>End time</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${patientsInvoicedAppointments}" var="AppointmentEntity">
                                        <tr>
                                           <td><c:out value="${AppointmentEntity.doctorsName}" /></td>
                                           <td><c:out value="${AppointmentEntity.duration}" /> Minutes</td>
                                           <td><c:out value="${AppointmentEntity.notes}" /></td>
                                           <td><c:out value="${AppointmentEntity.charge}" /></td>
                                           <td><c:out value="${AppointmentEntity.dateStr}" /></td>
                                           <td><c:out value="${AppointmentEntity.startTime}" /></td>
                                           <td><c:out value="${AppointmentEntity.endTime}" /></td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div class="col-12 mb-3">
                                <button type="submit" class="w-100 btn btn-outline-success btn-lg" name="patientOperation" value="payAppointment">Pay for appointment</button>
                            </div>
                        </div>

                        <h2>Paid appointments</h2>
                        <div class="table-responsive">
                          <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Practitioner</th>
                                    <th>Duration</th>
                                    <th>Notes</th>
                                    <th>Charge</th>
                                    <th>Date</th>
                                    <th>Starting time</th>
                                    <th>End time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${patientsPaidAppointments}" var="AppointmentEntity">
                                    <tr>
                                       <td><c:out value="${AppointmentEntity.doctorsName}" /></td>
                                       <td><c:out value="${AppointmentEntity.duration}" /> Minutes</td>
                                       <td><c:out value="${AppointmentEntity.notes}" /></td>
                                       <td><c:out value="${AppointmentEntity.charge}" /></td>
                                       <td><c:out value="${AppointmentEntity.dateStr}" /></td>
                                       <td><c:out value="${AppointmentEntity.startTime}" /></td>
                                       <td><c:out value="${AppointmentEntity.endTime}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>

                        <h2>Cancelled appointments</h2>
                        <div class="table-responsive">
                          <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Practitioner</th>
                                    <th>Duration</th>
                                    <th>Notes</th>
                                    <th>Charge</th>
                                    <th>Date</th>
                                    <th>Starting time</th>
                                    <th>End time</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${patientsCancelledAppointments}" var="AppointmentEntity">
                                    <tr>
                                       <td><c:out value="${AppointmentEntity.doctorsName}" /></td>
                                       <td><c:out value="${AppointmentEntity.duration}" /> Minutes</td>
                                       <td><c:out value="${AppointmentEntity.notes}" /></td>
                                       <td><c:out value="${AppointmentEntity.charge}" /></td>
                                       <td><c:out value="${AppointmentEntity.dateStr}" /></td>
                                       <td><c:out value="${AppointmentEntity.startTime}" /></td>
                                       <td><c:out value="${AppointmentEntity.endTime}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                          </table>
                        </div>
                    </main> 
                </div>     
            

            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>