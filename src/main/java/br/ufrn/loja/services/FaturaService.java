package br.ufrn.loja.services;

import br.ufrn.loja.dao.FaturaDao;
import br.ufrn.loja.model.Fatura;
import br.ufrn.loja.model.ItemFatura;

public class FaturaService extends AbstractService<Fatura> {

    public FaturaService() {
        this.dao = new FaturaDao(); // Inicializa o DAO
    }

    @Override
    protected boolean validar() {
        return true;
    }

    @Override
    protected void remover() {
    }

    @Override
    protected void imprimir() {
    }

    @Override
    public boolean buscar() {
        return false;
    }

    // Método para criar uma nova fatura
    public void criarFatura(String cliente) {
        this.objeto = new Fatura();
        this.objeto.setCliente(cliente);
        // definir ainda outros atributos necessários
    }

    public void adicionarItemFatura(ItemFatura item) {
        this.objeto.adicionarItem(item);
    }

    public void calcularTotalFatura() {
        this.objeto.calcularTotal();
    }
}
