package br.ufrn.loja.utils;

public class CorUtils {
	public static String verde(String texto) {
		return "\u001B[32m" + texto + "\u001B[0m";
	}

	public static String azul(String texto) {
		return "\u001B[34m" + texto + "\u001B[0m";
	}
	
	public static String laranja(String texto) {
		return "\u001B[38;5;208m" + texto + "\u001B[0m";
	}
}
