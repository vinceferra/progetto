<%@ page language="java" pageEncoding="ISO-8859-1"  import="it.unisa.model.*, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" charset="text/html; ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>

<body data-context-path="<%= request.getContextPath() %>">

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
								    <a href="<%= request.getContextPath() %>/account?page=impostazioni.jsp">IMPOSTAZIONI</a>
								    <% if(!user.isAmministratore()){ %>
                                    <a href="<%= request.getContextPath() %>/home?page=Aiuto.jsp">AIUTO</a>
                                    <% } %>	
                                    <%if(user.isAmministratore()){ %>
									<a href="<%= request.getContextPath() %>/catalogo?action=admin&page=admin/GestioneCatalogo.jsp">GESTIONE CATALOGO</a>
									<a href="<%= request.getContextPath() %>/ordiniAdmin">ORDINI</a>
											
									<%} %>
									<a href="<%= request.getContextPath() %>/Logout">LOGOUT</a>
									
								  </div>
							</li>										
			<%}else{ %>
				<li><a href="<%= request.getContextPath() %>/Login">ACCEDI</a></li><%} %>
				<li><a href="<%= request.getContextPath() %>/carrello?page=Carrello.jsp"><img src="${pageContext.request.contextPath}/images/carrello.png" height="13" width="32"></a></li>
			</ul>
		</nav>
	</div>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="<%= request.getContextPath() %>/script/header.js"></script>


</body>
