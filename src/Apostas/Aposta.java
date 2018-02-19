package Apostas;

/**
 * Classe abstrata que representa uma aposta feita em cenarios de apostas, contendo um apostador, um valor e uma previsao.
 * 
 * @author CaioTorrez
 *
 */
public abstract class Aposta {
	
	private String nomeDoApostador;
	protected int valor;
	private String previsao;

	
	/**
	 * Construtor da classe abstrata Aposta contendo os parametros apostador, valor e previsao.
	 * 
	 * @param apostador String que representarar o nome do apostador a ser cadastrado.
	 * @param valor um inteiro com o valor a ser apostado.
	 * @param previsao uma String que cadastra a previsao da conclusao do cenario.
	 */
	public Aposta(String apostador, int valor, String previsao) {
		this.nomeDoApostador = apostador;
		this.valor = valor;
		this.previsao = previsao;
	}
	
	
	/**
	 * Exibe a previsao que foi apostada.
	 * 
	 * @return retorna uma String com a previsao.
	 */
	public String getPrevisao() {
		return this.previsao;
	}

	
	/**
	 * Exibe a quantia apostada.
	 * 
	 * @return Retorna um inteiro com o valor apostado.
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
	 * Retorna uma String com a representacao textual do Apostador, no formado "apostador - valor - previsao".
	 */
	public String toString() {
		return this.nomeDoApostador + " - R$" + this.valor + " - " + this.previsao;
	}
}
