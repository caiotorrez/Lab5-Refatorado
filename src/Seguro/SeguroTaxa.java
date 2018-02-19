package Seguro;

/**
 * Classe que herda um {@link Seguro} e constroi um valor assegurado a partir de um valor e uma taxa.
 * 
 * @author CaioTorrez
 *
 */
public class SeguroTaxa extends Seguro{
	
	private double taxa;
	
	/**
	 * Construtor da classe que recebe um valor e uma taxa para o calculo de valor a ser assegurado, na forma valor * taxa.
	 * 
	 * @param valorDaAposta um inteiro que representar um valor a ser a base do calculo da taxa.
	 * @param taxa um double que representa uma porcentagem.	
	 */
	public SeguroTaxa(int valorDaAposta, double taxa) {
		super((int) (taxa * valorDaAposta));
		this.taxa = taxa;
	}
	
	
	/**
	 * Exibe a taxa cadastrada.
	 * 
	 * @return Retorna um double com a taxa.
	 */
	public double getTaxa() {
		return this.taxa;
	}
	
	/**
	 * Retorna uma String com o tipo de seguro.
	 */
	@Override
	public String toString() {
		return "taxa";
	}

}
