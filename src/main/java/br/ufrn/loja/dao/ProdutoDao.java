package br.ufrn.loja.dao;

import java.sql.Connection;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.Produto;

/**
 * @brief Implementação do DAO para a entidade Produto.
 *
 * Esta classe é responsável por interagir com o banco de dados para realizar operações relacionadas a produtos.
 */
public class ProdutoDao implements GenericDao<Produto> {

	private final String INSERT = "INSERT INTO produto(nome, preco_custo, preco_venda, estoque, fabricante) VALUES (?, ?, ?, ?, ?)";
	private Connection con;
	private PreparedStatement pstm;
	
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
			con = ConnectionFactory.getInstance();
			pstm = (PreparedStatement) con.prepareStatement(INSERT);
			pstm.setString(1, obj.getNome());
			pstm.setDouble(2, obj.getPreco_custo());
			pstm.setDouble(3, obj.getPreco_venda());
			pstm.setInt(4, obj.getEstoque());
			pstm.setString(5, obj.getFabricante());

			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fecharRecursos();
		}
	}
	
	 /**
     * @brief Método privado para fechar os recursos (PreparedStatement e Connection).
     *
     * Este método é responsável por fechar os recursos utilizados pela classe (PreparedStatement e Connection).
     */
	private void fecharRecursos() {
        try {
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
