package com.jsp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.jsp.connection.SingleConnection;
import com.jsp.model.ModelLogin;
import com.jsp.model.ModelTelefone;

public class DAOUsuarioRepository {

	private Connection connection;
	
	public DAOUsuarioRepository() {
		connection = SingleConnection.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objeto, Long userLogado) throws SQLException {
		
		String sql = "insert into model_login(login,senha,nome,email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero, datanascimento, rendamensal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		
		preparedSql.setString(1, objeto.getLogin());
		preparedSql.setString(2, objeto.getSenha());
		preparedSql.setString(3, objeto.getNome());
		preparedSql.setString(4, objeto.getEmail());
		preparedSql.setLong(5, userLogado);
		preparedSql.setString(6, objeto.getPerfil());
		preparedSql.setString(7, objeto.getSexo());
		preparedSql.setString(8, objeto.getCep());
		preparedSql.setString(9, objeto.getLogradouro());
		preparedSql.setString(10, objeto.getBairro());
		preparedSql.setString(11, objeto.getLocalidade());
		preparedSql.setString(12, objeto.getUf());
		preparedSql.setString(13, objeto.getNumero());
		preparedSql.setString(14, objeto.getDataNascimento().toString());
		preparedSql.setDouble(15, objeto.getRendaMensal());
		
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
			modelLogin.setFotouser(result.getString("fotouser"));
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
			modelLogin.setCep(result.getString("cep"));
			modelLogin.setLogradouro(result.getString("logradouro"));
			modelLogin.setBairro(result.getString("bairro"));
			modelLogin.setLocalidade(result.getString("localidade"));
			modelLogin.setUf(result.getString("uf"));
			modelLogin.setNumero(result.getString("numero"));
			modelLogin.setDataNascimento(result.getDate("dataNascimento"));
			modelLogin.setRendaMensal(result.getDouble("rendaMensal"));
		}
		
		preparedSql.execute();
		
