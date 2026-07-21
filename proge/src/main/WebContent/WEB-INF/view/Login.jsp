<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	
	<title>Login</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp" %>
		<%@ include file="./fragments/menu.jsp" %>
	
		<div id="main" class="clear">
	
	<h2>Login</h2>

	<form action="${pageContext.request.contextPath}/Login" method="post" id="myform">
			<%
			  String erroreLogin = (String) request.getAttribute("erroreLogin");
              if (erroreLogin != null) {%>

        <div class="tableRow">
             <p></p>
             <p class="error"><%= erroreLogin %></p>
        </div>
    <%}
              
            String checkout = request.getParameter("checkout");
            if (checkout != null) {%>
               <input type="hidden" name="checkout" value="true">
    <%}%>
			
			<div class="tableRow">
			<p>Username:</p>	
			<p><input type="text" name="un" required placeholder="inserisci username"/></p>		
			</div>
			<div class="tableRow">
			<p>Password:</p>
			<p><input type="password" name="pw" required placeholder="inserisci password"/></p>
			</div>
			<div class="tableRow">
			<p></p>
			<p><input type="submit" value="login"> &nbsp;&nbsp;&nbsp; <a href="${pageContext.request.contextPath}/Registrazione">non sei registrato?</a></p>		
			</div>
		</form>
	</div>
	
		<%@ include file="./fragments/footer.jsp" %>
	
</body>
</html>
