package Cenarios;

import java.util.ArrayList;

import Apostas.Aposta;
import Apostas.ApostaComum;
import Apostas.ApostaSegura;
import Exceptions.CaractersInvalidoException;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;

/**
 * Representação de um cenário abstrato para apostas, onde é possível cadastrar um cenário com descrição, fechar o cenário, adcionar apostadores,
 * entre outras funcionalidades.
 * 
 * @author Caio Torres
 *
 */
public abstract class Cenario {
	
	private final String descricao;
	private String estado;
	private ArrayList<Aposta> apostas;
	
	/**
	 * Construtor do cenário onde o parâmentro a ser passado representa a descriação do cenário a ser previsto.
	 * @param descricao Inserir descrição do cenário à construir.
	 */
	public Cenario(String descricao) {
		if (descricao.trim().equals("")) {
			throw new CaractersInvalidoException("de cenario:", "Descricao nao pode ser vazia");
		}
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostas = new ArrayList<>();
	}
	
	/**
	 * Adciona um apostador sem seguro em {@link apostas} para esse cenário, a partir de um nome, um valor e uma previsão.
	 * @param nome Nome do apostador.
	 * @param valor Representa o dinheiro a ser apostado.
	 * @param previsao Previsão em que o apostador irá apostar, pode ser "VAI ACONTECER" ou "N VAI ACONTECER".
	 */
	public void addApostadorComum(String apostador, int valor, String previsao) {
		this.apostas.add(new ApostaComum(apostador, valor, previsao));
	}

	/**
	 * Adciona um apostador com {@link SeguroTaxa} em {@link apostas} para esse cenário, a partir de um nome, um valor e uma previsão e uma taxa.
	 * @param nome Nome do apostador.
	 * @param valor Representa o dinheiro a ser apostado.
	 * @param previsao Previsão em que o apostador irá apostar, pode ser "VAI ACONTECER" ou "N VAI ACONTECER".
	 */
	public int addApostadorSeguroTaxa(String apostador, int valor, String previsao, double taxa) {
		this.apostas.add(new ApostaSegura(apostador, valor, previsao, taxa));
		return this.apostas.size();
	}
	
	/**
	 * Adciona um apostador com {@link SeguroValor} em {@link apostas} para esse cenário, a partir de um nome, um valor e uma previsão e um valor assegurado.
	 * @param nome Nome do apostador.
	 * @param valor Representa o dinheiro a ser apostado.
	 * @param previsao Previsão em que o apostador irá apostar, pode ser "VAI ACONTECER" ou "N VAI ACONTECER".
	 */
	public int addApostadorSeguroValor(String apostador, int valor, String previsao, int valorAssegurado) {
		this.apostas.add(new ApostaSegura(apostador, valor, previsao, valorAssegurado));
		return this.apostas.size();
	}
	
	/**
	 * Método para retornar uma String com a descrição cadastrada no cenário.
	 * @return Retorna uma String com a descrição do cenário.
	 */
	public String getDescricao() {
		return this.descricao;
	}
	
	/**
	 * Método para retornar o estado em que o cenário se encontra.
	 * @return Retorna uma String com a representação atual do estado do cenário.
	 */
	public String getEstado() {
		return this.estado;
	}
	
	/**
	 * Método que calcula o total de valor apostado na previsão "VAI ACONTECER" desse cenário.
	 * @return retorna um inteiro com o valor total apostado.
	 */
	private int getTotalApostadoVaiAcontecer() {
		int total = 0;
		for (int i = 0; i < this.apostas.size(); i++) {
			if (this.apostas.get(i).getPrevisao().equals("VAI ACONTECER")) {
				total += this.apostas.get(i).getValor();
			}
		} return total;
	}

	/**
	 * Método que calcula o total de valor apostado na previsão "N VAI ACONTECER" desse cenário.
	 * @return retorna um inteiro com o valor total apostado.
	 */
	private int getTotalApostadoNaoVaiAcontecer() {
		int total = 0;
		for (int i = 0; i < this.apostas.size(); i++) {
			if (this.apostas.get(i).getPrevisao().equals("N VAI ACONTECER")) {
				total += this.apostas.get(i).getValor();
			} 
		} return total;
	}
	
