package bike.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

import bike.customerRecords.Customer;
import bike.customerRecords.Email;
import bike.customerRecords.IllegalEmailException;
import bike.inventory.Shop;

public class LogInView extends BikeView {
	
	private LoginPanel pnlLogin = new LoginPanel();
	
	public LogInView(Shop shop) {
		super(shop);
		pnlLogin.getSignInButton().addActionListener(event -> logIn());
	}
	
	private void logIn() {
		String emailString = pnlLogin.getEmailEntry();
		Customer customer = null;
		Email email = null;
		try {
			email = new Email(emailString);
		}
		catch (IllegalEmailException ex) {
			if (! emailString.isEmpty()) {
			JOptionPane.showMessageDialog(null, emailString + " is not a real email.", "Invalid Email", JOptionPane.ERROR_MESSAGE);
			}
		}
		if (email != null) {
			String username = email.getUsername();
			try {
				Customer potentialC = Customer.loadCustomerState(username);
				if (potentialC.getEmail().getDomain().equals(email.getDomain())) {
					if (potentialC.getPassword().equals(pnlLogin.getPasswordEntry())) {
						customer = potentialC;
					}
					else {
						JOptionPane.showMessageDialog(null, "The password for " + username + " is incorrect.",
								"Invalid Password", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					showInvalidEmailError(emailString);
				}
			}
			catch (FileNotFoundException ex) {
				showInvalidEmailError(emailString);
			}
		}
		if (customer == null) pnlLogin.resetEntries();
		else {
			getShop().setCustomer(customer);
			getFrame().accept(new ProductsView(getShop()));
		}
	}
	
	private void showInvalidEmailError(String emailString) {
		JOptionPane.showMessageDialog(null, "We cannot find an account registered under: " + emailString,
				"Invalid Email", JOptionPane.ERROR_MESSAGE);
	}
	
	public void addViewTransitions() {
		BikeFrame frame = getFrame();
		pnlLogin.getRegisterLabel().addMouseListener(new MouseAdapter() {
			@Override 
			public void mousePressed(MouseEvent e) {
				frame.accept(new RegisterView(getShop()));
			}
		});
		pnlLogin.getBackLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				frame.accept(new ProductsView(getShop()));
			}
		});
	}
	
	public void displayView() {
		getFrame().add(pnlLogin);
	}
}
