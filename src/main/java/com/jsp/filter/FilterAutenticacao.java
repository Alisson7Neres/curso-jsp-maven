package com.jsp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = {"/principal/*"}) /*Intercepta todas as requisições que vierem do mapeamento*/
public class FilterAutenticacao implements Filter {

    public FilterAutenticacao() {
    }

    // Encerra o processo quando o servidor é parado
	public void destroy() {
	}

	// Intercepta as requisições e as respostas no sistema
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); // URL está sendo acessada
		
		// Validar se está logado
		if(usuarioLogado == null
				&& !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")) {
			
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Realize o LOGIN!");
			redireciona.forward(request, response);
			return; // redireciona para o login
		} else {
			chain.doFilter(request, response);
		}
	}

	// Inicia os processos ou recursos quando o servidor sobe
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
