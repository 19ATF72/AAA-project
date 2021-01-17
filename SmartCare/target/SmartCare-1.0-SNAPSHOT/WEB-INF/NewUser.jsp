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
        <%@include file='/html/header.html'%>
        
        <%! int i=0; String str="Register"; String url = "NewUserController.do"; %>
        <% if((String)request.getAttribute("msg")=="del") { str = "Delete"; url = "Delete.do"; }
           else { str="Register"; url = "NewUserController.do"; } %>

        <main class="container">
            
            <div class="container mt-4">
                <div class="col-md-8 offset-md-2 col-lg-8">
                    <h4 class="mb-3">User Sign up</h4>
                    <form class="needs-validation" novalidate="" method="POST" action="<%=url%>" >
                        <div class="row g-3">

                          <div class="col-12">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="Bobby@tables.com">
                            <div class="invalid-feedback">
                              Please enter a valid email address.
                            </div>
                          </div>

                          <div class="col-12">
                            <label for="inputPassword" class="visually-hidden">Password</label>
                            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="">
                            <div class="invalid-feedback">
                              Please enter a valid password.
                            </div>
                          </div>

                          <div class="col-sm-12">
                            <label for="firstName" class="form-label">Full legal name</label>
                            <input type="text" class="form-control" name="username" id="username" placeholder="Bobby M. Tables" value="" required="">
                            <div class="invalid-feedback">
                              Valid full legal name is required.
                            </div>
                          </div>

                          <div class="col-sm-12">
                              <label for="picUrl" class="form-label">Profile Picture</label>
                              <input type="file" name="picUrl" id="picUrl" >
                              <div class="invalid-feedback">
                                Valid profile picture format.
                              </div>
                          </div>

                          <div class="col-12">
                            <label for="postcode" class="form-label">Postcode</label>
                            <input type="text" class="form-control" name="postcode" id="postcode" placeholder="BS1 3RT" required="">
                            <div class="invalid-feedback">
                              Please enter your postcode.
                            </div>
                          </div>

                          <div class="col-12">
                            <label for="address" class="form-label">Address</label>
                            <input type="text" class="form-control" name="address" id="address" placeholder="1234 Main St" required="">
                            <div class="invalid-feedback">
                              Please enter your shipping address.
                            </div>
                          </div>

                          <div class="col-12">
                            <label for="address2" class="form-label">Address 2 <span class="text-muted">(Optional)</span></label>
                            <input type="text" class="form-control" name="address2" id="address2" placeholder="Apartment or suite">
                          </div>
                        </div>

                        <hr class="my-4">

                        <label class="form-label">Are you a:</label>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="role" value="0" id="accountType1" required />
                          <label class="form-check-label" for="accountType1">
                            Patient
                          </label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="role" value="1" id="accountType2" />
                          <label class="form-check-label" for="accountType2">
                            Doctor
                          </label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="role" value="2" id="accountType3" />
                          <label class="form-check-label" for="accountType3">
                            Nurse
                          </label>
                        </div>

                        <script type="text/javascript">
                          $('input[type=radio][name=role]').change(function() {
                            if (this.value == '1' || this.value == '2') {
                                $('#organisationNameWrapper').removeClass("d-none")
                                $('#patientTypeWrapper').addClass("d-none")
                            }
                            else {
                                $('#organisationNameWrapper').addClass("d-none")
                                $('#patientTypeWrapper').removeClass("d-none")
                            }
                          });
                        </script>

                        <div class="col-sm-12 mt-4 d-none" id="organisationNameWrapper" >
                          <label for="organisationName" class="form-label">Organisation name</label>
                          <input type="text" class="form-control" name="organizationName" id="organisationName" placeholder="" value="" required="">
                          <div class="invalid-feedback">
                            Valid organisation name.
                          </div>
                        </div>

                        <div class="col-sm-12 mt-4 d-none" id="patientTypeWrapper" >
                          <label class="form-label">Is this a:</label>
                          <div class="form-check">
                            <input class="form-check-input" type="radio" name="patientType" value="1" id="patientType1">
                            <label class="form-check-label" for="patientType1">
                              NHS visit
                            </label>
                          </div>
                          <div class="form-check d">
                            <input class="form-check-input" type="radio" name="patientType" value="2" id="patientType2">
                            <label class="form-check-label" for="patientType2">
                              Private visit
                            </label>
                          </div>
                        </div>

                        <hr class="my-4">
                        
                        <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                            <c:out value="${not empty message ? message: ''}" />
                        </div>

                        <button class="w-100 btn btn-primary btn-lg" type="submit" value="<%=str%>" >Sign Up</button>
                    </form>
                </div>
            </div>

            <%@include file='/html/footer.html'%>
        </main>
    </body>
</html>