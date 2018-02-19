package Seguro;

/**
 * Classe concretizada herdada de {@link Seguro} com toString modificado.
 * 
 * @author CaioTorrez
 *
 */
public class SeguroValor extends Seguro {

	/**
	 * Cadastra um seguro a partir de um valor atribuido.
	 * 
	 * @param valor um inteiro que representa o valor a ser assegurado.
	 */
	public SeguroValor(int valor) {
		super(valor);
	}
	
	/**
	 * Retorna uma String com o tipo de seguro.
	 */
	public String toString() {
		return "valor";
	}
}
