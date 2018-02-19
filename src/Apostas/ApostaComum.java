package Apostas;

import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;

/**
 * Classe herdada de {@link Aposta}, com mesma funcao sem modificacoes.
 * 
 * @author CaioTorrez
 *
 */
public class ApostaComum extends Aposta {
	
	private void checkCadastro(String nome, int valor, String previsao) {
		if (valor < 1) {
			throw new ValorInvalidoException("de aposta:");
		}
		else if (nome == null || nome.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta:");
		}
		else if (previsao == null || previsao.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta:", "Previsao nao pode ser vazia ou nula");
			
		} else if (!previsao.equals("N VAI ACONTECER") && !previsao.equals("VAI ACONTECER")) {
			throw new CaractersInvalidoException("de aposta:", "Previsao invalida");
		}
	}
	
	
	/**
	 * Construtor herdado da classe {@linkAposta}, contendo os parametros apostador, valor e previsao.
	 * 
	 * @param apostador String que representarar o nome do apostador a ser cadastrado.
	 * @param valor um inteiro com o valor a ser apostado.
	 * @param previsao uma String que cadastra a previsao da conclusao do cenario.
	 */
	public ApostaComum(String nome, int valor, String previsao) {
		super(nome, valor, previsao);
		this.checkCadastro(nome, valor, previsao);
	}
}
