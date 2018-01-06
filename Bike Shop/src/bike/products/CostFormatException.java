package bike.products;

public class CostFormatException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	private String costString;
	
	public CostFormatException(String costString) {
		super(costString + " is an invalid cost.");
		this.costString = costString;
	}
	
	public String getCostString() {
		return costString;
	}

}
