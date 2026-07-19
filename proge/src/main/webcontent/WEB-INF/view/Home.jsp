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

		<%@ include file="./fragments/header.jsp" %>
		<%@ include file="./fragments/menu.jsp" %>
		
<div id="main" class="clear">

<%
    boolean utenteLoggato =
        session.getAttribute("currentSessionUser") != null;

    for (int i = 0; i < categorie.size(); i++) {

        ArrayList<ProdottoBean> prodottiCategoria = categorie.get(i);

        // Da non loggato non mostra Ultimi acquisti
        if (i == 0 && !utenteLoggato) {
            continue;
        }

        // Non mostra sezioni vuote
        if (prodottiCategoria == null || prodottiCategoria.isEmpty()) {
            continue;
        }

        /*
         * La Home visualizza solamente:
         * 0 = Ultimi acquisti
         * 1 = Prodotti personalizzati/casuali
         *
         * Le altre liste servono alle rispettive pagine del menu.
         */
        if (i > 1) {
            continue;
        }
%>

    <div class="categoria categoria<%= i %>">

        <% if (i == 0) { %>

            <h2>ULTIMI ACQUISTI</h2>
            <hr>

        <% } else if (i == 1 && utenteLoggato) { %>

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
                    <li><a href="<%= request.getContextPath() %>/Dettagli?id=<%= bean.getIdProdotto() %>"><img src="<%= bean.getImmagine() %>" height="130" width="130" alt="<%= bean.getNome() %>"></a></li>
                    <li><b><%= bean.getNome() %></b></li>
                    <li>Prezzo: &euro;<%= bean.getPrezzo() %></li>
                    <li><a href="<%= request.getContextPath() %>/carrello?action=addC&id=<%= bean.getIdProdotto() %>&page=Home.jsp"><button type="button">Aggiungi al carrello</button></a></li>
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