<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Restaurant menu</title>
</head>
<body>
    <h1>Restaurant menu</h1>
        <%-- Display a welcome message based on the time of the day --%>
        <%
            // Get the current hour of the day
            java.util.Calendar cal = java.util.Calendar.getInstance();
            int hour = cal.get(java.util.Calendar.HOUR_OF_DAY);
            String welcomeMessage = "Welcome";
            if (hour >= 5 && hour < 12) {
                welcomeMessage = "Good morning";
            } else if (hour >= 12 && hour < 18) {
                welcomeMessage = "Good afternoon";
            } else {
                welcomeMessage = "Good evening";
            }
        %>
        <p><%= welcomeMessage %>, enjoy our menu! </p>
    <ul>
        <li><a href="${pageContext.request.contextPath}/myapp/menu/${item1}">${item1}</a></li>
        <li><a href="${pageContext.request.contextPath}/myapp/menu/${item2}">${item2}</a></li>
        <li><a href="${pageContext.request.contextPath}/myapp/menu/${item3}">${item3}</a></li>
    </ul>
    <a href="${pageContext.request.contextPath}/myapp/order">Place an order</a>
    <br>
        <h2>Special offers:</h2>
        <ul>
            <li><a href="${pageContext.request.contextPath}/myapp/specials">General special deals</a></li>
            <li><a href="${pageContext.request.contextPath}/myapp/specials?discount=student">Student special deals</a></li>
            <li><a href="${pageContext.request.contextPath}/myapp/specials?discount=senior">Senior special deals</a></li>
        </ul>
</body>
</html>