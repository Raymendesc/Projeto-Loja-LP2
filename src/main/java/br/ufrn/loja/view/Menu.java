package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.exception.OpcaoInvalidaException;
import br.ufrn.loja.utils.CorUtils;
import br.ufrn.loja.view.TelaFaturamento;
public class Menu extends MenuAbstract {

	public Menu() {
		saiu = false;
		in = new Scanner(System.in);
	}

	/**
	 * Metodo responsavel por chamar o menu inicial
	 */
	public static void run() {
		System.out.println(CorUtils.verde("Iniciando Sistema..."));
		new Menu().exibir();
	}

	/**
	 * Faz a chamada do menu inicial, e mantem em um laço ate o usuario decidir
	 * encerrar.
	 */
	public void exibir() {
		while (!saiu) {
			telaInicial();
			realizarAcao();
		}
		in.close();
		System.out.println(CorUtils.verde("Programa Encerrado!"));
	}

	/**
	 * Imprime no console o menu inicial e ler a opção escolhida
	 */
	private void telaInicial() {

		System.out.println("╔══════════════════════════════════════╗");
		System.out.println("║           " + CorUtils.laranja("MENU DE OPÇÕES") + "             ║");
		System.out.println("╠══════════════════════════════════════╣");
		System.out.println("║  " + CADASTRAR + ". Cadastrar Produto                ║");
		System.out.println("║  " + BUSCAR + ". Buscar Produto                   ║");
		System.out.println("║  " + VER_TODOS + ". Ver Todos                        ║");
		System.out.println("║  " + FATURAMENTO + ". Faturamento                      ║");
		System.out.println("║  " + VENDER + ". Vender                           ║");
		System.out.println("║  " + SAIR + ". Sair                             ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.print("Escolha uma opção: ");
		this.opcao = in.nextInt();

	}

	/**
	 * Verifica qual opção o usuario escolheu e redireciona para a execucao da acao
	 * desejada
	 */
	private void realizarAcao() {
		try {
			switch (opcao) {
				case CADASTRAR:
					new TelaCadastro(in).run();
					break;

				case BUSCAR:
					new TelaBusca(in).run(opcao);
					break;

				case VER_TODOS:
					new TelaBusca(in).run(opcao);
					break;

				case FATURAMENTO:
					new TelaFaturamento(in).run();
					break;

				case VENDER:
					new TelaVendas(in).exibirMenuVendas();
					break;

				case SAIR:
					saiu = true;
					break;

				default:
					throw new OpcaoInvalidaException("Opção inválida! Por favor, escolha uma opção válida.");
			}
		} catch (OpcaoInvalidaException e) {
			System.out.println(CorUtils.vermelho(e.getMessage()));
		}
	}
}
