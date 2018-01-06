package bike.customerRecords;

import java.io.File;
import java.io.FileNotFoundException;

import bike.io.RecordsIO;

public class Customer extends Person {
	
	private static final long serialVersionUID = 1L;
	private String password;
	private Address billingAddress;
	private CreditCardDetails cardDetails;
	private Cart cart;

	public Customer(String firstName, String lastName, int age, String phone,
			Email email, String password) {
		super(firstName, lastName, age, phone, email);
		this.password = password;
		this.billingAddress = null;
		this.cardDetails = null;
		cart = new Cart();
	}
	
	public Customer(String firstName, String lastName, int age, String phone, 
			Email email, String password, Address billingAddress, 
			CreditCardDetails cardDetails) {
		super(firstName, lastName, age, phone, email);
		this.password = password;
		this.billingAddress = billingAddress;
		this.cardDetails = cardDetails;
	}

	public String getPassword() {
		return password;
	}

	public Address getAddress() {
		return billingAddress;
	}
	
	public CreditCardDetails getCardDetails() {
		return cardDetails;
	}
	
	public Cart getCart() {
		return cart;
	}
	
	public void setPassword(String password) {
		if (isValidPassword(password)) {
			this.password = password;
		}
		else throw new IllegalPasswordException(password);
	}
	
	public void setAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	public void setCardDetails(CreditCardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	
	public File saveCustomerState() {
		return super.savePersonState(RecordsIO.CUSTOMER_DIRECTORY);
	}
	
	public static Customer loadCustomerState(String emailUsername) throws FileNotFoundException {
		return (Customer) Person.loadPersonState(RecordsIO.CUSTOMER_DIRECTORY, emailUsername);
	}
	
	public static boolean isValidPassword(String password) {
		return password.length() >= 6;
	}
	
	public boolean hasPaymentInfo() {
		return (billingAddress != null) && (cardDetails != null);
	}
}
