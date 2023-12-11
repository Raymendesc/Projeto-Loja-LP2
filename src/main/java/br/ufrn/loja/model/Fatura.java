package br.ufrn.loja.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fatura {

    private Long id;
    private LocalDate data;
    private String cliente; // Nome do cliente que vai sair a fatura
    private List<ItemFatura> itensFatura;
    private double total;

    public Fatura(String cliente) {
        this.cliente = cliente;
        this.itensFatura = new ArrayList<>();
        this.total = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<ItemFatura> getItensFatura() {
        return itensFatura;
    }

    public void setItensFatura(List<ItemFatura> itensFatura) {
        this.itensFatura = itensFatura;
    }

    public double getTotal() {
        return total;
    }

    
    public double calcularTotal() {
        total = itensFatura.stream().mapToDouble(ItemFatura::getSubTotal).sum();
        return total; 
    }

    
    public void adicionarItem(ItemFatura item) {
        this.itensFatura.add(item);
        this.calcularTotal(); // Atualiza o total sempre que um item é adicionado
    }
}
