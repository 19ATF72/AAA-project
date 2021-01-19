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
<%@page import="java.util.ArrayList"%>
<%@include file='/html/scripts.html'%>
<style><%@include file="/css/style.css"%></style>

    <body>
        <%@include file='/html/headerSignOut.html'%>
        
        <%! int i=0; String str="employeeController"; String url = "employeeController.do"; %>
        <% str="employeeController"; url = "employeeController.do"; %>

        <div class="container-fluid">
            
            <form method="POST" action="<%=url%>"> 
                <div class="row" >
                    
                    <%@include file='/html/patientSidebar.html'%>

                    <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                        <div class="row row-cols-1 row-cols-md-3 mb-3 mt-4">
                            <div class="col-md-6 offset-md-2">
                                <div class="card mb-4 shadow-sm">
                                    <div class="card-header">
                                      <h1 class="h3 mt-1 fw-normal text-center">Create Prescription</h1>
                                    </div>
                                    <div class="card-body">
                                        <form method="POST" action="<%=url%>">
                                            <div class="text-center">
                                                <i class="fas fa-file-prescription" style="font-size:90px"></i>
                                            </div>
                                            <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                              <c:out value="${not empty message ? message: ''}" />
                                            </div>
                                            
                                            <div class="col-12 mt-3">
                                                <label for="prescription" class="form-label">Prescription details:</label>
                                                <input type="text" class="form-control" id="prescription" name="prescription" placeholder="E.G Name of medicine / dosage">
                                            </div>

                                            <div class="col-12 mt-2">
                                                <input id="repeat" name="repeat" type="checkbox" value="repeat" class="form-check-input" required="">
                                                <label class="form-check-label" for="repeat">Repeat prescription</label>
                                            </div>
                                            
                                            <div class="col-12 mt-3">
                                                <label for="notes" class="form-label">Notes:</label>
                                                <input type="text" class="form-control" id="notes" name="notes" placeholder="Enter notes">
                                            </div>

                                            <button type="submit" name="employeeOperation" value="recorded" class="btn btn-primary mt-3">Create prescription</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>  
                    </main>
                </div>
            </form>
            <%@include file='/html/footer.html'%>
        </div>
    </body>
</html>