<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP List Users Records</title>
    </head>
<body>
    <%! int i=0;
        String str="List Patients"; 
        String url = "ListController.do";
    %>
    <%
        str="List Patients"; 
        url = "ListController.do";
    %>
    <div align="center">
      <form method="POST" action="<%=url%>">
        <div>
            <label class="form-check-label">
                List patients by type:
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
            NHS patient
          </label>
        </div>

        <div class="form-check">
          <input class="form-check-input" type="radio" name="patientType" value="2" id="patientType2">
          <label class="form-check-label" for="patientType2">
            Private patient
          </label>
        </div>

        <br/>

        <div>
            <label class="form-check-label">
                Between:
            </label>
        </div>

        <div class="form-check">
          <label for="startTime">Start date:</label>
          <input type="datetime-local" id="startTime" name="startTime">
        </div>

        <div class="form-check">
          <label for="endTime">End date:</label>
          <input type="datetime-local" id="endTime" name="endTime">
        </div>

        <div>
             <tr>
                <td> <button type="submit" name="ListPatients" value="ListPatients"><%=str%></button></td>
            </tr>
        </div>

      </form>
    </div>

    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of users</h2></caption>
            <tr>
                <th>Pid</th>
                <th>Address</th>
                <th>Patient Type PID</th>
                <th>UUID</th>
            </tr>
            <c:forEach var="row" items="${patient.rows}">
                <tr>
                    <td><c:out value="${patient.pid}" /></td>
                    <td><c:out value="${patient.address}" /></td>
                    <td><c:out value="${patient.patient_type_ptid}" /></td>
                    <td><c:out value="${patient.users_uuid}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <%
        out.println(request.getAttribute("message"));  
    %>
    <jsp:include page="foot.jsp"/>  
</body>
</html>
