package bike.products;

/**
 * A representation of a bike light being sold at a shop.
 * 
 * @author Eugene Koval
 */
@SuppressWarnings("serial")
public class Light extends Accessory {

	/**
	 * Creates a bike light with a manufacturer's price, rental costs, and retail
	 * costs. 
	 *  
	 * @param wholeSale The manufacturer's price for this helmet.
	 */
	public Light(Cost wholeSale, String productNumber, String make, String model) {
		super(wholeSale, "Lights", productNumber, make, model);
	}
	
	public Product createProduct(String[] parameters) {
		return new Light(Cost.parseCost(parameters[4]), parameters[1], 
				parameters[2], parameters[3]);
	}
}
