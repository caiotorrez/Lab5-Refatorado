import java.util.ArrayList;
import java.util.List;

import ClassePrincipal.Controller;

public class MainTeste {
	
	public static void main(String[] args) {
		
		Controller cc = new Controller();
		
		List<String> teste = new ArrayList<>();
		
		cc.inicializa(100, 0.01);
		
		cc.cadastrarCenario("vai da certo");
		cc.cadastrarCenario("vai da certo");
		cc.cadastrarCenario("vai da certo");
		cc.cadastrarCenario("bem que nao vai da certo");
		cc.cadastrarCenario("agora vai da certo");
		
		cc.cadastrarAposta(1, "Caio", 100, "VAI ACONTECER");
		cc.cadastrarAposta(1, "Caio", 100, "VAI ACONTECER");
		cc.cadastrarAposta(2, "Caio", 100, "VAI ACONTECER");
		
		
		cc.alterarOrdem("NOME");
		System.out.println(cc.exibirCenarios());
		cc.alterarOrdem("CADASTRO");
		System.out.println(cc.exibirCenarios());
		cc.alterarOrdem("eg");
		System.out.println(cc.exibirCenarios());
	}

}
