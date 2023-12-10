package br.ufrn.loja.view;

import br.ufrn.loja.services.FaturaService;
import br.ufrn.loja.utils.CorUtils;

public class TelaFaturamento extends MenuAbstract {

    private FaturaService faturaService;

    public TelaFaturamento() {
        this.faturaService = new FaturaService();
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
        System.out.println("║           " + CorUtils.laranja("MENU DE FATURAMENTO") + "           ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  " + CADASTRAR + ". Criar Fatura                ║");
        System.out.println("║  " + BUSCAR + ". Adicionar Item à Fatura       ║");
        System.out.println("║  " + VER_TODOS + ". Finalizar Fatura            ║");
        System.out.println("║  " + SAIR + ". Voltar                        ║");
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
                adicionarItemFatura();
                break;
            case VER_TODOS:
                calcularTotalFatura();
                break;
            case SAIR:
                saiu = true;
                break;
            default:
                System.out.println(CorUtils.vermelho("Opção inválida. Tente novamente."));
        }
    }

    private void criarFatura() {
    }

    private void adicionarItemFatura() {
    }

    private void calcularTotalFatura() {
    }


}
