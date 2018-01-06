package bike.products;

/**
 * A representation of a bicycle pump being sold at a store.
 * 
 * @author Eugene Koval
 */
@SuppressWarnings("serial")
public class AirPump extends Accessory{

	/**
	 * Creates an air pump with a manufacturer's price, rental costs, and retail
	 * costs. 
	 *  
	 * @param wholeSale The manufacturer's price for this helmet.
	 */
	public AirPump(Cost wholeSale, String productNumber, String make, String model) {
		super(wholeSale, "Air Pumps", productNumber, make, model);
	}
	
	public Product createProduct(String[] parameters) {
		return new AirPump(Cost.parseCost(parameters[4]), parameters[1], 
				parameters[2], parameters[3]);
	}
}
