package Exceptions;

public class ConsultaException extends RuntimeException {
	
	public ConsultaException(String causa) {
		super("Erro na consulta de cenario: " + causa);
	}
	
	public ConsultaException(String causa, String msg) {
		super("Erro na consulta " + causa + ": " + msg);
	}

}
