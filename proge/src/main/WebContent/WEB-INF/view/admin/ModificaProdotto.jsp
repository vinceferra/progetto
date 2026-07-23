<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="it.unisa.model.ProdottoBean, it.unisa.model.UserBean, java.util.*" %>
    
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
<title>Modifica prodotto</title>
</head>
<body>

	<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	
	<div id="main" class="clear">
	
		<h2>MODIFICA PRODOTTO</h2>
		
<%
String erroreCatalogo =(String) request.getAttribute("erroreCatalogo");
if (erroreCatalogo != null) {
%>
<p class="error"><%= erroreCatalogo %></p>
<%}%>
		
	<% int id = Integer.parseInt(request.getParameter("prodotto"));
		ArrayList<ProdottoBean> prodotti = (ArrayList<ProdottoBean>) request.getSession().getAttribute("products");
		ProdottoBean bean = null;
	for(ProdottoBean prodotto : prodotti){
		if(prodotto.getIdProdotto() == id){
			bean = prodotto;
			break;} 
			}%>
	
	<form action="${pageContext.request.contextPath}/catalogo"  method="post" id="myform">
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
			<p><input type="number" name="iva" value="<%= bean.getIva() %>" min="0" step="0.01" placeholder="22" title="Inserire un numero" required></p>
		</div>
		<div class="tableRow">
			<p>Prezzo:</p>
			<p><input type="number" name="prezzo" value="<%=bean.getPrezzo() %>" min="0" step="0.01" placeholder="solo numeri" title="Inserire un numero" required></p>
		</div>		
		<div class="tableRow">
			<p>Quantità:</p>
			<p><input type="number" name="quantita" value="<%= bean.getQuantita() %>" min="0" step="1" title="La quantità deve essere un numero intero positivo" required></p>
		</div>
		<div class="tableRow">
			<p>Immagine:</p>
			<p><input type="text" name="img" value="<%=bean.getImmagine() %>" required></p>
		</div>
		<div class="tableRow">
			<p>Taglia:</p>
			<p><input type="text" name="Taglia" value="<%= bean.getTaglie() != null ? bean.getTaglie() : "" %>" pattern="^[A-Za-z,\s]+$" placeholder="xs,s,m,xl.." title="Sono consentite solo lettere, spazi e virgole"></p>
		</div>
		<div class="tableRow">
			<p>Vendute:</p>
			<p><input type="number" name="Vendite" value="<%=bean.getvendite() %>" class="formInput" readonly></p>
		</div>
		<div class="tableRow">
    <p>Categoria:</p>
    <p>
        <label for="Categoria"></label>
        <select id="Categoria" name="Categoria" class="formInput" required>
            <option value="Abbigliamento" <%= bean.getCategoria().equals("Abbigliamento") ? "selected" : "" %>>Abbigliamento</option>
            <option value="Accessori" <%= bean.getCategoria().equals("Accessori") ? "selected" : "" %>>Accessori</option>
            <option value="Elettronica" <%= bean.getCategoria().equals("Elettronica") ? "selected" : "" %>>Elettronica</option>
            <option value="Giochi/Giocattoli" <%= bean.getCategoria().equals("Giochi/Giocattoli") ? "selected" : "" %>>Giochi/Giocattoli</option>
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