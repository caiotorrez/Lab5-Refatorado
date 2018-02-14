package Apostas;

import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;
import Seguro.Seguro;

public class ApostaComum extends Aposta {
	
	private void checkCadastro(String nome, int valor, String previsao) {
		if (valor < 1) {
			throw new ValorInvalidoException("de aposta:");
		}
		else if (nome == null || nome.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta:");
		}
		else if (previsao == null || previsao.trim().equals("")) {
			throw new CaractersInvalidoException("de aposta:", "Previsao nao pode ser vazia ou nula");
			
		} else if (!previsao.equals("N VAI ACONTECER") && !previsao.equals("VAI ACONTECER")) {
			throw new CaractersInvalidoException("de aposta:", "Previsao invalida");
		}
	}
	
	public ApostaComum(String nome, int valor, String previsao) {
		super(nome, valor, previsao);
		this.checkCadastro(nome, valor, previsao);
	}

	@Override
	public void alteraSeguro(Seguro seguro) {
	}
	
	@Override
	public int getValorAssegurado() {
		return 0;
	}


}
