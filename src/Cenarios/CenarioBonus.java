package Cenarios;

import java.text.DecimalFormat;

import Exceptions.ValorInvalidoException;


/**
 * Classe herdeira de {@link Cenario} onde desempenha funcao concreta com uma opcao a mais que e o bonus.
 * 
 * @author CaioTorrez
 *
 */
public class CenarioBonus extends Cenario {
	
	private int bonus;
	
	
	/**
	 * Construtor do cenario onde se faz nescessaria uma descricao para os apostadores escolherem onde desejam apostar.
	 * 
	 * @param descricao representa a descricao a ser atribuida ao cenario a ser construido.
	 * @param bonus Valor a ser somado ao rateio e bonificado aos ganhadores do cenario.
	 * @param id representa o valor de identificacao do cenario a ser construido.
	 */
	public CenarioBonus(String descricao, int bonus, int id) {
		super(descricao, id);
		if (bonus <= 0) {
			throw new ValorInvalidoException("de cenario:", "Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	/**
	 * Calcula o valor a ser distribuido pelos ganhadores de acordo com uma taxa passada somado a um bonus cadastrado.
	 * 
	 * @param taxa Taxa é o valor a ser taxado pelo total arrecadado.
	 * @return Retorna um inteiro com o valor de rateio.
	 */
	@Override
	public int getTotalRateioCenario(double taxa) {
		return super.getValorPremiado() - (int) (super.getValorPremiado() * taxa) + this.bonus;
	}
	
	
	/**
	 * Retorna uma String no modelo "descricao - estado - bonus".
	 */
	@Override
	public String toString() {
		String bonus = new DecimalFormat("0.00").format(this.bonus / 100.0);
		return super.getDescricao() + " - " + super.getEstado() + " - R$ " + bonus;
	}
}
