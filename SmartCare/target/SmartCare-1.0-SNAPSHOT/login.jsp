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
<link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />

    <body>
        <%@include file='/html/headerSignIn.html'%>
        
        <%! int i=0; String str="Login"; String url = "auth"; %>
        <%  str="Login"; url = "auth"; %>

        <main class="container">
            
            <div class="row row-cols-1 row-cols-md-3 mb-3 mt-4 text-center">
                <div class="col-md-6 offset-md-3">
                    <div class="card mb-4 shadow-sm">
                        <div class="card-header">
                          <h1 class="h3 mt-1 fw-normal">User Login</h1>
                        </div>
                        <div class="card-body">
                            <form method="POST" action="app">   
                                <i class="fas fa-laptop-medical mb-4" style="font-size:90px"></i>
                                
                                <c:if test="${badPw == true}">
                                    <br>
                                    <div class="alert alert-danger" role="alert">
                                       Invalid Email or Password
                                    </div>
                                    <br>                                    
                                </c:if>                            
                                <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                  <c:out value="${not empty message ? message: ''}" />
                                </div>
                                <label for="inputEmail" class="visually-hidden">Email address</label>
                                <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required="" autofocus="" name="mail" required />
                                
                                <label for="inputPassword" class="visually-hidden">Password</label>
                                <input type="password" id="inputPassword" class="form-control" placeholder="Password" required="" name="password" required />
                                
                                <button class="w-100 btn btn-lg btn-primary" type="submit" name="LoginOperation" value="Login" >Login</button>
                             </form>
                            <form method="POST" action="signup">
                              <button class="w-100 mt-3 btn btn-lg btn-outline-primary" type="submit" name="LoginOperation" value="NewUser">Sign Up</button>
                            </form>
                      </div>
                    </div>
                </div>
            </div>  
                              
            <%@include file='/html/footer.html'%>
        </main>
    </body>
</html>