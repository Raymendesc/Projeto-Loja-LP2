package br.ufrn.loja.services;


import br.ufrn.loja.dao.ProdutoDao;
import br.ufrn.loja.model.Produto;
import br.ufrn.loja.utils.CorUtils;

public class ProdutoService extends AbstractService<Produto> {

	public ProdutoService() {
		dao = new ProdutoDao();
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
		System.out.println("\n------ "+CorUtils.laranja("Lista de Produtos")+" ----------------------");
        System.out.printf("%-5s%-25s%-15s%-15s%-10s%s\n", "ID", "Nome", "Preço Custo", "Preço Venda", "Estoque", "Fabricante");
        dao.buscarTodos().forEach(System.out::print);
        System.out.println("------------------------------------------------");
		
	}

	@Override
	protected void remover() {
		if(dao.existePorId(objeto.getId())) {
			dao.delete(objeto.getId());
			System.out.println(CorUtils.azul("Produto removido com sucesso!!"));
		} 
	}
	@Override
	public boolean buscar() {
		if(dao.existePorId(objeto.getId())) {
			System.out.printf("\n%-5s%-20s%-15s%-15s%-10s%s\n", "ID", "Nome", "Preço Custo", "Preço Venda", "Estoque", "Fabricante");
			System.out.println(dao.buscarPorId(objeto.getId()));
			return true;
		}else 
			System.out.println(CorUtils.vermelho("Esse produto não existe!"));
		return false;
	}
	
	
	
}
