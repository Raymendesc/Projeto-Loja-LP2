package br.ufrn.loja.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @class ArquivoUtils
 * @brief Classe utilitária para operações relacionadas a arquivos.
 */
public class ArquivoUtils {

	/**
     * @brief Método para ler o conteúdo de um arquivo de texto.
     * @param nomeArquivo Nome do arquivo a ser lido.
     * @return String contendo o conteúdo do arquivo.
     */
	public static String lerTExtoArquivo(final String nomeArquivo) {
		try {
			long time = System.currentTimeMillis();
			BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo));
			StringBuilder sb = new StringBuilder();
			String linha;

			while ((linha = reader.readLine()) != null) {
				sb.append(linha).append("\n");
			}
			reader.close();
			time = System.currentTimeMillis() - time;

			return sb.toString();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
