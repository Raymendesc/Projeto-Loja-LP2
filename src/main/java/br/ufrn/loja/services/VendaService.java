package br.ufrn.loja.services;

import br.ufrn.loja.dao.VendaDao;
import br.ufrn.loja.model.Venda;
import br.ufrn.loja.utils.CorUtils;

import java.util.List;

public class VendaService extends AbstractService<Venda> {


    public VendaService() {
        dao = new VendaDao();
    }

    @Override
    protected boolean validar() {
        if(objeto.getItens().isEmpty()){
            System.out.println("Voce precisa adicionar produtos!");
            return false;
        }
        return true;
    }

    @Override
    protected void imprimir() {
        System.out.println("\n----- Detalhes da Venda ----------------------");
        System.out.printf("%-5s%-20s%s\n", "ID", "Data", "Itens");
        dao.buscarTodos().forEach(venda -> {
            System.out.printf("%-5s%-20s%s\n", venda.getId(), venda.getData(), venda.getItens());
        });
        System.out.println("------------------------------------------------");
    }

    @Override
    protected void remover() {
        if (dao.existePorId(objeto.getId())) {
            dao.delete(objeto.getId());
            System.out.println(CorUtils.azul("Venda removida com sucesso!!"));
        } else {
            System.out.println(CorUtils.vermelho("Essa venda não existe!"));
        }
    }

    @Override
    public boolean buscar() {
        if (dao.existePorId(objeto.getId())) {
            System.out.println("\n----- Detalhes da Venda ----------------------");
            System.out.printf("%-5s%-20s%s\n", "ID", "Data", "Itens");
            System.out.println(dao.buscarPorId(objeto.getId()));
            System.out.println("------------------------------------------------");
            return true;
        } else {
            System.out.println(CorUtils.vermelho("Essa venda não existe!"));
            return false;
        }
    }

    public Venda[] obterTodasAsVendas() {
        List<Venda> listaDeVendas = dao.buscarTodos();
        Venda[] arrayDeVendas = new Venda[listaDeVendas.size()];
        return ((List<?>) listaDeVendas).toArray(arrayDeVendas);
    }

}

