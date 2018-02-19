package Apostas;

import java.util.Random;

import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;
import Seguro.Seguro;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;

public class ApostaSegura extends Aposta {
	
	private Seguro seguro;
	private int id;

	private void checkCadastro(String apostador, int valor, String previsao, double valorAssegurado) {
		if (apostador == null || apostador.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":");
		}
		else if (valor < 1) {
			throw new ValorInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":");
		}
		else if (previsao == null || previsao.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":", "Previsao nao pode ser vazia ou nula");
		}
		else if (!previsao.equals("N VAI ACONTECER") && !previsao.equals("VAI ACONTECER")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":", "Previsao invalida");
		}
	}

	/**
	 * Construtor da Classe {@link ApostaSegura}, herdando os paramentos da Classe {@link Aposta} e contendo o
	 * parametro @param valorAssegurado que representa o valor a ser assegurado em caso de perder a aposta.
	 * 
	 * @param apostador String que representarar o nome do apostador a ser cadastrado.
	 * @param valor um inteiro com o valor a ser apostado.
	 * @param previsao uma String que cadastra a previsao da conclusao do cenario.
	 * @param valorAssegurado um inteiro que representa o valor a ser assegurado.
	 */
	public ApostaSegura(String apostador, int valor, String previsao, int valorAssegurado) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroValor(valorAssegurado);
		this.id = new Random().nextInt();
		checkCadastro(apostador, valor, previsao, valorAssegurado);
	}
	
	
	/**
	 * Construtor da Classe {@link ApostaSegura}, herdando os paramentos da Classe {@link Aposta} e contendo o
	 * parametro @param taxa que representa a taxa a ser calculada com o valor total da aposta e ser o valor
	 * assegurado em caso de perder a aposta.
	 * 
	 * @param apostador String que representarar o nome do apostador a ser cadastrado.
	 * @param valor um inteiro com o valor a ser apostado.
	 * @param previsao uma String que cadastra a previsao da conclusao do cenario.
	 * @param valorAssegurado um inteiro que representa o valor a ser assegurado.
	 */
	public ApostaSegura(String apostador, int valor, String previsao, double taxa) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroTaxa(valor, taxa);
		this.id = new Random().nextInt();
		checkCadastro(apostador, valor, previsao, taxa);
	}
	
	
	/**
	 * Altera o seguro recebendo {@link Seguro} como parametro.
	 * 
	 * @param seguro um Seguro que contem um valor a ser assegurado.
	 * @return retorna a identificacao(ID) dessa aposta segura.
	 */
	public int alteraSeguro(Seguro seguro) {
		this.seguro = seguro;
		return this.id;
	}

	
	/**
	 * Exibe o valor assegurado cadastrado no seguro desta aposta.
	 * 
	 * @return Retorna um inteiro contendo o valor assegurado.
	 */
	public int getValorAssegurado() {
		return this.seguro.getValorAssegurado();
	}

	/**
	 * Exibe a identificacao da aposta segura.
	 * 
	 * @return Retorna um inteiro com o ID.
	 */
	public int getId() {
		return id;
	}
	
}