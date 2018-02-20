package OrdenadoresCenarios;

import java.util.Comparator;

import Cenarios.Cenario;

public class CenarioComparatorQtApostas implements Comparator<Cenario> {

	@Override
	public int compare(Cenario cenario, Cenario outroCenario) {
		if (cenario.getTotalDeApostadores() > outroCenario.getTotalDeApostadores()) {
			return -1;
		} else if (cenario.getTotalDeApostadores() < outroCenario.getTotalDeApostadores()) {
			return 1;
		} else {
			if (cenario.getID() < outroCenario.getID()) {
				return 1;
			} else {
				return -1;
			}
		}
	}
}
