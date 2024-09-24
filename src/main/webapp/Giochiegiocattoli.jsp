<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Giochiegiocattoli.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>giochi / giocattoli</title>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	<!-- Prezzo -->
        <div class="filter-group">
            <label for="price">Prezzo massimo: <span id="price-value">5000</span>&#8364</label>
            <input type="range" id="price" name="price" min="0" max="5000" value="5000" oninput="document.getElementById('price-value').innerText = this.value">
        </div>

        <!-- Marca -->
        <div class="filter-group">
            <label for="brand">Marca:</label>
            <input type="text" id="brand" name="brand" placeholder="Inserisci marca">
        </div>

        <!-- Disponibilità -->
        <div class="filter-group">
            <input type="checkbox" id="available" name="available">
            <label for="available">Solo prodotti disponibili</label>
        </div>

        <!-- Pulsante di ricerca -->
        <div class="filter-group">
            <input type="submit" value="Cerca">
        </div>

    </form>
</div>
	
	
	<%
	ArrayList<ProdottoBean> Giochiegiocattoli = categorie.get(3);%>
	
	<div id="main" class="clear">
	
	<h2>Giochi / Giocattoli</h2>
	
		<%
			if (Giochiegiocattoli != null && Giochiegiocattoli.size() != 0) {
				Iterator<?> it = Giochiegiocattoli.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<div class="item">
			<ul>
			<li><a href="Dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
			<li><%=bean.getNome()%></li>
			<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
			<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Giochiegiocattoli.jsp"><button>Aggiungi al carrello</button></a></li>
		 </ul>
		</div>
		<%
				}
			} else {
		%>
		
			<h2>No products available</h2>
		<%
			}
		%>
	</div>
	
		<%@ include file="./fragments/footer.jsp" %>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</body>
</html>