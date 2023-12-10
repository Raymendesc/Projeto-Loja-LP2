package br.ufrn.loja.dao;

import br.ufrn.loja.model.Fatura;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaturaDao implements GenericDao<Fatura> {

    private Connection con;
    private Statement statement;

    public FaturaDao() {
        this.con = ConnectionFactory.getInstance().getConnection();
    }

    @Override
    public void salvar(Fatura obj) {
    }

    @Override
    public Fatura buscarPorId(int id) {
        return null;
    }

    @Override
    public List<Fatura> buscarTodos() {
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
    public void alterar(Fatura obj) {
    }

    // Métodos auxiliares
}
