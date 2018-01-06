package bike.gui;

import bike.customerRecords.Address;
import bike.customerRecords.Country;
import bike.customerRecords.CreditCardDetails;
import bike.customerRecords.Customer;
import bike.customerRecords.Email;
import bike.customerRecords.IllegalEmailException;
import bike.customerRecords.Person;
import bike.customerRecords.State;
import bike.inventory.Shop;

public class EditProfileView extends BikeView {
	
	private EditProfilePanel pnlEdit;

	public EditProfileView(Shop shop) {
		super(shop);
		pnlEdit = new EditProfilePanel(getShop().getCustomer());
	}

	@Override
	public void addViewTransitions() {
		pnlEdit.getSaveButton().addActionListener(event -> {
			pnlEdit.resetError();
			if (saveProfileChanges()) backToProfile();
		});
		pnlEdit.getBackButton().addActionListener(event -> backToProfile());
	}
	
	private void backToProfile() {
		getFrame().accept(new CustomerProfileView(getShop()));
	}
	
	private boolean saveProfileChanges() {
		boolean allChangesSuccessful = true;
		Customer customer = getShop().getCustomer();
		String firstName = pnlEdit.getFirstName();
		String lastName = pnlEdit.getLastName();
		String emailString = pnlEdit.getEmail();
		String phone = pnlEdit.getPhone();
		String ageString = pnlEdit.getAge();
		String street = pnlEdit.getStreet();
		String city = pnlEdit.getCity();
		State state = pnlEdit.getState();
		Country country = pnlEdit.getCountry();
		String zip = pnlEdit.getZip();
		String cardNum = pnlEdit.getCardNumber();
		Integer expMonth = pnlEdit.getExpMonth();
		Integer expYear = pnlEdit.getExpYear();
		Address address;
		try {
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			customer.setPhone(phone);
			customer.setAge(Integer.parseInt(ageString));
			address = new Address(street, city, state, country, zip);
			customer.setAddress(address);
		}
		catch (NumberFormatException ex) {
			pnlEdit.showError("That is not a real age.");
		}
		catch (IllegalArgumentException ex) {
			pnlEdit.showError("There is something wrong with your "
		+ ex.getMessage() + ".");
			allChangesSuccessful = false;
		}
		Email email = null;
		try {
			email = new Email(emailString);
		}
		catch (IllegalEmailException ex) {
			pnlEdit.showError("That is not a real email.");
			allChangesSuccessful = false;
		}
		if (! Person.isValidPhone(phone)) {
			pnlEdit.showError("That is not a real phone.");
			allChangesSuccessful = false;;
		}
		customer.setEmail(email);
		CreditCardDetails card;
		if (CreditCardDetails.isValidNumber(cardNum)) {
			card = new CreditCardDetails(cardNum,expMonth, expYear);
			if (card.isExpired()) {
				pnlEdit.showError("Your card is expired.");
				allChangesSuccessful = false;
			}
			else {
				customer.setCardDetails(card);
			}
		}
		else {
			pnlEdit.showError("The card number... 12 digits, no spaces, starting with 4.");
			allChangesSuccessful = false;
		}
		customer.saveCustomerState();
		return allChangesSuccessful;
	}

	@Override
	public void displayView() {
		getFrame().add(pnlEdit);
	}
}