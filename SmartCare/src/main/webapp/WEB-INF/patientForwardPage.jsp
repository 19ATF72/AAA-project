<%-- 
    Document   : driver
    Created on : 16-Jan-2020, 15:18:08
    Author     : micah
--%>
<!DOCTYPE html>
<html lang="en">
    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='/html/scripts.html'%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<style><%@include file="/css/style.css"%></style>

    <body>
        <%@include file='/html/headerSignIn.html'%>
        
        <%! int i=0; String str="Register"; String url = "NewUserController.do"; %>
        <% if((String)request.getAttribute("msg")=="del") { str = "Delete"; url = "Delete.do"; }
           else { str="Register"; url = "NewUserController.do"; } %>

        <main class="container">
            
            <div class="container mt-4">
                <div class="col-md-8 offset-md-2 col-lg-8">
                    <h4 class="mb-3">User Sign up</h4>
                    <form class="needs-validation" novalidate="" method="POST" action="<%=url%>" >
                        <div class="row g-3">

                            <div class="col-md-2">
                                <label for="prefix" class="form-label">Prefix</label>
                                <select class="form-select" id="prefix" name="userPrefix" required="">
                                  <option value="">Choose...</option>
                                  <option>Dr.</option>
                                  <option>Mr.</option>
                                  <option>Mrs.</option>
                                  <option>Miss.</option>
                                  <option>None</option>
                                </select>
                                <div class="invalid-feedback">
                                  Please select a valid country.
                                </div>
                            </div>
                        <button class="w-100 btn btn-primary btn-lg" type="submit" value="<%=str%>" >Sign Up</button>
                    </form>
                </div>
            </div>

            <%@include file='/html/footer.html'%>
        </main>
    </body>
</html>