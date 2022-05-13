package com.jsp.servlets;

import java.io.IOException;
import java.util.List;

import com.jsp.dao.DAOTelefoneRepository;
import com.jsp.dao.DAOUsuarioRepository;
import com.jsp.model.ModelLogin;
import com.jsp.model.ModelTelefone;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/ServletTelefone")
public class ServletTelefone extends ServletGenericUtil {
	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();
	private DAOTelefoneRepository daoTelefoneRepository = new DAOTelefoneRepository();

	public ServletTelefone() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String iduser = request.getParameter("iduser");
			String acao = request.getParameter("acao");
			
			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("excluir")) {
				
				String idFone = request.getParameter("id");
				
				daoTelefoneRepository.deletarTelefone(Long.parseLong(idFone));
				
				String userpai = request.getParameter("userpai");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(userpai));

				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);
				
				request.setAttribute("msg", "Telefone excluído!");
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
				return;
			}

			if (iduser != null && !iduser.isEmpty()) {

				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(iduser));


				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(modelLogin.getId());
				request.setAttribute("modelTelefones", modelTelefones);
				
				request.setAttribute("modelLogin", modelLogin);
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
			} else {
				List<ModelLogin> modelLogins = daoUsuarioRepository.listar(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("totalPagina", daoUsuarioRepository.totalPagina(this.getUserLogado(request)));
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

				String acao = request.getParameter("acao");
				
	
				String numero = request.getParameter("numero");
				String usuario_pai_id = request.getParameter("id");
	
				ModelTelefone modelTelefone = new ModelTelefone();
			
				modelTelefone.setNumero(numero);
				modelTelefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id)));
				modelTelefone.setUsuario_cad_id(super.getUserLogadoObj(request));

				daoTelefoneRepository.gravarTelefone(modelTelefone);
				
				List<ModelTelefone> modelTelefones = daoTelefoneRepository.listarTelefone(Long.parseLong(usuario_pai_id));
				
				ModelLogin modelLogin = daoUsuarioRepository.consultaUsuarioID(Long.parseLong(usuario_pai_id));
				
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("modelTelefones", modelTelefones);
				request.setAttribute("msg", "Salvo com sucesso");
				request.getRequestDispatcher("principal/telefone.jsp").forward(request, response);
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
