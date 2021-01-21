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
        
        <%! String str="List"; String url = "prescriptionController.do"; %>
        <% str="List"; url = "prescriptionController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/patientSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        
                        <div class="row row-cols-1 row-cols-md-3 mb-3 mt-4">
                            <div class="col-md-6 offset-md-2">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                      <h1 class="h3 mt-1 fw-normal text-center">Prescriptions List</h1>
                                    </div>
                                    <div class="card-body">
                                        
                                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                            <c:out value="${not empty message ? message: ''}" />
                                        </div>
                                        
                                        <div class="${not empty repeatPrescriptions ? repeatPrescriptions: 'd-none'}">
                                            <h2>Repeat prescriptions</h2>
                                            <div class="table-responsive">
                                                <table class="table table-striped table-sm">
                                                    <thead>
                                                        <tr>
                                                            <th>Prescription ID</th>
                                                            <th>Medcine</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${repeatPrescriptions}" var="row">
                                                            <tr>
                                                                <td><c:out value="${row[0]}" /></td>
                                                                <td><c:out value="${row[2]}" /></td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>

                                        <div class="${not empty prescriptions ? prescriptions: 'd-none'}">
                                            <h2>Other prescriptions</h2>
                                            <div class="table-responsive">
                                                <table class="table table-striped table-sm">
                                                    <thead>
                                                        <tr>
                                                            <th>Prescription ID</th>
                                                            <th>Medcine</th>
                                                            <th>Select</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${prescriptions}" var="row">
                                                            <tr>
                                                                <td><c:out value="${row[0]}" /></td>
                                                                <td><c:out value="${row[2]}" /></td>
                                                                <td>
                                                                    <button type="submit" class="btn btn-outline-primary" name="requestRepeat" value="<c:out value="${row[0]}" />">Request repeat</button>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </main> 
                </div>     
            </form>
            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>
