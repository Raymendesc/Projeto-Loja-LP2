package br.ufrn.loja.model;

public class ProdutoBuilder {

	private Produto produto = new Produto();

	public ProdutoBuilder comId(int valor) {
		produto.setId(valor);
		return this;
	}

	public ProdutoBuilder comNome(String valor) {
		produto.setNome(valor);
		return this;
	}

	public ProdutoBuilder comPrecoCusto(double valor) {
		produto.setPreco_custo(valor);
		return this;
	}

	public ProdutoBuilder comPrecoVenda(double valor) {
		produto.setPreco_venda(valor);
		return this;
	}

	public ProdutoBuilder comEstoque(int valor) {
		produto.setEstoque(valor);
		return this;
	}

	public ProdutoBuilder comFabricante(String valor) {
		produto.setFabricante(valor);
		return this;
	}

	public Produto build() {
		return this.produto;
	}
}