	/**
	 * Soma o valor total do que foi apostado entre as previsões.
	 * @return Retorna um inteiro com o valor total apostado.
	 */
	public int getTotalApostado() {
		return this.getTotalApostadoNaoVaiAcontecer() + this.getTotalApostadoVaiAcontecer();
	}
	
	/**
	 * Método que retorna o total de apostadores cadastrados.
	 * @return retorna um inteiro representando o número de apostadores cadastrados.
	 */
	public int getTotalDeApostadores() {
		return this.apostas.size();
	}
	
	/**
	 * Método que retorna uma String com todas as apostas em ordem cronológica, caso não haja apostas a mensagem
	 * "Nenhum apostador encontrado." será exibida.
	 * 
	 * @return Retorna uma String com o histórico de apostas.
	 */
	public String getApostas() {
		String output = "";
		for (int i = 0; i < apostas.size(); i++) {
			output += this.apostas.get(i).toString() + "\n";
		}
		return output == "" ? "Não há apostas cadastradas nesse cenário" : output;
	}
	
	/**
	 * Fecha um cenário a partir de um valor booleano.
	 * @param resultado Deve ser inserido <code>true</code> caso o cenário tenha acontecido ou <code>false</code> caso contrário.
	 */
	public void fecharCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = "Finalizado (ocorreu)";
		} else {
			this.estado = "Finalizado (n ocorreu)";
		}
	}

	/**
	 * Método que retorna um valor inteiro representado o total de dinheiro que os apostadores irão ganhar.
	 * @return retorna um inteiro com a quantidade de dinheiro dos apostadores que perderam.
	 */
	public int getValorPremiado() {
		if (this.estado.equals("Finalizado (n ocorreu)")) {
			return this.getTotalApostadoVaiAcontecer();
		} else if (this.estado.equals("Finalizado (ocorreu)")) {
			return this.getTotalApostadoNaoVaiAcontecer();
		} else {
			return 0;
		}
	}
	
	/**
	 * Método responsável pela troca de seguro de valor para seguro com taxa cadastrado na aposta segura.
	 * @param id um inteiro que representa a aposta.
	 * @param taxa um double representando a porcentagem a ser calculada com o valor da aposta.
	 * @return retorna o id do apostador.
	 */
	public int trocaSeguroTaxa(int id, double taxa) {
		this.apostas.get(id - 1).alteraSeguro(new SeguroTaxa(this.apostas.get(id - 1).getValor(), taxa));
		return id;
	}
	
	/**
	 * Método responsável pela troca de seguro com taxa para seguro de valor cadastrado na aposta segura.
	 * @param id um inteiro que representa a aposta.
	 * @param taxa um double representando a porcentagem a ser calculada com o valor da aposta.
	 * @return retorna o id do apostador.
	 */
	public int trocaSeguroValor(int id, int valor) {
		this.apostas.get(id - 1).alteraSeguro(new SeguroValor(valor));
		return id;
	}
	
	/**
	 * Soma o valor total assegurado dos apostadores assegurados perdedores.
	 * @return Retorna um inteiro com a quantidade de valor assegurado das apostas.
	 */
	public int getValorAssegurado() {
		int total = 0;
		if (this.estado.equals("Finalizado (ocorreu)")) {
			for (int i = 0; i < this.apostas.size(); i++) {
				if (this.apostas.get(i).getPrevisao().equals("N VAI ACONTECER")) {
					total += this.apostas.get(i).getValorAssegurado();
				}
			}	
		} else if (this.estado.equals("Finalizado (n ocorreu)")) {
			for (int i = 0; i < this.apostas.size(); i++) {
					total += this.apostas.get(i).getValorAssegurado();
				}
		} return total;
	}
	
	/**
	 * Método que retorna um int com a quantidade de dinheiro a ser distribuida pelos apostadores vencedores do cenário atual. 
	 * @param taxa Taxa é o parâmetro a ser mutiplicado para a separação dos bens a serem distribuidos.
	 * @return Retorna um <code>int</code> com o valor a ser rateiado entre os vencedores do cenário.
	 */
	public int getTotalRateioCenario(double taxa) {
		return this.getValorPremiado() - (int) (this.getValorPremiado() * taxa);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	/**
	 * Retorna uma String no formato %"Descrição" + " - " + %"Estado em que o cenário se encontra".
	 */
	@Override
	public String toString() {
		return this.descricao + " - " + this.estado;
	}
}
