package bike.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bike.customerRecords.Address;
import bike.customerRecords.Country;
import bike.customerRecords.CreditCardDetails;
import bike.customerRecords.Customer;
import bike.customerRecords.State;

@SuppressWarnings("serial")
public class EditProfilePanel extends DisplayPanel {
	
	private static final int SPACING = 20;
	private Customer customer;
	private CenteredContentPanel pnlContent;
	private EntryPanel pnlCardExp = new EntryPanel("Card Exp:   ", EntryPanel.HORIZONTAL);
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAge;
	private JTextField txtStreet;
	private JTextField txtCity;
	private BikeComboBox<State> states = new BikeComboBox<State>(State.values());
    private BikeComboBox<Country> countries = new BikeComboBox<Country>(Country.values());
    private JTextField txtZip;
    private JTextField txtCardNum;
    private BikeComboBox<Integer> cbxMonth = new BikeComboBox<Integer>();
    private BikeComboBox<Integer> cbxYear = new BikeComboBox<Integer>();
	private ErrorLabel lblError = new ErrorLabel();
	private FormButton btnSave = new FormButton("Save Changes");
	private FormButton btnBack = new FormButton("Back to Profile");
	
	public EditProfilePanel(Customer customer) {
		super(20);
		this.customer = customer;
		setUpContentPanel();
		setUpPanel();
		setExistingFields();
	}
	
	private void setUpContentPanel() {
		pnlContent = new CenteredContentPanel(font.getSize());
		pnlContent.setBackground(Color.WHITE);
		pnlContent.setContentBackground(Color.WHITE);
	}
	
	private void setUpPanel() {
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setBackground(Color.WHITE);
		setUpHeader();
		setUpComboBoxes();
		addComponents();
	}
	
	private void setUpHeader() {
		JPanel pnlHeader = DisplayPanel.createHeaderPanel("Edit " + customer.getFirstName() + "'s Profile");
		pnlContent.addContent(pnlHeader, 30);
	}
	
	private void setUpComboBoxes() {
		for (int i = 1; i <= 12; i++) {
			cbxMonth.addItem(i);
		}
		pnlCardExp.add(cbxMonth);
		JLabel lblSlash = new JLabel("/");
		lblSlash.setFont(font);
		pnlCardExp.add(lblSlash);
		int stopYear = Calendar.getInstance().get(Calendar.YEAR) - 1;
		int startYear = stopYear + 10;
		for (int i = startYear; i != stopYear; i--) {
			cbxYear.addItem(i);
		}
		pnlCardExp.add(cbxYear);
	}
	
	private void addComponents() {
		ArrayList<JComponent> compList = new ArrayList<JComponent>();
		
		EntryPanel pnlFirstName = new EntryPanel("First Name:", EntryPanel.HORIZONTAL);
		EntryPanel pnlLastName = new EntryPanel("Last Name:", EntryPanel.HORIZONTAL);
		EntryPanel pnlEmail = new EntryPanel("Email:        ", EntryPanel.HORIZONTAL);
		EntryPanel pnlPhone = new EntryPanel("Phone:      ", EntryPanel.HORIZONTAL);
		EntryPanel pnlAge = new EntryPanel("Age:          ", EntryPanel.HORIZONTAL);
		EntryPanel pnlStAddrs = new EntryPanel("Street:       ", EntryPanel.HORIZONTAL);
		EntryPanel pnlCity = new EntryPanel("City:          ", EntryPanel.HORIZONTAL);
		EntryPanel pnlState = new EntryPanel("State:        ", EntryPanel.HORIZONTAL);
		EntryPanel pnlCountry = new EntryPanel("Country:    ", EntryPanel.HORIZONTAL);
		EntryPanel pnlZip = new EntryPanel("Zip Code:   ", EntryPanel.HORIZONTAL);
		EntryPanel pnlCardNumber = new EntryPanel("Card #:      ", EntryPanel.HORIZONTAL);

		compList.add(pnlFirstName);
		compList.add(pnlLastName);
		compList.add(pnlEmail);
		compList.add(pnlPhone);
		compList.add(pnlAge);
		compList.add(pnlStAddrs);
		compList.add(pnlCity);
		compList.add(pnlState);
		compList.add(pnlCountry);
		compList.add(pnlZip);
		compList.add(pnlCardNumber);
		compList.add(pnlCardExp);
		compList.add(lblError);
		compList.add(btnSave);
		compList.add(btnBack);
		compList.forEach(comp -> pnlContent.addContent(comp, SPACING));
		add(pnlContent);

		txtFirstName = pnlFirstName.createField(30);
		txtLastName = pnlLastName.createField(30);
		txtEmail = pnlEmail.createField(30);
		txtPhone = pnlPhone.createField(30);
		txtAge = pnlAge.createField(3);
		txtStreet = pnlStAddrs.createField(30);
		txtCity =pnlCity.createField(30);
		pnlState.add(states);
		pnlCountry.add(countries);
		txtZip = pnlZip.createField(8);
		txtCardNum = pnlCardNumber.createField(30);
	}
	
	private void setExistingFields() {
		txtFirstName.setText(customer.getFirstName());
		txtLastName.setText(customer.getLastName());
		txtEmail.setText(customer.getEmail().getEmailAddress());
		txtPhone.setText(customer.getPhone());
		txtAge.setText(Integer.toString(customer.getAge()));
		Address address = customer.getAddress();
		if (address != null) {
			txtStreet.setText(address.getStreetAddress());
			txtCity.setText(address.getCity());
			states.setSelectedItem(address.getState());
			countries.setSelectedItem(address.getCountry());
			txtZip.setText(address.getZipCode());
		}
		CreditCardDetails card = customer.getCardDetails();
		if (card != null) {
			txtCardNum.setText(card.getCardNumber());
			cbxMonth.setSelectedItem(card.getExpirationMonth());
			cbxYear.setSelectedItem(card.getExpirationYear());
		}
	}
	
	public String getFirstName() {
		return txtFirstName.getText();
	}
	
	public String getLastName() {
		return txtLastName.getText();
	}
	
	public String getEmail() {
		return txtEmail.getText();
	}
	
	public String getPhone() {
		return txtPhone.getText();
	}
	
	public String getAge() {
		return txtAge.getText();
	}
	
	public String getStreet() {
		return txtStreet.getText();
	}
	
	public String getCity() {
		return txtCity.getText();
	}
	
	public State getState() {
		return (State) states.getSelectedItem();
	}
	
	public Country getCountry() {
		return (Country) countries.getSelectedItem();
	}
	
	public String getZip() {
		return txtZip.getText();
	}
	
	public String getCardNumber() {
		return txtCardNum.getText();
	}
	
	public Integer getExpMonth() {
		return (Integer) cbxMonth.getSelectedItem();
	}
	
	public Integer getExpYear() {
		return (Integer) cbxYear.getSelectedItem();
	}
	
	public FormButton getSaveButton() {
		return btnSave;
	}
	
	public FormButton getBackButton() {
		return btnBack;
	}
	
	public void showError(String errorText) {
		lblError.setErrorText(errorText);
	}
	
	public void resetError() {
		lblError.reset();
	}
}
