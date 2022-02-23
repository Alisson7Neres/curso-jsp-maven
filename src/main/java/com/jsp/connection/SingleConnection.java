package com.jsp.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp-maven?autoReconnect=true";
	private static String user = "postgres";
	private static String password = "admin";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		 
		try {
			if(connection == null) {
				Class.forName("org.postgresql.Driver"); // carrega o driver de conexão do banco
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
