package bike.products;

/**
 * A class that represents a bicycle being rented from a shop.
 * 
 * @author Eugene Koval
 */
@SuppressWarnings("serial")
public abstract class Bike extends Product {
	
	// Whether the bike is a women's bike or not.
	private boolean womens;
	
	/**
	 * Creates a bike with a manufacturer's price, rental costs, a number, make,
	 * and model. 
	 * 
	 * @param wholeSale The manufacturer's price for this bike.
	 * @param number The bike's unique identifying number.
	 * @param make The name of the bike's manufacturer.
	 * @param model The bike's particular model.
	 * @param womens Whether the bike is a women's bike or not.
	 */
	public Bike(Cost wholeSale, String type, String productNumber, String make, String model,
			boolean womens) {	
		super(wholeSale, type, productNumber, make, model);
		this.womens = womens;
	}
	
	/**
	 * Indicates whether this bike is a women's bike or not. 
	 * 
	 * @return true if the bike is a women's bike and false otherwise.
	 */
	public boolean isWomens() {
		return womens;
	}
}
