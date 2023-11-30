package br.ufrn.loja.view;

import java.util.Scanner;

public class TelaCadastro extends MenuAbstract{

	public TelaCadastro(Scanner in) {
		this.in = in;
	}

	/**
	 * @brief Método que inicia a execução do processo de cadastro.
	 */
	public void run() {
		while (!saiu) {
			produtoService.setObjeto(lerProduto());
			produtoService.processar(Menu.CADASTRAR);
			continuar();
		}
		
	}

	/**
	 * @brief Método que pergunta ao usuário se deseja continuar cadastrando.
	 */
	public void continuar() {
		System.out.println("\n[Digite " + Menu.SAIR + " para sair ou qualquer numero para continuar cadastrando.]");
		this.opcao = in.nextInt();
		if (opcao == Menu.SAIR)
			this.saiu = true;
	}

	
	

}
