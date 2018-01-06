package bike.products;

/**
 * A representation of a helmet being sold at a shop.
 * 
 * @author Eugene Koval
 */
@SuppressWarnings("serial")
public class Helmet extends Accessory {
	
	/**
	 * Creates a helmet with a manufacturer's price, rental costs, and retail
	 * costs. 
	 *  
	 * @param wholeSale The manufacturer's price for this helmet.
	 */
	public Helmet(Cost wholeSale, String productNumber, String make, String model) {
		super(wholeSale, "Helmets", productNumber, make, model);
	}
	
	public Product createProduct(String[] parameters) {
		return new Helmet(Cost.parseCost(parameters[4]), parameters[1], 
				parameters[2], parameters[3]);
	}
}
