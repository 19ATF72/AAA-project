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
        <%@include file='/html/headerSignIn.html'%>
        
        <%! String str="Forward"; String url = "patientForwardController.do"; %>
        <% str="Forward"; url = "patientForwardController.do"; %>

        <div class="row">
        <%@include file='/html/employeeSidebar.html'%>
        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
            

                <div class="col-md-8 offset-md-2 col-lg-8">
                    <h4 class="mb-3 mt-3">Forward to specialist</h4>
                    <form class="needs-validation" novalidate="" method="POST" action="<%=url%>" >
                        <div class="row">
                            <div class="col-md-12">
                                <label for="patient" class="form-label">Patient</label>
                                <select class="form-select" id="prefix" name="patient" required="">
                                    <c:forEach items="${allPatients}" var="row">  
                                        <option <c:out value="${row[1]}" />>
                                            <c:out value="${row[1]}" />
                                            <c:out value="${row[2]}" />
                                            <c:out value="${row[3]}" />
                                            <c:out value="${row[4]}" />
                                        </option>
                                    </c:forEach>   
                                </select>
                            </div>
                            <div class="col-md-12 mt-1">
                                <label for="organisation" class="form-label">Organisation</label>
                                <select class="form-select" id="prefix" name="organization" required="">
                                  <c:forEach items="${allOrganizations}" var="row">  
                                    <option <c:out value="${row.getOId()}" />><c:out value="${row.getName()}" />
                                    </option>
                                  </c:forEach>   
                                </select>
                            </div>
                            
                            <div class="col-12 mb-2 mt-4">
                                <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                    <c:out value="${not empty message ? message: ''}" />
                                </div>
                            </div>
                            
                            <div class="col-12 mb-2 mt-0">
                                <button class="w-100 btn btn-primary btn-lg" type="submit" name="forward" value="forwarded" >Forward</button>
                            </div>
                        </div>   
                    </form>
                </div>


            
        </main>
                                <%@include file='/html/footer.html'%>
        </div>
                                
    </body>
</html>