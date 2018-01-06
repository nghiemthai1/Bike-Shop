package bike.gui;

import java.awt.Dimension;
import java.awt.Frame;
import java.util.stream.Stream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import bike.customerRecords.Customer;
import bike.inventory.Inventory;
import bike.products.Product;

public class ProductsDisplayPanel extends JPanel {

	FilterPanel pnlFilter;

	private Stream<Product> stream;
	
	private static final long serialVersionUID = 1L;
	
	public ProductsDisplayPanel() {
		setOpaque(false);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	}
	
	public void addProductsFromStream( Stream<Product> stream, Customer customer, Frame frame){

		this.stream = stream;
		stream.forEach(product -> {
	    	add(new ProductShoppingPanel(product, customer, frame));
	    	add(Box.createRigidArea(new Dimension(0, 80)));
	    });
	}
	
	
	public void addProductsFromInventory(Inventory inventory, Customer customer, Frame frame) {
		pnlFilter = new FilterPanel(inventory, customer, frame, this);
	    pnlFilter.getFilter()
	    .forEach(product -> {
	    	add(new ProductShoppingPanel(product, customer, frame));
	    	add(Box.createRigidArea(new Dimension(0, 80)));
	    });
	}
	
	public void addProductsFromCart(Customer customer, Inventory inventory) {
		customer.getCart()
				.getOrders()
				.forEach(order -> add(new ProductCartPanel(customer, order, inventory)));
	}
}
