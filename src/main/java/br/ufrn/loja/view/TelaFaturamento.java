package br.ufrn.loja.view;

import java.util.Scanner;
import br.ufrn.loja.services.FaturaService;
import br.ufrn.loja.utils.CorUtils;

public class TelaFaturamento {

    private Scanner in;
    private FaturaService faturaService;

    public TelaFaturamento(Scanner in, FaturaService faturaService) {
        this.in = in;
        this.faturaService = faturaService;
    }

    public void exibirMenuFaturamento() {
        int opcao;
        do {
            System.out.println("╔══════════════════════════════════════╗");
            System.out.println("║           " + CorUtils.laranja("MENU DE FATURAMENTO") + "             ║");
            System.out.println("╠══════════════════════════════════════╣");
            /*
             adicionar a integração com as opções do menu
            */
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    criarFatura();
                    break;
                case 2:
                    adicionarItemFatura();
                    break;
                case 3:
                    finalizarFatura();
                    break;
                case 0:
                    break;
                default:
                    System.out.println(CorUtils.vermelho("Opção inválida. Tente novamente."));
                    break;
            }
        } while (opcao != 0);
    }



    private void criarFatura() {
    }

    private void adicionarItemFatura() {
    }

    private void finalizarFatura() {
    }

}
