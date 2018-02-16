package Cenarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Apostas.Aposta;
import Apostas.ApostaComum;
import Apostas.ApostaSegura;
import Exceptions.CaractersInvalidoException;
import Seguro.SeguroTaxa;
import Seguro.SeguroValor;


public abstract class Cenario {

	private final String descricao;
	private String estado;
	private ArrayList<Aposta> apostasComum;
	private Map<Integer, ApostaSegura> apostasSeguras;

	public Cenario(String descricao) {
		if (descricao.trim().equals("")) {
			throw new CaractersInvalidoException("de cenario:", "Descricao nao pode ser vazia");
		}
		this.descricao = descricao;
		this.estado = "Nao finalizado";
		this.apostasComum = new ArrayList<>();
		this.apostasSeguras = new HashMap<>();
	}

	public void addApostadorComum(String apostador, int valor, String previsao) {
		this.apostasComum.add(new ApostaComum(apostador, valor, previsao));
	}

	public int addApostadorSeguroTaxa(String apostador, int valor, String previsao, double taxa) {
		ApostaSegura aposta = new ApostaSegura(apostador, valor, previsao, taxa);
		this.apostasSeguras.put(aposta.getId(), aposta);
		return aposta.getId();
	}

	public int addApostadorSeguroValor(String apostador, int valor, String previsao, int valorAssegurado) {
		ApostaSegura aposta = new ApostaSegura(apostador, valor, previsao, valorAssegurado);
		this.apostasSeguras.put(aposta.getId(), aposta);
		return aposta.getId();
	}

	public String getDescricao() {
		return this.descricao;
	}

	public String getEstado() {
		return this.estado;
	}

	private int getTotalApostadoVaiAcontecer() {
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

	private int getTotalApostadoNaoVaiAcontecer() {
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

	public int getTotalApostado() {
		return this.getTotalApostadoNaoVaiAcontecer() + this.getTotalApostadoVaiAcontecer();
	}

	public int getTotalDeApostadores() {
		return this.apostasComum.size() + this.apostasSeguras.size();
	}

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

	public void fecharCenario(boolean ocorreu) {
		if (ocorreu) {
			this.estado = "Finalizado (ocorreu)";
		} else {
			this.estado = "Finalizado (n ocorreu)";
		}
	}

	public int getValorPremiado() {
		if (this.estado.equals("Finalizado (n ocorreu)")) {
			return this.getTotalApostadoVaiAcontecer();
		} else if (this.estado.equals("Finalizado (ocorreu)")) {
			return this.getTotalApostadoNaoVaiAcontecer();
		} else {
			return 0;
		}
	}


	public int trocaSeguroTaxa(int id, double taxa) {
		return this.apostasSeguras.get(id).alteraSeguro(new SeguroTaxa(this.apostasSeguras.get(id).getValor(), taxa));
	}

	public int trocaSeguroValor(int id, int valor) {
		return this.apostasSeguras.get(id).alteraSeguro(new SeguroValor(valor));
	}

	
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

	@Override
	public String toString() {
		return this.descricao + " - " + this.estado;
	}
}
