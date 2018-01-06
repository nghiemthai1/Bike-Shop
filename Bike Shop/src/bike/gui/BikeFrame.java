package bike.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * A frame to hold the Bike Shop User Interface.
 * 
 * @author Eugene Koval
 */
public class BikeFrame extends JFrame implements Display {
	
	// Serial Version Number.
	private static final long serialVersionUID = 1L;
	// The frame's minimum size.
	private static final Dimension MINIMUM_SIZE = new Dimension(800, 800);
	
	/**
	 * Constructs a frame that will display a user interface based on the store
	 * passed as the argument.
	 * 
	 * @param shop the shop for which to make the frame.
	 */
	public BikeFrame(String title) {
		super(title);
		setUpFrame();
	}
	
	/**
	 * Sets the frame's basic properties.
	 */
	private void setUpFrame() {
		setMinimumSize(MINIMUM_SIZE);
		setExtendedState(MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		setIconImage(ImagePanel.createSystemImage("logo.png"));
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
	}
	
	public void accept(View view) {
		getContentPane().removeAll();
		view.visit(this);
		repaint();
		revalidate();
	}
}