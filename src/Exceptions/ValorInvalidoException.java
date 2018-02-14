package Exceptions;

@SuppressWarnings("serial")
public class ValorInvalidoException extends CadastroInvalidoException {
	
	public ValorInvalidoException(String tipo) {
		super(tipo, "Valor nao pode ser menor ou igual a zero");
	}
	
	public ValorInvalidoException(String tipo, String msg) {
		super(tipo, msg);
	}
	
}