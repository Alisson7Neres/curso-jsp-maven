package com.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.connection.SingleConnection;
import com.jsp.model.ModelLogin;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws SQLException {
		
		String sql = "insert into model_login(login,senha,nome,email, usuario_id, perfil, sexo) values (?,?,?,?,?,?,?)";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.setLong(5, userLogado);
		preparedSql.setString(6, objeto.getPerfil());
		preparedSql.setString(7, objeto.getSexo());
		
		preparedSql.execute();
		connection.commit();
		
		if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
			sql = "update model_login set fotouser =?, extensaofotouser =? where login =?";
			preparedSql = connection.prepareStatement(sql);
			preparedSql.setString(1, objeto.getFotouser());
			preparedSql.setString(2, objeto.getExtensaofotouser());
			preparedSql.setString(3, objeto.getLogin());
			
			preparedSql.execute();
			connection.commit();
		}
		
		return this.consultaUsuario(objeto.getLogin(), userLogado);
	}
	
public ModelLogin consultaUsuarioLogado(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select *from model_login where upper(login) = upper('"+login+"')";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		ResultSet result = preparedSql.executeQuery();
		
		while(result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setUseradmin(result.getBoolean("useradmin"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setSexo(result.getString("fotouser"));
		}
		
		preparedSql.execute();
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioLogin(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select *from model_login where upper(login) = upper('"+login+"') and useradmin is false";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		ResultSet result = preparedSql.executeQuery();
		
		while(result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setUseradmin(result.getBoolean("useradmin"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setSexo(result.getString("fotouser"));
		}
		
		preparedSql.execute();
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuario(String login, Long userLogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select *from model_login where upper(login) = upper('"+login+"') and useradmin is false and usuario_id = " + userLogado;
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		ResultSet result = preparedSql.executeQuery();
		
		while(result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
		}
		
		preparedSql.execute();
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioID(String id, Long userLogado) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select * from model_login where id = ? and useradmin is false and usuario_id = ?";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1, Long.parseLong(id));
		preparedSql.setLong(2, userLogado);
		
		
		ResultSet result = preparedSql.executeQuery();
		
		while(result.next()) {
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setSenha(result.getString("senha"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			modelLogin.setFotouser(result.getString("fotouser"));
			modelLogin.setExtensaofotouser(result.getString("extensaofotouser"));
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
	
	public void atualizarUsuario(ModelLogin objeto) throws SQLException {
		
		String sql = "UPDATE model_login SET senha=?, login=?, nome=?, email=?, perfil=?, sexo=? WHERE id = "+objeto.getId()+";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, objeto.getSenha());
		statement.setString(2, objeto.getLogin());
		statement.setString(3, objeto.getNome());
		statement.setString(4, objeto.getEmail());
		statement.setString(5, objeto.getPerfil());
		statement.setString(6, objeto.getSexo());
		
		statement.executeUpdate();
		connection.commit();
		
		if (objeto.getFotouser() != null && !objeto.getFotouser().isEmpty()) {
			sql = "update model_login set fotouser =?, extensaofotouser =? where id =?";
			statement = connection.prepareStatement(sql);
			statement.setString(1, objeto.getFotouser());
			statement.setString(2, objeto.getExtensaofotouser());
			statement.setLong(3, objeto.getId());
			
			statement.execute();
			connection.commit();
		}
		
	}
	
	public List<ModelLogin> consultar(String nome, Long userLogado) throws SQLException {
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%"+nome+"%");
		statement.setLong(2, userLogado);
		
		ResultSet result = statement.executeQuery();
		
		// Percorre as linhas de resultado do sql
		while(result.next()) {
			
			ModelLogin modelLogin = new ModelLogin();
			
			modelLogin.setId(result.getLong("id"));
			modelLogin.setLogin(result.getString("login"));
			modelLogin.setNome(result.getString("nome"));
			modelLogin.setEmail(result.getString("email"));
			modelLogin.setPerfil(result.getString("perfil"));
			modelLogin.setSexo(result.getString("sexo"));
			
			lista.add(modelLogin);
		}
		
		statement.execute();
		
		return lista;
	}
	
	public List<ModelLogin> listar(Long userLogado) throws SQLException {
		
		List<ModelLogin> modelLogin = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			
			ModelLogin listar = new ModelLogin();
			
			listar.setId(result.getLong("id"));
			listar.setLogin(result.getString("login"));
			listar.setNome(result.getString("nome"));
			listar.setEmail(result.getString("email"));
			listar.setPerfil(result.getString("perfil"));
			listar.setSexo(result.getString("sexo"));
			
			modelLogin.add(listar);
		}
		
		return modelLogin;
	}
	
	public void deletar(String id) throws SQLException {
		
		String sql = "DELETE FROM model_login where id = ? and useradmin is false;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		
		statement.executeUpdate();
		connection.commit();
	}
}
