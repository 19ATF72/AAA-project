<%-- 
    Document   : logout
    Created on : 20-Jan-2021, 11:20:50
    Author     : George
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SmartCare - Logout</title>
    </head>
    <body>
        <%
        session.invalidate();
        response.sendRedirect("login.jsp");
        %>
    </body>
</html>
