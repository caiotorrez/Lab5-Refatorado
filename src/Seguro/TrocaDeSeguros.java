package Seguro;

public interface TrocaDeSeguros {
	
	public abstract int trocaSeguroTaxa(int id, double taxa);
	
	public abstract int trocaSeguroValor(int id, int valor);

}
