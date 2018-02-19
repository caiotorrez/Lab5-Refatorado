package Seguro;

/**
 * Classe abstrata que representa um seguro contendo um valor assegurado.
 * 
 * @author CaioTorrez
 *
 */
public abstract class Seguro {
	
	private int valorAssegurado;
	
	/**
	 * Cadastra um seguro a partir de um valor atribuido.
	 * 
	 * @param valor um inteiro que representa o valor a ser assegurado.
	 */
	public Seguro(int valor) {
		this.valorAssegurado = valor; 
	}
	
	/**
	 * Exibe o valor assegurado.
	 * 
	 * @return Retorna o valor assegurado.
	 */
	public int getValorAssegurado() {
		return this.valorAssegurado;
	}
}
