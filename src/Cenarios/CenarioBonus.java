package Cenarios;

import java.text.DecimalFormat;

import Exceptions.ValorInvalidoException;

public class CenarioBonus extends Cenario {
	
	private int bonus;

	/**
	 * Construtor do cen�rio onde o par�mentro a ser passado representa a descria��o do cen�rio a ser previsto.
	 * @param descricao Inserir descri��o do cen�rio � construir.
	 * @param bonus � o valor extra a ser adcionado no rateio dos apostadores vencedores.
	 */
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		if (bonus <= 0) {
			throw new ValorInvalidoException("de cenario:", "Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	/**
	 * M�todo que retorna um int com a quantidade de dinheiro a ser distribuida pelos apostadores vencedores do cen�rio atual 
	 * adcionando um valor de bonifica��o cadastrado. 
	 * @param taxa Taxa � o par�metro a ser mutiplicado para a separa��o dos bens a serem distribuidos.
	 * @return Retorna um <code>int</code> com o valor a ser rateiado entre os vencedores do cen�rio.
	 */
	public int getTotalRateioCenario(double taxa) {
		return super.getValorPremiado() - (int) (super.getValorPremiado() * taxa) + this.bonus;
	}
	
	public String toString() {
		String saldo = new DecimalFormat("0.00").format(this.bonus / 100.0);
		return super.getDescricao() + " - " + super.getEstado() + " - R$ " + saldo;
	}
}
