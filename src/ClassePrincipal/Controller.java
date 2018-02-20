package ClassePrincipal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Cenarios.Cenario;
import Cenarios.CenarioBonus;
import Cenarios.CenarioComum;
import OrdenadoresCenarios.CenarioComparatorDescricao;
import OrdenadoresCenarios.CenarioComparatorQtApostas;
import Validador.ValidadorDoController;
import easyaccept.EasyAccept;

public class Controller {

	
	private List<Cenario> cenarios;
	private int caixa;
	private double taxa;
	ValidadorDoController validar;
	private String ordem;
	
	
	public void inicializa(int caixa, double taxa) {
		this.validar = new ValidadorDoController();
		this.cenarios = new ArrayList<>();
		this.validar.validaCaixa(caixa, taxa);
		this.caixa = caixa;
		this.taxa = taxa;
		this.ordem = "cadastro";
	}
	
	
	public int getCaixa() {
		return this.caixa;
	}
	
	
	public int cadastrarCenario(String descricao) {
		this.cenarios.add(new CenarioComum(descricao, this.cenarios.size()));
		return this.cenarios.size();
	}
	
	
	public int cadastrarCenario(String descricao, int bonus) {
		this.caixa -= bonus;
		this.cenarios.add(new CenarioBonus(descricao, bonus, this.cenarios.size()));
		return this.cenarios.size();
	}
	
	
	public String exibirCenario(int cenario) {
		this.validar.validaExibicaoCenario(cenario, this.cenarios.size());
		return cenario + " - " + this.cenarios.get(cenario - 1);
	}
	
	
	public String exibirCenarios() {
		String output = "";
		for (int i = 1; i <= cenarios.size(); i++) {
			output+= this.exibirCenario(i) + "\n";
		} return output == "" ? "Não há cenarios cadastrados." : output;
	}
	
	
	public void cadastrarAposta(int cenario, String apostador, int valor, String previsao) {
		this.validar.checkCadastroAposta(cenario, "", this.cenarios.size());
		this.cenarios.get(cenario - 1).addApostadorComum(apostador, valor, previsao);
	}
	
	
	public int cadastrarApostaSeguraValor(int cenario, String apostador, int valor, String previsao, int valorAssegurado, int custo) {
		this.validar.checkCadastroAposta(cenario, " assegurada por valor", this.cenarios.size());
		this.caixa += custo;
		return this.cenarios.get(cenario - 1).addApostadorSeguroValor(apostador, valor, previsao, valorAssegurado);
	}
	
	
	public int cadastrarApostaSeguraTaxa(int cenario, String apostador, int valor, String previsao, double taxa, int custo) {
		this.validar.checkCadastroAposta(cenario, " assegurada por taxa", this.cenarios.size());
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
		this.validar.checkValorDeApostas(cenario, this.cenarios.size());
		return this.cenarios.get(cenario - 1).getTotalApostado();
	}
	
	
	public int totalDeApostas(int cenario) {
		this.validar.checkTotalApostas(cenario, this.cenarios.size());
		return this.cenarios.get(cenario - 1).getTotalDeApostadores();
	}
	
	
	public String exibeApostas(int cenario) {
		this.validar.checkExibeApostas(cenario, this.cenarios.size());
		String output = this.cenarios.get(cenario - 1).getApostas();
		return output == "" ? "Não há apostadores cadastrados nesse cenário." : output;
	}
	
	
	public int getCaixaCenario(int cenario) {
		this.validar.checkGetCaixaCenario(cenario, this.cenarios.size());
		this.validar.checkGetCaixaEstado(this.cenarios.get(cenario - 1).getEstado());
		return (int) (this.cenarios.get(cenario - 1).getValorPremiado() * this.taxa);
	}
	
	
	public void fecharAposta(int cenario, boolean ocorreu) {
		this.validar.checkFecharAposta(cenario, this.cenarios.size());
		this.validar.checkFecharApostaEstado(this.cenarios.get(cenario - 1).getEstado());
		this.cenarios.get(cenario - 1).fecharCenario(ocorreu);
		this.caixa += this.getCaixaCenario(cenario) - this.cenarios.get(cenario - 1).getValorAssegurado();
	}
	
	
	public int getTotalRateioCenario(int cenario) {
		this.validar.checkTotalRateioCenario(cenario, this.cenarios.size());
		this.validar.checkTotalRateioEstado(this.cenarios.get(cenario - 1).getEstado());
		return this.cenarios.get(cenario - 1).getTotalRateioCenario(this.taxa);
	}
	
	
	public void alterarOrdem(String ordem) {
		this.validar.checkAlteraOrdem(ordem);
		this.ordem = ordem.toLowerCase();
	}
	
	
	public String exibirCenarioOrdenado(int cenario) {
		this.validar.checkExibirCenarioOrdenado(cenario, this.cenarios.size());
		if (this.ordem.equals("nome")) {
			Collections.sort(this.cenarios, new CenarioComparatorDescricao());
		}
		else if (this.ordem.equals("apostas")) {
			Collections.sort(this.cenarios, new CenarioComparatorQtApostas());
		}
		else {
			Collections.sort(this.cenarios);
		}
		String saida = this.cenarios.get(cenario - 1).getID() + " - " + this.cenarios.get(cenario - 1).toString();
		Collections.sort(this.cenarios);
		return saida;
	}
	
	
	public static void main(String[] args) {
		args = new String[] {"ClassePrincipal.Controller", "TestesAceitacao/us1_test.txt", "TestesAceitacao/us2_test.txt", "TestesAceitacao/us3_test.txt",
				"TestesAceitacao/us4_test.txt", "TestesAceitacao/us5_test.txt", "TestesAceitacao/us6_test.txt", "TestesAceitacao/us7_test.txt"};
		EasyAccept.main(args);
	}
}
