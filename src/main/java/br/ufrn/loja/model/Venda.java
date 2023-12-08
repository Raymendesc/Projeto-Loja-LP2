package br.ufrn.loja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private int id;
    private LocalDate data;
    private double total_venda;
    private List<ItemVenda> itens;

    public Venda() {
        this.itens = new ArrayList<>();
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

    public double getTotal() {
        double total = 0;
        for (ItemVenda item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void setTotal_venda(double total_venda) {
        this.total_venda = total_venda;
    }

    public void addItem(ItemVenda item) {
        itens.add(item);
    }

}