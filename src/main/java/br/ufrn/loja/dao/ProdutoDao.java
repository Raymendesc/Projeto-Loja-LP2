package br.ufrn.loja.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.Produto;

/**
 * @brief Implementação do DAO para a entidade Produto.
 *
 *        Esta classe é responsável por interagir com o banco de dados para
 *        realizar operações relacionadas a produtos.
 */
public class ProdutoDao implements GenericDao<Produto> {

	private static final String INSERT = "INSERT INTO produto(nome, preco_custo, preco_venda, estoque, fabricante) VALUES (%)";
	private static final String SELECT_ALL = "SELECT * FROM produto";
	private Connection con;
	private Statement statement;

	public ProdutoDao() {
		con = ConnectionFactory.getInstance().getConnection();
	}

	/**
	 * @brief Implementação do método salvar da interface GenericDao.
	 *
	 * Este método salva um objeto Produto no banco de dados.
	 *
	 * @param obj O objeto Produto a ser salvo.
	 */
	@Override
	public void salvar(Produto obj) {
		try {
			statement = con.createStatement();
			statement.executeUpdate(INSERT.replace("%", 
					"'" + obj.getNome() + 
					"', " + obj.getPreco_custo() + 
					", "+ obj.getPreco_venda() + 
					", " + obj.getEstoque() + 
					", '" + obj.getFabricante() + "'"));
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}

	/**
	 * Retorna uma lista de todos os produtos no banco de dados.
	 *  @return Lista de produtos.
	 */
	@Override
	public List<Produto> verTodos() {
		List<Produto> resultado = new ArrayList<>();
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);
			while(rs.next()){
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco_custo(rs.getDouble("preco_custo"));
				produto.setPreco_venda(rs.getDouble("preco_venda"));
				produto.setEstoque(rs.getInt("estoque"));
				produto.setFabricante(rs.getString("fabricante"));
				resultado.add(produto);
		      }
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return resultado;
	}
	
	 /**
     * @brief Fecha os recursos (Statement) utilizados para interagir com o banco de dados.
     */
	private void fecharRecursos() {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
