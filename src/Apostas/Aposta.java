package Apostas;

/**
 * Classe que representa apostas a serem feitas em um cen�rio.
 * @author CaioTorrez
 *
 */
public abstract class Aposta {
	
	private String nomeDoApostador;
	protected int valor;
	private String previsao;

	/**
	 * Cadastra uma aposta a partir de um nome do apostador, um valor e uma previs�o.
	 * @param apostador Apostador � o nome do representante da aposta.
	 * @param valor Valor � um inteiro representando o total a ser apostado.
	 * @param previsao Previs�o s� s�o possiveis duas, ou "VAI ACONTECER" ou "N�O VAI ACONTECER".
	 */
	public Aposta(String apostador, int valor, String previsao) {
		this.nomeDoApostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}
	
	/**
	 * Mostra o estado de previs�o apostado.
	 * @return Retorna uma String representando a previs�o feita nessa aposta.
	 */
	public String getPrevisao() {
		return this.previsao;
	}

	/**
	 * Mostra o valor que foi apostado.
	 * @return retorna um inteiro mostrando o valor apostado.
	 */
	public int getValor() {
		return this.valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomeDoApostador == null) ? 0 : nomeDoApostador.hashCode());
		result = prime * result + ((previsao == null) ? 0 : previsao.hashCode());
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aposta other = (Aposta) obj;
		if (nomeDoApostador == null) {
			if (other.nomeDoApostador != null)
				return false;
		} else if (!nomeDoApostador.equals(other.nomeDoApostador))
			return false;
		if (previsao == null) {
			if (other.previsao != null)
				return false;
		} else if (!previsao.equals(other.previsao))
			return false;
		if (valor != other.valor)
			return false;
		return true;
	}
	
	/**
	 * Retorna uma String com nome do apostador, o valor apostado e a previs�o apostada.
	 */
	public String toString() {
		return this.nomeDoApostador + " - R$" + this.valor + " - " + this.previsao;
	}
}
