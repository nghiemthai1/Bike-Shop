package bike.products;

public class InvalidProductException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String productType;

	public InvalidProductException(String productType) {
		super(productType + " is an invalid product type.");
	}
	
	public String getInvalidProductType() {
		return productType;
	}
}
