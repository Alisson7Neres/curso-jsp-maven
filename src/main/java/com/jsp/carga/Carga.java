package com.jsp.carga;

import com.jsp.dao.DAOUsuarioRepository;
import com.jsp.model.ModelLogin;

public class Carga implements Runnable{
	
	DAOUsuarioRepository repository = new DAOUsuarioRepository();
	
	ModelLogin login = new ModelLogin();
	
	public void carga() throws Exception {
		
		System.out.println("ENTROU!");
		
		login.setLogin("admin");
		login.setSenha("admin");
		
		repository.gravarUsuario(login);
	}

	@Override
	public void run() {
		try {
			this.carga();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
