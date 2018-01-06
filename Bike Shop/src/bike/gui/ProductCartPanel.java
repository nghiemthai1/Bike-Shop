package bike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import bike.customerRecords.Customer;
import bike.customerRecords.Order;
import bike.inventory.Inventory;
import bike.products.Cost;

@SuppressWarnings("serial")
public class ProductCartPanel extends ProductPanel {

	public static final Color REMOVE_COLOR = new Color(222, 12, 12);
	private Customer customer;
	private Order order;
	private JPanel pnlQuantity = new JPanel(new FlowLayout(FlowLayout.CENTER));
	private BikeSpinner spnQuantity;
	private JLabel lblInfo  = new JLabel();
	private BikeButton btnRemove = new BikeButton("Remove", font, REMOVE_COLOR);
	
	public ProductCartPanel(Customer customer, Order order, Inventory inventory) {
		super(order.getProduct());
		this.customer = customer;
		this.order = order;
		int inInventory = inventory.getProducts().get(order.getProduct());
		spnQuantity = new BikeSpinner(order.getQuantity(), 1, inInventory, new Font("Dialog", Font.PLAIN, 15));
		setUpComponents();
		addListeners();
	}
	
	private void setUpComponents() {
		pnlQuantity.setBackground(Color.WHITE);
		JLabel lblQuantity = new JLabel("Quantity");
		spnQuantity.setPreferredSize(new Dimension(50, 40));
		spnQuantity.addChangeListener(event -> order.setQuantity((int) spnQuantity.getValue()));
		pnlQuantity.add(lblQuantity);
		pnlQuantity.add(spnQuantity);
		RentDuration duration = order.getDuration();
		Cost total = order.getTotal();
		lblInfo.setFont(font);
		if (duration != null) {
			lblInfo.setText("Rent for: " + duration.displayDuration() + " (Total: "+ total + ")");
		}
		else {
			lblInfo.setText("Purchase Total: " + total);
		}
		addComponents();
	}
	
	private void addComponents() {
		add(lblInfo);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(btnRemove);
		add(Box.createRigidArea(new Dimension(20, 0)));
		add(pnlQuantity);
		add(Box.createRigidArea(new Dimension(20, 0)));
	}
	
	private void addListeners() {
		btnRemove.addActionListener(event -> {
			customer.getCart().removeOrder(order);
			btnRemove.setText("Removed");
			btnRemove.setBackground(Color.gray);
			customer.saveCustomerState();
		});
	}
}
