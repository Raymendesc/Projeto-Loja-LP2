package br.ufrn.loja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
	private int id;
	private LocalDate data;
	private double total;
	private StatusVenda status;
	private List<ItemVenda> itens;

	public Venda() {
		this.itens = new ArrayList<>();
		this.status = StatusVenda.CONCLUIDA;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public double calcular_total() {
		for (ItemVenda item : itens) {
			total += item.getSubtotal();
		}
		return total;
	}

	public double getTotal() {
		return total;
	}

	public StatusVenda getStatus() {
		return status;
	}

	public void setStatus(StatusVenda status) {
		this.status = status;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void addItem(ItemVenda item) {
		itens.add(item);
	}

}