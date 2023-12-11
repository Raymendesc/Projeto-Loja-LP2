package br.ufrn.loja.view;


import java.util.Scanner;

import br.ufrn.loja.dao.ProdutoDao;
import br.ufrn.loja.model.ItemFatura;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.services.FaturaService;
import br.ufrn.loja.utils.CorUtils;

public class TelaFaturamento extends MenuAbstract {

    private FaturaService faturaService;
     private Scanner in;
    private ProdutoDao produtoDao; 

     public TelaFaturamento(Scanner in) {
        this.in = in; 
        this.faturaService = new FaturaService();
        this.produtoDao = new ProdutoDao();
    }

    /**
     * @brief Exibe o menu de faturamento e processa as opções escolhidas pelo usuário.
     */
    public void exibirMenuFaturamento() {
        while (!saiu) {
            exibirOpcoesFaturamento();
            processarOpcaoFaturamento();
        }
    }

    /**
     * @brief Exibe as opções do menu de faturamento.
     */
    public void exibirOpcoesFaturamento() {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           " + CorUtils.laranja("MENU DE FATURAMENTO") + "        ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  " + CADASTRAR + ". Criar Fatura                     ║");
        System.out.println("║  " + BUSCAR + ". Adicionar Item à Fatura          ║");
        System.out.println("║  " + VER_TODOS + ". Finalizar Fatura                 ║");
        System.out.println("║  " + SAIR + ". Voltar                           ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.print("Escolha uma opção: ");

        opcao = in.nextInt();
    }

    /**
     * @brief Processa a opção escolhida pelo usuário no menu de faturamento.
     */
    private void processarOpcaoFaturamento() {
        switch (opcao) {
            case CADASTRAR:
                criarFatura();
                break;
            case BUSCAR:
                if (faturaService.getObjeto() != null) {
                    adicionarItemFatura();
                } else {
                    System.out.println("Por favor, crie uma fatura primeiro.");
                }
                break;
            case VER_TODOS:
                if (faturaService.getObjeto() != null) {
                    calcularTotalFatura();
                } else {
                    System.out.println("Por favor, crie uma fatura primeiro.");
                }
                break;
            case SAIR:
                saiu = true;
                break;
            default:
                System.out.println(CorUtils.vermelho("Opção inválida. Tente novamente."));
        }
    }

    private void criarFatura() {
        in.nextLine();  

        System.out.print("Digite o nome do cliente: ");
        String cliente = in.nextLine();

       
        faturaService.criarFatura(cliente);

        System.out.println("Fatura criada com sucesso!");
    }

    private void adicionarItemFatura() {
    in.nextLine();  

    System.out.print("Digite o ID do produto: ");
    int idProduto = in.nextInt();

    System.out.print("Digite a quantidade do produto: ");
    int quantidade = in.nextInt();

    
    Produto produto = produtoDao.buscarPorId(idProduto);

    if (produto != null) {
        ItemFatura item = new ItemFatura(produto, quantidade);

        
        faturaService.adicionarItemFatura(item);

        System.out.println("Item adicionado com sucesso!");
    } else {
        System.out.println("Produto não encontrado.");
    }
}

    private void calcularTotalFatura() {
        double total = faturaService.calcularTotalFatura();
        System.out.println("O total da fatura é: " + total);
    }


}
