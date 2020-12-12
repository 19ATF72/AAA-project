<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<style><%@include file="/calandar.css"%></style>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <div align="center">
  <h1>Employee Register Form</h1>
  <form action="<%= request.getContextPath() %>/bookings" method="post">
   <table style="with: 80%">
    <tr>
     <td>First Name</td>
     <td><input type="text" name="time" /></td>
    </tr>
    <tr>
     <td>Last Name</td>
     <td><input type="text" name="doctor" /></td>
    </tr>
    <tr>
     <td>UserName</td>
     <td><input type="text" name="patient" /></td>
    </tr>
   </table>
   <input type="submit" value="Submit" />
  </form>
</body>
</html>