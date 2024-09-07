<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="it.unisa.model.ProdottoBean, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>Modifica prodotto</title>
</head>
<body>

	<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	
	<div id="main" class="clear">
	
		<h2>MODIFICA PRODOTTO</h2>
		
	<% int id = Integer.parseInt(request.getParameter("prodotto"));
		ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getSession().getAttribute("products");
		ProdottoBean bean = null;
	for(ProdottoBean prodotto : prodotti){
		if(prodotto.getIdProdotto() == id){
			bean = prodotto;} 
			}%>
	
	<form action="../catalogo" method="post" id="myform">
		<input type="hidden" name="action" value="modifica">
		<input type="hidden" name="page" value="admin/GestioneCatalogo.jsp">
		<div class="tableRow">
			<p>ID:</p>
			<p><input type="text" name="id" value="<%=bean.getIdProdotto() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Nome:</p>
			<p><input type="text" name="nome" value="<%=bean.getNome() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Descrizione:</p>
			<p><input type="text" name="descrizione" value="<%=bean.getDescrizione() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Iva:</p>
			<p><input type="text" name="iva" value="<%=bean.getIva() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Prezzo:</p>
			<p><input type="text" name="prezzo" value="<%=bean.getPrezzo() %>" required></p>
		</div>		
		<div class="tableRow">
			<p>Quantità:</p>
			<p><input type="number" name="quantita" value="<%=bean.getQuantita() %>"placeholder="quantita cifra intera senza virgola o punto" class="formInput" required></p>
		</div>
		<div class="tableRow">
			<p>Immagine:</p>
			<p><input type="text" name="img" value="<%=bean.getImmagine() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Taglie:</p>
			<p><input type="text" name="taglie" value="<%=bean.getTaglie() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Vendite:</p>
			<p><input type="number" name="vendite" value="<%=bean.getvendite() %>"placeholder="quantita cifra intera senza virgola o punto" class="formInput" required></p>
		</div>
		<div class="tableRow">
    <p>Categoria:</p>
    <p>
        <label for="Categoria"></label>
        <select id="Categoria" name="Categoria" class="formInput" required>
            <option value="Abbigliamento" <%= bean.getCategoria().equals("Abbigliamento") ? "selected" : "" %>>Abbigliamento</option>
            <option value="Accessori" <%= bean.getCategoria().equals("Accessori") ? "selected" : "" %>>Accessori</option>
            <option value="Elettronica" <%= bean.getCategoria().equals("Elettronica") ? "selected" : "" %>>Elettronica</option>
            <option value="Giochi e Giocattoli" <%= bean.getCategoria().equals("Giochi e Giocattoli") ? "selected" : "" %>>Giochi e Giocattoli</option>
        </select>
    </p>
</div>
		<div class="tableRow">
			<p></p>
			<p><input type="submit" value="modifica"></p>
		</div>
	</form>
	
		</div>
	

	<%@ include file="../fragments/footer.jsp" %>

</body>
</html>