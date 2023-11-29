package br.ufrn.loja.exception;

public class OpcaoInvalidaException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public OpcaoInvalidaException(String mensagem) {
		super(mensagem);
	}
}
