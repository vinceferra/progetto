<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Abbigliamento.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>ABBIGLIAMENTO</title>
</head>
<body>
<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	<%
	ArrayList<ProdottoBean> Abbigliamento = categorie.get(5);%>
	
	<div id="main" class="clear">
	
	
	
	
<div class="filter-section">
    <h2>Filtra Prodotti</h2>
    <form action="risultati_ricerca.html" method="GET">
        
        <!-- Taglia -->
        <div class="filter-group">
            <label for="taglia">Taglie italiane:</label>
            <select id="taglia" name="taglia">
                <option value="">Tutte le taglie</option>
                <option value="XS">XS / 38</option>
                <option value="S">S / 40</option>
                <option value="M">M / 42</option>
                <option value="L">L / 44</option>
                <option value="XL">XL / 46</option>
                <option value="XXL">XXL / 48</option>
                <option value="XXXL">XXXL / 50</option>
                <option value="XXXXL">XXXXL / 52</option>
                
            </select>
        </div>

        <!-- TESSUTO -->
        <div class="filter-group">
            <label for="tessuto">Tessuti:</label>
            <select id="tessuto" name="tessuto">
                <option value="">Tutti i tessuti</option>
                <option value="cotone">cotone</option>
                <option value="lana">lana</option>
                <option value="pelle">pelle</option>
                <option value="pile">pile</option>
                <option value="lino">lino</option>
                <option value="jeans">jeans</option>
                <option value="paillettes">paillettes</option>
                <option value="seta">seta</option>
                <option value="poliestere">poliestere</option>
                <option value="viscoso">viscoso</option>
                
            </select>
        </div>

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
	<h2>ABBIGLIAMENTO</h2>
		<%
			if (Abbigliamento != null && Abbigliamento.size() != 0) {
				Iterator<?> it = Abbigliamento.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		
		
		<div class="item">
			<ul>
			<li><a href="dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
			<li><%=bean.getNome()%></li>
			<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
			<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Abbigliamento.jsp"><button>Aggiungi al carrello</button></a></li>
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