package bike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;

import bike.customerRecords.Customer;
import bike.customerRecords.Order;
import bike.products.Buyable;
import bike.products.Product;
import bike.products.Rentable;

@SuppressWarnings("serial")
public class ProductShoppingPanel extends ProductPanel {

	public static final Color RENT_COLOR = new Color(0, 174, 249);
	public static final Color BUY_COLOR = new Color(103, 226, 12);
	private static final String IN_CART = "Added to Cart";
	private Frame frame;
	private Customer customer;
	private boolean hasProduct;
	private BikeButton btnRent = new BikeButton("Rent", font, RENT_COLOR);
	private BikeButton btnBuy = new BikeButton("Buy", font, BUY_COLOR);
	private RentListener lsnRent = new RentListener();
	private BuyListener lsnBuy = new BuyListener();
	
	public ProductShoppingPanel(Product product, Customer customer, Frame frame) {
		super(product);
		this.customer = customer;
		if (customer != null) {
			hasProduct = customer.getCart()
			                     .getOrders()
			                     .parallelStream()
			                     .anyMatch(order -> order.getProduct()
			                    		                 .equals(product));
			addShoppingButtons();
		}
	}

	private  void addShoppingButtons() {
		Product product = getProduct();
		if (product instanceof Buyable) {
			add(btnBuy);
			if (hasProduct) {
				btnBuy.setText(IN_CART);
			}
			else btnBuy.addActionListener(lsnBuy);
		}
		add(Box.createRigidArea(new Dimension(20, 0)));
		if (product instanceof Rentable) {
			add(btnRent);
			if (hasProduct) {
				btnRent.setText(IN_CART);
			}
			else btnRent.addActionListener(lsnRent);
		}
		add(Box.createRigidArea(new Dimension(20, 0)));
	}
	
	private void disableButtons() {
		btnBuy.setText(IN_CART);
		btnRent.setText(IN_CART);
		btnBuy.removeActionListener(lsnBuy);
		btnRent.removeActionListener(lsnRent);
		customer.saveCustomerState();
	}
	
	private class BuyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			customer.getCart().addOrder(new Order(getProduct(), null));
			disableButtons();
		}
		
	}
	
	private class RentListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			RentDuration duration = RentDialog.showRentDialog(frame, btnRent);
			if (! (duration == null || duration.isZero())) {
				customer.getCart().addOrder(new Order(getProduct(), duration));
				disableButtons();
			}
			// System.out.println(duration.getReturnTime().format(DateTimeFormatter.ofPattern("HH:mm | MM dd uuuu")));
		}
	}
}
