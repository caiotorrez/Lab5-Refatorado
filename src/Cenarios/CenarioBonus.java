package Cenarios;

import java.text.DecimalFormat;

import Exceptions.ValorInvalidoException;

public class CenarioBonus extends Cenario {
	
	private int bonus;

	/**
	 * Construtor do cenário onde o parâmentro a ser passado representa a descriação do cenário a ser previsto.
	 * @param descricao Inserir descrição do cenário à construir.
	 * @param bonus é o valor extra a ser adcionado no rateio dos apostadores vencedores.
	 */
	public CenarioBonus(String descricao, int bonus) {
		super(descricao);
		if (bonus <= 0) {
			throw new ValorInvalidoException("de cenario:", "Bonus invalido");
		}
		this.bonus = bonus;
	}
	
	/**
	 * Método que retorna um int com a quantidade de dinheiro a ser distribuida pelos apostadores vencedores do cenário atual 
	 * adcionando um valor de bonificação cadastrado. 
	 * @param taxa Taxa é o parâmetro a ser mutiplicado para a separação dos bens a serem distribuidos.
	 * @return Retorna um <code>int</code> com o valor a ser rateiado entre os vencedores do cenário.
	 */
	public int getTotalRateioCenario(double taxa) {
		return super.getValorPremiado() - (int) (super.getValorPremiado() * taxa) + this.bonus;
	}
	
	public String toString() {
		String saldo = new DecimalFormat("0.00").format(this.bonus / 100.0);
		return super.getDescricao() + " - " + super.getEstado() + " - R$ " + saldo;
	}
}
