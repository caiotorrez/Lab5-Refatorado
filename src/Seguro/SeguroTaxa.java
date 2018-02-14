package Seguro;

public class SeguroTaxa extends Seguro{
	
	private double taxa;
	
	public SeguroTaxa(int valorDaAposta, double taxa) {
		super((int) (taxa * valorDaAposta));
		this.taxa = taxa;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	@Override
	public String toString() {
		return "taxa";
	}

}
