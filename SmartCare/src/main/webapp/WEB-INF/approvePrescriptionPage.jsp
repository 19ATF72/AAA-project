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
        
        <%! String str="List"; String url = "approvePrescriptionController.do"; %>
        <% str="List"; url = "approvePrescriptionController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/employeeSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        
                        <div class="row row-cols-1 row-cols-md-3 mb-3 mt-4">
                            <div class="col-md-6 offset-md-2">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                      <h1 class="h3 mt-1 fw-normal text-center">Repeat Prescriptions List</h1>
                                    </div>
                                    <div class="card-body">
                                        
                                        <div class="alert alert-success ${not empty message ? message: 'd-none'}" role="alert">
                                            <c:out value="${not empty message ? message: ''}" />
                                        </div>
                                        
                                        <div class="${not empty repeatPrescriptionsPendingApproval ? repeatPrescriptionsPendingApproval: 'd-none'}">
                                            <h2>Pending Approval</h2>
                                            <div class="table-responsive">
                                                <table class="table table-striped table-sm">
                                                    <thead>
                                                        <tr>
                                                            <th>UUID</th>
                                                            <th>Patient</th>
                                                            <th>DOB</th>
                                                            <th>Prescription ID</th>
                                                            <th>Medcine</th>
                                                            <th>Approve Repeat</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach items="${repeatPrescriptionsPendingApproval}" var="row">
                                                            <tr>
                                                                <td><c:out value="${row[0]}" /></td>
                                                                <td><c:out value="${row[1]}" /><c:out value="${row[2]}" /><c:out value="${row[3]}" /></td>
                                                                <td><c:out value="${row[4]}" /></td>
                                                                <td><c:out value="${row[5]}" /></td>
                                                                <td><c:out value="${row[6]}" /></td>
                                                                <td>
                                                                    <button type="submit" class="btn btn-outline-primary" name="approveRepeat" value="<c:out value="${row[5]}" />">Approve</button>
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
