package br.ufrn.loja.view;

import java.time.LocalDate;
import java.util.Scanner;

import br.ufrn.loja.dao.ProdutoDao;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.model.Venda;
import br.ufrn.loja.services.VendaService;

public class TelaVendas extends MenuAbstract {

    private static final int VENDER = 1;
    private static final int VER_TODOS = 2;

    public TelaVendas(Scanner in) {
        this.in = in;
    }

    public void exibirMenuVendas() {
        while (!saiu) {
            exibirOpcoesVendas();
            processarOpcaoVendas();
        }
    }

    public void exibirOpcoesVendas() {
        System.out.println("\n----- Menu de Vendas ------------------------");
        System.out.println("1. Iniciar Nova Venda");
        System.out.println("2. Exibir Vendas Realizadas");
        System.out.println("0. Voltar ao Menu Principal");
        System.out.println("---------------------------------------------");
        System.out.println("Escolha a opção: ");
        opcao = in.nextInt();
    }

    private void processarOpcaoVendas() {
        System.out.println("Opção escolhida: " + opcao);

        switch (opcao) {
            case SAIR:
                saiu = true;
                break;
            case VENDER:
                iniciarNovaVenda();
                break;
            case VER_TODOS:
                exibirVendasRealizadas();
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private void exibirVendasRealizadas() {
        System.out.println("Exibindo as vendas realizadas:");


        for (Venda venda : vendaService.obterTodasAsVendas()) {
            System.out.printf("ID da Venda: %d\n", venda.getId());
            System.out.printf("Data da Venda: %s\n", venda.getData());

            System.out.println("Itens:");
            for (ItemVenda item : venda.getItens()) {
                System.out.printf("- Produto: %s, Quantidade: %d, Subtotal: %.2f\n",
                        item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
            }

            System.out.println("--------------------------------------------");
        }
    }
    private void iniciarNovaVenda() {
        VendaService vendaService = new VendaService();
        ProdutoDao produtoDao = new ProdutoDao();
        Venda novaVenda = new Venda();


        System.out.println("\nAdicione produtos à venda:");
        while (true) {
            Produto produto = new Produto();
            System.out.print("Digite o codigo do produto (digite '0' para encerrar):");

            int codigo = in.nextInt();
            if ( codigo == 0) {
                break;
            } else if(produtoDao.existePorId(codigo)) {
                produto = produtoDao.buscarPorId(codigo);
                ItemVenda item = new ItemVenda();
                item.setProduto(produto);

                System.out.println("Digite a quantidade: ");
                int quantidade = in.nextInt();
                item.setQuantidade(quantidade);

                novaVenda.addItem(item);
            } else {
                System.out.println("Esse produto não existe!");
            }
        }

        novaVenda.setData(LocalDate.now());
        vendaService.setObjeto(novaVenda);
        vendaService.processar(Menu.CADASTRAR);

        System.out.println("----- Resumo da Nova Venda ------------------");
        System.out.println("Data: " + novaVenda.getData());
        System.out.println("Itens:");
        for (ItemVenda item : novaVenda.getItens()) {
            System.out.printf("- Produto: %s, Quantidade: %d, Subtotal: %.2f\n",
                    item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
        }
        System.out.printf("Total: %.2f\n", novaVenda.getTotal());
        System.out.println("--------------------------------------------");
    }

}
