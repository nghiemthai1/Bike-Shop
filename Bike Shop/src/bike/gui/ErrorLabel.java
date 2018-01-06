package bike.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ErrorLabel extends JLabel {
	
	public static final Font FONT = new Font("Dialog", Font.ITALIC, 18);
	public static final Color COLOR = new Color(240, 116, 40);
	
	public ErrorLabel() {
		super();
	}
	
	public void reset() {
		setText("");
	}
	
	public void setErrorText(String errorText) {
		setText("!! " + errorText);
	}
	
	@Override
	public Font getFont() {
		return FONT;
	}
	
	public Color getForeground() {
		return COLOR;
	}

}
