package Exceptions;


public class PosicaoInvalidaException extends ConsultaException {
	
	public PosicaoInvalidaException(String causa) {
		super(causa);
	}
	
	public PosicaoInvalidaException(String causa, String msg) {
		super(causa, msg);
	}

}
