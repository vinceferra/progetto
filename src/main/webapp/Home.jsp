<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Home.jsp");	
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

		<%@ include file="../fragments/header.jsp" %>
		<%@ include file="../fragments/menu.jsp" %>
		
		<div class="filter-section">
    <h2>Filtro ricerca</h2>
    <form action="risultati_ricerca.html" method="GET">

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
		
		<div id="main" class="clear">
			
			<%for(int i = 0 ; i < categorie.size() ; i++){%>
				<div class="categoria categoria<%=i%>">
				<%switch(i){
					case 0 : %> <h2>ULTIMI ACQUISTI</h2><a href="MieiOrdini.jsp" id="orange">vedi tutta la categoria</a> <hr>
								<%break;
					case 1 : %> <h2>PRODOTTI CHE FANNO PER TE</h2> <hr>
								<%break;
				
				}for(int j = 0; j< categorie.get(i).size(); j++){
					ProdottoBean bean = categorie.get(i).get(j);%>
					 <div class="item">
						<ul>
							<li><a href="dettagliprodotti?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
							<li><b><%=bean.getNome()%></b></li>
							<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
							<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Home.jsp"><button>Aggiungi al carrello</button></a></li>
		 				</ul>
					</div>
				<%}%></div><%}%> 
		</div>
	
		<%@ include file="./fragments/footer.jsp" %>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>	
		
</body>
</html>