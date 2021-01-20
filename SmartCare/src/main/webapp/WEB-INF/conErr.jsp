<%-- 
    Document   : conErr
    Created on : 30-Nov-2015, 13:26:37
    Author     : me-aydin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connection Error Page</title>
        <link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />
        <%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<%@include file='/html/scripts.html'%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<style><%@include file="/css/style.css"%></style>
<link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />
        
    </head>
    <body>
        <p></p>
        <h1>DB Connection could not been established!!</h1>
         <jsp:include page="/html/footer.html"/>
    </body>
</html>
