package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.utils.CorUtils;

public class Menu {

	public static final int SAIR = 0;
	public static final int CADASTRAR = 1;
	public static final int BUSCAR = 2;
	public static final int VER_TODOS = 3;
	public static final int FATURAMENTO = 4;

	private int opcao;
	private boolean saiu;
	private Scanner in;

	public Menu() {
		this.saiu = false;
		this.in = new Scanner(System.in);
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

		System.out.println("\n"+CorUtils.laranja("Digite a opção:"));
		System.out.println(CADASTRAR + " - Cadastrar Produto");
		System.out.println(BUSCAR + " - Buscar Produto");
		System.out.println(VER_TODOS + " - Ver todos os produtos");
		System.out.println(FATURAMENTO + " - Ver faturamento");
		System.out.println(SAIR + " - sair");

		this.opcao = in.nextInt();

	}

	/**
	 * Verifica qual opção o usuario escolheu e redireciona para a execucao da acao
	 * desejada
	 */
	private void realizarAcao() {
		switch (opcao) {
		case CADASTRAR:
			new TelaCadastro(in).run();
			break;

		case BUSCAR:
			System.out.println("Busacando");
			break;

		case VER_TODOS:
			System.out.println("Vendo todos");
			break;

		case FATURAMENTO:
			System.out.println("Exibindo faturamento");
			break;

		case SAIR:
			saiu = true;
			break;

		default:
			System.out.println("Valor Invalido!");// criar uma exceção
		}
	}
}
