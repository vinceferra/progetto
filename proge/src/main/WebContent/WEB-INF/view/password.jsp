<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">

    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

    <title>Cambio Password</title>
</head>

<body>

<%
String errorMessage = (String) request.getAttribute("errorMessage");

if (errorMessage != null && !errorMessage.isBlank()) {
%>

<div class="error">
    <%= errorMessage %>
</div>

<%
}
%>

<div id="main" class="clear">

    <h2>Cambia la tua Password</h2>

    <form action="${pageContext.request.contextPath}/account" method="post" id="passwordForm">

        <input type="hidden" name="action" value="Cambia Password">

        <input type="hidden" name="page" value="impostazioni.jsp">

        <label for="currentPassword">Password Attuale:</label>
        <br>

        <input type="password" id="currentPassword" name="currentPassword" required>

        <p id="errCurrentPassword"></p>

        <label for="newPassword">Nuova Password:</label>
        <br>

        <input type="password" id="newPassword" name="newPassword" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z0-9]).{8,}$"
       title="Minimo 8 caratteri, una maiuscola, una minuscola, un numero e un carattere speciale" required>

        <p id="errNewPassword"></p>

        <label for="confirmPassword">Conferma Nuova Password:</label>
        <br>

        <input type="password" id="confirmPassword" name="confirmPassword" required>

        <p id="errConfirmPassword"></p>
        
        <input type="submit" value="Cambia Password">
        
    </form>
</div>

<script src="${pageContext.request.contextPath}/script/password.js"></script>

</body>
</html>