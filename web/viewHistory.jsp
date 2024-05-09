<%-- 
    Document   : viewHistory
    Created on : Feb 24, 2024, 4:56:22 PM
    Author     : acer
--%>

<%@page import="dto.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Order"%>
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
            if(acc != null){
                ArrayList<Order> list = (ArrayList)request.getAttribute("LISTORDER");
                if(list != null && list.size() > 0){
                    for(Order order:list){
        %>
                        <p>order id: <%= order.getOrderid() %></p>
                        <p>order date: <%= order.getOrderdate() %></p>
                        <p>status: <%= order.getStatus() %>[1: new, 2:processing, 3:completed, 4:cancel]</p>
                        <p>
                            <%
                                if(order.getStatus() == 1){
                            %>
                                    <form action="mainController">
                                        <input type="hidden" value="<%= order.getOrderid() %>"/>
                                        <input type="submit" value="cancelorder" name="action"/>
                                    </form>
                            
                            <%
                                }
                            %>
                        </p>
                        <table>
                            <%
                                for(OrderDetail detail: order.getList()){
                            %>
                                <tr>
                                    <td><%= detail.getItemid() %></td>
                                    <td><%= detail.getQuantity() %></td>
                                    <td><%= detail.getPrice() %></td>
                                </tr>
                            <%}%>
                        </table>
        
        <%
                    }
                }
                
            
            }else{
                out.print("bạn cần login");
                out.print("<p><a href='mainController?action=home'>home</a></p>");
            }
        %>
    </body>
</html>
