package bike.products;

/**
 * An interface to represent anything that may be rented out for an hourly fee.
 * 
 * @author Eugene Koval
 */
public interface Rentable {

	/**
	 * Calculates and returns the amount for the safety deposit given the 
	 * original price.
	 * 
	 * @param cost The price from which to calculate the deposit.
	 * @return the amount for the safety deposit.
	 */
	abstract public Cost calculateDeposit(Cost cost);
	
	/**
	 * Calculates and returns the amount for the hourly rental fee given the 
	 * original price.
	 * 
	 * @param cost The price from which to calculate the hourly fee.
	 * @return the amount for the hourly rental fee.
	 */
	abstract public Cost calculateHourlyFee(Cost cost);
}
