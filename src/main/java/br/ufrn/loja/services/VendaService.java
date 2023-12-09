package br.ufrn.loja.services;

import br.ufrn.loja.dao.ItemVendaDao;
import br.ufrn.loja.dao.VendaDao;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Venda;
import br.ufrn.loja.utils.CorUtils;

import java.time.LocalDate;
import java.util.ArrayList;
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

    /**
     * @brief Obtém todas as vendas no intervalo de datas especificado.
     * @param inicio Data de início do intervalo no formato "yyyy-MM-dd".
     * @param fim Data de fim do intervalo no formato "yyyy-MM-dd".
     * @return Lista de vendas no intervalo de datas especificado.
     */
    public List<Venda> obterTodasAsVendas(String inicio, String fim) {
        List<Venda> listaDeVendas = dao.buscarTodos();
        List<Venda> retorno = new ArrayList<>();
        for(Venda venda : listaDeVendas) {
        	if(venda.getData().isAfter(LocalDate.parse(inicio)) || venda.getData().isEqual(LocalDate.parse(inicio))) {
        		if(venda.getData().isBefore(LocalDate.parse(fim)) || venda.getData().isEqual(LocalDate.parse(fim))) {
        			retorno.add(venda);
        		}
        	}
        }
        return retorno;
    }

}

