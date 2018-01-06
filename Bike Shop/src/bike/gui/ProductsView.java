package bike.gui;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import bike.inventory.Shop;

public class ProductsView extends BikeView {
	

	private ImagePanel pnlBackground = new ImagePanel();
	private HeaderPanel pnlHeader = new HeaderPanel();
	private ProductsDisplayPanel pnlDisplay = new ProductsDisplayPanel();
	private FilterPanel pnlFilter;
	
	public ProductsView(Shop shop) {
		super(shop);
		pnlFilter = new FilterPanel(shop.getInventory(), shop.getCustomer(), getFrame(), pnlDisplay);
		setUpView();
		addScrollPane();
	}
	
	private void setUpView() {
		pnlBackground.setImage(ImagePanel.createSystemImage("background.jpg"));
		pnlBackground.setLayout(new BorderLayout());
		pnlBackground.add(pnlHeader, BorderLayout.NORTH);
		pnlBackground.add(pnlDisplay, BorderLayout.CENTER);
		pnlBackground.add(pnlFilter, BorderLayout.WEST);
		JButton btnSignIn = pnlHeader.getSignInButton();
		if (getShop().hasCustomer()) {
			btnSignIn.setText("Hello, " + getShop().getCustomer().getFirstName());
		}
		else {
			btnSignIn.setText("Sign In");
			pnlHeader.getSignInButton().setBorder(BorderFactory.createDashedBorder(null, 10f, 5f));

		}
	}
	
	private void addScrollPane() {
		JScrollPane scroll = new JScrollPane(pnlDisplay);
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		pnlBackground.add(scroll);
	}
	
	public void addViewTransitions() {
		BikeFrame frame = getFrame();
		Shop shop = getShop();
		pnlHeader.getSignInButton().addActionListener(event -> {
			if (shop.hasCustomer()) {
				frame.accept(new CustomerProfileView(shop));
			}
			else frame.accept(new LogInView(shop));
		});
		pnlHeader.getBackButton().addActionListener(event -> frame.accept(new GreetingView(shop)));
		pnlHeader.getCartButton().addActionListener(event -> {
			if (shop.getCustomer() == null) {
				JOptionPane.showMessageDialog(null, "You must be signed in to view the cart.", "Please sign in", JOptionPane.WARNING_MESSAGE);
			}
			else {
				frame.accept(new CartView(shop));
			}
		});
	}
	
	public void displayView() {
		Shop shop = getShop();
		pnlDisplay.addProductsFromInventory(shop.getInventory(), shop.getCustomer(), getFrame());
		getFrame().add(pnlBackground);
	}
}
