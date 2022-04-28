<%@page import="com.jsp.model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">


<jsp:include page="head.jsp"></jsp:include>

<body>
	<!-- Pre-loader start -->

	<jsp:include page="theme-loader.jsp"></jsp:include>

	<!-- Pre-loader end -->
	<div id="pcoded" class="pcoded">
		<div class="pcoded-overlay-box"></div>
		<div class="pcoded-container navbar-wrapper">

			<jsp:include page="navbar.jsp"></jsp:include>

			<div class="pcoded-main-container">
				<div class="pcoded-wrapper">

					<jsp:include page="navbarmainmenu.jsp"></jsp:include>

					<div class="pcoded-content">
						<!-- Page-header start -->

						<jsp:include page="page-header.jsp"></jsp:include>

						<!-- Page-header end -->
						<div class="pcoded-inner-content">
							<!-- Main-body start -->
							<div class="main-body">
								<div class="page-wrapper">
									<!-- Page-body start -->
									<div class="page-body">

										<div class="row">
											<div class="col-sm-12">
												<!-- Basic Form Inputs card start -->
												<div class="card">
													<div class="card-block">
														<h4 class="sub-title">Cad. Usuário</h4>

														<form class="form-material" enctype="multipart/form-data"   
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="post" id="formUser">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	class="form-control" value="${modelLogin.id}">
																<span class="form-bar"></span> <label
																	class="float-label">ID:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<div class="input-group-prepend">
																	<img src="" id="fotoembase64" width="70px">
																</div>
																<input type="file" accept="image/*" id="fileFoto" name="fileFoto"
																	onchange="vizualizarImg('fotoembase64', 'fileFoto');" class="form-control-file">
																<span class="form-bar"></span> <label
																	class="float-label"></label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome"
																	class="form-control" required="required"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="email" name="email" id="email"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.email}">
																<span class="form-bar"></span> <label
																	class="float-label">E-mail:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<select class="form-control" aria-label="Default select example" name="perfil">
																  <option selected disabled="disabled">--Selecione o perfil--</option>
																 
																  <option value="ADMIN"
																   
																   <% 
																   
																   ModelLogin admin = (ModelLogin) request.getAttribute("modelLogin");
																   
																   if(admin != null && admin.getPerfil().equals("ADMIN")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %> >Admin</option>
																  	
																  <option value="SECRETARIA" 
																  
																  <% 
																  
																  ModelLogin secretaria = (ModelLogin) request.getAttribute("modelLogin");
																  
																  if(secretaria != null && secretaria.getPerfil().equals("SECRETARIA")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %> >Secretaria</option>
																  
																  <option value="AUXILIAR" 
																  
																  <% 
																  
																  ModelLogin auxiliar = (ModelLogin) request.getAttribute("modelLogin");
																  
																  if(auxiliar != null && auxiliar.getPerfil().equals("AUXILIAR")) {
																	  out.print(" ");
																	  out.print("selected=\"selected\"");
																	  out.print(" ");
																  } %>>Auxiliar</option>
																  
																</select>
																<span class="form-bar"></span> <label
																	class="float-label">Perfil:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="text" name="login" id="login"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.login}">
																<span class="form-bar"></span> <label
																	class="float-label">Login</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input type="password" name="senha" id="senha"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.senha}">
																<span class="form-bar"></span> <label
																	class="float-label">Senha</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="radio" name="sexo" value="MASCULINO" 
																
																<% 
																	
																	ModelLogin masculino = (ModelLogin) request.getAttribute("modelLogin");
																
																	if(masculino != null && masculino.getSexo().equals("MASCULINO")) {
																		 out.print(" ");
																		 out.print("checked=\"checked\"");
																		 out.print(" ");
																	}
																
																%>>Masculino</>
																
																<input type="radio" name="sexo" value="FEMININO" 
																
																<% 
																
																	ModelLogin feminino = (ModelLogin) request.getAttribute("modelLogin");
																
																	if(feminino != null && feminino.getSexo() != null && feminino.getSexo().equals("FEMININO")) {
																		 out.print(" ");
																		 out.print("checked=\"checked\"");
																		 out.print(" ");
																	}
																
																%>>Feminino</>
															</div>

															<button type="button"
																class="btn btn-primary waves-effect waves-light"
																onclick="limparForm();">Novo</button>
															<button class="btn btn-success waves-effect waves-light">Salvar</button>
															<button type="button"
																class="btn btn-info waves-effect waves-light"
																onclick="criarDeleteComAjax();">Excluir</button>
															<!-- Button trigger modal -->
															<button type="button" class="btn btn-dark"
																data-toggle="modal" data-target="#exampleModal">
																Pesquisar</button>
														</form>

													</div>
												</div>
											</div>
										</div>
										<span id="msg">${msg}</span>


									<div style="overflow:scroll; height: 300px;">
										<table class="table" id="tabelaResultadosView">
											<thead>
												<tr>
													<th scope="col">#</th>
													<th scope="col">Nome</th>
													<th scope="col">Ver</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ modelLogins }" var="ml">
													<tr>
														<td><c:out value="${ ml.id }"></c:out></td>
														<td><c:out value="${ ml.nome }"></c:out></td>
														<td><a href="ServletUsuarioController?acao=buscarEditar&id=${ ml.id }" class="btn btn-primary"
						data-dismiss="modal">Ver</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
				
				
									</div>
									<!-- Page-body end -->
								</div>
								<div id="styleSelector"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<jsp:include page="javascriptfile.jsp"></jsp:include>

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Pesquisa de
						usuário</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<div class="input-group mb-3">
						<input type="text" class="form-control" placeholder="Nome"
							aria-label="nome" aria-describedby="basic-addon2" id="nomeBusca">

						<div class="input-group-append">
							<button class="btn btn-success" type="button"
								onclick="buscarUsuario();">Buscar</button>
						</div>
					</div>

				<div style="overflow:scroll; height: 300px;">
					<table class="table" id="tabelaResultados">
						<thead>
							<tr>
								<th scope="col">#</th>
								<th scope="col">Nome</th>
								<th scope="col">Ver</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
					<span id="tabelaResultadosSpan"></span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Fechar</button>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function buscarUsuario() {

			var nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

				var urlAction = document.getElementById('formUser').action;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "nomeBusca=" + nomeBusca + '&acao=buscar',
					success : function(response) {
						
						//  Irá pegar os registros da servlet e converter para o formato JSON, percorrerá o tamanho do JSON e imprimirá na tela
						// os resultados da consulta
						var json = JSON.parse(response);
						
						$('tabelaResultados > tbody > tr').remove()
						
						for(var p = 0; p < json.length; p++) {
							$('#tabelaResultados > tbody').append(
									'<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td> <td> <button class="btn btn-primary" onclick="verEditar('+json[p].id+')">Ver</button> </td> </tr>')
						}
						
						document.getElementById('tabelaResultadosSpan').textContent = 'Resultado(s): ' + json.length;
						
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao buscar usuário por nome: '
									+ xhr.responseText);
						});

			}
		}
		
		function verEditar(id) {
			
			var urlAction = document.getElementById('formUser').action;
			
			window.location.href = urlAction + '?acao=buscarEditar&id=' + id;
		}

		function criarDeleteComAjax() {

			if (confirm('Deseja realmente excluir os dados?')) {

				var urlAction = document.getElementById('formUser').action;
				var idUser = document.getElementById('id').value;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "id=" + idUser + '&acao=deletarajax',
					success : function(response) {

						limparForm();
						document.getElementById('msg').textContent = response;
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao deletar usuário por id: '
									+ xhr.responseText);
						});

			}

		}

		function criarDelete() {

			if (confirm('Deseja realmente excluir os dados?')) {

				document.getElementById("formUser").method = 'get';
				document.getElementById("acao").value = 'deletar';
				document.getElementById("formUser").submit();

			}

		}

		function limparForm() {

			var elementos = document.getElementById("formUser").elements; /*Retorna os elementos html dentro do form*/

			for (p = 0; p < elementos.length; p++) {
				elementos[p].value = '';
			}
		}
		
		function vizualizarImg(fotoembase64, fileFoto) {
			
			var preview = document.getElementById(fotoembase64); // campo IMG html
			var fileUser = document.getElementById(fileFoto).files[0];
			var reader = new FileReader();
			
			reader.onloadend = function() {
				preview.src = reader.result; // Carrega a foto na tela
			};
			
			if(fileUser) {
				reader.readAsDataURL(fileUser); // Preview da imagem
			} else {
				preview.src = '';
			}
		}
	</script>
</body>
</html>