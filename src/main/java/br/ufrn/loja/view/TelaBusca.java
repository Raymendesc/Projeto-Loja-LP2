package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.model.Produto;
import br.ufrn.loja.services.AbstractService;
import br.ufrn.loja.services.ProdutoService;

public class TelaBusca {

	private boolean saiu = false;
	private Scanner in;
	private int opcao;
	private AbstractService<Produto> produtoService = new ProdutoService();

	public TelaBusca(Scanner in) {
		this.in = in;
	}

	/**
	 * @brief Método que inicia a execução da TelaBusca.
	 */
	public void run(int opcao) {
		this.opcao = opcao;
		switch (this.opcao) {
		case Menu.VER_TODOS:
			verTodos();
			break;
		default:
			System.out.println("Erro");
		}
	}

	private void verTodos() {
		produtoService.processar(opcao);
	}

}
