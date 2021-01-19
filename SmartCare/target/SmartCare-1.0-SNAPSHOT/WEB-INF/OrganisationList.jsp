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

    <body>
        <%@include file='/html/headerSignOut.html'%>
        
        <%! int i=0; String str="List Organisations"; String url = "OrganisationServlet.do"; %>
        <% str="List Organisations"; url = "OrganisationServlet.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/adminSidebar.html'%>
                    
                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        
                        <div class="${not empty organisationList ? organisationList: 'd-none'}">
                            <h2>List of organisations</h2>
                            <div class="table-responsive">
                                <table class="table table-striped table-sm">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Name</th>
                                            <th>Type</th>
                                            <th>Address</th>
                                            <th>PostCode</th>
                                            <th>Phone Number</th>
                                            <th>Edit</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="OrganisationEntity" items="${organisationList}">
                                            <tr>
                                                <td><c:out value="${OrganisationEntity.getOId()}" /></td>
                                                <td><c:out value="${OrganisationEntity.getName()}" /></td>
                                                <td><c:out value="${OrganisationEntity.getOrgType()}" /></td>
                                                <td><c:out value="${OrganisationEntity.getAddress()}" /></td>
                                                <td><c:out value="${OrganisationEntity.getPostcode()}" /></td>
                                                <td><c:out value="${OrganisationEntity.getPhoneNum()}" /></td>
                                                <td>
                                                    <c:set var = "oId" scope = "session" value = "${OrganisationEntity.getOId()}"/>

                                                    <input type="hidden" name="oId" value="<c:out value='${OrganisationEntity.getOId()}'/>"> 

                                                    <a  href="OrganisationServlet.do/edit_organisation?id=<c:out value='${OrganisationEntity.getOId()}' />">Edit</a>
                                                    <a href="OrganisationServlet.do/delete_organisation">Delete</a>                     
                                                 </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                                
                                <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                    <c:out value="${not empty message ? message: ''}" />
                                </div>
                                
                                <div class="col-12 mb-2 mt-2">
                                    <button class="w-100 btn btn-primary btn-lg" type="submit" name="ListOrganisations" value="ListOrganisations" ><%=str%></button>  
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