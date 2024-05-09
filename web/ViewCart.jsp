<%-- 
    Document   : ViewCart
    Created on : Feb 21, 2024, 4:08:30 PM
    Author     : acer
--%>

<%@page import="dto.Account"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.HashMap"%>
<%@page import="dto.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./mystyle.css"/>
    </head>
    <body>
        <header>
            <nav>
                <ul>
                    <li><a href="mainController?action=home">Home</a></li>
                    <li><a href="">Register</a></li>
                    <li><a>Contact</a></li>
                    <li><a href="mainController?action=history">order history</a></li>
                    <li><a href="mainController?action=viewcart">view cart</a></li>
                    <li><form action="mainController" method="post">
                            <input type="text" name="txtTopic" value="${param.topic}"/>
                            <input type="submit" value="find" name="action"/>
                    </form></li>
                    
                    
                    
                    <li>welcome ${sessionScope.loginedUser.fullname}</li>
                </ul>
            </nav>
        </header>
    <session>
        <h1>Thong tin gio hang cua ban</h1>
        <c:set var="giohang" value="${sessionScope.cart}" />
        <c:if test="${giohang != null}">
            <table>
                <tr>
                    <th>ID</th>
                    <th>NAME</th>
                    <th>IMAGE</th>
                    <th>PRICE</th>
                    <th>QUANTITY</th>
                    <th>ACTION</th>
                </tr>
                <c:set var="total" value="0" />
                
                <c:set var="list" value="${giohang.keySet()}" />
                <c:forEach var="it" items="${list}">
                    <tr><form action="mainController">
                    <input type="hidden" value="${it.itemid}" name="txtitemid" >
                        <td>${it.itemid}</td>
                        <td>${it.itemname}</td>
                        <td><img src="${it.imageurl}"></td>
                        <td>${it.price}</td>
                        <td><input type="number" value="${ giohang.get(it) }" name="txtquantity"></td>
                        <td>
                            <input type="submit" value="update" name="action"/>
                            <input type="submit" value="remove" name="action"/>
                        </td>
                    </form></tr>
                    <c:set var="total" value="${total + giohang.get(it)*it.price}"/>
                </c:forEach>
            </table>
        </c:if>
        
        <h4>Tong tien thanh toan: ${total}   dong</h4>
        <h4>Ngay dat:     </h4>
        <h4>Ngay giao du kien: 7 ngay</h4>
        <form action="mainController">
            <input type="hidden" value="${total}" name="txttotal" />
            <h4><input type="submit" value="payment" name="action"></h4>
        </form>
        
            <c:if test="${giohang == null}">
                <h2>gio hang trong</h2>
            </c:if>
        
        
    </session>
            <h3>${requestScope.ERROR}</h3>
    </body>
</html>
