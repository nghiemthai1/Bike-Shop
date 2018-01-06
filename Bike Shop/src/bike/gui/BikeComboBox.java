package bike.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class BikeComboBox<E> extends JComboBox<E> {
	
	public static final Dimension SIZE = new Dimension(60, 30);
	public static final Color COLOR = Color.WHITE;
	public static final Font FONT = new Font("Dialog", Font.PLAIN, 15);
	
	public BikeComboBox() {
		super();
	}
	
	public BikeComboBox(E[] items) {
		super(items);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return SIZE;
	}
	
	@Override
	public Color getBackground() {
		return COLOR;
	}
	
	@Override 
	public Font getFont() {
		return FONT;
	}
}
