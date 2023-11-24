package br.ufrn.loja.infra;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @brief Classe responsável por fornecer instâncias de conexão com o banco de dados MySQL.
 */
public class ConnectionFactory {

	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/loja";

	private static Connection connection;

	private ConnectionFactory() {
	}
	
	/**
     * @brief Obtém uma instância de conexão com o banco de dados.
     *
     * @return Instância de Connection para interação com o banco de dados.
     * @throws Exception Se ocorrer um erro durante a obtenção da conexão.
     */
	public static Connection getInstance() throws Exception {
		if (connection == null || connection.isClosed()) {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		}
		return connection;
	}

}
