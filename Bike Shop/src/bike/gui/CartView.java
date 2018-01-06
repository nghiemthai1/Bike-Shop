package bike.gui;

import javax.swing.JOptionPane;

import bike.inventory.Shop;

public class CartView extends BikeView {

	private CartPanel pnlCart;
	
	public CartView(Shop shop) {
		super(shop);
		pnlCart = new CartPanel(shop);
	}

	@Override
	public void addViewTransitions() {
		pnlCart.getBackButton().addActionListener(event -> backToProducts());
		pnlCart.getRefreshButton().addActionListener(event -> refreshPage());
		pnlCart.getCheckoutButton().addActionListener(event -> {
			if (getShop().getCustomer().hasPaymentInfo()) {
				String total = getShop().getCustomer().getCart().getGrandTotal().toString();
				emptyCart();
				backToProducts();
				getShop().getCustomer().saveCustomerState();
				JOptionPane.showMessageDialog(null, "Thank you for your order!\n" + total + " will be charged to your account.",
						"Order Successful", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				JOptionPane.showMessageDialog(null, "Your account is missing some payment information.",
						"Unable to Process Order", JOptionPane.WARNING_MESSAGE);
			}
		});
		pnlCart.getEmptyButton().addActionListener(event -> {
			int response = JOptionPane.showConfirmDialog(null, "You will lose all of your chosen products.\n"
					+ "Are you sure you want to continue?", "Empty Cart",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				emptyCart();
				refreshPage();
			}
		});
	}
	
	public void emptyCart() {
		getShop().getCustomer().getCart().getOrders().clear();
		
	}
	
	public void refreshPage() {
		getFrame().accept(new CartView(getShop()));
	}
	
	public void backToProducts() {
		getFrame().accept(new ProductsView(getShop()));
	}

	@Override
	public void displayView() {
		getFrame().add(pnlCart);
	}
}
