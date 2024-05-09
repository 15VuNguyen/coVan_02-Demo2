<%-- 
    Document   : accountpage
    Created on : Jan 24, 2024, 5:03:00 PM
    Author     : acer
--%>

<%@page import="dto.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Account loginacc = (Account)session.getAttribute("loginedUser");
            if(loginacc == null) response.sendRedirect("mainController?action=home");
            
        
        %>
        <h4>welcome  <%=  loginacc.getFullname()  %>   come back</h4>
        <h4><a href="mainController?action=logout">logout</a></h4> 
        <!--làm cách copy từng page
        hoặc có phương pháp tốt hơn là tạo một trang jsp tên là header 
        dể mỗi khi admin xuất hiện thì sử dụng @inlcude để copy code của header 
        vào đầu của mỗi trang nào admin tới-->
        
        <form action="mainController" method="post">
            <input type="hidden" name="action" value="searchaccount" />
            <!--cách tracking 2 thì là đường link getRequestDispatcher nên là getParameter-->
            <input type="text" name="txtsearch" value="<%= (request.getParameter("findname") != null) ? request.getParameter("findname") : ""  %>"/>
            <input type="submit" value="go"/>
        </form>
        
        <%
            ArrayList<Account> list = (ArrayList)request.getAttribute("ListAccount");
            if(list != null && list.size() > 0){
        %>
            <table>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>EMAIL</th>
                    <th>STATUS</th>
                    <th>ACTION</th>
                </tr>
                
                <%
                    for (Account acc : list) {
                %> 
                <form action="mainController">
                    <input type="hidden" name="txtaccid" value="<%=  acc.getAccid()  %>"/>
                <tr>
                    <td> <%=  acc.getAccid() %>    </td>
                    <td> <%=  acc.getFullname()  %>    </td>
                    <td> <%=  acc.getEmail()  %>    </td>
                    <td> <%=  acc.isStatus()%>    </td>
                    <td><input type='submit' value='removeaccount' name="action" onclick="return window.confirm('are u sure?')" >
                        <% if(loginacc.getAccid() != acc.getAccid()){ %>
                            <input type='submit' value='block/unblock' name="action" onclick="return window.confirm('are u sure?')" >
                        <%  } %>
                    </td>
                </tr>
                </form>
                <%
                        }
                %>
            </table>
        <%
            }


        %>
        
        
    </body>
</html>
