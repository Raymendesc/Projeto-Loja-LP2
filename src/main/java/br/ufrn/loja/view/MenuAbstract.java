package br.ufrn.loja.view;

import java.util.Scanner;

import br.ufrn.loja.model.Produto;
import br.ufrn.loja.model.ProdutoBuilder;
import br.ufrn.loja.services.AbstractService;
import br.ufrn.loja.services.ProdutoService;
import br.ufrn.loja.services.VendaService;

public abstract class MenuAbstract {

	public static final int SAIR = 0;
	public static final int CADASTRAR = 1;
	public static final int BUSCAR = 2;
	public static final int VER_TODOS = 3;
	public static final int FATURAMENTO = 4;
	public static final int VENDER = 5;
	public static final int REMOVER = 6;
	public static final int ALTERAR = 7;
	public static final int CANCELAR = 8;
	
	
	protected AbstractService<Produto> produtoService = new ProdutoService();

	protected VendaService vendaService = new VendaService();
	protected int opcao;
	protected boolean saiu = false;
	protected Scanner in = new Scanner(System.in);

	/**
	 * @brief Método que lê os dados de um produto e o retorna.
	 */
	public Produto lerProduto() {
		
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
		
		return new ProdutoBuilder()
				.comNome(nome)
				.comPrecoCusto(preco_custo)
				.comPrecoVenda(preco_venda)
				.comEstoque(estoque).comFabricante(fabricante).build();
	}
}
