<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Specials</title>
</head>
<body>
    <h1>Special offers</h1>
    <ul>
        <li>${special1}</li>
        <li>${special2}</li>
        <li>${special3}</li>
    </ul>
    <a href="${pageContext.request.contextPath}/myapp/menu">Back to Menu</a>
</body>
</html>
