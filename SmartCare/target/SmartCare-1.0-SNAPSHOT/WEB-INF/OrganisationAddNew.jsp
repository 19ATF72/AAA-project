<%-- 
    Document   : driver
    Created on : 16-Jan-2020, 15:18:08
    Author     : micah
--%>
<!DOCTYPE html>
<html lang="en">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
           
        <%! int i=0; String str="Add New Organisation"; String url = "OrganisationServlet.do/new_organsiation"; %>
        <% str="Add New Organisation"; url = "OrganisationServlet.do/new_organsiation"; %>

        <div class="container-fluid">
            
            <c:if test="${organisation != null}">
                <form action="update" method="post">
            </c:if>
            <c:if test="${organisation == null}">
                <form action="insert_organisation" method="post">
            </c:if>         
                    <div class="row" >

                        <%@include file='/html/adminSidebar.html'%>

                        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                            
                            <div class="row g-3">
                                
                                <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                                    <h1 class="h2">
                                        <c:if test="${organisation != null}">
                                            Edit Organisation
                                        </c:if>
                                        <c:if test="${organisation == null}">
                                            Add New Organisation
                                        </c:if>
                                    </h1>
                                </div>

                                <div class="col-12">
                                  <label for="name" class="form-label">Name</label>
                                  <input type="text" name="name" size="45" value="<c:out value='${organisation.name}' />" />
                                </div>
                                
                                <div class="col-12">
                                  <label for="orgType" class="form-label">Organisation type</label>
                                  <input type="text" name="orgType" size="45" value="<c:out value='${organisation.orgType}' />"/>
                                </div>
                                
                                <div class="col-12">
                                  <label for="address" class="form-label">Address</label>
                                  <input type="text" name="address" size="45" value="<c:out value='${organisation.address}' />"/>
                                </div>
                                
                                <div class="col-12">
                                  <label for="postcode" class="form-label">Postcode</label>
                                  <input type="text" name="postcode" size="10" value="<c:out value='${organisation.postcode}' />"/>
                                </div>

                                <div class="col-12">
                                  <label for="phoneNum" class="form-label">Phone Number</label>
                                  <input type="tel" name="phoneNum" size="30" value="<c:out value='${organisation.phoneNum}' />"/>
                                </div>
                              
                                <c:if test="${organisation != null}">
                                    <input type="hidden" name="oId" value="<c:out value='${organisation.oId}' />" />
                                </c:if>      

                                <div class="col-12 mb-2 mt-3">
                                    <button class="w-100 btn btn-primary btn-lg" type="submit" value="Save" >Save</button>  
                                </div>
                            
                            </div>
                        </main> 
                    </div>     
                </form>

            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>