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

    <body>
        <%@include file='/html/headerSignOut.html'%>
        
        <%! int i=0; String str="adminController"; String url = "adminController.do"; %>
        <% str="AdminController"; url = "adminController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/adminSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                          <h1 class="h2">Admin Dashboard</h1>
                        </div>
                        
                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <div class="col-12 mt-3">
                            <button type="submit" class="w-100 btn btn-outline-primary btn-lg" name="adminOperation" value="recordAppointment">Perform action</button>
                        </div>
                    </main>
                </div>
            </form>

            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>