package bike.gui;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class HeaderPanel extends BorderPanel {
	
	private HeaderButton btnBack = new HeaderButton("<<", font);
	private HeaderButton btnCart = new HeaderButton("Cart", font);
	private HeaderButton btnSignIn = new HeaderButton("", font);
	
	public HeaderPanel() {
		super(20);
		setTotalBackground(THEME_DARK);
		addComponents();
	}
	
	private void addComponents() {
		Box buttonBox = Box.createHorizontalBox();
		buttonBox.add(btnBack);
		buttonBox.add(btnCart);
		addContent(buttonBox, BorderLayout.WEST);
		addContent(btnSignIn, BorderLayout.EAST);
	}
	
	public JButton getSignInButton() {
		return btnSignIn;
	}
	
	public JButton getBackButton() {
		return btnBack;
	}
	
	public JButton getCartButton() {
		return btnCart;
	}
	
	
}