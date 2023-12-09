package br.ufrn.loja.model;

public class ItemVenda {
    private int id;
    private Produto produto;
    private int quantidade;

    private Venda vendaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Produto getProduto() {
        if (produto != null) {
            return produto;
        }
        return new Produto();
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Venda getVendaId() {
        return vendaId;
    }

    public void setVendaId(Venda vendaId) {
        this.vendaId = vendaId;
    }

    public double getSubtotal() {
        if (produto != null) {
            return quantidade * produto.getPreco_venda();
        }
        return 0;
    }
}

