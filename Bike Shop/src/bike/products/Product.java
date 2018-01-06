package bike.products;

import java.io.Serializable;

/**
 * A class to represent a product sold at the shop. All products may be rented
 * out for an hourly fee.
 * 
 * @author Eugene Koval
 */
public abstract class Product implements Rentable, Serializable {
	
	private static final long serialVersionUID = 1L;
	// The product type
	private String type;
	// A unique product number.
	private String number;
	// The maker of this product.
	private String make;
	// This product's model.
	private String model;
	// The manufacturer's price for this product.
	private Cost wholeSaleCost;
	// Safety deposit for the rental. Covers possible damages to the product.
	private Cost rentalDeposit;
	// The hourly fee for renting this product.
	private Cost rentalCharge;
	// The percentage of the wholesale price used to calculate deposit.
	static final int DEPOSIT_PERCENTAGE = 10;
	// The percentage of the wholesale price used to calculate hourly fee.
	static final int HOURLY_PERCENTAGE = 5;

	/**
	 * Constructs a product with a manufacturer's price. This price is then
	 * used to calculate and set the rental deposit and hourly rental charge.
	 * 
	 * @param wholeSale The manufacturer's price for this product.
	 */
	public Product(Cost wholeSale, String type, String productNumber, String make, String model) {
		wholeSaleCost = wholeSale;
		rentalDeposit = calculateDeposit(wholeSale);
		rentalCharge = calculateHourlyFee(wholeSale);
		this.type = type;
		this.number = productNumber;
		this.make = make;
		this.model = model;
	}
	
	/**
	 * Constructs a new Product with the given array of parameters.
	 * Parameters must be of the same data types as the product's constructor.
	 * 
	 * @param parameters - the parameters with which to construct the product. 
	 * @return the new product.
	 */
	public abstract Product createProduct(String[] parameters);
	
	public Cost calculateDeposit(Cost cost) {
		return new Cost(cost.calculatePercentage(DEPOSIT_PERCENTAGE));
	}
	
	public Cost calculateHourlyFee(Cost cost) {
		return new Cost(cost.calculatePercentage(HOURLY_PERCENTAGE));
	}
	
	/**
	 * Returns the type of item this product happens to be.
	 * 
	 * @return this product's type.
	 */
	public String getProductType() {
		return type;
	}
	
	/**
	 * Returns this product's unique product number.
	 * 
	 * @return this product's number.
	 */
	public String getProductNumber() {
		return number;
	}
	
	/** 
	 * Returns the name of this product's manufacturer.
	 * 
	 * @return the product's maker.
	 */
	public String getMake() {
		return make;
	}
	
	/** 
	 * Returns this product's model.
	 * 
	 * @return the product's model.
	 */
	public String getModel() {
		return model;
	}
	
	/**
	 * Returns the manufacturer's price for this product.
	 * 
	 * @return the wholesale cost of this product.
	 */
	public Cost getWholeSaleCost() {
		return wholeSaleCost;
	}
	
	/**
	 * Returns the amount for the safety deposit for this product.
	 * 
	 * @return the amount for the rental deposit.
	 */
	public Cost getRentalDeposit() {
		return rentalDeposit;
	}
	
	/**
	 * Returns the hourly rental fee for this product.
	 * 
	 * @return the hourly rental fee.
	 */
	public Cost getRentalCharge() {
		return rentalCharge;
	}
	
	/**
	 * Returns a formatted string representation of this product.
	 * The string includes the product's make, model, and rental costs.
	 * 
	 * @return a formatted string representation of this product.
	 */
	@Override
	public String toString() {
		return make + " " + model + 
				"\n\n\tRental Charge : " + rentalCharge + " per hour" +
				"\n\n\tRental Deposit: " + rentalDeposit;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (! (obj instanceof Product)) {
			return false;
		}
		Product other = (Product) obj;
		return number.equals(other.getProductNumber());
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 37 * result + type.hashCode();
		result = 37 * result + number.hashCode();
		result = 37 * result + make.hashCode();
		result = 37 * result + model.hashCode();
		return result;
	}
}
