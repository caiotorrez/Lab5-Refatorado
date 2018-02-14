package TestesApostas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Apostas.Aposta;
import Apostas.ApostaComum;

public class TesteApostaComum {
	
	private Aposta aposta;

	@Before
	public void setUp() {
		this.aposta = new ApostaComum("Caio", 1000, "VAI ACONTECER");
	}
	
	@Test
	public void testeApostaValorZero() {
		try {
			this.aposta = new ApostaComum("Caio", 0, "VAI ACONTECER");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaValorNegativo() {
		try {
			this.aposta = new ApostaComum("Caio", -1, "VAI ACONTECER");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaPrevisaoInvalido() {
		try {
			this.aposta = new ApostaComum("zé", 100, "XXX VAI ACONTECER");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Previsao invalida", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaPrevisaoEmBranco() {
		try {
			this.aposta = new ApostaComum("zé", 100, "  ");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaPrevisaoNull() {
		try {
			this.aposta = new ApostaComum("zé", 100, null);
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Previsao nao pode ser vazia ou nula", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaApostadorEmBranco() {
		try {
			this.aposta = new ApostaComum("  ", 100, "VAI ACONTECER");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo", e.getMessage());
		}
	}
	
	@Test
	public void testeApostaApostadorNull() {
		try {
			this.aposta = new ApostaComum(null, 100, "VAI ACONTECER");
			fail("Não pode ser possível.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo", e.getMessage());
		}
	}
	
	@Test
	public void testeGetValor() {
		assertEquals(1000, this.aposta.getValor());
	}


}