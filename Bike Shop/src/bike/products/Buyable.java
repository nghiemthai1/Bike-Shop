package bike.products;

/**
 * An interface to represent anything that may be purchased.
 * 
 * @author Eugene Koval
 */
public interface Buyable {
	
	/**
	 * Calculates and returns the marked up retail price given the original 
	 * price.
	 * 
	 * @param cost The price from which to calculate the retail cost.
	 * @return the retail price 
	 */
	public Cost calculateRetailCost(Cost cost);
	
	/**
	 * Returns the retail price for this accessory.
	 * 
	 * @return the retail price.
	 */
	public Cost getRetailCost();
}
