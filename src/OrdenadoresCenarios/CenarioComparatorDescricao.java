package OrdenadoresCenarios;

import java.util.Comparator;

import Cenarios.Cenario;


/**
 * Classe que auxilia a ordenação da Classe pai {@link Cenario} e comparar dois cenarios pela descricao.
 * 
 * @author CaioTorrez
 *
 */
public class CenarioComparatorDescricao implements Comparator<Cenario> {

	/**
	 * Implementação do metodo {@link Comparator} para ordenacao dos cenarios por descricao.
	 */
	@Override
	public int compare(Cenario cenario, Cenario outroCenario) {
		return cenario.getDescricao().compareTo(outroCenario.getDescricao());
	}
	

}
