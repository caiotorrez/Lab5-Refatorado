package Apostas;

import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;
import Seguro.Seguro;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;

public class ApostaSegura extends Aposta {
	
	private Seguro seguro;

	private void checkCadastro(String apostador, int valor, String previsao, double valorAssegurado) {
		if (apostador == null || apostador.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":");
		}
		else if (valor < 1) {
			throw new ValorInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":");
		}
		else if (previsao == null || previsao.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":", "Previsao nao pode ser vazia ou nula");
		}
		else if (!previsao.equals("N VAI ACONTECER") && !previsao.equals("VAI ACONTECER")) {
			throw new CaractersInvalidoException("de aposta assegurada por " + this.seguro.toString() + ":", "Previsao invalida");
		}
	}

	public ApostaSegura(String apostador, int valor, String previsao, int valorAssegurado) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroValor(valorAssegurado);
		checkCadastro(apostador, valor, previsao, valorAssegurado);
			
		}
	
	public ApostaSegura(String apostador, int valor, String previsao, double taxa) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroTaxa(valor, taxa);
		checkCadastro(apostador, valor, previsao, taxa);
	}
	
	@Override
	public void alteraSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	@Override
	public int getValorAssegurado() {
		return this.seguro.getValorAssegurado();
	}
}