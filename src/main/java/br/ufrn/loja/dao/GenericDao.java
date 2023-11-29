package br.ufrn.loja.dao;

import java.util.List;

public interface GenericDao<E> {
	public void salvar(E obj);
	public List<E> verTodos();
}
