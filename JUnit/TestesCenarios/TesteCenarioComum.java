package TestesCenarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Cenarios.Cenario;
import Cenarios.CenarioComum;
import Exceptions.CaractersInvalidoException;
import Exceptions.ValorInvalidoException;

public class TesteCenarioComum {
	
	private Cenario cenario;
	
	@Before
	public void setUp() {
		this.cenario = new CenarioComum("Todo mundo vai pagar com 10 LP2!!1!", 0);
	}

	@Test
	public void testCadastroNull() {
		try {
			this.cenario = new CenarioComum(null, 0);
			fail("N�o pode ser poss�vel.");
		} catch (NullPointerException npe){
			assertEquals(null, npe.getMessage());
		}
	}
	
	@Test
	public void testCadastroEmBranco() {
		try {
			this.cenario = new CenarioComum("   ", 0);
			fail("N�o pode ser poss�vel.");
		} catch (CaractersInvalidoException nie) {
			assertEquals("Erro no cadastro de cenario: Descricao nao pode ser vazia", nie.getMessage());
		}
	}
	
	@Test
	public void testFecharCenarioAndGetCenario() {
		assertEquals("Nao finalizado", this.cenario.getEstado());
		
		cenario.fecharCenario(true);
		assertEquals("Finalizado (ocorreu)", this.cenario.getEstado());
		
		cenario.fecharCenario(false);
		assertEquals("Finalizado (n ocorreu)", this.cenario.getEstado());
	}
	
	@Test
	public void testAddApostadorValorInvalido() {
		try {
			this.cenario.addApostadorComum("z�", 0, "VAI ACONTECER");
			fail("N�o pode ser poss�vel adcionar apostador com valores abaixo de 1");
		} catch (ValorInvalidoException vie) {
			assertEquals("Erro no cadastro de aposta: Valor nao pode ser menor ou igual a zero", vie.getMessage());
		}
	}
	
	@Test
	public void testAddApostadorNomeInvalido() {
		try {
			this.cenario.addApostadorComum("   ", 110, "VAI ACONTECER");
			fail("N�o pode ser poss�vel adcionar apostador nome em branco.");
		} catch (CaractersInvalidoException nie) {
			assertEquals("Erro no cadastro de aposta: Apostador nao pode ser vazio ou nulo", nie.getMessage());
		}
	}
	
	@Test
	public void testAddApostadorPrevisaoInvalida() {
		try {
			this.cenario.addApostadorComum("Jos� do �i", 110, "akakakak");
			fail("N�o pode ser poss�vel cadastrar com uma previs�o inv�lida.");
		} catch (CaractersInvalidoException cie) {
			assertEquals("Erro no cadastro de aposta: Previsao invalida", cie.getMessage());
		}
	}
	
	@Test
	public void testGetCashSemFechar() {
		this.cenario.addApostadorComum("z�", 100, "VAI ACONTECER");
		assertEquals(0, this.cenario.getValorPremiado());
	}
	
	@Test
	public void testGetCash() {
		this.cenario.addApostadorComum("z�", 100, "VAI ACONTECER");
		this.cenario.addApostadorComum("maria", 50, "N VAI ACONTECER");
		
		this.cenario.fecharCenario(true);
		assertEquals(50, this.cenario.getValorPremiado());
		
		this.cenario.fecharCenario(false);
		assertEquals(100, this.cenario.getValorPremiado());
	}
	
	@Test
	public void testGetCashTotal() {
		this.cenario.addApostadorComum("z�", 100, "VAI ACONTECER");
		this.cenario.addApostadorComum("maria", 50, "N VAI ACONTECER");
		
		assertEquals(150, this.cenario.getTotalApostado());
	}
	
}