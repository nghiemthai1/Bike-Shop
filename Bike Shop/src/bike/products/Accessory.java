package bike.products;

/**
 * A class to represent an accessory sold at a shop. In addition to being rented
 * out, accessories may also be purchased.
 * 
 * @author Eugene Koval
 */
@SuppressWarnings("serial")
public abstract class Accessory 
	extends Product implements Buyable {
	
	// The retail price for an accessory.
	private Cost retailCost;
	// The percentage for the retail mark up.
	private static final int RETAIL_MARKUP = 125;
	
	/**
	 * Creates a new accessory with a manufacturer's price. This price is used
	 * to calculate the retail price.
	 * 
	 * @param wholeSale The manufacturer's price for this accessory.
	 */
	public Accessory(Cost wholeSale, String type, String productNumber, String make, String model) {
		super(wholeSale, type, productNumber, make, model);
		retailCost = calculateRetailCost(wholeSale);
	}
	
	public Cost getRetailCost() {
		return retailCost;
	}
	
	public Cost calculateRetailCost(Cost cost) {
		return new Cost(cost.calculatePercentage(RETAIL_MARKUP)); 
	}

	/**
	 * Returns a formatted string representation of this accessory.
	 * The string includes the accessory's make, model, rental costs, and retail cost.
	 * 
	 * @return a formatted string representation of this accessory.
	 */
	@Override
	public String toString() {
		return super.toString() + 
				"\n\n\tBuy It Now: " + retailCost;
	}
}
