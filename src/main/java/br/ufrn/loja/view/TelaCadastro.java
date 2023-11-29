package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.exception.OpcaoInvalidaException;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.services.AbstractService;
import br.ufrn.loja.services.ProdutoService;
import br.ufrn.loja.utils.CorUtils;

public class TelaCadastro {

	private final int SAIR = 0;
	private final int PRODUTO_COMUM = 1;

	private int opcao;
	private boolean saiu = false;
	private Scanner in;
	private Produto produtoComum;

	private AbstractService<Produto> produtoService = new ProdutoService();

	public TelaCadastro(Scanner in) {
		this.in = in;
	}

	/**
	 * @brief Método que inicia a execução da tela de cadastro.
	 */
	public void run() {
		exibirMenuCadastro();
	}

	/**
	 * @brief Método que exibe o menu de cadastro até que o usuário escolha sair.
	 */
	public void exibirMenuCadastro() {
		while (!saiu) {
			menuCadastro();
			realizarAcao();
			if (this.opcao != SAIR)
				continuar();
		}
	}

	/**
	 * @brief Método imprime no console o menu de cadastro.
	 */
	public void menuCadastro() {
		System.out.println("\n" + CorUtils.laranja("Escolha qual vai cadastrar:"));
		System.out.println(PRODUTO_COMUM + " - Produto comum");
		System.out.println(SAIR + " - Sair");
		this.opcao = in.nextInt();
	}

	/**
	 * @brief Método que realiza a ação correspondente à escolha do usuário.
	 */
	private void realizarAcao() {
		try {
			switch (opcao) {
				case PRODUTO_COMUM:
					lerProdutoComum();
					produtoService.processar(Menu.CADASTRAR);
					break;
				case SAIR:
					this.saiu = true;
					break;
				default:
					throw new OpcaoInvalidaException("Opção inválida! Por favor, escolha uma opção válida.");
			}
		} catch (OpcaoInvalidaException e) {
			System.out.println(CorUtils.vermelho(e.getMessage()));
		}
	}

	/**
	 * @brief Método que pergunta ao usuário se deseja continuar cadastrando.
	 */
	public void continuar() {
		System.out.println("\n[Digite " + SAIR + " para sair ou qualquer numero para continuar cadastrando.]");
		this.opcao = in.nextInt();
		if (opcao == SAIR)
			this.saiu = true;
	}

	/**
	 * @brief Método que lê os dados de um produto comum e seta no service.
	 */
	private void lerProdutoComum() {
		in.nextLine();

		System.out.print("Digite o nome do produto: ");
		String nome = in.nextLine();

		System.out.print("Digite o preço de custo do produto: ");
		double preco_custo = in.nextDouble();

		System.out.print("Digite o preço de venda do produto: ");
		double preco_venda = in.nextDouble();
		in.nextLine();

		System.out.print("Digite o estoque do produto: ");
		int estoque = in.nextInt();
		in.nextLine();

		System.out.print("Digite o fabricante do produto: ");
		String fabricante = in.nextLine();

		produtoComum = new Produto(nome, preco_custo, preco_venda, estoque, fabricante);
		produtoService.setObjeto(produtoComum);
	}

}
