package Exceptions;

public class OrdemInvalidaException extends RuntimeException {
	
	public OrdemInvalidaException(String causa) {
		super("Erro ao alterar ordem: " + causa);
	}
	
	

}
