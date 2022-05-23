package com.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jsp.connection.SingleConnection;
import com.jsp.model.ModelTelefone;

public class DAOTelefoneRepository {

	private Connection connection;
	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public DAOTelefoneRepository() {
		connection = SingleConnection.getConnection();
	}

	public void gravarTelefone(ModelTelefone modelTelefone) throws Exception {

		String sql = "insert into telefone (numero, usuario_pai_id , usuario_cad_id) values (?, ?, ?)";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelTelefone.getNumero());
		statement.setLong(2, modelTelefone.getUsuario_pai_id().getId());
		statement.setLong(3, modelTelefone.getUsuario_cad_id().getId());

		statement.execute();
		connection.commit();

	}

	public void deletarTelefone(Long id) throws Exception {

		String sql = "delete from telefone where id =?";

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, id);

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
			telefone.setUsuario_cad_id(daoUsuarioRepository.consultaUsuarioID(result.getLong("usuario_cad_id")));
			telefone.setUsuario_pai_id(daoUsuarioRepository.consultaUsuarioID(result.getLong("usuario_pai_id")));

			retorno.add(telefone);
		}

		return retorno;
	}

	public boolean existeFone(String fone, Long idUser) throws SQLException {
		String sql = "select count(1) > 0 as existe from telefone where usuario_pai_id =? and numero =?";
		PreparedStatement statement = connection.prepareStatement(sql);
		
		statement.setLong(1, idUser);
		statement.setString(2, fone);
		
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		
		return resultSet.getBoolean("existe");
		
	}

}
