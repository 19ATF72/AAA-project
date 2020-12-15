<%-- 
    Document   : driver
    Created on : 01-Nov-2015, 15:18:08
    Author     : me-aydin
--%>

<%@page import="dao.DynamicDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
