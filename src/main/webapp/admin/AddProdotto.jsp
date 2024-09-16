<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

<title>Aggiungi prodotto</title>
</head>
<body>


	<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	
	<div id="main" class="clear">
	
		<h2>AGGIUNGI PRODOTTO</h2>

	<form action="../catalogo" method="post" id="myform">
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
			<p><input type="text" name="iva" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Prezzo:</p>
			<p><input type="text" name="prezzo" value="" required></p>
		</div>		
		<div class="tableRow">
			<p>Quantità:</p>
			<p><input type="number" name="quantita"placeholder="quantita cifra intera senza virgola o punto" class="formInput" required></p>
		</div>
		<div class="tableRow">
			<p>Immagine:</p>
			<p><input type="text" name="img" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Taglie:</p>
			<p><input type="text" name="Taglie" placeholder="Non inserire se non è un abbigliamento" value="" required></p>
		</div>
		<div class="tableRow">
			<p>Vendite:</p>
			<p><input type="number" name="Vendite" class="formInput" required></p>
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