<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Ps4.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>BESTSELLER</title>
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
	ArrayList<ProdottoBean> abbigliamento = categorie.get(3);%>
	
	<div id="main" class="clear">
	
	<h2 id=>PRODOTTI PIU' VENDUTI</h2>
	
		<%
			if (abbigliamento != null && abbigliamento.size() != 0) {
				Iterator<?> it = abbigliamento.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<div class="item">
			<ul>
			<li><a href="dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
			<li><%=bean.getNome()%></li>
			<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
			<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=BESTSELLER.jsp"><button>Aggiungi al carrello</button></a></li>
		 </ul>
		
		<%
				}
			} 
		%>
	</div>
	
		<%@ include file="./fragments/footer.jsp" %>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
		<script>
			$(document).ready(function(){
				$("img").hover(function(){
					$(this).css({"height": "135px", "width" :"135px"});
				}, function(){
						$(this).css({"height" : "130px", "width" : "130px"});
					});
				});
			</script>

</body>
</html>