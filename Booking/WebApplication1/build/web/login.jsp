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
        <h2>Login</h2>
        <%! int i=0;
            String str="Login"; 
            String url = "Login.do";
        %>
        <% 
             str="Login"; 
             url = "Login.do";
        %>
        <form method="POST" action="<%=url%>">     
            <table>
                <tr>
                    <th></th>
                    <th>Login:</th>
                </tr>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password"/></td>
                </tr>
                <tr> 
                    <td> <input type="submit" value="<%=str%>"/></td>
                </tr>
            </table>
        </form>   
        <%
            if (i++>0 && request.getAttribute("message")!=null) {
                out.println(request.getAttribute("message"));
                i--;
            }
        %>
        </br>
        <jsp:include page="foot.jsp"/>
    </body>
</html>
