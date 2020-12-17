<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Main Page</title>
    </head>
    <body>
        <div align="center">
            <h2>Patient Dashboard</h2>
            <%! int i=0;
                String str="PatientController"; 
                String url = "PatientController.do";
            %>
            <% 
                 str="PatientController"; 
                 url = "PatientController.do";
            %>
            <form method="POST" action="<%=url%>"> 
                <table>
                    <tr>
                       <td> 
                        <div align="center" style="height:200px;overflow:auto;"> 
                            <table border="1" cellpadding="5">
                                <caption><h3>Appointments</h3></caption>
                                <tr>
                                <th>Doctor Name</th>
                                <th>Duration</th>
                                <th>Notes</th>
                                <th>Charge</th>
                                <th>Date</th>
                                <th>Starting time</th>
                                <th>End time</th>
                                <th>Status</th>
                            </tr>
                            <c:forEach items="${schedule}" var="row">
                                <tr>
                                   <td><c:out value="${row[7]}" /> minutes</td>
                                   <td><c:out value="${row[0]*10}" /> minutes</td>
                                   <td><c:out value="${row[1]}" /></td>
                                   <td><c:out value="${row[2]}" /></td>
                                   <td><c:out value="${row[3]}" /></td>
                                   <td><c:out value="${row[4]}" /></td>
                                   <td><c:out value="${row[5]}" /></td>
                                   <td><c:out value="${row[6]}" /></td> 
                                </tr>
                            </c:forEach>
                            </table>
                        </div>
                        </td>
                    </tr>
                    <tr>
                      <td> <button type="submit" name="patientOperation" value="bookAppointment">Book new appointment</button></td>
                    </tr>
            </table>
            </form>
            <p><c:out value="${not empty message ? message: ''}" /></p>
        </div>
        <jsp:include page="/WEB-INF/foot.jsp"/>
    </body>
</html>
