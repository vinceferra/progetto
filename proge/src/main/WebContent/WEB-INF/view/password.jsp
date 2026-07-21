<!-- changePassword.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">	
    <title>Cambio Password</title>
</head>
<body>
	<!-- Display error message if present -->
	
	
<%
  String errorMessage =(String) request.getAttribute("errorMessage");
  if (errorMessage != null && !errorMessage.isBlank()) {
%>
<div style="color:red;">
    <%= errorMessage %>
</div>
<%}%>

    <div id="main" class="clear">
    <h2>Cambia la tua Password</h2>
    
    <form action="${pageContext.request.contextPath}/account" method="post">
    <input type="hidden" name="action" value="Cambia Password">
	<input type="hidden" name="page" value="impostazioni.jsp">
        <label for="currentPassword">Password Attuale:</label><br>
        <input type="password" id="currentPassword" name="currentPassword" required><br><br>
        
        <label for="newPassword">Nuova Password:</label><br>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        
        <label for="confirmPassword">Conferma Nuova Password:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
        
        <input type="submit" value="Cambia Password">
    </form>
    </div>
</body>
</html>