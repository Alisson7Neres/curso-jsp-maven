<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Página inicial</title>
</head>
<body>
	<h1>Cadastro</h1>
	
	<%
		out.print("Preencha os campos");
	%>
	
	<form action="receber-nome.jsp">
		<input type="text" name="nome"/>
		<input type="text" name="idade"/>
		
		<input type="submit">
	</form>
	
</body>
</html>