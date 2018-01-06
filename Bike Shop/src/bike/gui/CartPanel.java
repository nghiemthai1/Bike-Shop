package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import bike.customerRecords.Customer;
import bike.inventory.Shop;

@SuppressWarnings("serial")
public class CartPanel extends DisplayPanel {
	
	private Shop shop;
	private Customer customer;
	private ProductsDisplayPanel pnlProducts = new ProductsDisplayPanel();
	private HeaderButton btnBack = new HeaderButton("<<", font);
	private HeaderButton btnRefresh = new HeaderButton("Refresh", font);
	private HeaderButton btnCheckout = new HeaderButton("Checkout", font);
	private HeaderButton btnEmpty = new HeaderButton("Empty Cart", font);
	private JLabel lblTotal = new JLabel();
	
	public CartPanel(Shop shop) {
		super(20);
		this.shop = shop;
		this.customer = shop.getCustomer();
		setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());
		this.addComponent();
		this.addScrollPane();
	}

	private void addComponent() {	
		//Make Top Panel
		BorderPanel pnlHeader = new BorderPanel(20);
		pnlHeader.setTotalBackground(Color.WHITE);
		pnlHeader.addContent(createHeaderPanel(customer.getFirstName() + "'s Cart"), BorderLayout.EAST);
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(btnBack);
		buttonBox.add(btnRefresh);
		pnlHeader.addContent(buttonBox, BorderLayout.WEST);
		
		//Make Center Panel
		if (customer.getCart().getOrders().isEmpty()) {
			pnlProducts.add(createMissingLabel("No products to show at this time."));
		}
		else pnlProducts.addProductsFromCart(customer, shop.getInventory());
		
		//Make Bottom Panel
		lblTotal.setFont(font);
		BorderPanel pnlCheckout = new BorderPanel(20);
		JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlTotal.add(lblTotal);
		lblTotal.setText("Grand Total: " + customer.getCart().getGrandTotal().toString());
		pnlTotal.setBackground(Color.WHITE);
		pnlCheckout.setTotalBackground(Color.WHITE);
		pnlCheckout.addContent(btnCheckout, BorderLayout.EAST);
		pnlCheckout.addContent(btnEmpty, BorderLayout.WEST);
		pnlCheckout.addContent(pnlTotal, BorderLayout.CENTER);
		
		//Add Components
		this.add(pnlHeader, BorderLayout.NORTH);
		this.add(pnlProducts, BorderLayout.CENTER);
		this.add(pnlCheckout, BorderLayout.SOUTH);
	}
	
	private void addScrollPane() {
		JScrollPane scroll = new JScrollPane(pnlProducts);
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		add(scroll);
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getRefreshButton() {
		return btnRefresh;
	}
	
	public JButton getCheckoutButton() {
		return btnCheckout;
	}
	
	public JButton getEmptyButton(){
		return btnEmpty;
	}
}