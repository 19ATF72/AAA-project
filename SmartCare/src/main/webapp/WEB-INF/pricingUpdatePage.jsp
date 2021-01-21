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
        
        <%! int i=0; String str="pricingUpdateController"; String url = "pricingUpdateController.do"; %>
        <% str="pricingUpdateController"; url = "pricingUpdateController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/adminSidebar.html'%>

                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="row row-cols-1 row-cols-md-3 mb-3 mt-4">
                            <div class="col-md-6 offset-md-2">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                      <h1 class="h3 mt-1 fw-normal text-center">Update salaries and charges</h1>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="<%=url%>">
                                            <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                              <c:out value="${not empty message ? message: ''}" />
                                            </div>
                                            
                                            <div class="col-12 mt-3">
                                                <label for="doctorSalary" class="form-label">Update doctor salaries:</label>
                                                <input type="text" class="form-control" id="doctorSalary" name="doctorSalary">
                                            </div>
                                            <div class="col-12 mt-3">
                                                <label for="nurseSalary" class="form-label">Update nurse salaries:</label>
                                                <input type="text" class="form-control" id="nurseSalary" name="nurseSalary">
                                            </div>
                                            <div class="col-12 mt-3">
                                                <label for="patientCharge" class="form-label">Update patient charge:</label>
                                                <input type="text" class="form-control" id="patientCharge" name="patientCharge">
                                            </div>
                                            <button type="submit" name="adminOperation" value="recorded" class="btn btn-primary mt-3">Update values</button>
                                        </form>
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