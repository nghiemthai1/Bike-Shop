package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextPane;

import bike.customerRecords.Address;
import bike.customerRecords.CreditCardDetails;
import bike.customerRecords.Customer;

@SuppressWarnings("serial")
public class CustomerProfilePanel extends DisplayPanel {
	
	private static final int SPACING = 30;
	private Customer customer;
	private JLabel lblEdit = new JLabel("Edit Profile");
	private JLabel lblBack = new JLabel("<-- Back");
	private JLabel lblLogOut = new JLabel("Log Out");
	
	public CustomerProfilePanel(Customer customer) {
		super(20);
		this.customer = customer;
		setUpPanel();
	}

	private void setUpPanel() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		setUpLeftPanel();
		setUpRightPanel();
		setUpCenterPanel();
	}
		
	private void setUpLeftPanel() {
		CenteredContentPanel pnlStore = new CenteredContentPanel(25);
		pnlStore.setBackground(Color.WHITE);
		pnlStore.setContentBackground(Color.WHITE);
		ImagePanel imgLogo = new ImagePanel();
		imgLogo.setBackground(Color.WHITE);
		imgLogo.setImage(ImagePanel.createSystemImage("logo.png"));
		imgLogo.adjustSize(120, 120, true);
		pnlStore.addContent(imgLogo, 20);
		JTextPane txtName = new JTextPane();
		txtName.setEditable(false);
		txtName.setText(customer.getFirstName() + "'s \nBike Shop");
		txtName.setFont(pnlStore.font);
		pnlStore.addContent(txtName, 0);
		add(pnlStore, BorderLayout.WEST);
	}
	
	private void setUpRightPanel() {
		CenteredContentPanel pnlMenu = new CenteredContentPanel(20);
		pnlMenu.setBackground(Color.WHITE);
		pnlMenu.setContentBackground(Color.WHITE);
		
		lblEdit.setFont(pnlMenu.font);
		lblEdit.setForeground(Color.gray);
		pnlMenu.addContent(lblEdit, 250);
		
		lblLogOut.setFont(pnlMenu.font);
		lblLogOut.setForeground(Color.gray);
		pnlMenu.addContent(lblLogOut, 80);
		
		lblBack.setFont(pnlMenu.font);
		lblBack.setForeground(Color.gray);
		pnlMenu.addContent(lblBack, 80);
		
		add(pnlMenu, BorderLayout.EAST);
	}
	
	private void setUpCenterPanel() {
		CenteredContentPanel pnlInfo = new CenteredContentPanel(30);
		pnlInfo.setContentBackground(Color.WHITE);
		pnlInfo.setBackground(Color.WHITE);
		/*-----------------------*/
		/*     Personal Panel    */
		/*-----------------------*/
		DisplayTextArea txtPerson = new DisplayTextArea("Personal Information", font);
		txtPerson.setText(
				      "\n    Name:\t    " + customer.getFirstName() + " " + customer.getLastName() + "    " +
				    "\n\n    Email:\t    " + customer.getEmail().getEmailAddress() + "    " +
				    "\n\n    Phone:\t    " + customer.getPhone() + "    " +
				    "\n\n    Age:\t    " + customer.getAge() + "    \n"
				   );
		pnlInfo.addContent(txtPerson, 60);
		/*-----------------------*/
		/*    Address Panel      */
		/*-----------------------*/
		if (customer.getAddress() != null) {
			Address adrs = customer.getAddress();
			DisplayTextArea txtAddress = new DisplayTextArea("Billing Address", font);
			txtAddress.setText(
					       "\n    Street:\t    " + adrs.getStreetAddress() + "    " +
					     "\n\n    City:\t    " + adrs.getCity() + "    " +
					     "\n\n    State:\t    " + adrs.getState() + "    " +
					     "\n\n    Country:\t    " + adrs.getCountry() + "    " + 
					     "\n\n    Zip Code:\t    " + adrs.getZipCode() + "    \n"
					     );
			pnlInfo.addContent(txtAddress, SPACING);
		}
		else {
			pnlInfo.addContent(createMissingLabel("You have not entered an address."), SPACING);
		}
		/*-----------------------*/
		/*       Card Panel      */
		/*-----------------------*/
		if (customer.getCardDetails() != null) {
			CreditCardDetails card = customer.getCardDetails();
			DisplayTextArea txtCard = new DisplayTextArea("Card Details", font);
			txtCard.setText(
					    "\n    Card Number:      " + card.getHiddenCardNumber() + "    " +
			          "\n\n    Expiration Date:      " + card.getExpirationMonth() + "/" + card.getExpirationYear() + "    \n"
			          );
			pnlInfo.addContent(txtCard, SPACING);
		}
		else {
			pnlInfo.addContent(createMissingLabel("You have not entered any card details."), SPACING);
		}
		add(pnlInfo, BorderLayout.CENTER);
	}
	
	public JLabel getEditButton() {
		return lblEdit;
	}
	
	public JLabel getLogOutButton() {
		return lblLogOut;
	}
	
	public JLabel getBackButton() {
		return lblBack;
	}
}