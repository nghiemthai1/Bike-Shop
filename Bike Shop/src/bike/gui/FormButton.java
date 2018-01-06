package bike.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class FormButton extends JButton {
	
	public static final Color BACKGROUND = Color.WHITE;
	public static final Color FOREGROUND = Color.blue;
	public static final Font FONT = new Font("Dialog", Font.BOLD, 20);
	
	public FormButton() {
		super();
	}
	
	public FormButton(String text) {
		super(text);
	}
	
	@Override
	public Color getBackground() {
		return BACKGROUND;
	}
	
	@Override 
	public Color getForeground() {
		return FOREGROUND;
	}
	
	@Override
	public Font getFont() {
		return FONT;
	}
}
