package Cenarios;

import java.text.DecimalFormat;

import Exceptions.ValorInvalidoException;

public class CenarioBonus extends Cenario {
	
	private int bonus;

	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		if (bonus <= 0) {
			throw new ValorInvalidoException("de cenario:", "Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	public int getTotalRateioCenario(double taxa) {
		return super.getValorPremiado() - (int) (super.getValorPremiado() * taxa) + this.bonus;
	}
	
	public String toString() {
		String saldo = new DecimalFormat("0.00").format(this.bonus / 100.0);
		return super.getDescricao() + " - " + super.getEstado() + " - R$ " + saldo;
	}
}
