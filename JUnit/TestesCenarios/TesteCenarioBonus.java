package TestesCenarios;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import Cenarios.Cenario;
import Cenarios.CenarioBonus;
import Exceptions.CaractersInvalidoException;

public class TesteCenarioBonus {

	private Cenario cenario;
	
	@Before
	public void setUp() {
		this.cenario = new CenarioBonus("Todo mundo vai pagar com 10 LP2!!1!", 1000);
	}
	
	@Test
	public void testCadastroNull() {
		try {
			this.cenario = new CenarioBonus(null, 0);
			fail("Não pode ser possível.");
		} catch (NullPointerException npe){
			assertEquals(null, npe.getMessage());
		}
	}
	
	@Test
	public void testCadastroEmBranco() {
		try {
			this.cenario = new CenarioBonus("   ", 0);
			fail("Não pode ser possível.");
		} catch (CaractersInvalidoException nie) {
			assertEquals("Erro no cadastro de cenario: Descricao nao pode ser vazia", nie.getMessage());
		}
	}
	
	@Test
	public void testCadastroValorZero() {
		try {
			this.cenario = new CenarioBonus("vai da errado?", 0);
			fail("Não pode ser possível cadastrar um cenário bônus com valor igual ou menor que 0.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de cenario: Bonus invalido", e.getMessage());
		}
	}
	
	@Test
	public void testCadastroValorNegativo() {
		try {
			this.cenario = new CenarioBonus("vai da errado?", -1);
			fail("Não pode ser possível cadastrar um cenário bônus com valor igual ou menor que 0.");
		} catch (Exception e) {
			assertEquals("Erro no cadastro de cenario: Bonus invalido", e.getMessage());
		}
	}
	
	@Test
	public void testToString() {
		assertEquals("Todo mundo vai pagar com 10 LP2!!1! - Nao finalizado - R$ 10,00", this.cenario.toString());
	}

}
