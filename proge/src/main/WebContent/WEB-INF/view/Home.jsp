<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
    ArrayList<ArrayList<ProdottoBean>> categorie =(ArrayList<ArrayList<ProdottoBean>>)request.getSession().getAttribute("categorie");
    if (categorie == null) {
        response.sendRedirect(request.getContextPath() + "/home?page=Home.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">	
<title>BUY INTELLIGENTLY</title>
</head>
<body>

<%
String messaggio = (String) session.getAttribute("messaggio");
if (messaggio != null) {
%>
<p style="text-align:center; color:green; font-weight:bold;"><%= messaggio %></p>
<% session.removeAttribute("messaggio");
}
%>

		<%@ include file="./fragments/header.jsp" %>
		<%@ include file="./fragments/menu.jsp" %>
		
<div id="main" class="clear">

<%
boolean utenteLoggato = session.getAttribute("currentSessionUser") != null;
boolean haEffettuatoAcquisti = categorie.size() > 0 && categorie.get(0) != null && !categorie.get(0).isEmpty();
%>

<%
for (int i = 0; i < categorie.size(); i++) {

    ArrayList<ProdottoBean> prodottiCategoria = categorie.get(i);

    /*
     * Utente loggato ma senza acquisti:
     * non mostra la sezione Ultimi acquisti.
     */
    if (i == 0 && (!utenteLoggato || !haEffettuatoAcquisti)) {
        continue;
    }

    /*
     * La Home mostra solamente:
     * 0 = Ultimi acquisti
     * 1 = Prodotti personalizzati/consigliati
     */
    if (i > 1) {
        continue;
    }

    if (prodottiCategoria == null || prodottiCategoria.isEmpty()) {
        continue;
    }
%>

    <div class="categoria categoria<%= i %>">

        <% if (i == 0) { %>

            <h2>ULTIMI ACQUISTI</h2>
            <hr>

 <% } else if (i == 1 && utenteLoggato && haEffettuatoAcquisti) { %>

    <h2>PRODOTTI CHE FANNO PER TE</h2>
    <hr>

<% } else if (i == 1) { %>

    <h2>PRODOTTI CONSIGLIATI</h2>
    <hr>

<% } %>

        <%
            for (ProdottoBean bean : prodottiCategoria) {
        %>

<div class="item">
    <ul>
        <li><a href="${pageContext.request.contextPath}/Dettagli?id=<%= bean.getIdProdotto() %>"><img src="<%= bean.getImmagine() %>" height="130" width="130" alt="<%= bean.getNome() %>"></a></li>
        <li><b><%= bean.getNome() %></b></li>
        <li>Prezzo: &euro;<%= String.format("%.2f", bean.getPrezzo()) %></li>
        <li><a href="${pageContext.request.contextPath}/carrello?action=addC&id=<%= bean.getIdProdotto() %>&page=Home.jsp"><button type="button">Aggiungi al carrello</button></a></li>
    </ul>
</div>
        <%}%>

    </div>
<%}%>
</div>
	
		<%@ include file="./fragments/footer.jsp" %>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
</body>
</html>