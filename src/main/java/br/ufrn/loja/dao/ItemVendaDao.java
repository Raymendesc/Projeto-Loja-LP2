package br.ufrn.loja.dao;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.ItemVenda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemVendaDao implements GenericDao<ItemVenda> {

    private static final String INSERT = "INSERT INTO item_venda(venda_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (%)";

    private Connection con;
    private Statement statement;

    public ItemVendaDao(){
        con = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void salvar(ItemVenda obj) {
        String sql = "INSERT INTO item_venda (venda_id, produto_id, quantidade, preco_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, obj.getVendaId().getId());
            pstmt.setInt(2, obj.getProduto().getId());
            pstmt.setInt(3, obj.getQuantidade());
            pstmt.setDouble(4, obj.getProduto().getPreco_venda());
            pstmt.setDouble(5, obj.getSubtotal());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            fecharRecursos();
        }
    }


    @Override
    public List<ItemVenda> buscarTodos() {
        return null;
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
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ItemVenda> buscarPorVendaId(int vendaId) {
        List<ItemVenda> itens = new ArrayList<>();
        String sql = "SELECT * FROM item_venda WHERE venda_id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setInt(1, vendaId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                item.setId(rs.getInt("id"));
                item.setQuantidade(rs.getInt("quantidade"));


                itens.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itens;
    }
}
