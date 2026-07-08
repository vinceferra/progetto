<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Aiuto.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>
<%@ include file="../fragments/header.jsp" %>
<div align=center>	
<a
  href="https://mail.google.com/mail/?view=cm&fs=1&to=vincyferry97@gmail.com&su=The%20object%20of%20the%20email&body=Describe%20your%20problem" target="_blank" style="color:red">
  <br><br><br><br><br>   &#34 <u>CLICCA QUI PER CONTATTARCI</u> &#34
</a>
</div>
</body>
</html>