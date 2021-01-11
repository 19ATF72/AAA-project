<%-- 
    Document   : organisations
    Created on : 14-Dec-2020, 13:35:26
    Author     : rob
--%>
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <%! int i=0;
        String str="List Organisations"; 
        String url = "OrganisationServlet.do";
    %>
    <%
        str="List Organisations"; 
        url = "OrganisationServlet.do";
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart Care</title>
    </head>
    
    <body>
        <form method="POST" action="<%=url%>">
            <center>
        <h2>
            <a href="OrganisationServlet.do/new_organisation">Add New Organisation</a>
            &nbsp;&nbsp;&nbsp;
            <a href="OrganisationServlet.do">List All Organisations</a>
             
        </h2>
    </center>
        <div align="center">
            
         <div>
            <p>
                <td> <button type="submit" name="ListOrganisations" value="ListOrganisations"><%=str%></button></td>
            </p>
        </div>    
        <table border="1" cellpadding="5">
            <caption><h2>List of organisations</h2></caption>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Type</th>
                <th>Address</th>
                <th>PostCode</th>
                <th>Phone Number</th>
                <th>Edit</th>
            </tr>

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
                        <a href="OrganisationServlet.do/edit_organisation?id=<c:out value='${OrganisationEntity.getOId()}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="OrganisationServlet.do/delete_organisation?id=<c:out value='${OrganisationEntity.getOId()}' />">Delete</a>                     
                     </td>
                </tr>
            </c:forEach>
        </table>
    </div>
        </form>
    </body>
</html>
