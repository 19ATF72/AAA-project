<%@page import="java.sql.Date"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDate"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <%@include file='/html/scripts.html'%>
   <style><%@include file="/css/style.css"%></style>

   <body>
      <%! int i=0;
         String str="List Patients"; 
         String url = "PatientController.do";
         %>
      <%
         str="List Patients"; 
         url = "PatientController.do";
         %>
      <%
         LocalDate todaysDate = LocalDate.now();
         
          %>
      <jsp:include page="/html/headerSignOut.html"/>
      <main>
         <div class="container-fluid">
            <div class="row">
               <jsp:include page="/html/employeeSidebar.html"/>
               <main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
                  <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
                     <div>
                        <label class="form-check-label">
                           <caption>
                              <h2>Book Appointment:</h2>
                           </caption>
                        </label>
                     </div>
                  </div>
                  <br>
                  <div class="col-md-4">
                     <div>
                        <h5>Select a medical professional:</h5>
                     </div>
                     <form method="POST" action="<%=url%>">
                        <div class="dropdown">
                           <select class="form-select" name="docChoice" value="" required>
                              <c:forEach items="${doctors}" var="row">
                                 <tr>
                                    <option class="dropdown-item" value="<c:out value="${row[0]}" />">
                                    (<c:out value="${row[3]}" />) 
                                    <c:out value="${row[1]}" />
                                    <c:out value="${row[2]}" />
                                    </option>
                                 </tr>
                              </c:forEach>
                           </select>
                           <br>
                           <h5>Select an appointment date:</h5>
                           <%
                              String bookingDate = request.getParameter("bookingDate");
                              if(bookingDate == null){
                              %>   
                           <input type="date" name="bookingDate" id="datePicker" min="<%= todaysDate%>" required>
                           <%
                              }
                              else { 
                              %>
                           <input type="date" value="<%= bookingDate%>" name="bookingDate" id="datePicker" min="<%= todaysDate%>" required>
                           <%
                              } 
                              %>                     
                        </div>
                        <br>
                        <button class="btn btn-primary" name="patientOperation" value="choosen" type="submit" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Find available appointment</button>
                     
                     <c:choose>
                        <c:when test="${dayOfWeek eq 7 || dayOfWeek eq 1}">
                           <br>
                           <div class="alert alert-danger" role="alert">
                              Please select a working day. (Monday - Friday)
                           </div>
                           <br>
                        </c:when>
                        <c:otherwise>

                  </div>
                  <div class="time_select">
                  <c:if test="${dateSelected}">
                  <br>
                  <h5>Select a time slot:</h5>
                  <select name="chosenTime" class="form-select" multiple aria-label="multiple select example">
                  <table>
                  <c:forEach items="${lengths}" var="row">
                  <tr>
                  <td>
                  <label>
                  <c:out value="${row[0]}" />
                  </label>
                  </td>
                  <c:forEach items="${row[1]}" var="col">
                  <td>
                  <option type="checkbox" data-toggle="toggle" value="<c:out value="${col[0]},${col[1]},${col[2]}" />">
                  <label>
                  <c:out value="${col[1]}" />
                  - 
                  <c:out value="${col[2]}" />
                  </label>
                  </option>
                  </td>
                  </c:forEach>
                  </tr>
                  </c:forEach>
                  </table>
                  </select>
                  </div>
                  <br>
                  <h5>Appointment details:</h5>
                  <h9>400 Character Limit</h9>
                  <div class="input-group">
                  <textarea class="form-control" name="appointmentNotes" aria-label="With textarea"></textarea>
                  </div>           
                  <br>
                  <button class="btn btn-primary" name="patientOperation" value="booked" type="submit" id="menuButton"  aria-haspopup="true" aria-expanded="false">Book Appointment</button>
                  </form>
            </div>
            </c:if>
            </c:otherwise>
            </c:choose>
            </main>
         </div>
         </div>
         <jsp:include page="/html/footer.html"/>
      </main>
   </body>
</html>