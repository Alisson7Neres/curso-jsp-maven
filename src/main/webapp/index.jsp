<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/index.css" />
<title>Página inicial</title>
</head>
<body>
	<h1>Cadastro</h1>

	<div>
		<%
		out.print("Preencha os campos");
		%>
	</div>

	<form action="ServletLogin" method="post">
		<input type="hidden" value="<%= request.getParameter("url") %>" name="url">
		<table>
			<tr>
				<td><label>Login</label></td>
			</tr>
			<tr>
				<td><input type="text" name="login" /></td>
			</tr>

			<tr>
				<td><label>Senha</label></td>
			</tr>
			<tr>
				<td><input type="password" name="senha" /></td>
			</tr>
			<tr>
				<td><input type="submit"></td>
			</tr>
		</table>

	</form>
	<h3>${msg}</h3>


</body>
</html>