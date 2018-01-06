package bike.gui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class RegisterPanel extends CenteredContentPanel {
	
	private static final int SPACING = 40;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtPhone;
	private JTextField txtAge;
	private JPasswordField txtPassword;
	private JPasswordField txtPasswordTwo;
	private JButton btnRegister;
	private JLabel lblError = new JLabel();
	private JLabel lblBack = new JLabel("               <-- Go Back");

	public RegisterPanel() {
		super(20);
		setBackground(Color.WHITE);
		setContentBackground(Color.WHITE);
		addComponents();
	}
	
	private void addComponents() {
		List<JComponent> compList = new ArrayList<JComponent>();
		ImagePanel imgLogo = new ImagePanel();
		JPanel pnlAccount = DisplayPanel.createHeaderPanel("Create account");
		EntryPanel pnlName = new EntryPanel("Your name", EntryPanel.VERTICAL);
		EntryPanel pnlEmail = new EntryPanel("Email", EntryPanel.VERTICAL);
		EntryPanel pnlPhone = new EntryPanel("Phone", EntryPanel.VERTICAL);
		EntryPanel pnlAge = new EntryPanel("Age", EntryPanel.VERTICAL);
		EntryPanel pnlPassword = new EntryPanel("Password", EntryPanel.VERTICAL);
		EntryPanel pnlPasswordTwo = new EntryPanel("Password again", EntryPanel.VERTICAL);
		setUpImage(imgLogo);
		setUpButtons();
		setUpErrorLabel();
		compList.add(imgLogo);
		compList.add(pnlAccount);
		compList.add(pnlName);
		compList.add(pnlEmail);
		compList.add(pnlPhone);
		compList.add(pnlAge);
		compList.add(pnlPassword);
		compList.add(pnlPasswordTwo);
		compList.add(btnRegister);
		compList.add(lblError);
		compList.add(lblBack);
		compList.forEach(component -> addContent(component, SPACING));
		txtName = pnlName.createField(30);
		txtEmail = pnlEmail.createField();
		txtPhone = pnlPhone.createField();
		txtAge = pnlAge.createField();
		txtPassword = pnlPassword.createPasswordField();
		txtPasswordTwo = pnlPasswordTwo.createPasswordField();
	}

	private void setUpImage(ImagePanel imgLogo) {
		imgLogo.setImage(ImagePanel.createSystemImage("logo.png"));
		imgLogo.adjustSize(100, 100, true);
		imgLogo.setBackground(Color.WHITE);
		JLabel lblHello = new JLabel("Acme Bike Rentals");
		lblHello.setFont(font);
		imgLogo.add(lblHello);
	}
	
	private void setUpButtons() {
		btnRegister = new JButton("Create your Acme Account");
		btnRegister.setBackground(Color.WHITE);
		btnRegister.setForeground(Color.blue);
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 20));
		lblBack.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblBack.setForeground(Color.gray);
	}
	
	private void setUpErrorLabel() {
		lblError.setFont(new Font("Dialog", Font.ITALIC, 18));
		lblError.setForeground(new Color(240, 116, 40));
	}
	
	public String getNameText() {
		return txtName.getText().trim();
	}

	public String getEmailText() {
		return txtEmail.getText().trim();
	}
	
	public String getPhoneText() {
		return txtPhone.getText().trim();
	}

	public String getAgeText() {
		return txtAge.getText().trim();
	}

	public String getPasswordText() {
		return new String(txtPassword.getPassword()).trim();
	}

	public String getPasswordTwoText() {
		return new String(txtPasswordTwo.getPassword()).trim();
	}

	public JButton getRegisterButton() {
		return btnRegister;
	}
	
	public JLabel getBackLabel() {
		return lblBack;
	}
	
	public void setErrorText(String text) {
		lblError.setText(text);
	}
}
