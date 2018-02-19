package Cenarios;

/**
 * Classe herdeira de {@link Cenario} onde desempenha funcao concreta com toString modificado.
 * 
 * @author CaioTorrez
 *
 */
public class CenarioComum extends Cenario {

	/**
	 * Construtor do cenario onde se faz nescessaria uma descricao para os apostadores escolherem onde desejam apostar.
	 * 
	 * @param descricao representa a descricao a ser atribuida ao cenario a ser construido.	
	 * @param id representa o valor de identificacao do cenario a ser construido.
	 */
	public CenarioComum(String descricao, int id) {
		super(descricao, id);
	}
	
	
	/**
	 * Retorna uma String no modelo "descricao - estado".
	 */
	@Override
	public String toString() {
		return super.getDescricao() + " - " + super.getEstado();
	}
}
