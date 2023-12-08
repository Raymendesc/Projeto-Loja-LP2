package br.ufrn.loja.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufrn.loja.infra.ConnectionFactory;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Venda;

//organizar
public class VendaDao implements GenericDao<Venda> {

    private static final String INSERT = "INSERT INTO venda(data, total_venda) VALUES ('%', '%')";
    private static final String SELECT_ALL = "SELECT * FROM venda";
    private static final String DELETE = "DELETE FROM venda WHERE id = ";
    private static final String EXISTE = "SELECT COUNT(*) FROM venda WHERE id = ";
    private static final String SELECT = "SELECT * FROM venda WHERE id = ";
    private Connection con;
    private Statement statement;

    public VendaDao() {
        con = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void salvar(Venda obj) {
        try {
            statement = con.createStatement();
            String sql = INSERT.replaceFirst("%", obj.getData().toString())
                    .replaceFirst("%", Double.toString(obj.getTotal()));
            statement.executeUpdate(sql);
            obj.setId(buscarUltimoId());
            for(ItemVenda item : obj.getItens()){
                item.setVendaId(obj);
                new ItemVendaDao().salvar(item);
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
                venda.setData(rs.getDate("data").toLocalDate());

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
            ResultSet result = statement.executeQuery(SELECT + id);
            if (result.next()) {
                Venda venda = new Venda();
                venda.setId(result.getInt("id"));
                venda.setData(result.getDate("data").toLocalDate());

                // recupere os itens da venda do banco de dados
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
    }

    public int buscarUltimoId(){
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
