package bike.products;

/**
 * A representation of a unicycle being rented from a shop.
 * 
 * @author Eugene Koval
 *
 */
@SuppressWarnings("serial")
public class Unicycle extends Bike {
	
	/**
	 * Creates a unicycle with a manufacturer's price, rental costs, 
	 * a number, make, and model. 
	 * 
	 * @param wholeSale The manufacturer's price for this bike.
	 * @param number The bike's unique identifying number.
	 * @param make The name of the bike's manufacturer.
	 * @param model The bike's particular model.
	 * @param womens Whether the bike is a women's bike or not.
	 */
	public Unicycle(Cost wholeSale, String number,
			String make, String model, boolean womens) {	
		super(wholeSale, "Unicycles", number, make, model, womens);
	}
	
	public Product createProduct(String[] parameters) {
		return new Unicycle(Cost.parseCost(parameters[4]), parameters[1], 
				parameters[2], parameters[3], parameters[5].equals("Y"));
	}

}
