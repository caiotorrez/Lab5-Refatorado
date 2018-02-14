package Exceptions;

public class CadastroInvalidoException extends RuntimeException {
	
	public CadastroInvalidoException(String tipo, String causa) {
		super("Erro no cadastro " + tipo + " " + causa);
	}
	
	public CadastroInvalidoException(String causa) {
		super("Erro no cadastro " + causa);
	}

}
