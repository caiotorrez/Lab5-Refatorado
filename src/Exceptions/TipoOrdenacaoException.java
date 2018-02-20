package Exceptions;

public class TipoOrdenacaoException extends RuntimeException {
	
	public TipoOrdenacaoException() {
		super("O tipo de ordenação é inválido, ordenação não cadastrada no sistema.");
	}

}
