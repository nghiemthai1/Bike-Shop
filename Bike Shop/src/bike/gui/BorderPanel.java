package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BorderPanel extends DisplayPanel {

	private static final int SPACING = 10;
	private JPanel pnlContent = new JPanel(new BorderLayout());
	
	public BorderPanel(int fontSize) {
		super(fontSize);
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createRigidArea(new Dimension(0, SPACING)));
		add(pnlContent);
		add(Box.createRigidArea(new Dimension(0, SPACING)));
	}
	
	public void addContent(Component comp, Object constraints) {
		pnlContent.add(comp, constraints);
	}
	
	public void setTotalBackground(Color color) {
		setBackground(color);
		pnlContent.setBackground(color);
	}
}
