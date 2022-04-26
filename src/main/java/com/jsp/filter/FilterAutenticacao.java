package com.jsp.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.jsp.connection.SingleConnection;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/principal/*" }) /* Intercepta todas as requisições que vierem do mapeamento */
public class FilterAutenticacao implements Filter {

	private static Connection connection;

	public FilterAutenticacao() {
	}

	// Encerra o processo quando o servidor é parado
	public void destroy() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Intercepta as requisições e as respostas no sistema
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpSession session = req.getSession();

			String usuarioLogado = (String) session.getAttribute("usuario");

			String urlParaAutenticar = req.getServletPath(); // URL está sendo acessada

			// Validar se está logado
			if (usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {

				RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				request.setAttribute("msg", "Realize o LOGIN!");
				redireciona.forward(request, response);
				return; // redireciona para o login
			} else {
				chain.doFilter(request, response);
			}
			
			connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("error.jsp");
			request.setAttribute("msg",e.getMessage());
			redirecionar.forward(request, response);
		}
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	// Inicia os processos ou recursos quando o servidor sobe
	public void init(FilterConfig fConfig) throws ServletException {
		connection = SingleConnection.getConnection();
	}

}
