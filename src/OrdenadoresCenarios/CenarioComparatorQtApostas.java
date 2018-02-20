package OrdenadoresCenarios;

import java.util.Comparator;

import Cenarios.Cenario;

/**
 * Classe que auxilia a ordenação da Classe pai {@link Cenario} e comparar dois cenarios pela quantidade de apostas cadastradas.
 * 
 * @author CaioTorrez
 *
 */
public class CenarioComparatorQtApostas implements Comparator<Cenario> {

	/**
	 * Implementação do metodo {@link Comparator} para ordenacao dos cenarios por apostas cadastradas, caso tenham a mesma
	 * quantidade de apostas a identificacao sera comparada.
	 */
	@Override
	public int compare(Cenario cenario, Cenario outroCenario) {
		if (cenario.getTotalDeApostadores() > outroCenario.getTotalDeApostadores()) {
			return -1;
		} else if (cenario.getTotalDeApostadores() < outroCenario.getTotalDeApostadores()) {
			return 1;
		} else {
			if (cenario.getID() < outroCenario.getID()) {
				return -1;
			} else {
				return 1;
			}
		}
	}
}
