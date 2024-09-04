<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.*,it.unisa.model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

<title>Account</title>
</head>
<body>

	<% UserBean us = (UserBean) request.getSession().getAttribute("currentSessionUser");
		IndirizzoSpedizioneBean spedizione = (IndirizzoSpedizioneBean) request.getSession().getAttribute("spedizione");
		MetodoPagamentoBean pagamento = (MetodoPagamentoBean) request.getSession().getAttribute("pagamento");%>

	<%@ include file="./fragments/header.jsp" %>
	<%@ include file="./fragments/menu.jsp" %>
	
	<div id="main" class="clear">
	
	<div>
		<h2>Account</h2>
		
		<h3>Dati Personali</h3>
		<ul>
			<li>Nome: <%=us.getNome() %></li>
			<li>Cognome: <%= us.getCognome() %>
			<li>Email: <%=us.getEmail() %>
		</ul>
	</div>
	
	<div class="account">
		<h3>Indirizzo di spedizione predefinito</h3>
			<form action="account" method="post">
				<input type="hidden" name="action" value="addS">
				<input type="hidden" name="page" value="Account.jsp">
				<div class="tableRow">
				<p>Nome:</p>
				<p><input type="text" name="nome" required/></p>
				</div>	
				<div class="tableRow">
					<p>Cognome:</p>
					<p><input type="text" name="cognome" required/></p>
				</div>
				<div class="tableRow">
					<p>telefono:</p>
					<p><input type="text" name="tel" required/></p>
				</div>
				<div class="tableRow">
					<p>Indirizzo:</p>
					<p><input type="text"  name="ind" required/></p>
				</div>
				<div class="tableRow">
					<p>Cap:</p>
					<p><input type="text" name="cap" required/></p>
				</div>
				<div class="tableRow">
					<p>Provincia:</p>
					<p><input type="text" name="prov" required/></p>
				</div>
				<div class="tableRow">
					<p>Citt�:</p>
					<p><input type="text" name="citt�" required/></p>
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
					<p><input type="text" name="tit" required/></p>
				</div>	
				<div class="tableRow">
					<p>Numero:</p>
					<p><input type="text" name="numC" required/></p>
				</div>
				<div class="tableRow">
					<p>Scadenza:</p>
					<p><input type="date"  name="scad" required/></p>
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