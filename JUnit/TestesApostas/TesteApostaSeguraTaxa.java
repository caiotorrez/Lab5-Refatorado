package TestesApostas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Apostas.ApostaSegura;
import Seguro.SeguroValor;

public class TesteApostaSeguraTaxa {
	
	private ApostaSegura aposta;

	@Before
	public void setUp() {
		this.aposta = new ApostaSegura("Caio", 1000, "VAI ACONTECER", 0.10);
	}
	
	@Test
	public void testeApostaSeguraValorZero() {
		try {
			this.aposta = new ApostaSegura("Caio", 0, "VAI ACONTECER", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorNegativo() {
		try {
			this.aposta = new ApostaSegura("Caio", -1, "VAI ACONTECER", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Valor nao pode ser menor ou igual a zero", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorPrevisaoInvalida() {
		try {
			this.aposta = new ApostaSegura("Caio", 1000, "XXX VAI ACONTECER", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Previsao invalida", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorPrevisaoEmBranco() {
		try {
			this.aposta = new ApostaSegura("Caio", 1000, "  ", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorPrevisaoNull() {
		try {
			this.aposta = new ApostaSegura("Caio", 1000, null, 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Previsao nao pode ser vazia ou nula", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorApostadorEmBranco() {
		try {
			this.aposta = new ApostaSegura(" ", 1000, "VAI ACONTECER", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorApostadorNull() {
		try {
			this.aposta = new ApostaSegura(null, 1000, "VAI ACONTECER", 0.10);
			fail("N�o pode ser poss�vel.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta assegurada por taxa: Apostador nao pode ser vazio ou nulo", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaSeguraValorGetValorAssegurado() {
		assertEquals(100, (int) this.aposta.getValorAssegurado());
	}
	
	@Test
	public void testeApostaSeguraValorTrocaTipo() {
		this.aposta.alteraSeguro(new SeguroValor(150));
		assertEquals(150, (int) (this.aposta.getValorAssegurado()));
	}

}