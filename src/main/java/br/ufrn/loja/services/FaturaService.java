package br.ufrn.loja.services;

import br.ufrn.loja.dao.FaturaDao;
import br.ufrn.loja.model.Fatura;
import br.ufrn.loja.model.ItemFatura;

public class FaturaService extends AbstractService<Fatura> {

    public FaturaService() {
        dao = new FaturaDao(); // Inicializa o DAO
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

  
    public void criarFatura(String cliente) {
        this.objeto = new Fatura(cliente);
        dao.salvar(this.objeto);  
    }

    public void adicionarItemFatura(ItemFatura item) {
        this.objeto.adicionarItem(item);
        dao.alterar(this.objeto);  
    }

    public double calcularTotalFatura() {
        double total = this.objeto.calcularTotal();
        dao.alterar(this.objeto);  
        return total;
    }
}
