package bike.customerRecords;

import java.io.Serializable;

import bike.gui.RentDuration;
import bike.products.Buyable;
import bike.products.Cost;
import bike.products.Product;

@SuppressWarnings("serial")
public class Order implements Serializable {

	private Product product;
	private int quantity;
	private RentDuration duration;
	private Cost total;
	
	public Order(Product product, RentDuration duration) {
		this.product = product;
		this.duration = duration;
		if (duration != null) {
			determineRentalTotal();
		}
		else {
			total = ((Buyable) product).getRetailCost();
		}
		setQuantity(1);
	}
	
	public Product getProduct() {
		return product;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public final void setQuantity(int quantity) {
		this.quantity = quantity;
		total = total.multiply(quantity);
	}
	
	public RentDuration getDuration() {
		return duration;
	}
	
	public Cost getTotal() {
		return total;
	}
	
	public void determineRentalTotal() {
		Cost total = product.getRentalDeposit();
		this.total = total.add(product.getRentalCharge().multiply(duration.getDurationInHours()));
	}
}
