/**
 * @file VendaService.java
 * @brief Definição da classe VendaService que fornece serviços relacionados à entidade Venda.
 */
package br.ufrn.loja.services;

import br.ufrn.loja.dao.VendaDao;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.StatusVenda;
import br.ufrn.loja.model.Venda;
import br.ufrn.loja.utils.CorUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @class VendaService
 * @brief Fornece serviços relacionados à entidade Venda.
 */
public class VendaService extends AbstractService<Venda> {

    /**
     * @brief Construtor padrão da classe VendaService.
     */
    public VendaService() {
        dao = new VendaDao();
    }
    /**
     * @brief Valida se a venda possui itens adicionados.
     * @return True se a venda for válida, False caso contrário.
     */
    @Override
    protected boolean validar() {
        if(objeto.getItens().isEmpty()){
            System.out.println("Voce precisa adicionar produtos!");
            return false;
        }
        return true;
    }
    /**
     * @brief Imprime os detalhes de todas as vendas cadastradas.
     */
    @Override
    protected void imprimir() {
        System.out.println("\n----- Detalhes da Venda ----------------------");
        System.out.printf("%-5s%-20s%s\n", "ID", "Data", "Itens");
        dao.buscarTodos().forEach(venda -> {
            System.out.printf("%-5s%-20s%s\n", venda.getId(), venda.getData(), venda.getItens());
        });
        System.out.println("------------------------------------------------");
    }
    /**
     * @brief Remove a venda do sistema.
     */
    @Override
    protected void remover() {
        if (dao.existePorId(objeto.getId())) {
            dao.delete(objeto.getId());
            System.out.println(CorUtils.azul("Venda removida com sucesso!!"));
        } else {
            System.out.println(CorUtils.vermelho("Essa venda não existe!"));
        }
    }
    /**
     * @brief Busca e imprime os detalhes de uma venda pelo seu ID.
     * @return True se a venda for encontrada, False caso contrário.
     */
    @Override
    public boolean buscar() {
        Venda vendaEncontrada = dao.buscarPorId(objeto.getId());

        if (vendaEncontrada != null) {
            System.out.println("\n----- Detalhes da Venda ----------------------");
            System.out.printf("%-5s%-20s%s\n", "ID", "Data", "Itens");
            exibirDetalhesVenda(vendaEncontrada);
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
    /**
     * @brief Busca uma venda pelo seu ID.
     * @param idVenda ID da venda a ser buscada.
     * @return Objeto Venda correspondente ao ID ou null se não encontrada.
     */
    public Venda buscarVendaPorId(int idVenda) {
        return dao.buscarPorId(idVenda);
    }
    /**
     * @brief Exibe os detalhes de uma venda, incluindo seus itens.
     * @param venda Venda a ser exibida.
     */
    private void exibirDetalhesVenda(Venda venda) {
        System.out.printf("%-5d%-20s\n", venda.getId(), venda.getData());

        System.out.println("Itens:");
        for (ItemVenda item : venda.getItens()) {
            System.out.printf("- Produto: %s, Quantidade: %d, Subtotal: %.2f\n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
        }
    }
    
    /**
     * Calcula o faturamento total das vendas no período especificado, excluindo vendas canceladas.
     *
     * @param inicio Data de início do período.
     * @param fim    Data de término do período.
     * @return O faturamento total no período, excluindo vendas canceladas.
     */
    public double calcularFaturamento(String inicio, String fim) {
        return obterTodasAsVendas(inicio, fim).stream()
                .filter(venda -> venda.getStatus() != StatusVenda.CANCELADA)
                .mapToDouble(Venda::getTotal)
                .sum();
    }
}

