<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Main Page</title>
    </head>
    <body>
        <h2>REEEEEEEEEEE</h2>
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
                    <td> <button type="submit" name="bookOperation" value="booked">book appointment</button></td>
                </tr>
            </table>
        </form>
        <jsp:include page="/WEB-INF/foot.jsp"/>
    </body>
</html>
