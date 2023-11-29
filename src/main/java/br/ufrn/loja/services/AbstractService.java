package br.ufrn.loja.services;

import br.ufrn.loja.dao.GenericDao;

/**
 * @brief Classe abstrata que define operações básicas de movimentação (CRUD).
 *
 * @tparam E Tipo de objeto manipulado pela classe.
 * 
 */
public abstract class AbstractService<E> {
	public static final int CRIAR = 1;
	public static final int ALTERAR = 2;
	public static final int CONSULTAR = 3;
	public static final int REMOVER = 4;
	

	protected E objeto;
	protected GenericDao<E> dao;

	 /**
     * @brief Processa a operação solicitada.
     *
     * @param opcao A operação a ser realizada.
     */
	public void processar(int opcao) {
		switch (opcao) {
		case CRIAR: 
			cadastrar();
			break;
		case CONSULTAR:
			imprimir();
			break;
		case ALTERAR:
			System.out.println("Alterando");
			break;
		case REMOVER:
			System.out.println("Removendo");
			break;
		default:
			System.out.println("Nenhuma opcao");
		}

	}
	
	/**
     * @brief Método que prepara para o cadastro de um objeto.
     *
     */
	protected void cadastrar() {	
		if(validar()) {
			dao.salvar(objeto);
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
	protected abstract void alterar();
	
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
