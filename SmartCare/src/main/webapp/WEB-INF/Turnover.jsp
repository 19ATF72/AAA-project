<%-- 
   Document   : driver
   Created on : 17-Jan-2020, 18:12:08
   Author     : George
   --%>
<!DOCTYPE html>
<html lang="en">
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file='/html/scripts.html'%>
    <%@page import="java.time.LocalDate"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
    <%@page import="java.util.ArrayList"%>
    <style><%@include file="/css/style.css"%></style>
    <title>SmartCare - Turnover</title>
    <link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />
   
        <head>
            <link rel="shortcut icon" href="icons/favicon.ico?" type="image/x-icon" />
        </head>
    
        <body>
       
            <%@include file='/html/headerSignIn.html'%>
            <%! int i=0; String str="Calculate Turnover"; String url = "TurnoverController.do"; %>
            <% str="Calculate Turnover"; url = "TurnoverController.do"; %>
            <% LocalDate todaysDate = LocalDate.now(); %>
         
                <div class="container-fluid">
                    <div class="row">
                    
                        <%@include file='/html/employeeSidebar.html'%>
                    
                        <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">

                            <form method="POST" action="<%=url%>">

                                <div class="row">
                                    <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                                        <h1 class="h2">Turnover Operations:</h1>
                                    </div>
                                    <div class="col-md-4">
                                        <h4>By patient type</h4>
                                        <div class="form-check">
                                           <input class="form-check-input" type="radio" name="turnoverOperation" value="turnover" id="calculateTurnover0" >
                                           <label class="form-check-label" for="recordType0">Calculate Turnover</label>
                                        </div>
                                        <div class="form-check">
                                           <input class="form-check-input" type="radio" name="turnoverOperation" value="private" id="calculateTurnover1" > <!-- TODO set to required if the user being created is a patient-->
                                           <label class="form-check-label" for="calculateTurnover1">Privately Paid Invoices</label>
                                        </div>
                                        <div class="form-check">
                                           <input class="form-check-input" type="radio" name="turnoverOperation" value="nhs" id="calculateTurnover2" > <!-- TODO set to required if the user being created is a patient-->
                                           <label class="form-check-label" for="calculateTurnover2">NHS Paid Invoices</label>
                                        </div>
                                    </div>

                                    <div class="col-md-4">
                                        <h4>For selected period</h4>
                                        <div class="form-check">
                                           <label for="startTime">Start date:</label>
                                           <input type="date" id="startTime" name="startTime" max="<%= todaysDate%>" required>
                                        </div>
                                        <div class="form-check">
                                           <label for="endTime">End date:</label>
                                           <input type="date" id="endTime" name="endTime" max="<%= todaysDate%>" required>
                                        </div>
                                    </div>
                                    <div class="col-12 mb-2 mt-3">
                                        <button type="submit" class="w-100 btn btn-outline-primary btn-lg"  name="calculateTurnover"  value="calculateTurnover" ><%=str%></button>
                                    </div>
                                </div>
                            </form>

                            <div class="alert alert-secondary ${not empty message ? message: 'd-none'}" role="alert">
                                <c:out value="${not empty message ? message: ''}" />
                            </div>

                            <div class="table-responsive">
                               <table class="table table-striped table-sm">
                                  <thead>
                                     <tr>
                                        <th>Transaction ID:</th>
                                        <th>Collected from patient:</th>
                                        <th>Payment Type:</th>
                                        <th>Employee Salary:</th>
                                        <th>Date collected:</th>
                                        <th>Select:</th>
                                     </tr>
                                  </thead>
                                  <tbody>
                                     <c:forEach items="${resultTurnover}" var="row">
                                        <tr>
                                           <td>
                                              <c:out value="${row[0]}" />
                                           </td>
                                           <td>
                                              <c:out value="${row[1]}" />
                                           </td>
                                           <td>
                                              <c:out value="${row[2]}" />
                                           </td>
                                           <td>
                                              <c:out value="${row[3]}" />
                                           </td>
                                           <td>
                                              <c:out value="${row[4]}" />
                                           </td>
                                           <td>
                                              <input class="form-check-input" type="checkbox" value="
                                              <c:out value="${row[0]}" />
                                              " aria-label="Checkbox for following text input"></input>
                                           </td>
                                        </tr>
                                     </c:forEach>
                                  </tbody>
                               </table>
                            </div>


                            <h2>Total:</h2>
                            <div class="table-responsive">
                                <table class="table" style=width:25%;>
                                    <tbody>
                                        <tr>
                                            <th>Income</th>
                                            <td>
                                               <c:forEach items="${resultIncome}" var="row">
                                                  <c:out value="${row[0]}" />
                                               </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Outgoing</th>
                                            <td>
                                               <c:forEach items="${resultOutgoings}" var="row">
                                                  <c:out value="${row[0]}" />
                                               </c:forEach>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Profit</th>
                                            <td>
                                               <c:out value="${not empty resultProfit ? resultProfit: ''}" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </main>
                    </div>
                </div>
            <jsp:include page="/html/footer.html"/>  
        </body>
</html>
