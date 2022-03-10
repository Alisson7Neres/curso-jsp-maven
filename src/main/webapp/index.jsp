<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<link rel="stylesheet" href="css/index.css" />
<title>Página inicial</title>
</head>
<body>
	<h1>Login</h1>

	<form action="<%= request.getContextPath() %>ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate id="form">
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">
		<div class="mb-3">
			<label class="form-label">Login</label> <input class="form-control"
				type="text" name="login" required />
			<div class="invalid-feedback">Informe o Login</div>
		</div>

		<div class="mb-3">
			<label class="form-label">Senha</label> <input class="form-control"
				type="password" name="senha" required />
			<div class="invalid-feedback">Informe a Senha</div>
		</div>
		<input class="btn btn-primary" type="submit" value="Logar">
		<div class="msg container" id="msgDiv">
			<h4>${msg}</h4>
		</div>
	</form>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script type="text/javascript" src="js/index.js"></script>
</body>
</html>