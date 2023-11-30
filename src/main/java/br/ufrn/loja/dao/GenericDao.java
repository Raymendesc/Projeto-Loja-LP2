package br.ufrn.loja.dao;

import java.util.List;

/**
 * Interface genérica para operações básicas de persistência.
 *
 * @param <E> Tipo da entidade manipulada pela interface.
 */
public interface GenericDao<E> {

	/**
	 * Salva a entidade no banco de dados.
	 *
	 * @param obj Entidade a ser salva.
	 */
	public void salvar(E obj);

	/**
	 * Retorna uma lista contendo todas as entidades do tipo E no Banco de dados.
	 *
	 * @return Lista de entidades.
	 */
	public List<E> buscarTodos();

	/**
	 * Busca uma entidade por seu ID.
	 *
	 * @param id ID da entidade a ser buscada.
	 * @return Entidade encontrada ou null se não encontrada.
	 */
	public E buscarPorId(int id);

	/**
	 * Exclui uma entidade do banco de dados com base no ID.
	 *
	 * @param id ID da entidade a ser excluída.
	 */
	public void delete(int id);

	/**
	 * Verifica se uma entidade com o ID especificado existe no banco de dados.
	 *
	 * @param id ID da entidade a ser verificada.
	 * @return true se a entidade existir, false caso contrário.
	 */
	public boolean existePorId(int id);

	/**
	 * Altera os dados de uma entidade no banco de dados.
	 *
	 * @param obj Entidade com os dados atualizados.
	 */
	public void alterar(E obj);
}
