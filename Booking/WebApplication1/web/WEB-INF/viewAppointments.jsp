<%-- 
    Document   : F
    Created on : 08-Dec-2020, 17:27:52
    Author     : rob
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP List Users Records</title>
</head>
<body>
    
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>Name</th>
                <th>Email</th>
                <th>Profession</th>
                <th>ID</th>
            </tr>
            <c:forEach var="row" items="${appointments.rows}">
                <tr>
                    <td><c:out value="${appointments.time}" /></td>
                    <td><c:out value="${appointments.doctor}" /></td>
                    <td><c:out value="${appointments.patient}" /></td>
                    <td><c:out value="${appointments.id}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>
