package bike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;

public class BikeButton extends JButton {
	
	private static final long serialVersionUID = 1L;
	
	public BikeButton(String text, Font font, Color color) {
		super(text);
		setBackground(color);
		setForeground(Color.WHITE);
		setFont(font);
		setPreferredSize(new Dimension(200, 50));
	}
}
