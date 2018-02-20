package Validador;

import Exceptions.CadastroInvalidoException;
import Exceptions.ConsultaException;
import Exceptions.ExibicaoException;
import Exceptions.InicializacaoException;
import Exceptions.OrdemInvalidaException;
import Exceptions.PosicaoInvalidaException;

public class ValidadorDoController {
	
	public ValidadorDoController() { 
		
	}
	
	
	public void validaCaixa(int caixa, double taxa) {
		if (caixa < 0) {
			throw new InicializacaoException("Caixa");
		}
		if (taxa < 0) {
			throw new InicializacaoException("Taxa");
		}
	}
	
	public void validaExibicaoCenario(int cenario, int size) {
		if (cenario <= 0) {
			throw new PosicaoInvalidaException("Cenario invalido");
		
		} else if (cenario > size) {
			throw new PosicaoInvalidaException("Cenario nao cadastrado");
		}
	}
	
	public void checkCadastroAposta(int cenario, String causa, int size) {
		if (cenario < 1) {
			throw new CadastroInvalidoException("de aposta" + causa + ": Cenario invalido");

		} else if (cenario > size) {
			throw new CadastroInvalidoException("de aposta" + causa + ": Cenario nao cadastrado");
		}
	}


	public void checkValorDeApostas(int cenario, int size) {
		if (cenario < 1) {
			throw new PosicaoInvalidaException("do valor total de apostas", "Cenario invalido");
			
		} else if (cenario > size) {
			throw new PosicaoInvalidaException("do valor total de apostas", "Cenario nao cadastrado");
		}
		
	}


	public void checkTotalApostas(int cenario, int size) {
		if (cenario < 1) {
			throw new PosicaoInvalidaException("do total de apostas", "Cenario invalido");
			
		} else if (cenario > size) {
			throw new PosicaoInvalidaException("do total de apostas", "Cenario nao cadastrado");
		}
	}


	public void checkExibeApostas(int cenario, int size) {
		if (cenario < 1) {
			throw new ExibicaoException("Cenario invalido");
			
		} else if (cenario > size) {
			throw new ExibicaoException("Cenario nao cadastrado");
		}	
	}


	public void checkGetCaixaCenario(int cenario, int size) {
		if (cenario < 1) {
			throw new ConsultaException("do caixa do cenario", "Cenario invalido");
			
		} else if (cenario > size) {
			throw new ConsultaException("do caixa do cenario", "Cenario nao cadastrado");
		}
	}
	
	public void checkGetCaixaEstado(String estado) {
		if (estado.equals("Nao finalizado")) {
		throw new ConsultaException("do caixa do cenario", "Cenario ainda esta aberto");
		}
	}


	public void checkFecharAposta(int cenario, int size) {
		if (cenario < 1) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario invalido");
			
		} else if (cenario > size) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario nao cadastrado");
		}
	}
	
	public void checkFecharApostaEstado(String estado) {
		if (estado.contains("Finalizado")) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario ja esta fechado");
		}
	}


	public void checkTotalRateioCenario(int cenario, int size) {
		if (cenario < 1) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario invalido");

		} else if (cenario > size) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario nao cadastrado");
			
		}
	}
	
	public void checkTotalRateioEstado(String estado) {
		if (estado.equals("Nao finalizado")) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario ainda esta aberto");
		}
		
	}


	public void checkExibirCenarioOrdenado(int cenario, int size) {
		if (cenario < 1) {
			throw new ConsultaException("de cenario ordenado", "Cenario invalido");
			
		} else if (cenario > size) {
			throw new ConsultaException("de cenario ordenado", "Cenario nao cadastrado");
		}
	}

	public void checkAlteraOrdem(String ordem) {
		if (ordem == null || ordem.trim().equals("")) {
			throw new OrdemInvalidaException("Ordem nao pode ser vazia ou nula");
		}
		else if (!ordem.equalsIgnoreCase("cadastro") && !ordem.equalsIgnoreCase("nome") && !ordem.equalsIgnoreCase("apostas")) {
			throw new OrdemInvalidaException("Ordem invalida");
		}
		
	}
}
