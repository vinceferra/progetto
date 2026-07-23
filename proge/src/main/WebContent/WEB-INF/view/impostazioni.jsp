<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.*"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">

<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<link rel="icon"  href="${pageContext.request.contextPath}/images/favicon.ico">      
<title>Impostazioni</title>

</head>

<body>

<%
    UserBean us =(UserBean) request.getSession().getAttribute("currentSessionUser");
%>

<%@ include file="./fragments/header.jsp" %>
<%@ include file="./fragments/menu.jsp" %>

<div id="main" class="clear">

    <div>

        <h2>Impostazioni</h2>

        <h3>Dati Personali</h3>

        <ul>
            <li><span><b>Nome:</b></span> <%= us.getNome() %></li>
            <br>
            <li><span><b>Cognome:</b></span> <%= us.getCognome() %></li>
            <br>
            <li><span><b>Email:</b></span> <%= us.getEmail() %></li>
            <br>
            <li>
                <form action="${pageContext.request.contextPath}/account" method="get">
                      <input type="hidden" name="page" value="password.jsp">
                      <button type="submit">CAMBIA PASSWORD</button>
                </form>
            </li>
        </ul>
        
    </div>

</div>


<%@ include file="./fragments/footer.jsp" %>
<script src="${pageContext.request.contextPath}/script/checkout.js"></script>
</body>
</html>