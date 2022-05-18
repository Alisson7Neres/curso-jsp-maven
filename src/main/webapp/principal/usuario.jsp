<%@page import="com.jsp.model.ModelLogin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
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
																<c:if test="${modelLogin.fotouser  != '' && modelLogin.fotouser != null}">
																	<a href="<%=request.getContextPath()%>/ServletUsuarioController?acao=downloadFoto&id=${modelLogin.id}">
																		<img id="fotoembase64" src="${modelLogin.fotouser}" width="70px">
																	</a>
																</c:if>
																<c:if test="${modelLogin.fotouser  == '' || modelLogin.fotouser == null }">
																	<img id="fotoembase64" src="<%=request.getContextPath()%>/assets/images/user-icon.jpg" width="70px">
																</c:if>
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
																<input type="text" name="dataNascimento" id="dataNascimento"
																	class="form-control" required="required"
																	value="${modelLogin.dataNascimento}"> <span
																	class="form-bar"></span> <label class="float-label">Data:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="rendaMensal" id="rendaMensal"
																	class="form-control" required="required"
																	value="${modelLogin.rendaMensal}"> <span
																	class="form-bar"></span> <label class="float-label">Renda Mensal:</label>
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
																<input type="text" name="cep" id="cep"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.cep}" onblur="pesquisaCep()">
																<span class="form-bar"></span> <label
																	class="float-label">CEP</label>
															</div>
															
																<div class="form-group form-default form-static-label">
																<input type="text" name="logradouro" id="logradouro"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.logradouro}">
																<span class="form-bar"></span> <label
																	class="float-label">Logradouro</label>
															</div>
															
																<div class="form-group form-default form-static-label">
																<input type="text" name="bairro" id="bairro"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.bairro}">
																<span class="form-bar"></span> <label
																	class="float-label">Bairro</label>
															</div>
															
																<div class="form-group form-default form-static-label">
																<input type="text" name="localidade" id="localidade"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.localidade}">
																<span class="form-bar"></span> <label
																	class="float-label">Estado</label>
															</div>
															
																<div class="form-group form-default form-static-label">
																<input type="text" name="uf" id="uf"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.uf}">
																<span class="form-bar"></span> <label
																	class="float-label">UF</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="numero" id="numero"
																	class="form-control" required="required"
																	autocomplete="off" value="${modelLogin.numero}">
																<span class="form-bar"></span> <label
																	class="float-label">Número</label>
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
															
															<c:if test="${ modelLogin.id > 0 }">
															<a href="<%= request.getContextPath() %>/ServletTelefone?iduser=${ modelLogin.id }" class="btn btn-dark">Telefone</a>
															</c:if>
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
													<th scope="col">Foto</th>
													<th scope="col">Ver</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ modelLogins }" var="ml">
													<tr>
														<td><c:out value="${ ml.id }"></c:out></td>
														<td><c:out value="${ ml.nome }"></c:out></td>
														<td>
														<c:if test="${ml.fotouser  != '' && ml.fotouser != null }">
																	<img id="fotoembase64" class="img-80 img-radius mCS_img_loaded" src="<c:out value="${ ml.fotouser }"/>" width="60px" height="70px" onchange="vizualizarImg('fotoembase64', 'fileFoto');">
														</c:if>
														<c:if test="${ml.fotouser  == '' || ml.fotouser == null }">
																	<img id="fotoembase64" src="<%=request.getContextPath()%>/assets/images/user-icon.jpg" width="60px" height="70px">
														</c:if>
														</td>
														<td><a href="ServletUsuarioController?acao=buscarEditar&id=${ ml.id }" class="btn btn-primary"
						data-dismiss="modal">Ver</a></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
									
									<nav aria-label="Page navigation example">
									  <ul class="pagination">
									  
									  <%
									  	int totalPagina = (int) request.getAttribute("totalPagina");
									  	for (int p = 0; p < totalPagina; p++) {
									  		String url = request.getContextPath() + "/ServletUsuarioController?acao=paginar&pagina="+(p * 5);
									  		out.print("<li class=\"page-item\"><a class=\"page-link\" href=\""+url+"\">"+(p + 1)+"</a></li>");
									  	}
									  %>
									  </ul>
									</nav>

				
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
				
				<nav aria-label="Page navigation example">
					<ul class="pagination" id="ulPaginacaoUserAjax">
					</ul>
				</nav>
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
	
	$("#rendaMensal").maskMoney({ showSymbol:true, symbol:"R$ ", decimal:",", thousands:"."})
	
	$( function() {
		  
		  $("#dataNascimento").datepicker({
			    dateFormat: 'dd/mm/yy',
			    dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado'],
			    dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
			    dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
			    monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
			    monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez'],
			    nextText: 'Próximo',
			    prevText: 'Anterior'
			});
	} );
	
	$("#numero, #cep").keypress( function(event) {
		return /\d/.test(String.fromCharCode(event.keyCode));
	});
	
		function buscarUsuario() {

			var nomeBusca = document.getElementById('nomeBusca').value;

			if (nomeBusca != null && nomeBusca != '' && nomeBusca.trim() != '') {

				var urlAction = document.getElementById('formUser').action;

				$.ajax({

					method : "get",
					url : urlAction,
					data : "nomeBusca=" + nomeBusca + '&acao=buscar',
					success : function(response, textStatus, xhr) {
						
						//  Irá pegar os registros da servlet e converter para o formato JSON, percorrerá o tamanho do JSON e imprimirá na tela
						// os resultados da consulta
						var json = JSON.parse(response);
						
						$('#tabelaResultados > tbody > tr').remove()
						$("#ulPaginacaoUserAjax > li").remove()
						
						for(var p = 0; p < json.length; p++) {
							$('#tabelaResultados > tbody').append(
									'<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td> <td> <button class="btn btn-primary" onclick="verEditar('+json[p].id+')">Ver</button> </td> </tr>')
						}
						
						document.getElementById('tabelaResultadosSpan').textContent = 'Resultado(s): ' + json.length;
						
						var totalPagina = xhr.getResponseHeader("totalPagina");
						
						for (var p = 0; p < totalPagina; p++) {
							
							var url = 'nomeBusca=' + nomeBusca + '&acao=buscarPage&pagina=' + (p * 5);
							
							$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+ url +'\')">'+ (p + 1)+ '</a></li>');
						}
						
					}

				}).fail(
						function(xhr, status, errorThrown) {
							alert('Erro ao buscar usuário por nome: '
									+ xhr.responseText);
						});

			}
		}
		
		function buscaUserPagAjax(url) {
			
			var nomeBusca = document.getElementById('nomeBusca').value;
			var urlAction = document.getElementById('formUser').action;
			
			$.ajax({

				method : "get",
				url : urlAction,
				data : url ,
				success : function(response, textStatus, xhr) {
					
					//  Irá pegar os registros da servlet e converter para o formato JSON, percorrerá o tamanho do JSON e imprimirá na tela
					// os resultados da consulta
					var json = JSON.parse(response);
					
					$('#tabelaResultados > tbody > tr').remove()
					$("#ulPaginacaoUserAjax > li").remove()
					
					for(var p = 0; p < json.length; p++) {
						$('#tabelaResultados > tbody').append(
								'<tr> <td>'+json[p].id+'</td> <td>'+json[p].nome+'</td> <td> <button class="btn btn-primary" onclick="verEditar('+json[p].id+')">Ver</button> </td> </tr>')
					}
					
					document.getElementById('tabelaResultadosSpan').textContent = 'Resultado(s): ' + json.length;
					
					var totalPagina = xhr.getResponseHeader("totalPagina");
					
					for (var p = 0; p < totalPagina; p++) {
						
						var url = 'nomeBusca=' + nomeBusca + '&acao=buscarPage&pagina=' + (p * 5);
						
						$("#ulPaginacaoUserAjax").append('<li class="page-item"><a class="page-link" href="#" onclick="buscaUserPagAjax(\''+ url +'\')">'+ (p + 1)+ '</a></li>');
					}
					
				}

			}).fail(
					function(xhr, status, errorThrown) {
						alert('Erro ao buscar usuário por nome: '
								+ xhr.responseText);
					});
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
		
		function pesquisaCep() {
			var cep = $('#cep').val();
			var logradouro = $('#logradouro').val();
			var bairro = $('#bairro').val();
			var localidade = $('#localidade').val();
			var uf = $('#uf').val();
			
			$.getJSON("https://viacep.com.br/ws/"+cep+"/json/",function(dados) {
				
				 if (!("erro" in dados)) {
					$("#logradouro").val(dados.logradouro);
					$("#bairro").val(dados.bairro);
					$("#localidade").val(dados.localidade);
					$("#uf").val(dados.uf);
				 } else {
                     //CEP pesquisado não foi encontrado.
                     limpa_formulário_cep();
                     alert("CEP não encontrado.");
                 }
			});
				
			}
	</script>
	
</body>
</html>