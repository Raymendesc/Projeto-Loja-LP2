package br.ufrn.loja.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import br.ufrn.loja.exception.CriacaoConexaoException;
import br.ufrn.loja.utils.ArquivoUtils;

/**
 * @brief Classe responsável por fornecer instâncias de conexão com o banco de
 *        dados SQLite.
 */
public class ConnectionFactory {

	private static ConnectionFactory INSTANCE = null;

	private Connection connection = null;

	/**
     * Construtor privado para garantir que a classe só pode ser instanciada internamente.
     */
	private ConnectionFactory() {
		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:loja.db");
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			String sql = ArquivoUtils.lerTExtoArquivo("src/main/resources/import.sql");
			statement.executeUpdate(sql);
		} catch (Exception e) {
			throw new CriacaoConexaoException("Houve um problema na criação do arquivo do banco.", e);
		}
	}

	/**
     * Obtém a única instância da classe, para assim evitar que se crie diversas conexões.
     *
     * @return Instância da ConnectionFactory
     */
	public static ConnectionFactory getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionFactory();
		}
		return INSTANCE;
	}

	/**
     * Obtém a conexão com o banco de dados.
     *
     * @return Conexão com o banco de dados SQLite
     */
	public Connection getConnection() {
		return this.connection;
	}

}
