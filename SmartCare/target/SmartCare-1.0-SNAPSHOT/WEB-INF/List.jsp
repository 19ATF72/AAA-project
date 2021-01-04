<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP List Users Records</title>
    </head>
<body>
    <%!
        String str="List"; 
        String url = "ListController.do";
    %>
    <%
        str="List"; 
        url = "ListController.do";
    %>
    <c:set var="List" value="ListController.do"/> 
    
    <div align="center">
        <form method="POST" action="<%=url%>">
            <div>
                <label class="form-check-label">
                    <caption><h2>What to retrieve:</h2></caption>
                </label>
            </div>
        
            <div class="form-check">
              <input class="form-check-input" type="radio" name="recordType" value="retrievePatients" id="recordType0" selected>
              <label class="form-check-label" for="recordType0">
                Retrieve Patients
              </label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" name="recordType" value="retrieveInvoices" id="recordType1" > <!-- TODO set to required if the user being created is a patient-->
              <label class="form-check-label" for="recordType1">
                Retrieve Invoices
              </label>
            </div>
      
            <br/>
        
            <div>
                <label class="form-check-label">
                    <caption><h2>Retrieve by type:</h2></caption>
                </label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" name="patientType" value="0" id="patientType0" selected>
              <label class="form-check-label" for="patientType0">
                All Patients
              </label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" name="patientType" value="1" id="patientType1" > <!-- TODO set to required if the user being created is a patient-->
              <label class="form-check-label" for="patientType1">
                Private patient
              </label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" name="patientType" value="2" id="patientType2">
              <label class="form-check-label" for="patientType2">
                NHS patient
              </label>
            </div>

            <br/>

            <div>
                <label class="form-check-label">
                <caption><h2>Get Patients between:</h2></caption>
                </label>
            </div>

            <div class="form-check">
                <label for="startTime">Start date:</label>
                <input type="date" id="startTime" name="startTime">
            </div>

            <div class="form-check">
                <label for="endTime">End date:</label>
                <input type="date" id="endTime" name="endTime">
            </div>

            <div>
                <p>
                    <td> <button type="submit" name="ListPatients" value="ListPatients"><%=str%></button></td>
                </p>
            </div>

        </form>
 
        <p><c:out value="${not empty message ? message: ''}" /></p>

        <table border="1" cellpadding="5">
            <caption><h2>List of patients:</h2></caption>
            <tr>
                <th>UUID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Patient Type</th>
                <th>Created</th>
                <th>Select</th>
            </tr>
            <c:forEach items="${resultPatients}" var="row">
                <tr>
                    <td><c:out value="${row[0]}" /></td>
                    <td><c:out value="${row[1]}" /></td>
                    <td><c:out value="${row[2]}" /></td>
                    <td><c:out value="${row[3]}" /></td>
                    <td><c:out value="${row[4]}" /></td>
                    <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></td>
                </tr>
            </c:forEach>
        </table>
    
        <table border="1" cellpadding="5">
            <caption><h2>List of Invoices:</h2></caption>
            <tr>
                <th>UUID</th>
                <th>Name</th>
                <th>Address</th>
                <th>Patient Type</th>
                <th>Appointment ID</th>
                <th>Duration</th>
                <th>Date</th>
                <th>Charge</th>
                <th>Status</th>
                <th>Select</th>
            </tr>
            <c:forEach items="${resultInvoices}" var="row">
                <tr>
                    <td><c:out value="${row[0]}" /></td>
                    <td><c:out value="${row[1]}" /></td>
                    <td><c:out value="${row[2]}" /></td>
                    <td><c:out value="${row[3]}" /></td>
                    <td><c:out value="${row[4]}" /></td>
                    <td><c:out value="${row[5]}" /></td>
                    <td><c:out value="${row[6]}" /></td>
                    <td><c:out value="${row[7]}" /></td>
                    <td><c:out value="${row[8]}" /></td>
                    <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="foot.jsp"/>  
</body>
</html>
