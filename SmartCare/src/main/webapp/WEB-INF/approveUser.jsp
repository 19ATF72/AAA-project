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
        
        <%! String str="List"; String url = "approveUserController.do"; %>
        <% str="List"; url = "approveUserController.do"; %>
        <c:set var="List" value="approveUserController.do"/> 

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/adminSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">List Records</h1>
                        </div>
                        <div class="col-12 mb-2 mt-2">
                            <button type="submit" class="w-100 btn btn-outline-primary btn-lg" name="ListPatients" value="ListPatients"><%=str%></button>
                        </div>
                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <div class="${toBeApproved ? toBeApproved:""}">
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
                                        <c:forEach items="${toBeApproved}" var="row">
                                            <tr>
                                                <td><c:out value="${row[0]}" /></td>
                                                <td><c:out value="${row[1]}" /></td>
                                                <td><c:out value="${row[2]}" /></td>
                                                <td><c:out value="${row[3]}" /></td>
                                                <td><c:out value="${row[4]}" /></td>
<!--                                                <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></td>-->
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
