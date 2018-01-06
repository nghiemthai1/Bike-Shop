package bike.products;

/**
 * A representation of a mountain bike being rented from a shop.
 * 
 * @author Eugene Koval
 *
 */
@SuppressWarnings("serial")
public class MountainBike extends Bike {
	
	/**
	 * Creates a mountain bike with a manufacturer's price, rental costs, 
	 * a number, make, and model. 
	 * 
	 * @param wholeSale The manufacturer's price for this bike.
	 * @param number The bike's unique identifying number.
	 * @param make The name of the bike's manufacturer.
	 * @param model The bike's particular model.
	 * @param womens Whether the bike is a women's bike or not.
	 */
	public MountainBike(Cost wholeSale, String number,
			String make, String model, boolean womens) {	
		super(wholeSale, "Mountain Bikes", number, make, model, womens);
	}

	public Product createProduct(String[] parameters) {
		return new MountainBike(Cost.parseCost(parameters[4]), parameters[1], 
				parameters[2], parameters[3], parameters[5].equals("Y"));
	}
}
