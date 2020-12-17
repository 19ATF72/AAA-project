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
        <title>Register Page</title>
    </head>
    <body>
        <div align="center">
            <h2>User's details:</h2>
            <%! int i=0;
                String str="Register"; 
                String url = "NewUserController.do";
            %>
            <%
                if((String)request.getAttribute("msg")=="del") {
                    str = "Delete";
                    url = "Delete.do";
                }
                else {
                    str="Register";
                    url = "NewUserController.do";
                } 
            %>
            <form method="POST" action="<%=url%>">     
                <table>
                    <tr>
                        <th>Please provide your following details</th>
                    </tr>
                    <tr>
                         <td><span>Email:</span><input type="text" name="email" required/></td>
                    </tr>
                    <tr>
                        <td><span>Password:</span><input type="text" name="password" required/></td>
                    </tr>
                     <tr>
                         <td><span>Picture Url:</span><input type="file" name="picUrl" required/></td>
                    </tr>
                    <tr>
                        <td><span>Full legal name:</span><input type="text" name="username" required/></td>
                    </tr>
                    <tr>
                        <td><span>Address:</span><input type="text" name="Address" required/></td>
                    </tr>
                    <tr>
                        <td><span>Organization name if applicable:</span><input type="text" name="organizationName"/></td>
                    </tr>
                    <tr>
                        <td>
                        <div>
                            <label class="form-check-label">
                                    Are you creating a Doctor or a Patient account ? CSS TO HIDE OPTIONS
                            </label>
                            <!-- Todo make radio values auto generate from database table employee types -->
                        </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="Role" value="0" id="accountType1" required>
                                <label class="form-check-label" for="accountType1">
                                  Patient
                                </label>
                            </div>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="Role" value="1" id="accountType2">
                              <label class="form-check-label" for="accountType2">
                                Doctor
                              </label>
                            <div class="form-check">
                                <input class="form-check-input" type="radio" name="Role" value="2" id="accountType3">
                              <label class="form-check-label" for="accountType3">
                                Nurse
                              </label>
                            </div>
                        </td>
                    </tr>
                     <tr>
                        <td>
                        <div>
                            <label class="form-check-label">
                                    Are you a: Todo make radio values auto generate from database table patient types
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
                        </td>
                    </tr>
                    <tr> 
                        <td> <input type="submit" value="<%=str%>"/></td>
                    </tr>
                </table>
            </form>   
            <p><c:out value="${not empty message ? message: ''}" /></p>
            </br>
        </div>
    </body>
</html>
