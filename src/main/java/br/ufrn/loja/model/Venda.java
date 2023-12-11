/**
 * @file Venda.java
 * @brief Definição da classe Venda que representa uma transação de venda.
 */

package br.ufrn.loja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 * @class Venda
 * @brief Representa uma transação de venda realizada na loja.
 */
public class Venda {
	private int id;
	private LocalDate data;
	private double total;
	private StatusVenda status;
	private List<ItemVenda> itens;
	/**
	 * @brief Construtor padrão da classe Venda.
	 */
	public Venda() {
		this.itens = new ArrayList<>();
		this.status = StatusVenda.CONCLUIDA;
	}
	/**
	 * @brief Cancela a venda, alterando seu status para CANCELADA.
	 */
	public void cancelarVenda() {
		this.status = StatusVenda.CANCELADA;
	}
	/**
	 * @brief Obtém o identificador único da venda.
	 * @return Identificador único da venda.
	 */
	public int getId() {
		return id;
	}
	/**
	 * @brief Define o identificador único da venda.
	 * @param id Identificador único da venda.
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @brief Obtém a data da realização da venda.
	 * @return Data da realização da venda.
	 */
	public LocalDate getData() {
		return data;
	}
	/**
	 * @brief Define a data da realização da venda.
	 * @param data Data da realização da venda.
	 */
	public void setData(LocalDate data) {
		this.data = data;
	}
	/**
	 * @brief Obtém a lista de itens associados à venda.
	 * @return Lista de itens associados à venda.
	 */
	public List<ItemVenda> getItens() {
		return itens;
	}
	/**
	 * @brief Define a lista de itens associados à venda.
	 * @param itens Lista de itens associados à venda.
	 */
	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}
	/**
	 * @brief Calcula o valor total da venda somando os subtotais de seus itens.
	 * @return Valor total da venda.
	 */
	public double calcular_total() {
		for (ItemVenda item : itens) {
			total += item.getSubtotal();
		}
		return total;
	}
	/**
	 * @brief Obtém o valor total da venda.
	 * @return Valor total da venda.
	 */
	public double getTotal() {
		return total;
	}
	/**
	 * @brief Obtém o status da venda.
	 * @return Status da venda (CONCLUÍDA ou CANCELADA).
	 */
	public StatusVenda getStatus() {
		return status;
	}
	/**
	 * @brief Define o status da venda.
	 * @param status Status da venda (CONCLUÍDA ou CANCELADA).
	 */
	public void setStatus(StatusVenda status) {
		this.status = status;
	}
	/**
	 * @brief Define o valor total da venda.
	 * @param total Valor total da venda.
	 */
	public void setTotal(double total) {
		this.total = total;
	}
	/**
	 * @brief Adiciona um item à lista de itens associados à venda.
	 * @param item Item de venda a ser adicionado.
	 */
	public void addItem(ItemVenda item) {
		itens.add(item);
	}
	/**
	 * @brief Busca um item de venda pelo seu identificador.
	 * @param id Identificador do item de venda.
	 * @return Item de venda encontrado ou null se não existir.
	 */
	public ItemVenda buscarItemPorId(int id) {
		for (ItemVenda item : itens) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

}