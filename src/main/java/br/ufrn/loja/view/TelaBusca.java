package br.ufrn.loja.view;

import java.util.Scanner;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.utils.CorUtils;

public class TelaBusca extends MenuAbstract {

    public TelaBusca(Scanner in) {
        this.in = in;
        produtoService.setObjeto(new Produto());
    }

    /**
     * Método que inicia a execução da TelaBusca.
     */
    public void run(int opcao) {
        this.opcao = opcao;
        switch (this.opcao) {
            case VER_TODOS:
            	 produtoService.processar(VER_TODOS);
                break;
            case BUSCAR:
                buscarProduto();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void buscarProduto() {
        System.out.println("Digite o ID para a busca");
        produtoService.getObjeto().setId(in.nextInt());

        if (produtoService.buscar()) {
            exibirOpcoesAlteracaoRemocao();
        }
    }

    private void exibirOpcoesAlteracaoRemocao() {
        System.out.println("["+SAIR+"] Voltar   ["+REMOVER+"] Remover    ["+ALTERAR+"] Alterar");
        opcao = in.nextInt();

        if (opcao != SAIR) {
            if (opcao == REMOVER) {
                produtoService.processar(REMOVER);
            } else if (opcao == ALTERAR) {
                alterarProduto();
            } else {
                System.out.println(CorUtils.vermelho("Opção inválida!"));
            }
        }
    }

    private void alterarProduto() {
        int id = produtoService.getObjeto().getId();
        produtoService.setObjeto(lerProduto());
        produtoService.getObjeto().setId(id);
        produtoService.processar(ALTERAR);
    }
}
