package Apostas;

import java.util.Random;

import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;
import Seguro.Seguro;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;

public class ApostaSegura extends Aposta {
	
	private Seguro seguro;
	private int id;

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
		this.id = new Random().nextInt();
		checkCadastro(apostador, valor, previsao, valorAssegurado);
	}
	
	public ApostaSegura(String apostador, int valor, String previsao, double taxa) {
		super(apostador, valor, previsao);
		this.seguro = new SeguroTaxa(valor, taxa);
		this.id = new Random().nextInt();
		checkCadastro(apostador, valor, previsao, taxa);
	}
	
	public int alteraSeguro(Seguro seguro) {
		this.seguro = seguro;
		return this.id;
	}

	public int getValorAssegurado() {
		return this.seguro.getValorAssegurado();
	}

	public int getId() {
		return id;
	}
	
}