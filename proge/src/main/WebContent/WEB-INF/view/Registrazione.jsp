<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
<title>Registrazione</title>
</head>
<body data-context-path="${pageContext.request.contextPath}">

		<%@ include file="./fragments/header.jsp" %>
		<%@ include file="./fragments/menu.jsp" %>
	
		<div id="main" class="clear">
	<h2>Registrazione</h2>
	<form action="${pageContext.request.contextPath}/Registrazione" method="post" id="myform">
		
		<div class="tableRow">
			<p>Nome:</p>
			<p><input type="text" name="nome" placeholder="Mario" required/></p>	
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errNome"></p> 
		</div>
		<div class="tableRow"> 
			<p>Cognome:</p>
			<p><input type="text" name="cognome" placeholder="Rossi" required/></p>
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errCognome"></p> 
		</div>
		<div class="tableRow">
			<p>Email:</p>
			<p><input type="text" id="em" name="email" placeholder="MarioRossi@gmail.com" required/></p>	
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errEmail"></p> 
		</div>
		<div class="tableRow">
			<p>Data di nascita:</p>
			<p><input type="date" name="nascita" required></p>	
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errNascita"></p> 
		</div>
		<div class="tableRow">
			<p>Username:</p>
			<p><input type="text" id="us" name="us" placeholder="MarioR87" required/></p>
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errUser"></p> 
		</div>
		<div class="tableRow">
			<p>Password:</p>
			<p><input type="password" name="pw" placeholder="********" required/></p>
		</div>
		<div class="tableRow">
			<p></p>
			<p id="errPass"></p> 
		</div>
		<div class="tableRow">
			<p></p>
			<p><input type="submit" value="Registrati">	</p>
		</div>		
		
	</form>
	</div>
	
			<%@ include file="./fragments/footer.jsp" %>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/script/Registrazione.js"></script>

	
</body>
</html>