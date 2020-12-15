<%-- 
    Document   : OrganisationAddNew
    Created on : 14-Dec-2020, 20:48:41
    Author     : rob
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>SmartCare</title>
</head>
<body>
    <center>
        <h1>Organisation Management</h1>
        <h2>
            <a href="/OrganisationServlet.do/new_organisation">Add New Organisation</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/OrganisationServlet.do">List All Organisations</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${organisation != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${organisation == null}">
            <form action="insert_organisation" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${organisation != null}">
                        Edit Organisation
                    </c:if>
                    <c:if test="${organisation == null}">
                        Add New Organisation
                    </c:if>
                </h2>
            </caption>
                <c:if test="${organisation != null}">
                    <input type="hidden" name="oId" 
                           value="<c:out value='${organisation.oId}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${organisation.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Organisation Type: </th>
                <td>
                    <input type="text" name="orgType" size="45"
                            value="<c:out value='${organisation.orgType}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Address: </th>
                <td>
                    <input type="text" name="address" size="45"
                            value="<c:out value='${organisation.address}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Postcode: </th>
                <td>
                    <input type="text" name="postcode" size="10"
                            value="<c:out value='${organisation.postcode}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Number: </th>
                <td>
                    <input type="text" name="phoneNum" size="30"
                            value="<c:out value='${organisation.phoneNum}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>