package me.lukedluz.SpecterPunish.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MySQL {

	public Connection connection;
	public Statement statement;
	private String host,user,password,database;
	
	public MySQL(String host, String user, String password, String database) {
		this.host = host;
		this.user = user;
		this.password = password;
		this.database = database;
	}

	public void startConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.database, this.user, this.password);
			this.statement = connection.createStatement();
			this.statement.execute("CREATE TABLE IF NOT EXISTS punish_geral (punicoes INT, id_atual INT);");
			this.statement.execute("CREATE TABLE IF NOT EXISTS punish_punishments (nome VARCHAR(16), id INT, motivo TEXT, punisher VARCHAR(16), status BOOLEAN, start LONG, prova TEXT);");
			this.statement.execute("CREATE TABLE IF NOT EXISTS punish_temporary (nome VARCHAR(16), id INT, motivo TEXT, punisher VARCHAR(16), status BOOLEAN, start LONG, end LONG, prova TEXT);");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void stopConnection() {
		try {
			this.connection.close();
			this.statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
