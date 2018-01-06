package bike.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import bike.customerRecords.Customer;
import bike.customerRecords.Email;
import bike.customerRecords.IllegalEmailException;
import bike.customerRecords.Person;
import bike.inventory.Shop;

public class RegisterView extends BikeView {
	
	private RegisterPanel pnlRegister = new RegisterPanel();

	public RegisterView(Shop shop) {
		super(shop);
		addListeners();
	}
	
	private void addListeners() {
		pnlRegister.getRegisterButton().addActionListener(event -> {
			pnlRegister.setErrorText(""); // Reset Error Message
			createAccount();
		});
	}

	public void addViewTransitions() {
		pnlRegister.getBackLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				getFrame().accept(new LogInView(getShop()));
			}
		});
	}
	
	private void createAccount() {
		String nameString = pnlRegister.getNameText();
		String emailString = pnlRegister.getEmailText();
		String phoneString = pnlRegister.getPhoneText();
		String ageString = pnlRegister.getAgeText();
		String passwordString = pnlRegister.getPasswordText();
		String passwordTwoString = pnlRegister.getPasswordTwoText();
		String[] nameArray;
		if (Person.isValidFullName(nameString)){
			nameArray = nameString.split("\\s");
		}
		else {
			showError("That is not a real name.");
			return;
		}
		Email email;
		try {
			email = new Email(emailString);
		}
		catch (IllegalEmailException ex) {
			showError("That is not a real email.");
			return;
		}
		if (! Person.isValidPhone(phoneString)) {
			showError("That is not a real phone.");
			return;
		}
		int age;
		try {
			age = Integer.parseInt(ageString);
		}
		catch (NumberFormatException ex) {
			showError("That is not a real age.");
			return;
		}
		if (! Person.isValidAge(age)) {
			showError("That age seems unrealistic...");
			return;
		}
		if (! Customer.isValidPassword(passwordString)) {
			showError("The password must be at least 6 characters.");
			return;
		}
		if (! passwordString.equals(passwordTwoString)) {
			showError("The passwords do not match.");
			return;
		}
		Customer newCustomer = new Customer(
				nameArray[0], 
				nameArray[1], 
				age, 
				phoneString, 
				email,
				passwordString);
		newCustomer.saveCustomerState();
		Shop shop = getShop();
		getShop().setCustomer(newCustomer);
		getFrame().accept(new ProductsView(shop));
	}
	
	private void showError(String text) {
		pnlRegister.setErrorText("!!  " + text);
	}
	
	@Override
	public void displayView() {
		getFrame().add(pnlRegister);
	}
}
