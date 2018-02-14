package ClassePrincipal;

import java.util.ArrayList;

import Cenarios.Cenario;
import Cenarios.CenarioBonus;
import Cenarios.CenarioComum;
import Exceptions.CadastroInvalidoException;
import Exceptions.ConsultaException;
import Exceptions.ExibicaoException;
import Exceptions.InicializacaoException;
import Exceptions.PosicaoInvalidaException;
import easyaccept.EasyAccept;

public class Controller {
	
	private ArrayList<Cenario> cenarios = new ArrayList<>();
	private int caixa;
	private double taxa;
	
	public void inicializa(int caixa, double taxa) {
		if (caixa < 0) {
			throw new InicializacaoException("Caixa");
		}
		if (taxa < 0) {
			throw new InicializacaoException("Taxa");
		}
		this.caixa = caixa;
		this.taxa = taxa;
	}
	
	public int getCaixa() {
		return this.caixa;
	}
	
	public int cadastrarCenario(String descricao) {
		this.cenarios.add(new CenarioComum(descricao));
		return this.cenarios.size();
	}
	
	public int cadastrarCenario(String descricao, int bonus) {
		this.caixa -= bonus;
		this.cenarios.add(new CenarioBonus(descricao, bonus));
		return this.cenarios.size();
	}
	
	public String exibirCenario(int cenario) {
		if (cenario <= 0) {
			throw new PosicaoInvalidaException("Cenario invalido");
		
		} else if (cenario > this.cenarios.size()) {
			throw new PosicaoInvalidaException("Cenario nao cadastrado");
		}
		return cenario + " - " + this.cenarios.get(cenario - 1);
	}
	
	public String exibirCenarios() {
		String output = "";
		for (int i = 1; i <= cenarios.size(); i++) {
			output+= this.exibirCenario(i) + "\n";
		} return output == "" ? "Não há cenários cadastrados." : output;
	}
	
	private void checkCadastroAposta(int cenario, String causa) {
		if (cenario < 1) {
			throw new CadastroInvalidoException("de aposta" + causa + ": Cenario invalido");

		} else if (cenario > this.cenarios.size()) {
			throw new CadastroInvalidoException("de aposta" + causa + ": Cenario nao cadastrado");
		}
	}
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.checkCadastroAposta(cenario, "");
		this.cenarios.get(cenario - 1).addApostadorComum(apostador, valor, previsao);
	}
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		this.checkCadastroAposta(cenario, " assegurada por valor");
		this.caixa += custo;
		return this.cenarios.get(cenario - 1).addApostadorSeguroValor(apostador, valor, previsao, valorAssegurado);
	}
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		this.checkCadastroAposta(cenario, " assegurada por taxa");
		this.caixa += custo;
		return this.cenarios.get(cenario - 1).addApostadorSeguroTaxa(apostador, valor, previsao, taxa);
	}
	
	public int alterarSeguroValor(int cenario, int apostaAssegurada, int valor) {
		this.cenarios.get(cenario - 1).trocaSeguroValor(apostaAssegurada, valor);
		return apostaAssegurada;
	}
	
	public int alterarSeguroTaxa(int cenario, int apostaAssegurada, double valor) {
		 this.cenarios.get(cenario - 1).trocaSeguroTaxa(apostaAssegurada, valor);
		 return apostaAssegurada;
	}
	
	public int valorTotalDeApostas(int cenario) {
		if (cenario < 1) {
			throw new PosicaoInvalidaException("do valor total de apostas", "Cenario invalido");
			
		} else if (cenario > cenarios.size()) {
			throw new PosicaoInvalidaException("do valor total de apostas", "Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario - 1).getTotalApostado();
	}
	
	public int totalDeApostas(int cenario) {
		if (cenario < 1) {
			throw new PosicaoInvalidaException("do total de apostas", "Cenario invalido");
			
		} else if (cenario > this.cenarios.size()) {
			throw new PosicaoInvalidaException("do total de apostas", "Cenario nao cadastrado");
		}
		return this.cenarios.get(cenario - 1).getTotalDeApostadores();
	}
	
	public String exibeApostas(int cenario) {
		if (cenario < 1) {
			throw new ExibicaoException("Cenario invalido");
			
		} else if (cenario > this.cenarios.size()) {
			throw new ExibicaoException("Cenario nao cadastrado");
		}
		String output = this.cenarios.get(cenario - 1).getApostas();
		return output == "" ? "Não há apostadores cadastrados nesse cenário." : output;
	}
	
	public int getCaixaCenario(int cenario) {
		if (cenario < 1) {
			throw new ConsultaException("do caixa do cenario", "Cenario invalido");
			
		} else if (cenario > this.cenarios.size()) {
			throw new ConsultaException("do caixa do cenario", "Cenario nao cadastrado");
			
		} if (this.cenarios.get(cenario - 1).getEstado().equals("Nao finalizado")) {
			throw new ConsultaException("do caixa do cenario", "Cenario ainda esta aberto");
		}
		
		return (int) (this.cenarios.get(cenario - 1).getValorPremiado() * this.taxa);
	}
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		if (cenario < 1) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario invalido");
			
		} else if (cenario > this.cenarios.size()) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario nao cadastrado");
			
		} else if (this.cenarios.get(cenario - 1).getEstado().contains("Finalizado")) {
			throw new ExibicaoException("Erro ao fechar aposta", "Cenario ja esta fechado");
		}
		this.cenarios.get(cenario - 1).fecharCenario(ocorreu);
		this.caixa += this.getCaixaCenario(cenario) - this.cenarios.get(cenario - 1).getValorAssegurado();
	}
	
	public int getTotalRateioCenario(int cenario) {
		if (cenario < 1) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario invalido");

		} else if (cenario > this.cenarios.size()) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario nao cadastrado");
			
		} if (this.cenarios.get(cenario - 1).getEstado().equals("Nao finalizado")) {
			throw new ConsultaException("do total de rateio do cenario", "Cenario ainda esta aberto");
		}
		return this.cenarios.get(cenario - 1).getTotalRateioCenario(this.taxa);
	}
	
	public static void main(String[] args) {
		args = new String[] {"ClassePrincipal.Controller", "TestesAceitacao/us1_test.txt", "TestesAceitacao/us2_test.txt", "TestesAceitacao/us3_test.txt",
				"TestesAceitacao/us4_test.txt", "TestesAceitacao/us5_test.txt", "TestesAceitacao/us6_test.txt"};
		EasyAccept.main(args);
	}
}
