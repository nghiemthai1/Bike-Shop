package bike.customerRecords;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import bike.products.Cost;

public class Cart implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<Order> orders;
	
	public Cart() {
		orders = new LinkedList<Order>();
	}
	
	public List<Order> getOrders() {
		return orders;
	}
	
	public void addOrder(Order order) {
		orders.add(order);
	}
	
	public void removeOrder(Order order) {
		orders.remove(order);
	}
	
	public Cost getGrandTotal() {
		Cost grandTotal = new Cost(0,0);
		for (Order order : orders) {
			grandTotal = grandTotal.add(order.getTotal());
		}
		return grandTotal;
	}
}
