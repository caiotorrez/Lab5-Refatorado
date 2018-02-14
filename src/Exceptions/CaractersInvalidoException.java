package Exceptions;

@SuppressWarnings("serial")
public class CaractersInvalidoException extends CadastroInvalidoException {

	public CaractersInvalidoException(String tipo, String causa) {
		super(tipo, causa);
	}

	public CaractersInvalidoException(String tipo) {
		super(tipo, "Apostador nao pode ser vazio ou nulo");
	}
}
