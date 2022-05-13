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

														<form class="form-material"  action="<%= request.getContextPath() %>/ServletTelefone" method="post" id="formFone">

															<input type="hidden" name="acao" id="acao" value="">

															<div class="form-group form-default form-static-label">
																<input type="text" name="id" id="id" readonly="readonly"
																	class="form-control" value="${modelLogin.id}">
																<span class="form-bar"></span> <label
																	class="float-label">ID:</label>
															</div>
															
															<div class="form-group form-default form-static-label">
																<input type="text" name="nome" id="nome" readonly="readonly"
																	class="form-control" required="required"
																	value="${modelLogin.nome}"> <span
																	class="form-bar"></span> <label class="float-label">Nome:</label>
															</div>

															<div class="form-group form-default form-static-label">
																<input name="numero" id="numero"
																	class="form-control" required="required"
																	autocomplete="off">
																<span class="form-bar"></span> <label
																	class="float-label">Telefone:</label>
															</div>
															
															<button class="btn btn-dark">Salvar</button>
					
														</form>
															<span id="mgs" ${ msg } ></span>
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
													<th scope="col">Número</th>
													<th scope="col">Excluír</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${ modelTelefones }" var="mt">
													<tr>
														<td><c:out value="${ mt.id }"></c:out></td>
														<td><c:out value="${ mt.numero }"></c:out></td>
														<td><a href="ServletTelefone?acao=excluir&id=${ mt.id }&userpai=${ modelLogin.id }" class="btn btn-primary" data-dismiss="modal">Excluír</a></td>
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
	
	    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.10/jquery.mask.js"></script>
	<script type="text/javascript">
	
		$("#numero").keypress(function(event) {
			return /\d/.test(String.fromCharCode(event.keyCode));
		});
		
		$('#numero').mask("(99) 9 9999-9999");
		
	</script>

</body>
</html>