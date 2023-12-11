/**
 * @file TelaVendas.java
 * @brief Definição da classe TelaVendas que representa a tela de vendas e suas funcionalidades.
 */
package br.ufrn.loja.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.ufrn.loja.dao.ProdutoDao;
import br.ufrn.loja.model.ItemVenda;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.model.Venda;
import br.ufrn.loja.services.VendaService;
import br.ufrn.loja.utils.CorUtils;

/**
 * @brief Classe que representa a tela de vendas e suas funcionalidades.
 */
public class TelaVendas extends MenuAbstract {
	/**
	 * @brief Construtor da classe TelaVendas.
	 * @param in Scanner para entrada de dados.
	 */
	public TelaVendas(Scanner in) {
		this.in = in;
	}

	/**
	 * @brief Exibe o menu de vendas e processa as opções escolhidas pelo usuário.
	 */
	public void exibirMenuVendas() {
		while (!saiu) {
			exibirOpcoesVendas();
			processarOpcaoVendas();
		}
	}

	/**
	 * @brief Exibe as opções do menu de vendas.
	 */
	public void exibirOpcoesVendas() {

		System.out.println("╔══════════════════════════════════════╗");
		System.out.println("║           " + CorUtils.laranja("MENU DE VENDAS") + "             ║");
		System.out.println("╠══════════════════════════════════════╣");
		System.out.println("║  " + CADASTRAR + ". Iniciar Nova Venda               ║");
		System.out.println("║  " + BUSCAR + ". Buscar uma venda                 ║");
		System.out.println("║  " + VER_TODOS + ". Exibir Vendas Realizadas         ║");
		System.out.println("║  " + SAIR + ". Sair                             ║");
		System.out.println("╚══════════════════════════════════════╝");
		System.out.print("Escolha uma opção: ");

		opcao = in.nextInt();
	}

	/**
	 * @brief Processa a opção escolhida pelo usuário no menu de vendas.
	 */
	private void processarOpcaoVendas() {
		System.out.println("Opção escolhida: " + opcao);

		switch (opcao) {
			case SAIR:
				saiu = true;
				break;
			case CADASTRAR:
				iniciarNovaVenda();
				break;
			case BUSCAR:
				buscarVendaPorId();
				break;
			case VER_TODOS:
				exibirVendasRealizadas();
				break;
			default:
				System.out.println("Opção inválida. Tente novamente.");
		}
	}

	/**
	 * @brief Exibe as vendas realizadas em um determinado período.
	 */
	private void exibirVendasRealizadas() {
		System.out.println("Digite o periodo no formato (yyyy-mm-dd)");
		System.out.print("Inicio: ");
		in.nextLine();
		String inicio = in.nextLine();

		System.out.print("Termino: ");
		String termino = in.nextLine();
		System.out.println(CorUtils.laranja("\n-------Exibindo as vendas realizadas:-------\n"));

		for (Venda venda : vendaService.obterTodasAsVendas(inicio, termino)) {
			imprimirVenda(venda);
		}
	}

	/**
	 * @brief Inicia uma nova venda, permitindo ao usuário adicionar produtos à
	 * venda.
	 */
	private void iniciarNovaVenda() {
		VendaService vendaService = new VendaService();
		ProdutoDao produtoDao = new ProdutoDao();
		Venda novaVenda = new Venda();

		System.out.println("\nAdicione produtos à venda:");
		while (true) {
			Produto produto = new Produto();
			System.out.print("\nDigite o código do produto (digite '0' para encerrar, '3' para cancelar):");

			int codigo = in.nextInt();
			if (codigo == 0) {
				break;
			} else if (codigo == 3) {
				System.out.println("Venda cancelada.");
				novaVenda.cancelarVenda();
				return;
			} else if (produtoDao.existePorId(codigo)) {
				produto = produtoDao.buscarPorId(codigo);
				ItemVenda item = new ItemVenda();
				item.setProduto(produto);

				System.out.print("Digite a quantidade: ");
				int quantidade = in.nextInt();
				if (produto.getEstoque() >= quantidade) {
					item.setQuantidade(quantidade);
					novaVenda.addItem(item);
				} else {
					System.out.println(CorUtils.vermelho("Não existe essa quantidade do produto no estoque!\nEstoque atual do produto: " + produto.getEstoque()));
				}
			} else {
				System.out.println(CorUtils.vermelho("Esse produto não existe!"));
			}
		}

		novaVenda.setData(LocalDate.now());
		novaVenda.setTotal(novaVenda.calcular_total());
		vendaService.setObjeto(novaVenda);
		imprimirVenda(novaVenda);
		vendaService.processar(Menu.CADASTRAR);

	}

	/**
	 * @param venda Venda a ser impressa.
	 * @brief Imprime os detalhes de uma venda.
	 */
	public void imprimirVenda(Venda venda) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if (venda.getId() == 0) {
			System.out.println("\n----- " + CorUtils.laranja("Resumo da Nova Venda") + " ------------------");
		} else {
			System.out.printf("ID da Venda: %d\n", venda.getId());
		}

		System.out.println(CorUtils.bold("Data: ") + venda.getData().format(formatter));
		System.out.println(CorUtils.bold("Status: " + venda.getStatus()));
		System.out.println(CorUtils.bold("Itens:"));
		for (ItemVenda item : venda.getItens()) {
			System.out.printf(CorUtils.bold("- Produto:") + " %s, Quantidade: %d, Subtotal: %.2f\n",
					item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
		}
		System.out.printf(CorUtils.verde("Total: %.2f\n"), venda.getTotal());
		System.out.println("--------------------------------------------\n");
	}
	/**
	 * @brief Busca uma venda pelo seu ID e exibe os detalhes.
	 */
	private void buscarVendaPorId() {
		System.out.println("Digite o ID da venda a ser buscada: ");
		int idVenda = in.nextInt();

		VendaService vendaService = new VendaService();
		Venda vendaEncontrada = vendaService.buscarVendaPorId(idVenda);

		if (vendaEncontrada != null) {
			exibirDetalhesVenda(vendaEncontrada);
		} else {
			System.out.println(CorUtils.vermelho("Venda não encontrada."));
		}
	}
	/**
	 * @param venda Venda a ser exibida.
	 * @brief Exibe os detalhes de uma venda.
	 */
	private void exibirDetalhesVenda(Venda venda) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.printf("ID da Venda: %d\n", venda.getId());
		System.out.println(CorUtils.bold("Data: ") + venda.getData().format(formatter));

		System.out.println("Itens:");
		for (ItemVenda item : venda.getItens()) {
			System.out.printf("- Produto: %s, Quantidade: %d, Subtotal: %.2f\n",
					item.getProduto().getNome(), item.getQuantidade(), item.getSubtotal());
		}

		System.out.printf(CorUtils.verde("Total: %.2f\n"), venda.calcular_total());
		System.out.println("--------------------------------------------\n");
	}
}
