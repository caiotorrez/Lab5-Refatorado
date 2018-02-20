package Exceptions;

public class ExibicaoException extends RuntimeException {
	
	public ExibicaoException(String causa) {
		super("Erro na exibição do cenario: " + causa);
	}
	
	public ExibicaoException(String causa, String msg) {
		super(causa + ": " + msg);
	}
	

}
