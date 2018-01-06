package bike.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import bike.inventory.Shop;

public class CustomerProfileView extends BikeView {
	
	private CustomerProfilePanel pnlProfile;
	
	public CustomerProfileView(Shop shop) {
		super(shop);
		pnlProfile = new CustomerProfilePanel(getShop().getCustomer());
	}
	
	public void addViewTransitions() {
		pnlProfile.getEditButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getFrame().accept(new EditProfileView(getShop()));
			}
		});
		pnlProfile.getBackButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getFrame().accept(new ProductsView(getShop()));
			}
		});
		pnlProfile.getLogOutButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getShop().setCustomer(null);
				JOptionPane.showMessageDialog(null, "Thanks for stopping by!", 
						"Log Out Successful", JOptionPane.INFORMATION_MESSAGE);
				getFrame().accept(new GreetingView(getShop()));
			}
		});
	}
	
	public void displayView() {
		getFrame().add(pnlProfile);
	}
}