		return modelLogin;
	}
	
	public ModelLogin consultaUsuarioID(Long id) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
	
		String sql = "select * from model_login where id = ? and useradmin is false";
		
		PreparedStatement preparedSql = connection.prepareStatement(sql);
		preparedSql.setLong(1, id);
		
		
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
			modelLogin.setCep(result.getString("cep"));
			modelLogin.setLogradouro(result.getString("logradouro"));
			modelLogin.setBairro(result.getString("bairro"));
			modelLogin.setLocalidade(result.getString("localidade"));
			modelLogin.setUf(result.getString("uf"));
			modelLogin.setNumero(result.getString("numero"));
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
		
		String sql = "UPDATE model_login SET senha=?, login=?, nome=?, email=?, perfil=?, sexo=?, cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=?, datanascimento=?, rendamensal=? WHERE id = "+objeto.getId()+";";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, objeto.getSenha());
		statement.setString(2, objeto.getLogin());
		statement.setString(3, objeto.getNome());
		statement.setString(4, objeto.getEmail());
		statement.setString(5, objeto.getPerfil());
		statement.setString(6, objeto.getSexo());
		statement.setString(7, objeto.getCep());
		statement.setString(8, objeto.getLogradouro());
		statement.setString(9, objeto.getBairro());
		statement.setString(10, objeto.getLocalidade());
		statement.setString(11, objeto.getUf());
		statement.setString(12, objeto.getNumero());
		statement.setDate(13, objeto.getDataNascimento());
		statement.setDouble(14, objeto.getRendaMensal());
		
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
	
	public List<ModelLogin> consultarOffset(String nome, Long userLogado, int offset) throws SQLException {
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? offset "+ offset +" limit 5";
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
	
	public int consultarTotalPaginaPaginacao(String nome, Long userLogado) throws SQLException {
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select count(1) as total from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setString(1, "%"+nome+"%");
		statement.setLong(2, userLogado);
		
		ResultSet result = statement.executeQuery();
		
		result.next();
		
		Double cadastros = result.getDouble("total");
		
		Double porPagina = 5.0;
		Double pagina = cadastros / porPagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina ++;
		}
		
		return pagina.intValue();
		
	}
	
	public List<ModelLogin> consultar(String nome, Long userLogado) throws SQLException {
		
		List<ModelLogin> lista = new ArrayList<ModelLogin>();
		
		String sql = "select * from model_login where upper(nome) like upper(?) and useradmin is false and usuario_id = ? limit 5";
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
	
	public List<ModelLogin> listarPaginada(Long userLogado, Integer offset) throws SQLException {
		
		List<ModelLogin> modelLogin = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " order by id asc offset "+offset+" limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			
			ModelLogin listar = new ModelLogin();
			
			listar.setId(result.getLong("id"));
			listar.setNome(result.getString("nome"));
			listar.setFotouser(result.getString("fotouser"));
			listar.setExtensaofotouser(result.getString("extensaofotouser"));
			
			modelLogin.add(listar);
		}
		
		return modelLogin;
	}
	
	public int totalPagina(Long userLogado) throws Exception{
		
		String sql = "select count(1) as total from model_login where usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		result.next();
		
		Double cadastros = result.getDouble("total");
		
		Double porPagina = 5.0;
		Double pagina = cadastros / porPagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina ++;
		}
		
		return pagina.intValue();
	}
	
	public List<ModelLogin> listar(Long userLogado) throws SQLException {
		
		List<ModelLogin> modelLogin = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado + " order by id asc limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			
			ModelLogin listar = new ModelLogin();
			
			listar.setId(result.getLong("id"));
			listar.setNome(result.getString("nome"));
			listar.setFotouser(result.getString("fotouser"));
			listar.setExtensaofotouser(result.getString("extensaofotouser"));
			
			modelLogin.add(listar);
		}
		
		return modelLogin;
	}
	
	public List<ModelLogin> listarRel(Long userLogado) throws Exception {
		
		List<ModelLogin> modelLogin = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			
			ModelLogin listar = new ModelLogin();
			
			listar.setId(result.getLong("id"));
			listar.setNome(result.getString("nome"));
			listar.setDataNascimento(result.getDate("dataNascimento"));
			listar.setEmail(result.getString("email"));
			
			modelLogin.add(listar);
		}
		
		return modelLogin;
	}
	
	public List<ModelLogin> listarRel(Long userLogado, String dataInicial, String dataFinal) throws Exception {
		
		List<ModelLogin> modelLogin = new ArrayList<ModelLogin>();
		String sql = "select * from model_login where useradmin is false and usuario_id = " + userLogado
				+ " and datanascimento >= ? and datanascimento <= ?"; 
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setDate(1,Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataInicial))));
		statement.setDate(2,Date.valueOf(new SimpleDateFormat("yyyy-mm-dd").format(new SimpleDateFormat("dd/mm/yyyy").parse(dataFinal))));
		ResultSet result = statement.executeQuery();
		
		while(result.next()) {
			
			ModelLogin listar = new ModelLogin();
			
			listar.setId(result.getLong("id"));
			listar.setNome(result.getString("nome"));
			listar.setDataNascimento(result.getDate("dataNascimento"));
			listar.setEmail(result.getString("email"));
			
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
	
	public List<ModelTelefone> listarTelefone(Long idUserPai) throws Exception {

		List<ModelTelefone> retorno = new ArrayList<ModelTelefone>();

		String sql = "select * from telefone where usuario_pai_id =?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, idUserPai);

		ResultSet result = statement.executeQuery();

		while (result.next()) {

			ModelTelefone telefone = new ModelTelefone();

			telefone.setId(result.getLong("id"));
			telefone.setNumero(result.getString("numero"));
			telefone.setUsuario_cad_id(this.consultaUsuarioID(result.getLong("usuario_cad_id")));
			telefone.setUsuario_pai_id(this.consultaUsuarioID(result.getLong("usuario_pai_id")));

			retorno.add(telefone);
		}

		return retorno;
	}
}
