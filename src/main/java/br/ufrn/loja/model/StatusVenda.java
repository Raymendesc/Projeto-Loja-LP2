package br.ufrn.loja.model;

public enum StatusVenda {
	CONCLUIDA("Concluida"), CANCELADA("Cancelada");

	private final String descricao;

	StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public static StatusVenda fromDescricao(String descricao) {
		for (StatusVenda status : StatusVenda.values()) {
			if (status.descricao.equalsIgnoreCase(descricao)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Nenhum status de venda encontrado com a descrição: " + descricao);
	}
}
