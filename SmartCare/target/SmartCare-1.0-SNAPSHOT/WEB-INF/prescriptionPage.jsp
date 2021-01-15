<%-- 
    Document   : driver
    Created on : 01-Nov-2015, 15:18:08
    Author     : me-aydin
--%>

<%@page import="model.Dao.DynamicDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Main Page</title>
    </head>
    <body>
        <div align="center">
            <h2>Create Prescription</h2>
            <%! int i=0;
                String str="employeeController"; 
                String url = "employeeController.do";
            %>
            <% 
                 str="employeeController"; 
                 url = "employeeController.do";
            %>
            <form method="POST" action="<%=url%>">     
                <table>
                    <tr>
                        <td>Medicine:</td>
                        <td><input type="text" name="prescription" /></td>
                        <td><input type="checkbox" name="prescription"  value="repeat" />Repeat prescription</td>
                    </tr>
                    <tr>
                        <td>Notes:</td>
                        <td><input type="text" name="notes" /></td>
                    </tr>
                    <tr> 
                        <td>
                            <input type="submit" name="employeeOperation" value="recorded"/>
                        </td>
                    </tr>
                </table>
            </form>
            <p><c:out value="${not empty message ? message: ''}" /></p>
            </br>
            <jsp:include page="/WEB-INF/foot.jsp"/>
        </div>
    </body>
</html>
