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
        
        <%! String str="List"; String url = "ListController.do"; %>
        <% str="List"; url = "ListController.do"; %>
        <c:set var="List" value="ListController.do"/> 

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/patientSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">List Records</h1>
                        </div>
                        
                        <div class="row">
                            <div class="col-sm-4">
                                <h4 class="mb-3" >What to retrieve</h4>
                                <div class="col-sm-12">
                                    <input class="form-check-input" type="radio" name="recordType" value="retrievePatients" id="recordType0" >
                                    <label for="recordType0" class="form-label">Retrieve patients</label>
                                </div>
                                <div class="col-sm-12">
                                    <input class="form-check-input" type="radio" name="recordType" value="retrieveInvoices" id="recordType1" >
                                    <label for="recordType1" class="form-label">Retrieve invoices</label>
                                </div>
                            </div>

                            <div class="col-sm-4">
                                <h4 class="mb-3" >Retrieve by type</h4>
                                <div class="col-sm-12">
                                    <input class="form-check-input" type="radio" name="patientType" value="0" id="patientType0">
                                    <label for="patientType0" class="form-label">All patients</label>
                                </div>
                                <div class="col-sm-12">
                                    <input class="form-check-input" type="radio" name="patientType" value="1" id="patientType1" >
                                    <label for="patientType1" class="form-label">Private patients</label>
                                </div>
                                <div class="col-sm-12">
                                    <input class="form-check-input" type="radio" name="patientType" value="2" id="patientType2">
                                    <label for="patientType2" class="form-label">NHS patients</label>
                                </div>
                            </div>
                                              
                            <div class="col-sm-4">
                                <h4 class="mb-3" >Records between dates</h4>
                                <div class="col-sm-12">
                                    <label for="startTime" class="form-label">Start</label>
                                    <input type="date" id="startTime" name="startTime">
                                </div>
                                <div class="col-sm-12">
                                    <label for="endTime" class="form-label">End</label>
                                    <input type="date" id="endTime" name="endTime">
                                </div>
                            </div>
                        </div>

                        <div class="col-12 mb-2 mt-2">
                            <button type="submit" class="w-100 btn btn-outline-primary btn-lg" name="ListPatients" value="ListPatients"><%=str%></button>
                        </div>
                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <div class="${not empty resultPatients ? resultPatients: 'd-none'}">
                            <h2>List of patients</h2>
                            <div class="table-responsive">
                                <table class="table table-striped table-sm">
                                    <thead>
                                        <tr>
                                            <th>UUID</th>
                                            <th>Name</th>
                                            <th>Address</th>
                                            <th>Patient Type</th>
                                            <th>Created</th>
                                            <th>Select</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${resultPatients}" var="row">
                                            <tr>
                                                <td><c:out value="${row[0]}" /></td>
                                                <td><c:out value="${row[1]}" /></td>
                                                <td><c:out value="${row[2]}" /></td>
                                                <td><c:out value="${row[3]}" /></td>
                                                <td><c:out value="${row[4]}" /></td>
                                                <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                        <div class="${not empty resultInvoices ? resultInvoices: 'd-none'}">
                            <h2>List of Invoices</h2>
                            <div class="table-responsive">
                                <table class="table table-striped table-sm">
                                    <thead>
                                        <tr>
                                            <th>UUID</th>
                                            <th>Name</th>
                                            <th>Address</th>
                                            <th>Patient Type</th>
                                            <th>Appointment ID</th>
                                            <th>Duration</th>
                                            <th>Date</th>
                                            <th>Charge</th>
                                            <th>Status</th>
                                            <th>Select</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${resultInvoices}" var="row">
                                        <tr>
                                            <td><c:out value="${row[0]}" /></td>
                                            <td><c:out value="${row[1]}" /></td>
                                            <td><c:out value="${row[2]}" /></td>
                                            <td><c:out value="${row[3]}" /></td>
                                            <td><c:out value="${row[4]}" /></td>
                                            <td><c:out value="${row[5]}" /></td>
                                            <td><c:out value="${row[6]}" /></td>
                                            <td><c:out value="${row[7]}" /></td>
                                            <td><c:out value="${row[8]}" /></td>
                                            <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></td>
                                        </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </main> 
                </div>     
            </form>
            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>
