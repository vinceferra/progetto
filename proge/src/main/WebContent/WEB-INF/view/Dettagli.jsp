<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" import="it.unisa.model.ProdottoBean" %>

<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<link rel="icon"  href="${pageContext.request.contextPath}/images/favicon.ico">
<title>Dettagli</title>
 
</head>

<body>

    <%@ include file="./fragments/header.jsp" %>
    <%@ include file="./fragments/menu.jsp" %>

    <div id="main" class="clear">

    <%
        ProdottoBean product = (ProdottoBean) request.getAttribute("product");

        if (product == null) {
    %>
        <h2>Prodotto non trovato</h2>
    <%
        } else {
    %>

        <h2><%= product.getNome() %></h2>

        <div id="image">
            <img src="<%= product.getImmagine() %>" height="270" width="250">
        </div>

        <div id="listDettagli">
            <ul>
                <li><span class="dettagli">Nome</span>: <%= product.getNome() %></li>
                <li><span class="dettagli">Descrizione</span>: <%= product.getDescrizione() %></li>
                <li><span class="dettagli">Categoria</span>: <%= product.getCategoria() %></li>
                <li><span class="dettagli">Prezzo</span>: &euro;<%= String.format("%.2f", product.getPrezzo()) %></li>

                <% if (product.isInVendita()) { %>
                    <li><span class="dettagli">Disponibilità immediata</span></li>
                <% } else { %>
                    <li><span class="dettagli">Non disponibile</span></li>
                <% } %>

                <% if(product.isInVendita() && product.getQuantita() > 0) { %>

                <li>
                   <a href="${pageContext.request.contextPath}/carrello?action=addC&id=<%= product.getIdProdotto() %>&page=Dettagli.jsp">
                      <button>Aggiungi al carrello</button>
                   </a>
                </li>

                <% } else { %>
                <li>
                    <span style="color:red;">Prodotto non disponibile</span>
                </li>

                <% } %>
                </ul>
                </div>

                <% } %>

                </div>

    <%@ include file="./fragments/footer.jsp" %>

</body>
</html>