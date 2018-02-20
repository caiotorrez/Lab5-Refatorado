package Exceptions;

public class InicializacaoException extends RuntimeException {
	
	public InicializacaoException(String tipo) {
		super("Erro na inicializacao: " + tipo + " nao pode ser inferior a 0");
	}

}
