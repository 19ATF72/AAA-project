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
        <div align="center">
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
                        <td>Email:</td>
                        <td><input type="text" name="mail" required /></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" required /></td>
                    </tr>
                    <tr>
                        <td><input type="submit" name="LoginOperation" value="Login"/></td>
                    </tr>
                </table>
            </form>
            <form method="POST" action="<%=url%>">     
                <table>
                     <tr> 
                        <td> <button type="submit" name="LoginOperation" value="NewUser">New user</button></td>
                    </tr>
                </table>
            </form>
            <p><c:out value="${not empty message ? message: ''}" /></p>
        </div>
    </body>
</html>
