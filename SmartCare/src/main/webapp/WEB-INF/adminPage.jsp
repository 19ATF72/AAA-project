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
        
        <%! int i=0; String str="adminController"; String url = "adminController.do"; %>
        <% str="AdminController"; url = "adminController.do"; %>

        <div class="container-fluid">
            
    
                <div class="row" >
                    
                    <%@include file='/html/adminSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">Admin Dashboard</h1>
                        </div>  
                        <div class="alert alert-success ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>
                         <h2>Patient List</h2>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>Prefix</th>
                                        <th>Name</th>
                                        <th>Surname</th>
                                        <th>Last Accessed</th>
                                        <th>Email</th>
                                        <th>Phone Number</th>
                                        <th>Delete Patient</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOfPatients}" var="patient">
                                        <tr>
                                           <td><c:out value="${patient.userPrefix}" /></td>
                                           <td><c:out value="${patient.userFirstname}" /></td>
                                           <td><c:out value="${patient.userSurname}" /></td>
                                           <td><c:out value="${patient.lastAccessed}" /></td>
                                           <td><c:out value="${patient.email}" /></td>
                                           <td><c:out value="${patient.phoneNumber}" /></td>           
                                           <td>
                                                <form action="./app/delete?id=<c:out value='${patient.uniqueUserId}'/>" method="post">
                                                    <button class="btn btn-danger" type="submit" name="patientOperation" value="delete" >Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <h2>List Doctors & Nurses</h2>
                        <div class="table-responsive">
                            <table class="table table-striped table-sm">
                                <thead>
                                    <tr>
                                        <th>Prefix</th>
                                        <th>Name</th>
                                        <th>Surname</th>
                                        <th>Last Accessed</th>
                                        <th>Email</th>
                                        <th>Phone Number</th>
                                        <th>Delete Patient</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${listOfEmployees}" var="employees">
                                        <tr>
                                           <td><c:out value="${employees.userPrefix}" /></td>
                                           <td><c:out value="${employees.userFirstname}" /></td>
                                           <td><c:out value="${employees.userSurname}" /></td>
                                           <td><c:out value="${employees.lastAccessed}" /></td>
                                           <td><c:out value="${employees.email}" /></td>
                                           <td><c:out value="${employees.phoneNumber}" /></td>           
                                           <td>
                                                <form action="./app/delete?id=<c:out value='${employees.uniqueUserId}'/>" method="post">
                                                    <button class="btn btn-danger" type="submit" name="patientOperation" value="delete" >Delete</button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>                           
                    </main>
                </div>
            </form>

            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>