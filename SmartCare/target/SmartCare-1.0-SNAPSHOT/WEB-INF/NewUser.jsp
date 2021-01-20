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

                            <div class="col-md-2">
                                <label for="prefix" class="form-label">Prefix</label>
                                <select class="form-select" id="prefix" name="prefix" required="">
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
                            
                            <div class="col-sm-5">
                                <label for="firstName" class="form-label">First Name</label>
                                <input type="text" class="form-control" name="firstname" id="firstname" placeholder="Bobby" value="" required="">
                                <div class="invalid-feedback">
                                  First name is required
                                </div>
                            </div>
                            
                            <div class="col-sm-5">
                                <label for="lastName" class="form-label">Last Name</label>
                                <input type="text" class="form-control" name="lastname" id="lastname" placeholder="Tables" value="" required="">
                                <div class="invalid-feedback">
                                  Last name is required
                                </div>
                            </div>
                            
                            <div class="col-sm-6">
                                <label for="dateofbirth" class="form-label">Date of birth</label>
                                <input type="date" class="form-control" id="dateofbirth" name="dateofbirth">
                            </div>

                            <div class="col-sm-6">
                                <label for="telephone" class="form-label">Telephone</label>
                                <input type="tel" class="form-control" id="telephone" name="telephone">
                            </div>
                            
                            <div class="col-12">
                                <!-- Postcode field -->
                                <label for="line1" class="form-label">Postcode search</label>
                                <div id="postcode_lookup"></div>      
                            </div>

                            <div class="col-12">
                                <label for="line1" class="form-label">First Address Line</label>
                                <input type="text" class="form-control" name="line1" id="line1" placeholder="1234 Main St" required="">
                            </div>

                            <div class="col-12">
                                <label for="line2" class="form-label">Second Address Line</label>
                                <input type="text" class="form-control" name="line2" id="line2" placeholder="Test Street" required="">
                            </div>

                            <div class="col-12">
                                <label for="line3" class="form-label">Third Address Line</label>
                                <input type="text" class="form-control" name="line3" id="line3" placeholder="" required="">
                            </div>

                            <div class="col-12">
                                <label for="town" class="form-label">Town</label>
                                <input type="text" class="form-control" name="town" id="town" placeholder="Bristol" required="">
                            </div>

                            <div class="col-12">
                                <label for="town" class="form-label">County</label>
                                <input type="text" class="form-control" name="county" id="county" placeholder="Avon" required="">
                            </div>

                            <div class="col-12">
                                <label for="town" class="form-label">Postcode</label>
                                <input type="text" class="form-control" name="postcode" id="postcode" placeholder="BS107AE" required="">
                                <div class="invalid-feedback">
                                  Please enter a valid postcode.
                                </div>
                            </div>
                        </div>

                        <!-- Add after your form -->
                        <script>
                        $('#postcode_lookup').getAddress(
                            {
                            api_key: 'DJ-7kyPlrkuXidoZT_c4EQ25507',  
                            output_fields:{
                                line_1: '#line1',
                                line_2: '#line2',
                                line_3: '#line3',
                                post_town: '#town',
                                county: '#county',
                                postcode: '#postcode'
                            }
                        });
                        </script>

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