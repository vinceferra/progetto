<%@ page language="java" pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" charset="text/html; ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>

<body>

<div align=right>
<select id="language" name="language">
<option value="ita">italiano</option>
</select>
</div>

<div class="header">
	<div class="left-header">
		<h2> <span id="violet"><i>BUY</i></span><span id="violet" ><i> INTELLIGENTLY</i></span></h2>
	</div>
	<div class="center-header">
		<input id="searchbar" name="search" type="search" placeholder="clicca per cercare...">
		
		<div class="risultati">
		</div>
	</div>
	<div class="right-header">
		<nav>
			<ul>
			<% UserBean user = (UserBean) request.getSession().getAttribute("currentSessionUser");
				if(user !=null){ %>
							<li class="dropdown">
									<a href="javascript:void(0)" class="dropbtn">ACCOUNT</a>
							 		 <div class="dropdown-content">
								    <a href="<%= request.getContextPath() %>/Ordine?action=mieiOrdini">I MIEI ORDINI</a>
								    <a href="<%= request.getContextPath() %>/impostazioni.jsp">IMPOSTAZIONI</a>
								    <a href="<%= request.getContextPath() %>/Aiuto.jsp">AIUTO</a>
								    	<%if(user.isAmministratore()){ %>
										    <a href="<%= request.getContextPath() %>/admin/GestioneCatalogo.jsp">GESTIONE CATALOGO</a>
											<a href="<%= request.getContextPath() %>/admin/ViewOrdini.jsp">ORDINI</a>
											<a href="<%= request.getContextPath() %>/impostazioni.jsp">IMPOSTAZIONI</a>
											<a href="<%= request.getContextPath() %>/Aiuto.jsp">AIUTO</a>
										<%} %>
									<a href="<%= request.getContextPath() %>/Logout">LOGOUT</a>
								  </div>
							</li>										
			<%}else{ %>
				<li><a href="<%= request.getContextPath() %>/Login.jsp">ACCEDI</a></li><%} %>
				<li><a href= "<%= request.getContextPath() %>/Carrello.jsp"><img src="images/carrello.png" height="22" weidth="32"></a></li>
			</ul>
		</nav>
	</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>
	$(document).ready(function(){
		$("#searchbar").keyup(function(){
			var x = $("#searchbar").val();
			if(x != ""){
				$.get("./RicercaProdotto", {"query": x}, function(data){
					if(data!= ""){
						$(".risultati").empty();
						$(".risultati").css({"display" : "block"});
						$.each(data, function(i,item){
							$(".risultati").append("<div id='item-r' class='item"+i+"'><img id='pic' width='65' height='65' src='" + item.immagine + "'/><p id='name'>" +item.nome + "</p></div>");
							$(".item"+i).click(function(){
								$.get("./dettagli",{"id" : item.idProdotto}, function(){
									window.location = "./Dettagli.jsp";
								});
							});
						});
					}
				});
						
				}else{
					$(".risultati").css({"display" : "none"});
				};
			});
	});
	
	

</script>

</body>
