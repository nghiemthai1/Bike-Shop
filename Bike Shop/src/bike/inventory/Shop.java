package bike.inventory;

import bike.customerRecords.Customer;

/**
 * A class to represent shop with an inventory of products and 
 * a customer.
 * 
 * @author Eugene Koval
 */
public class Shop {
	
	// The shop's inventory of products.
	private Inventory inventory;
	// The customer being served at the shop.
	private Customer currentCustomer;
	
	/**
	 * Constructs a shop with an empty inventory.
	 */
	public Shop() {
		inventory = new Inventory();
		currentCustomer = null;
	}
	
	/**
	 * Returns this shop's inventory.
	 * 
	 * @return this shop's inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}
	
	/**
	 * Returns this shop's current customer.
	 * 
	 * @return this shop's customer.
	 */
	public Customer getCustomer() {
		return currentCustomer;
	}
	
	/**
	 * Sets this shop's inventory to the one passed as an argument.
	 * 
	 * @param inventory - this shop's new inventory.
	 */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	/**
	 * Sets this shop's current customer to the one passed as an argument.
	 * 
	 * @param currentCustomer - this shop's new current customer.
	 */
	public void setCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}
	
	/**
	 * Returns true if the shop currently has a customer
	 * 
	 * @return true if customer is not null
	 */
	public boolean hasCustomer() {
		return currentCustomer != null;
	}
}
