<%-- 
    Document   : organisations
    Created on : 14-Dec-2020, 13:35:26
    Author     : rob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Smart Care</title>
    </head>
    <body>
        <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of organisations</h2></caption>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Type</th>
                <th>Address</th>
                <th>PostCode</th>
                <th>Phone Number</th>
            </tr>
            <c:forEach var="row" items="${organisation.rows}">
                <tr>
                    <td><c:out value="${organsation.name}" /></td>
                    <td><c:out value="${orgType}" /></td>
                    <td><c:out value="${organisation.address}" /></td>
                    <td><c:out value="${organisation.postcode}" /></td>
                    <td><c:out value="${organisation.phone_number}" /></td>
                    <td>
                        <a href="/edit?id=<c:out value='${organsation.id}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <a href="/delete?id=<c:out value='${organsation.id}' />">Delete</a>                     
                     </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </body>
</html>
