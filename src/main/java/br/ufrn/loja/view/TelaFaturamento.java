package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.utils.CorUtils;

/**
 * Tela responsável por exibir o faturamento no período especificado.
 */
public class TelaFaturamento extends MenuAbstract {

	public TelaFaturamento(Scanner in) {
		this.in = in;
	}

	/**
	 * Executa a lógica da tela para exibir o faturamento no período especificado.
	 */
	public void run() {
		System.out.println("Digite o período no formato (yyyy-mm-dd)");
		System.out.print("Inicio: ");
		in.nextLine();
		String inicio = in.nextLine();

		System.out.print("Termino: ");
		String termino = in.nextLine();

		exibirResultadoFaturamento(inicio, termino, vendaService.calcularFaturamento(inicio, termino));
	}

	/**
	 * Exibe o resultado do faturamento de forma estilizada no console.
	 *
	 * @param inicio            Data de início do período.
	 * @param termino           Data de término do período.
	 * @param totalFaturamento  Total de faturamento no período.
	 */
	private void exibirResultadoFaturamento(String inicio, String termino, double totalFaturamento) {
	    String mensagem = CorUtils.bold("\nFaturamento entre " + inicio + " e " + termino) +
	            "\n" + CorUtils.verde("╔════════════════════════════════════╗") +
	            "\n" + CorUtils.verde("║ " + String.format("Total: R$ %.2f", totalFaturamento) + "                  ║") +
	            "\n" + CorUtils.verde("╚════════════════════════════════════╝");

	    System.out.println(mensagem);
	}

}
