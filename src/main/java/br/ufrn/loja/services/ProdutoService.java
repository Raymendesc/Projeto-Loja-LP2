package br.ufrn.loja.services;


import br.ufrn.loja.dao.ProdutoDao;
import br.ufrn.loja.model.Produto;

public class ProdutoService extends AbstractService<Produto> {

	public ProdutoService() {
		dao = new ProdutoDao();
	}


	@Override
	protected void alterar() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void remover() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected boolean validar() {
		if(objeto.getNome() == null || objeto.getNome().isEmpty()) {
			System.out.println("\nVocê não digitou o nome do produto\n");
			return false;
		}
		return true;
	}


	@Override
	protected void imprimir() {
		System.out.println("\n----- Lista de Produtos ----------------------");
        System.out.printf("%-5s%-20s%-15s%-15s%-10s%s\n", "ID", "Nome", "Preço Custo", "Preço Venda", "Estoque", "Fabricante");
        dao.verTodos().forEach(System.out::print);
        System.out.println("------------------------------------------------");
		
	}
	
	
	
}
