<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
    
    <%
	ArrayList<ArrayList<ProdottoBean>> categorie = (ArrayList<ArrayList<ProdottoBean>>) request.getSession().getAttribute("categorie");
	if(categorie == null) {
		response.sendRedirect("./home?page=Switch.jsp");	
		return;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">

</head>
<body>
<%@ include file="../fragments/header.jsp" %>
	<%@ include file="../fragments/menu.jsp" %>
	
	<%
	ArrayList<ProdottoBean> nswitch = categorie.get(2);%>
	
	<div id="main" class="clear">
	
		<%
			if (nswitch != null && nswitch.size() != 0) {
				Iterator<?> it = nswitch.iterator();
				while (it.hasNext()) {
					ProdottoBean bean = (ProdottoBean) it.next();
		%>
		<div class="item">
			<ul>
			<li><a href="dettagli?id=<%=bean.getIdProdotto()%>"><img src="<%=bean.getImmagine()%>" height="130" width="130"></a></li>
			<li><%=bean.getNome()%></li>
			<li>prezzo: &euro;<%=bean.getPrezzo()%></li>
			<li><a href="carrello?action=addC&id=<%=bean.getIdProdotto()%>&page=Switch.jsp"><button>Aggiungi al carrello</button></a></li>
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