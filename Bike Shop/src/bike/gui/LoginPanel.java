package bike.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends CenteredContentPanel {
	
	private static final long serialVersionUID = 1L;
	private static final int SPACING = 20;
	private static final Color BACKGROUND = Color.WHITE;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	private JButton btnSignIn = new JButton("Sign In");
	private JLabel lblRegister = new JLabel("    New Visitor?");
	private JLabel lblBack = new JLabel("    <-- Go Back");
	
	public LoginPanel() {
		super(20);
		setBackground(BACKGROUND);
		setContentBackground(BACKGROUND);
		addComponents();
	}

	private void addComponents() {
		List<JComponent> compList = new ArrayList<JComponent>();
		
		ImagePanel pnlLogo = new ImagePanel();
		pnlLogo.setImage(ImagePanel.createSystemImage("logo.png"));
		compList.add(pnlLogo);
		pnlLogo.adjustSize(300, 300, true);
		addContent(pnlLogo, 200);
		
		EntryPanel pnlEmail = new EntryPanel("Email Address:", EntryPanel.VERTICAL);
		compList.add(pnlEmail);
		txtEmail = pnlEmail.createField();
		addContent(pnlEmail, SPACING);
		
		EntryPanel pnlPassword = new EntryPanel("Password:", EntryPanel.VERTICAL);
		compList.add(pnlPassword);
		txtPassword = pnlPassword.createPasswordField();
		addContent(pnlPassword, SPACING);
		
		JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.CENTER));
		compList.add(pnlButton);
		compList.add(btnSignIn);
		btnSignIn.setFont(font);
		btnSignIn.setForeground(Color.blue);
		pnlButton.add(btnSignIn);
		addContent(pnlButton, 10);
		
		lblRegister.setFont(new Font("Dialog", Font.ITALIC, 18));
		lblRegister.setForeground(Color.blue);
		addContent(lblRegister, 10);
		
		lblBack.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblBack.setForeground(Color.gray);
		addContent(lblBack, 80);

		compList.forEach(component -> component.setBackground(BACKGROUND));
		
		addContent(Box.createVerticalGlue(), 0);
	}
	
	public JLabel getBackLabel() {
		return lblBack;
	}
	
	public JLabel getRegisterLabel() {
		return lblRegister;
	}
	
	public JButton getSignInButton() {
		return btnSignIn;
	}
	
	public String getEmailEntry() {
		return txtEmail.getText();
	}
	
	public String getPasswordEntry() {
		return new String(txtPassword.getPassword());
	}
	
	public void resetEntries() {
		txtEmail.setText("");
		txtPassword.setText("");
	}
}
