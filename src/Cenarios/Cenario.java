package Cenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Apostas.Aposta;
import Apostas.ApostaComum;
import Apostas.ApostaSegura;
import Exceptions.CaractersInvalidoException;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;
import Seguro.TrocaDeSeguros;

/**
 * Clase abstrata que representa um cenario composta por apostas, onde o usuario pode cadastrar apostas comuns e seguras.
 * 
 * @author CaioTorrez
 *
 */
public abstract class Cenario implements Comparable<Cenario>, TrocaDeSeguros {

	private final int ID;
	private final String DESCRICAO;
	private String estado;
	private List<Aposta> apostasComum;
	private Map<Integer, ApostaSegura> apostasSeguras;

	
	/**
	 * Construtor do cenario onde se faz nescessaria uma descricao para os apostadores escolherem onde desejam apostar.
	 * 
	 * @param descricao representa a descricao a ser atribuida ao cenario a ser construido.	
	 * @param id representa o valor de identificacao do cenario a ser construido.
	 */
	public Cenario(String descricao, int id) {
		if (descricao.trim().equals("")) {
			throw new CaractersInvalidoException("de cenario:", "Descricao nao pode ser vazia");
		}
		this.DESCRICAO = descricao;
		this.ID = id + 1;
		this.estado = "Nao finalizado";
		this.apostasComum = new ArrayList<>();
		this.apostasSeguras = new HashMap<>();
	}
	
	
	/**
	 * Metodo responsavel pelo cadastro de apostas.
	 * 
	 * @param apostador Nome do apostador.
	 * @param valor Valor da aposta a ser cadastrada.
	 * @param previsao Previsao da aposta a ser cadastrada.
	 */
	public void addApostadorComum(String apostador, int valor, String previsao) {
		this.apostasComum.add(new ApostaComum(apostador, valor, previsao));
	}

	
	/**
	 * Metodo responsavel por cadastrar apostas seguras por taxa.
	 * 
	 * @param apostador Nome do apostador.
	 * @param valor Valor da aposta a ser cadastrada.
	 * @param previsao Previsao da aposta a ser cadastrada.
	 * @param taxa Taxa a ser assegurada em cima do valor apostado.
	 * @return Retorna um inteiro a identificacao(ID) do apostador.
	 */
	public int addApostadorSeguroTaxa(String apostador, int valor, String previsao, double taxa) {
		ApostaSegura aposta = new ApostaSegura(apostador, valor, previsao, taxa);
		this.apostasSeguras.put(aposta.getId(), aposta);
		return aposta.getId();
	}

	
	/**
	 * Metodo responsavel por cadastrar apostas seguras por taxa.
	 * 
	 * @param apostador Nome do apostador.
	 * @param valor Valor da aposta a ser cadastrada.
	 * @param previsao Previsao da aposta a ser cadastrada.
	 * @param valorAssegurado Valor a ser assegurado.
	 * @return Retorna um inteiro a identificacao(ID) do apostador.
	 */
	public int addApostadorSeguroValor(String apostador, int valor, String previsao, int valorAssegurado) {
		ApostaSegura aposta = new ApostaSegura(apostador, valor, previsao, valorAssegurado);
		this.apostasSeguras.put(aposta.getId(), aposta);
		return aposta.getId();
	}

	
	/**
	 * Retorna a descricao cadastrada.
	 * 
	 * @return Uma String com a descricao.
	 */
	public String getDescricao() {
		return this.DESCRICAO;
	}

	
	/**
	 * Retorna o estado cadastrado.
	 * 
	 * @return Uma String com o estado.
	 */
	public String getEstado() {
		return this.estado;
	}

	
	/**
	 * Calcula os valores das apostas cadastradas que optaram pela previsao "VAI ACONTECER".
	 * 
	 * @return retorna um inteiro com o total acumulado.
	 */
	private int calculaTotalVaiAcontecer() {
		int total = 0;
		for (Aposta aposta : apostasComum) {
			if (aposta.getPrevisao().equals("VAI ACONTECER")) {
				total += aposta.getValor();
			}
		}
		for (Integer i : apostasSeguras.keySet()) {
			if (this.apostasSeguras.get(i).getPrevisao().equals("VAI ACONTECER")) {
				total += this.apostasSeguras.get(i).getValor();
			}
		}
		return total;
	}

	
	/**
	 * Calcula os valores das apostas cadastradas que optaram pela previsao "N VAI ACONTECER".
	 * 
	 * @return retorna um inteiro com o total acumulado.
	 */
	private int calculaTotalNVaiAcontecer() {
		int total = 0;
		for (Aposta aposta : apostasComum) {
			if (aposta.getPrevisao().equals("N VAI ACONTECER")) {
				total += aposta.getValor();
			}
		}
		for (Integer i : apostasSeguras.keySet()) {
			if (this.apostasSeguras.get(i).getPrevisao().equals("N VAI ACONTECER")) {
				total += this.apostasSeguras.get(i).getValor();
			}
		}
		return total;
	}

	
	/**
	 * Retorna o total apostado de ambas previsoes.
	 * 
	 * @return retorna um inteiro com o total acumulado.
	 */
	public int getTotalApostado() {
		return this.calculaTotalNVaiAcontecer() + this.calculaTotalVaiAcontecer();
	}

	
	/**
	 * Retorna a quantidade de apostadores cadastrados neste cenario.
	 * 
	 * @return retorna um inteiro com o valor total.
	 */
	public int getTotalDeApostadores() {
		return this.apostasComum.size() + this.apostasSeguras.size();
	}

	
	/**
	 * Exibe todas as apostas cadastradas neste cenario.
	 * 
	 * @return retorna uma String com todos os apostadores cadastrados, caso nao haja, uma msg de negacao sera exibida.
	 */
	public String getApostas() {
		String output = "";
		for (Aposta aposta : apostasComum) {
			output += aposta.toString() + "\n";
		}
		for (Integer i : apostasSeguras.keySet()) {
			output += this.apostasSeguras.get(i).toString() + "\n";
		}
		return output == "" ? "Não há apostas cadastradas nesse cenário." : output;
	}

	
	/**
	 * Modifica o estado do cenario, caso ocorra o estado sera "Finalizado (ocorreu)",
	 * caso contrario, o estado sera "Finalizado (n ocorreu)".
	 * 
	 * @param ocorreu <code>true</code> caso o cenario tenha ocorrido, <code>false</code> caso contrario.
	 */
	public void fecharCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = "Finalizado (ocorreu)";
		} else {
			this.estado = "Finalizado (n ocorreu)";
		}
	}

	
	/**
	 * Retorna o valor total de um cenario encerrado que sera destinado a distribuicao entre as apostas vencedoras.
	 * 
	 * @return retorna um inteiro com o total premiado.
	 */
	public int getValorPremiado() {
		if (this.estado.equals("Finalizado (n ocorreu)")) {
			return this.calculaTotalVaiAcontecer();
		} else if (this.estado.equals("Finalizado (ocorreu)")) {
			return this.calculaTotalNVaiAcontecer();
		} else {
			return 0;
		}
	}

	
	/**
	 * Troca o seguro valor das apostas seguras por seguro taxa.
	 * 
	 * @param id Identificacao do apostador cadastrado.
	 * @param taxa Taxa a ser cadastrada no novo seguro.
	 * 
	 * @return Retorna um inteiro com a identificacao.
	 */
	public int trocaSeguroTaxa(int id, double taxa) {
		return this.apostasSeguras.get(id).alteraSeguro(new SeguroTaxa(this.apostasSeguras.get(id).getValor(), taxa));
	}

	
	/**
	 * Troca o seguro por taxa das apostas seguras por seguro valor.
	 * 
	 * @param id Identificacao do apostador cadastrado.
	 * @param taxa Taxa a ser cadastrada no novo seguro.
	 * 
	 * @return Retorna um inteiro com a identificacao.
	 */
	public int trocaSeguroValor(int id, int valor) {
		return this.apostasSeguras.get(id).alteraSeguro(new SeguroValor(valor));
	}

	
	/**
	 * Calcula o total de valor assegurado das apostas seguras de acordo com o estado setado.
	 * 
	 * @return Retorna um inteiro com o total acumulado.
	 */
	public int getValorAssegurado() {
		int total = 0;
		if (this.estado.equals("Finalizado (ocorreu)")) {
			for (Integer i : apostasSeguras.keySet()) {
				if (this.apostasSeguras.get(i).getPrevisao().equals("N VAI ACONTECER")) {
					total += this.apostasSeguras.get(i).getValorAssegurado();
				}
			}
		} else if (this.estado.equals("Finalizado (n ocorreu)")) {
			for (Integer i : apostasSeguras.keySet()) {
				total += this.apostasSeguras.get(i).getValorAssegurado();
			}
		}
		return total;
	}

	/**
	 * Calcula o valor a ser distribuido pelos ganhadores de acordo com uma taxa passada.
	 * 
	 * @param taxa Taxa é o valor a ser taxado pelo total arrecadado.
	 * @return Retorna um inteiro com o valor de rateio.
	 */
	public int getTotalRateioCenario(double taxa) {
		return this.getValorPremiado() - (int) (this.getValorPremiado() * taxa);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DESCRICAO == null) ? 0 : DESCRICAO.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cenario other = (Cenario) obj;
		if (DESCRICAO == null) {
			if (other.DESCRICAO != null)
				return false;
		} else if (!DESCRICAO.equals(other.DESCRICAO))
			return false;
		return true;
	}
	
	/**
	 * Metodo para vizualizar a ID do cenario.
	 * 
	 * @return retorna um inteiro com a identificacao do cenario.
	 */
	public int getID() {
		return this.ID;
	}
	
	/**
	 * Metodo para comparar dois cenarios pela identificacao(ID).
	 */
	public int compareTo(Cenario outroCenario) {
		return new Integer(this.getID()).compareTo(new Integer(outroCenario.getID()));
	}
	
	
	/**
	 * Metodo que retorna uma String no formato "ID - descricao - estado".
	 */
	@Override
	public String toString() {
		return this.ID + " - " + this.DESCRICAO + " - " + this.estado;
	}
}
