package br.ufrn.loja.dao;

import br.ufrn.loja.model.ItemFatura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemFaturaDao implements GenericDao<ItemFatura> {

    private Connection con;
    private Statement statement;

    public ItemFaturaDao() {
        this.con = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void salvar(ItemFatura obj) {
    }

    @Override
    public ItemFatura buscarPorId(int id) {
        return null;
    }

    @Override
    public List<ItemFatura> buscarTodos() {
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
    public void alterar(ItemFatura obj) {
    }

    // Métodos auxiliares
}
