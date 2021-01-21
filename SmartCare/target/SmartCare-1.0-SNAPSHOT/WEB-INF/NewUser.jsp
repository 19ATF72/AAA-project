<%-- 
    Document   : driver
    Created on : 16-Jan-2020, 15:18:08
    Author     : micah
--%>
<%@page import="model.Dao.DynamicDao"%>
<%@page import="java.sql.SQLException"%>
<%@page import="model.Entity.OrganisationEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Service.OrganisationService"%>
<!DOCTYPE html>
<html lang="en">
<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.time.LocalDate"%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file='/html/scripts.html'%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<style><%@include file="/css/style.css"%></style>
<%
         LocalDate todaysDate = LocalDate.now();
         
          %>
    <body>
        <%@include file='/html/headerSignIn.html'%>
        
        <%! int i=0; String str="Register"; String url = "NewUserController.do"; %>
        <% if((String)request.getAttribute("msg")=="del") { str = "Delete"; url = "Delete.do"; }
           else { str="Register"; url = "login_newAccReg"; } %>
            
        <main class="container">
            <div class="container mt-4">
                <div class="col-md-8 offset-md-2 col-lg-8">
                    <h4 class="mb-3">User Sign up</h4>
                    <form class="needs-validation" novalidate="" method="POST" action="<%=url%>" >
                        <div class="row g-3">

                            <div class="col-12">
                                <label for="email" class="form-label">Email</label>
                                <input type="email" class="form-control" name="email" id="email" placeholder="email@address.com" required="true">
                                <div class="invalid-feedback">
                                  Please enter a valid email address.
                                </div>
                            </div>

                            <div class="col-12">
                                <label for="inputPassword" class="visually-hidden">Password</label>
                                <input type="password" name="password" id="password" class="form-control" placeholder="Password" required="true">
                                <div class="invalid-feedback">
                                  Please enter a valid password.
                                </div>
                            </div>

                            <div class="col-md-2">
                                <label for="prefix" class="form-label">Prefix</label>
                                <select class="form-select" id="prefix" name="userPrefix" required="true">
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
                                <input type="text" class="form-control" name="userFirstName" id="firstname" placeholder="Bobby" value="" required="true">
                                <div class="invalid-feedback">
                                  First name is required
                                </div>
                            </div>
                            
                            <div class="col-sm-5">
                                <label for="lastName" class="form-label">Last Name</label>
                                <input type="text" class="form-control" name="userSurname" id="lastname" placeholder="Tables" value="" required="true">
                                <div class="invalid-feedback">
                                  Last name is required
                                </div>
                            </div>
                            
                            <div class="col-sm-6">
                                <label for="dateofbirth" class="form-label" required="true">Date of birth</label>
                                <input type="date" max="<%= todaysDate%>" class="form-control" id="dateofbirth" name="dateOfBirth" required="true">
                            </div>

                            <div class="col-sm-6">
                                <label for="telephone" class="form-label">Telephone</label>
                                <input type="tel" class="form-control" id="telephone" name="telephone" required="true">
                            </div>
                            
                            <div class="col-12">
                                <!-- Postcode field -->
                                <label for="line1" class="form-label">Postcode search</label>
                                <div id="postcode_lookup"></div>      
                            </div>

                            <div class="col-12">
                                <label for="line1" class="form-label">First Address Line</label>
                                <input type="text" class="form-control" name="houseNumber" id="line1" placeholder="House No." required="true">
                            </div>

                            <div class="col-12">
                                <label for="line2" class="form-label">Second Address Line</label>
                                <input type="text" class="form-control" name="line2" id="line2" placeholder="Street" required="">
                            </div>
                            <div class="col-12">
                                <label for="town" class="form-label">Town</label>
                                <input type="text" class="form-control" name="town" id="town" placeholder="Bristol" required="true">
                            </div>

                            <div class="col-12">
                                <label for="town" class="form-label">County</label>
                                <input type="text" class="form-control" name="county" id="county" placeholder="Avon" required="true">
                            </div>

                            <div class="col-12">
                                <label for="town" class="form-label">Postcode</label>
                                <input type="text" class="form-control" name="postcode" id="postcode" placeholder="BS107AE" required="true">
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
                          <input class="form-check-input" type="radio" name="role" value="patient" id="accountType1" required />
                          <label class="form-check-label" for="accountType1">
                            Patient
                          </label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="role" value="doctor" id="accountType2" />
                          <label class="form-check-label" for="accountType2">
                            Doctor
                          </label>
                        </div>
                        <div class="form-check">
                          <input class="form-check-input" type="radio" name="role" value="nurse" id="accountType3" />
                          <label class="form-check-label" for="accountType3">
                            Nurse
                          </label>
                        </div>

                        <script type="text/javascript">
                          $('input[type=radio][name=role]').change(function() {
                            if (this.value == 'doctor' || this.value == 'nurse') {
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
                          <label for="organisationName" class="form-label">Select An Organisation</label>
                          <div class="dropdown">
                              <select class="form-select" name="docChoice" value="" required>
                           <c:forEach items="${organisations}" var="organisation">
                                 <tr>
                                    <option class="dropdown-item" value="<c:out value="${organisation.getOId()}" />">
                                    <c:out value="${organisation.getName()}" />
                                    </option>
                                 </tr>
                              </c:forEach>
                            </select>
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

                        <button class="w-100 btn btn-primary btn-lg" type="submit" required="true" value="<%=str%>" >Sign Up</button>
                    </form>
                </div>
            </div>

            <%@include file='/html/footer.html'%>
        </main>
    </body>
</html>