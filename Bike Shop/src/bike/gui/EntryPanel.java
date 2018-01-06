package bike.gui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EntryPanel extends DisplayPanel {

	public static final int VERTICAL = 0;
	public static final int HORIZONTAL = 1;
	public static final Font ENTRY_FONT = new Font("Dialog", Font.PLAIN, 15);
	
	public EntryPanel(String header, int orientation) {
		super(20);
		if (orientation == VERTICAL) {
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		}
		else if (orientation == HORIZONTAL) {
			setLayout(new FlowLayout(20));
		}
		setBackground(Color.WHITE);
		addHeader(header);
	}
	
	private void addHeader(String header) {
		JLabel lblHeader = new JLabel(header);
		lblHeader.setFont(font);
		add(lblHeader);
	}
	
	public JTextField createField() {
		JTextField txtEntry = new JTextField();
		txtEntry.setFont(ENTRY_FONT);
		return (JTextField) add(txtEntry);
	}
	
	public JTextField createField(int size) {
		JTextField txtEntry = new JTextField(size);
		txtEntry.setFont(ENTRY_FONT);
		return (JTextField) add(txtEntry);
	}
	
	public JPasswordField createPasswordField() {
		JPasswordField pswEntry = new JPasswordField();
		pswEntry.setFont(ENTRY_FONT);
		return (JPasswordField) add(pswEntry);
	}
	
	public JPasswordField createPasswordField(int size) {
		JPasswordField pswEntry = new JPasswordField(size);
		pswEntry.setFont(ENTRY_FONT);
		return (JPasswordField) add(pswEntry);
	}
}
