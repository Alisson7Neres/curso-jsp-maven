package com.jsp.servlets;

import java.io.IOException;

import com.jsp.dao.DAOLoginRepository;
import com.jsp.dao.DAOUsuarioRepository;
import com.jsp.model.ModelLogin;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/principal/ServletLogin", "/ServletLogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOLoginRepository daoLoginRepository = new DAOLoginRepository();
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletLogin() {
		super();
	}

	// Recebe os dados peça URL em parametros
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		if(acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("logout")) {
			request.getSession().invalidate();
			RequestDispatcher redirecionar = request.getRequestDispatcher("index.jsp");
			redirecionar.forward(request, response);
		} else {
			doPost(request, response);
		}
		
	}

	// Recebe os dados enviados por um formulario
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");

		try {
			if (login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {

				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setLogin(login);
				modelLogin.setSenha(senha);

				if (daoLoginRepository.validarAutenticacao(modelLogin)) {

					modelLogin = daoUsuarioRepository.consultaUsuarioLogado(login);
					// Pega o login da sessão
					request.getSession().setAttribute("usuario", modelLogin.getLogin());
					request.getSession().setAttribute("perfil", modelLogin.getPerfil());

					if (url == null || url.equals("null")) {
						url = "principal/principal.jsp";
					}

					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				} else {
					request.setAttribute("msg", "Informe o login e senha CORRETAMENTE!");
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					redirecionar.forward(request, response);
				}
			} else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				request.setAttribute("msg", "Informe o login e senha CORRETAMENTE!");
				redirecionar.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}
}
