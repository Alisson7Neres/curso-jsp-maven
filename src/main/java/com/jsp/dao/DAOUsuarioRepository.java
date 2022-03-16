package com.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jsp.connection.SingleConnection;
import com.jsp.model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto) throws SQLException {
		
		String sql = "insert into model_login(login,senha,nome,email) values (?,?,?,?)";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		
		preparedSql.execute();
		connection.commit();
		
		return this.consultaUsuario(objeto.getLogin());
	}
	
	public ModelLogin consultaUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select *from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		ResultSet result = preparedSql.executeQuery();
		
		if(result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
		}
		
		preparedSql.execute();
		
		return modelLogin;
	}
	
	public boolean validarLogin(String login) throws Exception {
		String sql = "select count(1) > 0 as existe from model_login where upper(login) = upper('"+login+"')"; 
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		if(result.next()) {
			return result.getBoolean("existe");
		}
		return false;
	}
}
