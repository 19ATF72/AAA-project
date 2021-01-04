<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP List Users Records</title>
    </head>
<body>
    <%! int i=0;
        String str="List Patients"; 
        String url = "PatientController.do";
    %>
    <%
        str="List Patients"; 
        url = "PatientController.do";
    %>
    <div align="center">
        <form method="POST" action="<%=url%>">
            <div>
                <label class="form-check-label">
                    <caption><h2>Book Appointment:</h2></caption>
                </label>
            </div>                
            <div class="dropdown">
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <select name="docChooice" value="" required>
                        <option class="dropdown-item" value="" /> Choose a doctor</option>
                        <c:forEach items="${doctors}" var="row">
                            <tr>
                            <option class="dropdown-item" value="<c:out value="${row[0]}" />"><c:out value="${row[1]}" /> <c:out value="${row[2]}" /></option>
                            </tr>
                        </c:forEach>
                    </select>
                </div>
                <label>Date:</label>
                <input type="date" name="bookingDate" required>
                <button class="btn btn-secondary dropdown-toggle" name="patientOperation" value="choosen" type="submit" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Get Slots</button>
            </div>        
        </form>
        <br/>
        <form method="POST" action="<%=url%>">
            <button class="btn btn-secondary dropdown-toggle" name="patientOperation" value="choosen" type="button">
            <select name="book_choice" value="" multiple="multiple" required>
                <table>
                   <c:forEach items="${lengths}" var="row">
                    <tr>
                        <td>
                            <label><c:out value="${row[0]}" /></label>
                        </td>
                       <c:forEach items="${row[1]}" var="col">
                            <td>
                       <option type="checkbox" data-toggle="toggle" value="<c:out value="${col[0]},${col[1]},${col[2]}" />"><label><c:out value="${col[1]}" /> - <c:out value="${col[2]}" /></label></option>
                            </td>
                        </c:forEach>
                    </tr>
                   </c:forEach>
                </table>
            </select>
            <button class="btn btn-secondary dropdown-toggle" name="patientOperation" value="booked" type="submit" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Book Appointment</button>
        </form>
        <p><c:out value="${not empty message ? message: ''}" /></p>
    </div>
    <jsp:include page="foot.jsp"/>  
</body>
</html>
