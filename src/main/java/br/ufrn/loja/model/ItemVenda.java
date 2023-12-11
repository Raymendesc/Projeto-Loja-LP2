/**
 * @file ItemVenda.java
 * @brief Definição da classe ItemVenda que representa um item de venda.
 */
package br.ufrn.loja.model;
/**
 * @class ItemVenda
 * @brief Representa um item de venda associado a um produto e uma quantidade.
 */
public class ItemVenda {
    private int id;
    private Produto produto;
    private int quantidade;

    private Venda vendaId;
    /**
     * @brief Obtém o identificador único do item de venda.
     * @return Identificador único do item de venda.
     */
    public int getId() {
        return id;
    }
    /**
     * @brief Define o identificador único do item de venda.
     * @param id Identificador único do item de venda.
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @brief Obtém o produto associado ao item de venda.
     * @return Produto associado ao item de venda.
     */
    public Produto getProduto() {
        if (produto != null) {
            return produto;
        }
        return new Produto();
    }
    /**
     * @brief Define o produto associado ao item de venda.
     * @param produto Produto associado ao item de venda.
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }
    /**
     * @brief Obtém a quantidade do produto no item de venda.
     * @return Quantidade do produto no item de venda.
     */
    public int getQuantidade() {
        return quantidade;
    }
    /**
     * @brief Define a quantidade do produto no item de venda.
     * @param quantidade Quantidade do produto no item de venda.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    /**
     * @brief Obtém a referência à venda à qual o item pertence.
     * @return Referência à venda à qual o item pertence.
     */
    public Venda getVendaId() {
        return vendaId;
    }
    /**
     * @brief Define a referência à venda à qual o item pertence.
     * @param vendaId Referência à venda à qual o item pertence.
     */
    public void setVendaId(Venda vendaId) {
        this.vendaId = vendaId;
    }
    /**
     * @brief Calcula e obtém o subtotal do item de venda.
     * @return Subtotal do item de venda.
     */
    public double getSubtotal() {
        if (produto != null) {
            return quantidade * produto.getPreco_venda();
        }
        return 0;
    }
}

