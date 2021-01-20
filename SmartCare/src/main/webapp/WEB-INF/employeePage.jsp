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

    <body>
        <%@include file='/html/headerSignOut.html'%>
        
        <%! int i=0; String str="EmployeeController"; String url = "employeeController.do"; %>
        <% str="EmployeeController"; url = "employeeController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/employeeSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">Employee Dashboard</h1>
                        </div>
                        
                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <h2>All appointments</h2>
                        <div class="table-responsive">
                          <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Duration</th>
                                    <th>Notes</th>
                                    <th>Charge</th>
                                    <th>Date</th>
                                    <th>Starting time</th>
                                    <th>End time</th>
                                    <th>Status</th>
                                    <th>Name</th>
                                    <th>Select</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${schedule}" var="row">           
                                    <tr>
                                       <td><c:out value="${row[0]*10}" /> minutes</td>
                                       <td><c:out value="${row[1]}" /></td>
                                       <td><c:out value="${row[2]}" /></td>
                                       <td><c:out value="${row[3]}" /></td>
                                       <td><c:out value="${row[4]}" /></td>
                                       <td><c:out value="${row[5]}" /></td>
                                       <td><c:out value="${row[9]}" /></td>
                                       <td><c:out value="${row[6]}" /></td>
                                       <td><input class="form-check-input" name="appointmentToUpdate" type="radio" value="<c:out value="${row[7]},${row[8]}" />" aria-label="Checkbox for following text input"></td>
                                    </tr>
                                </c:forEach>   
                            </tbody>
                          </table>
                        </div>

                        <h2>Daily appointments</h2>
                        <div class="table-responsive">
                          <table class="table table-striped table-sm">
                            <thead>
                                <tr>
                                    <th>Duration</th>
                                    <th>Notes</th>
                                    <th>Charge</th>
                                    <th>Date</th>
                                    <th>Starting time</th>
                                    <th>End time</th>
                                    <th>Status</th>
                                    <th>Name</th>
                                    <th>Select</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${dailySchedule}" var="row">           
                                    <tr>
                                       <td><c:out value="${row[0]*10}" /> minutes</td>
                                       <td><c:out value="${row[1]}" /></td>
                                       <td><c:out value="${row[2]}" /></td>
                                       <td><c:out value="${row[3]}" /></td>
                                       <td><c:out value="${row[4]}" /></td>
                                       <td><c:out value="${row[5]}" /></td>
                                       <td><c:out value="${row[9]}" /></td>
                                       <td><c:out value="${row[6]}" /></td>
                                       <td><input class="form-check-input" name="appointmentToUpdate" type="radio" value="<c:out value="${row[7]},${row[8]}" />" aria-label="Checkbox for following text input"></td>
                                    </tr>
                                </c:forEach>       
                            </tbody>
                          </table>
                        </div>
                        <div class="col-12 mt-3">
                            <button type="submit" class="w-100 btn btn-outline-primary btn-lg" name="employeeOperation" value="recordAppointment">Action appointment</button>
                        </div>
                    </main>
                </div>
            </form>

            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>