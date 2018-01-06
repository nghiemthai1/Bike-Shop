package bike.products;

import java.io.Serializable;

/**
 * The Cost class represents all money objects with integer values. This is 
 * done with the purpose of preventing rounding errors in money calculations.
 * Integer values are derived by multiplying dollars and cents by a factor 
 * of 100. The class Cost includes methods for carrying out basic arithmetic
 * operations as well as an easy way to calculate a percentage.
 * All Cost objects are immutable.   
 * 
 * @author Eugene Koval
 * @version 1.0
 */
@SuppressWarnings("serial")
public class Cost implements Serializable{
	
	// The cost as an integer.
	private int value;
	
	/**
	 * Creates a cost object given the cost in dollars and cents.
	 * 
	 * @param value
	 */
	public Cost(int dollars, int cents) {
		this.value = (dollars * 100) + cents;
	}
	
	/**
	 * Creates a cost object given the cost value as an integer.
	 * 
	 * @param value The integer representation of a cost.
	 */
	protected Cost(int value) {
		this.value = value;
	}
	
	/**
	 * Parses the string argument as a cost item.
	 * 
	 * @param costString - the string containing the cost to be parsed
	 * @return the cost represented by the argument
	 * @throws CostFormatException - if the string does not contain a parsable cost.
	 */
	public static Cost parseCost(String costString) {
		int dollars, cents;
		try {
			if (costString.contains(".")) {
				String[] costArray = costString.split("[.]");
				dollars = Integer.parseInt(costArray[0]);
				cents = Integer.parseInt(costArray[1]);
			}
			else {
				dollars = Integer.parseInt(costString);
				cents = 0;
			}
		}
		catch (NumberFormatException ex) {
			throw new CostFormatException(costString);
		}
		return new Cost(dollars, cents);
	}
	
	/**
	 * Returns the integer representation of this cost object.
	 * 
	 * @return the cost's integer representation.
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Adds one cost to another.
	 * 
	 * @param cost The cost to add.
	 * @return the result of the addition.
	 */
	public Cost add(Cost cost) {
		return new Cost(this.value + cost.getValue());
	}
	
	/**
	 * Subtracts one cost from another.
	 * 
	 * @param cost The cost to subtract.
	 * @return the result of the subtraction.
	 */
	public Cost subtract(Cost cost) {
		return new Cost(this.value - cost.getValue());
	}
	
	/**
	 * Multiplies one cost by another.
	 * 
	 * @param cost The cost to multiply by.
	 * @return the result of the multiplication.
	 */
	public Cost multiply(Cost cost) {
		return new Cost(this.value * cost.getValue());
	}
	
	public Cost multiply(int multiplier) {
		return new Cost(this.value * multiplier);
	}
	
	/**
	 * Divides one cost by another.
	 * 
	 * @param cost The cost to divide by.
	 * @return the result of the division.
	 */
	public Cost divide(Cost cost) {
		return new Cost(this.value / cost.getValue());
	}
	
	/**
	 * Calculates and returns the percentage of this cost. 
	 * 
	 * @param percent The percent to be calculated.
	 * @return The percentage of the this cost.
	 */
	public int calculatePercentage(int percent) {
		return ((this.getValue() * 100) * percent)/10000; 
	}
	
	/**
	 * Returns the string representation of this cost by converting the integer
	 * value to dollars and cents and adding the appropriate currency sign and
	 * decimal.
	 */
	@Override
	public String toString() {
		if (value != 0) {
			String valueString = Integer.toString(value);
			String dollars = valueString.substring(0, valueString.length() - 2);
			String cents = valueString.substring(dollars.length());
			return '$' + dollars + '.' + cents;
		}
		else return "$0.00";
	}
}
