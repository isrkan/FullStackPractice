<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order</title>
</head>
<body>
    <h1>Place your order</h1>
    <form action="/myapp/order" method="post">
        <label for="item">Select item:</label>
        <select id="item" name="item">
            <option value="Pizza">Pizza</option>
            <option value="Burger">Burger</option>
            <option value="Salad">Salad</option>
        </select>
        <br>
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity" min="1" value="1">
        <br>
        <input type="submit" value="Order">
    </form>
    <%-- Use Java code to determine the message based on the selected item --%>
    <%
        String selectedItem = request.getParameter("item");
        String message = "";
        if ("Pizza".equals(selectedItem)) {
            message = "Our pizzas are freshly made with the finest ingredients!";
        } else if ("Burger".equals(selectedItem)) {
            message = "Try our juicy burgers for a satisfying meal!";
        } else if ("Salad".equals(selectedItem)) {
            message = "Enjoy our healthy and delicious salads!";
        }
    %>
    <p><%= message %></p>

    <a href="${pageContext.request.contextPath}/myapp/menu">Back to menu</a>
</body>
</html>