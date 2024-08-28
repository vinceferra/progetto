<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Switch.jsp");	
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
	
<a
  href="mailto:buyintelligently@gmail.com?subject=The%20object%20of%20the%20email&body=Describe%20your%20problem">
  <br>   &#34 CLICCA QUI PER CONTATTARCI &#34
</a>
	
</body>
</html>