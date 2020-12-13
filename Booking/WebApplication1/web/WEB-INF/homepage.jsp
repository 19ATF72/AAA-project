<html><head><title>JSP Templates</title></head>
<body background='graphics/background.jpg'>
<table>
   <tr valign='top'><td><%@include file='sidebar.html'%></td>
      <td><table>
         <tr><td><%@include file='header.html'%></td></tr>
         <tr><td><%@include file='introduction.html'%></td></tr>
         <tr><td><%@include file='footer.html'%></td></tr>
         </table>
      </td>
   </tr>
</table>
</body></html>