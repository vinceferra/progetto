<!-- changePassword.jsp -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Cambio Password</title>
</head>
<body>
    <h2>Cambia la tua Password</h2>
    <form action="changePasswordProcess.jsp" method="post">
        <label for="currentPassword">Password Attuale:</label><br>
        <input type="password" id="currentPassword" name="currentPassword" required><br><br>
        
        <label for="newPassword">Nuova Password:</label><br>
        <input type="password" id="newPassword" name="newPassword" required><br><br>
        
        <label for="confirmPassword">Conferma Nuova Password:</label><br>
        <input type="password" id="confirmPassword" name="confirmPassword" required><br><br>
        
        <input type="submit" value="Cambia Password">
    </form>
</body>
</html>