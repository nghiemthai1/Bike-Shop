package bike.gui;

import java.awt.Font;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

@SuppressWarnings("serial")
public class BikeSpinner extends JSpinner {
	
	public BikeSpinner(int initialValue, int min, int max, Font font) {
		super(new SpinnerNumberModel(initialValue, min, max, 1));
		setFont(font);
		((DefaultEditor) this.getEditor()).getTextField().setEditable(false);
	}
}