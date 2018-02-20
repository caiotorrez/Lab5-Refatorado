package OrdenadoresCenarios;

import java.util.Comparator;

import Cenarios.Cenario;

public class CenarioComparatorDescricao implements Comparator<Cenario> {

	@Override
	public int compare(Cenario cenario, Cenario outroCenario) {
		return cenario.getDescricao().compareTo(outroCenario.getDescricao());
	}
	

}
