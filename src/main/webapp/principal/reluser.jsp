<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
														<h4 class="sub-title">Rel. Usuário</h4>

														<form class="form-material needs-validation" novalidate
															action="<%=request.getContextPath()%>/ServletUsuarioController"
															method="get" id="formUser">

															<input type="hidden" id="acaoRelatorioImprimirTipo" name="acao"
																value="imprimirRelatorioUser">

															<div class="form-row">
																<div class="col-md-4 mb-3">
																	<label for="dataInicial">Data Inicial</label> <input
																		type="text" class="form-control" id="dataInicial"
																		name="dataInicial" value="${dataInicial}" required>
																</div>

																<div class="col-md-4 mb-3">
																	<label for="dataFinal">Data Final</label> <input
																		type="text" class="form-control" id="dataFinal"
																		name="dataFinal" value="${dataFinal}" required>
																</div>

															</div>
															<button class="btn btn-primary" type="button" onclick="imprimirHtml();">Imprimir
																Relatório form</button>
																		<button class="btn btn-primary" type="button" onclick="imprimirPdf()">Imprimir PDF </button>

														</form>

														<div style="overflow: scroll; height: 300px;">
															<table class="table" id="tabelaResultadosView">
																<thead>
																	<tr>
																		<th scope="col">#</th>
																		<th scope="col">Nome</th>
																	</tr>
																</thead>
																<tbody>
																	<c:forEach items="${ listaUser }" var="lu">
																		<tr>
																			<td><c:out value="${ lu.id }"></c:out></td>
																			<td><c:out value="${ lu.nome }"></c:out></td>
																		</tr>

																		<c:forEach items="${ lu.telefones }" var="fone">
																			<tr>
																				<td/>
																				<td /><p style="font-weight: bold;">Telefone(s):</p>
																				<td style="font-weight: bold;"><c:out value="${ fone.numero}" ></c:out></td>
																			</tr>
																		</c:forEach>
																	</c:forEach>
																</tbody>
															</table>
														</div>
														<button class="btn btn-warning" style="color: black;" >Download</button>
													</div>
												</div>
											</div>
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

	<script>
	
		function imprimirHtml() {
			document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioUser';
			$("#formUser").submit();
		}
		
		function imprimirPdf() {
			document.getElementById("acaoRelatorioImprimirTipo").value = 'imprimirRelatorioPDF';
			$("#formUser").submit();
		}
	
	
		$(function() {

			$("#dataInicial, #dataFinal")
					.datepicker(
							{
								dateFormat : 'dd/mm/yy',
								dayNames : [ 'Domingo', 'Segunda', 'Terça',
										'Quarta', 'Quinta', 'Sexta', 'Sábado' ],
								dayNamesMin : [ 'D', 'S', 'T', 'Q', 'Q', 'S',
										'S', 'D' ],
								dayNamesShort : [ 'Dom', 'Seg', 'Ter', 'Qua',
										'Qui', 'Sex', 'Sáb', 'Dom' ],
								monthNames : [ 'Janeiro', 'Fevereiro', 'Março',
										'Abril', 'Maio', 'Junho', 'Julho',
										'Agosto', 'Setembro', 'Outubro',
										'Novembro', 'Dezembro' ],
								monthNamesShort : [ 'Jan', 'Fev', 'Mar', 'Abr',
										'Mai', 'Jun', 'Jul', 'Ago', 'Set',
										'Out', 'Nov', 'Dez' ],
								nextText : 'Próximo',
								prevText : 'Anterior'
							});
		});
	</script>

</body>

</html>
