package bike.gui;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class DisplayTextArea extends JTextArea {
	
	public DisplayTextArea(String title, Font font) {
		setEditable(false);
		setFont(font);
		setBorder(new TitledBorder(title));
		TitledBorder border = (TitledBorder) getBorder();
		border.setTitleFont(font);
	}
}
