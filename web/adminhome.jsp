<%-- 
    Document   : adminhome
    Created on : Jan 24, 2024, 3:58:37 PM
    Author     : acer
--%>

<%@page import="dto.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Account acc = (Account)session.getAttribute("loginedUser");
            if(acc == null) response.sendRedirect("mainController?action=home");
            
        
        %>
        
        
        <h4>welcome  <%=  acc.getFullname()  %>   come back</h4>
        <h4><a href="mainController?action=logout">logout</a></h4>
        <p><a href="mainController?action=getAccounts">manage accounts</a></p>
        
        
        
    </body>
</html>
