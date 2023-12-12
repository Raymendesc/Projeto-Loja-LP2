/**
 * @file ItemVendaDao.java
 * @brief Implementação da interface GenericDao para a entidade ItemVenda.
 */
package br.ufrn.loja.dao;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @class ItemVendaDao
 * @brief Classe de acesso a dados (DAO) para a entidade ItemVenda.
 */
public class ItemVendaDao implements GenericDao<ItemVenda> {

	private static final String INSERT = "INSERT INTO item_venda (venda_id, produto_id, quantidade, subtotal) VALUES (?, ?, ?, ?)";
	private static final String SELECT_ALL = "SELECT * FROM item_venda";
	private Connection con;
	private PreparedStatement pstmt;

	/**
	 * @brief Construtor padrão que inicializa a conexão com o banco de dados.
	 */
	public ItemVendaDao() {
		con = ConnectionFactory.getInstance().getConnection();
	}

	@Override
	public void salvar(ItemVenda obj) {

		try {
			pstmt = con.prepareStatement(INSERT);
			pstmt.setInt(1, obj.getVendaId().getId());
			pstmt.setInt(2, obj.getProduto().getId());
			pstmt.setInt(3, obj.getQuantidade());
			pstmt.setDouble(4, obj.getSubtotal());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}
	
	@Override
	public List<ItemVenda> buscarTodos() {
		List<ItemVenda> resultado = new ArrayList<>();
		try (PreparedStatement pstmt = con.prepareStatement(SELECT_ALL)) {
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setQuantidade(rs.getInt("quantidade"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco_venda(rs.getDouble("preco_venda"));

				item.setProduto(produto);
				resultado.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
		return resultado;
	}

	@Override
	public ItemVenda buscarPorId(int id) {
		return null;
	}

	@Override
	public void delete(int id) {

	}

	@Override
	public boolean existePorId(int id) {
		return false;
	}

	@Override
	public void alterar(ItemVenda obj) {

	}

	/**
	 * @brief Fecha os recursos (Statement) utilizados para interagir com o banco de
	 *        dados.
	 */
	private void fecharRecursos() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @brief Busca todos os itens de uma venda especifica
	 * @param vendaId ID da venda associada aos itens.
	 * @return Lista de itens de venda associados à venda.
	 */
	public List<ItemVenda> buscarPorVendaId(int vendaId) {
		List<ItemVenda> itens = new ArrayList<>();
		String sql = "SELECT v.id, v.quantidade, v.produto_id, p.nome, p.preco_venda " + "FROM item_venda v "
				+ "JOIN produto p ON p.id = v.produto_id " + "WHERE v.venda_id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, vendaId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				ItemVenda item = new ItemVenda();
				item.setId(rs.getInt("id"));
				item.setQuantidade(rs.getInt("quantidade"));

				Produto produto = new Produto();
				produto.setId(rs.getInt("produto_id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco_venda(rs.getDouble("preco_venda"));

				item.setProduto(produto);
				itens.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}

		return itens;
	}



}
