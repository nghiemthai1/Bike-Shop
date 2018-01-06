package bike.gui;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class HeaderButton extends JButton {
	
	public HeaderButton(String text, Font font) {
		super(text);
		setFont(font);
		setBackground(DisplayPanel.THEME_DARK);
		setForeground(DisplayPanel.FONT_DARK);
		setPreferredSize(new Dimension(200, 40));
	}
}
