/**
 * @file VendaDao.java
 * @brief Implementação da interface GenericDao para a entidade Venda.
 */
package br.ufrn.loja.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.model.StatusVenda;
import br.ufrn.loja.model.Venda;

/**
 * @class VendaDao
 * @brief Classe de acesso a dados (DAO) para a entidade Venda.
 */
public class VendaDao implements GenericDao<Venda> {

	private static final String INSERT = "INSERT INTO venda(data, status, total_venda) VALUES ('%', '%' , '%')";
	private static final String SELECT_ALL = "SELECT * FROM venda";
	private static final String DELETE = "DELETE FROM venda WHERE id = ";
	private static final String EXISTE = "SELECT COUNT(*) FROM venda WHERE id = ";
	private Connection con;
	private Statement statement;

	/**
	 * @brief Construtor padrão que inicializa a conexão com o banco de dados.
	 */
	public VendaDao() {
		con = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void salvar(Venda obj) {
		try {
			statement = con.createStatement();
			String sql = INSERT.replaceFirst("%", obj.getData().toString())
					.replaceFirst("%", obj.getStatus().toString()).replaceFirst("%", Double.toString(obj.getTotal()));
			statement.executeUpdate(sql);
			obj.setId(buscarUltimoId());
			for (ItemVenda item : obj.getItens()) {
				item.setVendaId(obj);
				new ItemVendaDao().salvar(item);

				Produto produto = new ProdutoDao().buscarPorId(item.getProduto().getId());
				produto.setEstoque(produto.getEstoque() - item.getQuantidade());
				if (produto.getEstoque() >= 0) {
					new ProdutoDao().alterar(produto);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}

	@Override
	public List<Venda> buscarTodos() {
		List<Venda> resultado = new ArrayList<>();
		try {
			statement = con.createStatement();
			ResultSet rs = statement.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				venda.setData(LocalDate.parse(rs.getString("data")));
				venda.setStatus(StatusVenda.fromDescricao(rs.getString("status")));
				venda.setTotal(rs.getDouble("total_venda"));
				// recupere os itens da venda do banco de dados
				List<ItemVenda> itens = new ItemVendaDao().buscarPorVendaId(venda.getId());
				venda.setItens(itens);

				resultado.add(venda);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return resultado;
	}

	@Override
	public Venda buscarPorId(int id) {
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(SELECT_ALL + " where id = " + id);
			if (result.next()) {
				Venda venda = new Venda();
				venda.setId(result.getInt("id"));
				venda.setData(LocalDate.parse(result.getString("data")));
				venda.setStatus(StatusVenda.fromDescricao(result.getString("status")));
				venda.setTotal(result.getDouble("total_venda"));
				List<ItemVenda> itens = new ItemVendaDao().buscarPorVendaId(id);
				venda.setItens(itens);

				return venda;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return null;
	}

	@Override
	public void delete(int id) {
		try {
			statement = con.createStatement();
			statement.executeUpdate(DELETE + id);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}

	@Override
	public boolean existePorId(int id) {
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery(EXISTE + id);
			return result.next() && result.getInt(1) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return false;
	}

	@Override
	public void alterar(Venda obj) {
		try {
			statement = con.createStatement();
			statement.executeUpdate("UPDATE venda SET status = 'CANCELADA' WHERE id = " + obj.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}

	/**
	 * @brief Busca o último ID inserido no banco de dados.
	 * @return Último ID inserido.
	 */
	public int buscarUltimoId() {
		try {
			statement = con.createStatement();
			ResultSet result = statement.executeQuery("SELECT last_insert_rowid() AS novo_id;");
			return result.getInt("novo_id");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return 0;
	}

	/**
	 * @brief Fecha os recursos (Statement) utilizados para interagir com o banco de
	 *        dados.
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
