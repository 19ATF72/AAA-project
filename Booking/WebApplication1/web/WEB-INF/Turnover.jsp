<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP List Users Records</title>
    </head>
<body>
    <%! int i=0;
        String str="Calculate Turnover"; 
        String url = "TurnoverController.do";
    %>
    <%
        str="Calculate Turnover"; 
        url = "TurnoverController.do";
    %>
    <div align="center">
        <form method="POST" action="<%=url%>">
            <div>
                <label class="form-check-label">
                    <caption><h2>Turnover Operations:</h2></caption>
                </label>
            </div>
        
            <div class="form-check">
              <input class="form-check-input" type="radio" name="turnoverOperation" value="turnover" id="calculateTurnover0" selected>
              <label class="form-check-label" for="recordType0">
                Calculate Turnover
              </label>
            </div>

            <div class="form-check">
              <input class="form-check-input" type="radio" name="turnoverOperation" value="private" id="calculateTurnover1" > <!-- TODO set to required if the user being created is a patient-->
              <label class="form-check-label" for="calculateTurnover1">
                Privately Paid Invoices
              </label>
            </div>
            
            <div class="form-check">
              <input class="form-check-input" type="radio" name="turnoverOperation" value="nhs" id="calculateTurnover2" > <!-- TODO set to required if the user being created is a patient-->
              <label class="form-check-label" for="calculateTurnover2">
                NHS Paid Invoices
              </label>
            </div>

            <br/>

            <div>
                <label class="form-check-label">
                <caption><h2>For selected period:</h2></caption>
                </label>
            </div>

            <div class="form-check">
                <label for="startTime">Start date:</label>
                <input type="date" id="startTime" name="startTime">
            </div>

            <div class="form-check">
                <label for="endTime">End date:</label>
                <input type="date" id="endTime" name="endTime">
            </div>

            <div>
                <p>
                    <td> <button type="submit" name="calculateTurnover" value="calculateTurnover"><%=str%></button></td>
                </p>
            </div>

        </form>
    </div>
    <div align="center">
        <p><c:out value="${not empty message ? message: ''}" /></p>
    </div>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Transactions:</h2></caption>
            <tr>
                <th>Transaction ID</th>
                <th>Collected from patient</th>
                <th>Payment Type</th>
                <th>Employee Salary</th>
                <th>Date collected</th>
                <th>Select</th>
            </tr>
            <c:forEach items="${resultTurnover}" var="row">
                <tr>
                    <td><c:out value="${row[0]}" /></td>
                    <td><c:out value="${row[1]}" /></td>
                    <td><c:out value="${row[2]}" /></td>
                    <td><c:out value="${row[3]}" /></td>
                    <td><c:out value="${row[4]}" /></td>
                    <td><input class="form-check-input" type="checkbox" value="<c:out value="${row[0]}" />" aria-label="Checkbox for following text input"></input></td>
                </tr>
            </c:forEach>
        </table>
        
        <br/>
        
        <table border="1" cellpadding="5">
            <tr>
                <th>Total Income</th>
                <td>
                <c:forEach items="${resultIncome}" var="row">
                    <c:out value="${row[0]}" />
                </c:forEach>
                </td>
            </tr>         
        </table>
        <table border="1" cellpadding="5">
            <tr>
                <th>Total Outgoings</th>
                <td>
                <c:forEach items="${resultOutgoings}" var="row">
                    <c:out value="${row[0]}" />
                </c:forEach>
                </td>
            </tr>    
        </table>
        <table border="1" cellpadding="5">
            <tr>
                <th>Profit</th>
                <td><c:out value="${not empty resultProfit ? resultProfit: ''}" /></td>
            </tr>                 
        </table>
    </div>
    
    <div align="center">

    </div>
    
    <div align="center">
        <form method="POST" action="<%=url%>">
           

            
        </form>
    </div>
    <jsp:include page="foot.jsp"/>  
</body>
</html>
