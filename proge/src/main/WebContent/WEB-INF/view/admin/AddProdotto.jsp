<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="it.unisa.model.UserBean" %>
    
 <%
    UserBean User = (UserBean) session.getAttribute("currentSessionUser");

    if (User== null || !User.isAmministratore()) {
    	response.sendRedirect(request.getContextPath() + "/Login");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	<link rel="icon"  href="${pageContext.request.contextPath}/images/favicon.ico">

<title>Aggiungi prodotto</title>
</head>
<body>


	<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	
	<div id="main" class="clear">
	
		<h2>AGGIUNGI PRODOTTO</h2>
		
<%
String erroreCatalogo = (String) request.getAttribute("erroreCatalogo");
if (erroreCatalogo != null) {
%>
<p class="error"><%= erroreCatalogo %></p>
<%}%>

	<form action="${pageContext.request.contextPath}/catalogo" method="post" id="myform">
		<input type="hidden" name="action" value="add">
		<input type="hidden" name="page" value="admin/GestioneCatalogo.jsp"><br><br>
		<div class="tableRow">
			<p>Nome:</p>
			<p><input type="text" name="nome" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Descrizione:</p>
			<p><input type="text" name="descrizione" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Iva:</p>
			<p><input type="number" name="iva" min="0" step="0.01" placeholder="22" title="Inserire un numero" required></p>
		</div>
		<div class="tableRow">
			<p>Prezzo:</p>
			<p><input type="number" name="prezzo" min="0" step="0.01" value="" placeholder="solo numeri" title="Inserire un numero" required></p>
		</div>		
		<div class="tableRow">
			<p>Quantità:</p>
			<p><input type="number" name="quantita" placeholder="cifra intera" class="formInput" min="0" step="1" title="La quantità deve essere un numero intero positivo" required></p>
		</div>
		<div class="tableRow">
			<p>Immagine:</p>
			<p><input type="text" name="img" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Taglia:</p>
			<p><input type="text" name="Taglia" placeholder="XS,S,M,L,XL" pattern="^[A-Za-z,\s]+$" title="Sono consentite solo lettere, spazi e virgole"></p>
		</div>
		<div class="tableRow">
			<p>Vendite:</p>
			<p><input type="number" name="Vendite" value="0" class="formInput" readonly></p>
		</div>
		<div class="tableRow">
    <p>Categoria:</p>
    <p>
        <label for="Categoria"></label>
        <select id="Categoria" name="Categoria" class="formInput" required>
            <option value="Abbigliamento">Abbigliamento</option>
            <option value="Accessori" >Accessori</option>
            <option value="Elettronica">Elettronica</option>
            <option value="Giochi/Giocattoli">Giochi e Giocattoli</option>
        </select>
    </p>
</div>
		<div class="tableRow">
			<p></p>
			<p><input type="submit" value="aggiungi"></p>
		</div>
	</form>
	
	</div>

	<%@ include file="../fragments/footer.jsp" %>

</body>
</html>