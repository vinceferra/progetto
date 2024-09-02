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

		<%@ include file="./fragments/header.jsp" %>
		<%@ include file="./fragments/menu.jsp" %>
		
		<div id="main" class="clear">
			
			<%for(int i = 0 ; i < categorie.size() ; i++){%>
				<div class="categoria categoria<%=i%>">
				<%switch(i){
					case 0 : %> <h2>ULTIMI ACQUISTI</h2><a href="example.jsp" id="orange">vedi tutta la categoria</a> <hr>
								<%break;
					case 1 : %> <h2>PRODOTTI CHE FANNO PER TE</h2> <a href="example.jsp" id="orange">vedi tutta la categoria</a> <hr>
								<%break;
				
				}for(int j = 0; j< categorie.get(i).size(); j++){
					ProdottoBean bean = categorie.get(i).get(j);%>
					 <div class="item">
						<ul>
							<li><a href="dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
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