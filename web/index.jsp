<%-- 
    Document   : index
    Created on : Jan 24, 2024, 3:42:00 PM
    Author     : acer
--%>

<%@page import="dto.Item"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./mystyle.css"/>
    </head>
    <body>
        <form action="mainController" method="post">
            <!--ẩn để không nhìn thấy nó trên giao diện,
            hidden với control ẩn là action,
            để pass data với value=login
            vào mainController mà không lộ trên màn hình -->
            <input type="hidden" name="action" value="login"/>
            <p><input type="text" name="txtemail"/></p>
            <p><input type="password" name="txtpassword"/></p>
            <p><input type="submit" value="login"/></p>
        </form>
        
        <%
            String msg = (String)request.getAttribute("ERROR");
        %>
        <!-- để trường hợp != null vì sợ rằng trang này được mở đầu tiên 
        nên sẽ không có gì -->
        <p style="color:red"><%=  (msg != null) ? msg : ""   %></p>
        
        <hr/>
        
        <header>
            <nav>
                <ul>
                    <li><a href="mainController?action=home">Home</a></li>
                    <li><a href="">Register</a></li>
                    <li><a>Contact</a></li>
                    <li><a href="mainController?action=viewcart">view cart</a></li>
                    <li><form action="mainController" method="post">
                            <input type="text" name="txtTopic" value="<%= (request.getParameter("topic") != null) ? request.getParameter("topic"): ""  %>"/>
                            <input type="submit" value="find" name="action"/>
                    </form></li>
                </ul>
            </nav>
        </header>

        <section>
            <!--chỗ này để hiện thị các item của web-->
            <%
                ArrayList<Item> list = (ArrayList)request.getAttribute("ListItems");
                if(list != null && list.size() > 0){
                    for (Item it : list) {
            %>
            <div>
                <form action="mainController">
                    <input type="hidden" value="<%= it.getItemid() %>" name="txtItemId" />
                    <img src="<%= it.getImageurl()  %> " />
                    <br/>
                    <%= it.getItemname() %>
                    <br/>
                    <%= it.getPrice() %>
                    <input type="submit" value="buy" name="action"/>
                </form>
            </div>
            
            <%
                    }
                }


            %>

        </section>
        
        <footer>
            <!--chỗ này hiển thị quảng cáo nếu client accept cookie-->
            <%
                String s = (String)request.getAttribute("QC");
                if(s != null){
            %>
            <h2><%= s %></h2>
            <%
                }

            %>
        
        </footer>
        
        
    </body>
</html>
