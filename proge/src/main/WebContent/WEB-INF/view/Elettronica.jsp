<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect(request.getContextPath() + "/home?page=Elettronica.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<link rel="icon"  href="${pageContext.request.contextPath}/images/favicon.ico">
<title>ELETTRONICA</title>
</head>
<body>

<%@ include file="./fragments/header.jsp" %>
	<%@ include file="./fragments/menu.jsp" %>
	<%
	ArrayList<ProdottoBean> elettron = categorie.get(4);%>
	
	<div id="main" class="clear">
	
		<h2>ELETTRONICA</h2>
	
		<%
			if (elettron != null && elettron.size() != 0) {
				Iterator<?> it = elettron.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<div class="item">
			<ul>
			<li><a href="${pageContext.request.contextPath}/Dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
			<li><%=bean.getNome()%></li>
			<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
			<li><a href="${pageContext.request.contextPath}/carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Elettronica.jsp"><button>Aggiungi al carrello</button></a></li>
		 </ul>
		</div>
		<%
				}
			} else {
		%>
		
			<h2>Non ci sono prodotti disponibili</h2>
		<%
			}
		%>
	</div>
	
		<%@ include file="./fragments/footer.jsp" %>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		
	
</body>
</html>