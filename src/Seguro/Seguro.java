package Seguro;

public abstract class Seguro {
	
	private int valorAssegurado;
	
	public Seguro(int valor) {
		this.valorAssegurado = valor; 
	}
	
	public int getValorAssegurado() {
		return this.valorAssegurado;
	}
}
