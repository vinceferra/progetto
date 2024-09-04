<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

<title>Impostazioni</title>
</head>
<body>

	<% UserBean us = (UserBean) request.getSession().getAttribute("currentSessionUser");
		IndirizzoSpedizioneBean spedizione = (IndirizzoSpedizioneBean) request.getSession().getAttribute("spedizione");
		MetodoPagamentoBean pagamento = (MetodoPagamentoBean) request.getSession().getAttribute("pagamento");%>

	<%@ include file="./fragments/header.jsp" %>
	<%@ include file="./fragments/menu.jsp" %>
	
	<div id="main" class="clear">
	
	<div>
		<h2>Impostazioni</h2>
		
		<h3>Dati Personali</h3>
		<ul>
			<li>Nome: <%=us.getNome() %></li>
			<li>Cognome: <%= us.getCognome() %></li>
			<li>Email: <%=us.getEmail() %></li>
			<li>
			     <form action="password.jsp" method="get">
			     <button type="submit">CAMBIA PASSWORD</button>
			</li>
		</ul>
	</div>
	
	<div class="account">
		<h3>Indirizzo di spedizione predefinito</h3>
			<form action="account" method="post">
				<input type="hidden" name="action" value="addS">
				<input type="hidden" name="page" value="impostazioni.jsp">
				<div class="tableRow">
				<p>Nome:</p>
				<p><input type="text" name="nome" /></p>
				</div>	
				<div class="tableRow">
					<p>Cognome:</p>
					<p><input type="text" name="cognome" /></p>
				</div>
				<div class="tableRow">
					<p>telefono:</p>
					<p><input type="text" name="tel" /></p>
				</div>
				<div class="tableRow">
					<p>Indirizzo:</p>
					<p><input type="text"  name="ind" /></p>
				</div>
				<div class="tableRow">
					<p>Cap:</p>
					<p><input type="text" name="cap" /></p>
				</div>
				<div class="tableRow">
					<p>Provincia:</p>
					<p><input type="text" name="prov" /></p>
				</div>
				<div class="tableRow">
					<p>Città:</p>
					<p><input type="text" name="città" /></p>
				</div>
				<div class="tableRow">
					<p></p>
					<p><input type="submit" value="aggiungi"></p>
				</div>			
			</form>
	</div>
	
	<div class="account">
		<h3>Metodo di pagamento predefinito</h3>
			<form action="account" method="post">
				<input type="hidden" name="action" value="addP">
				<div class="tableRow">
					<p>Titolare carta:</p>
					<p><input type="text" name="tit" /></p>
				</div>	
				<div class="tableRow">
					<p>Numero:</p>
					<p><input type="text" name="numC" /></p>
				</div>
				<div class="tableRow">
					<p>Scadenza:</p>
					<p><input type="date"  name="scad" /></p>
				</div>
				<div class="tableRow">
					<p></p>
					<p><input type="submit" value="aggiungi"></p>		
				</div>
			</form>

	</div>
	</div>
		
	<%@ include file="./fragments/footer.jsp" %>
	
</body>
</html>