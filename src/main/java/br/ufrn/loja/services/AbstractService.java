package br.ufrn.loja.services;

import br.ufrn.loja.dao.GenericDao;
import br.ufrn.loja.utils.CorUtils;
import br.ufrn.loja.view.MenuAbstract;

/**
 * @brief Classe abstrata que define operações básicas de movimentação (CRUD).
 *
 * @tparam E Tipo de objeto manipulado pela classe.
 * 
 */
public abstract class AbstractService<E> {
	
	protected E objeto;
	protected GenericDao<E> dao;

	 /**
     * @brief Processa a operação solicitada.
     *
     * @param opcao A operação a ser realizada.
     */
	public void processar(int opcao) {
		switch (opcao) {
		case MenuAbstract.CADASTRAR: 
			cadastrar();
			break;
		case MenuAbstract.VER_TODOS:
			imprimir();
			break;
		case MenuAbstract.ALTERAR:
			alterar();
			break;
		case MenuAbstract.REMOVER:
			remover();
			break;
		default:
			System.out.println("Nenhuma opcao");
		}

	}
	
	public abstract boolean buscar();

	/**
     * @brief Método que prepara para o cadastro de um objeto.
     *
     */
	protected void cadastrar() {	
		if(validar()) {
			dao.salvar(objeto);
			System.out.println(CorUtils.azul("Cadastro realizado com sucesso!"));
		}
	}
	
	/**
	 * Valida o objeto
	 * @return true ou false
	 */
	protected abstract boolean validar();
	
	/**
     * @brief Método abstrato para alterar o objeto.
     */
	protected  void alterar() {
		dao.alterar(objeto);
		System.out.println(CorUtils.azul("Alteração realizada com sucesso!"));
	}
	
	/**
     * @brief Método abstrato para remover o objeto.
     */
	protected abstract void remover();
	
	/**
     * Imprime a lista de produtos de forma estilizada.
     */
	protected abstract void imprimir();
	
	public E getObjeto() {
		return objeto;
	}

	public void setObjeto(E objeto) {
		this.objeto = objeto;
	}
}
