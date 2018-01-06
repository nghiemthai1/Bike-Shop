package bike.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CenteredContentPanel extends DisplayPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnlContent = new JPanel();
	
	public CenteredContentPanel(int fontSize) {
		super(fontSize);
		add(pnlContent);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlContent.setLayout(new BoxLayout(pnlContent, BoxLayout.Y_AXIS));
	}
	
	public void addContent(Component comp, int spacing) {
		if (spacing != 0) {
			pnlContent.add(Box.createRigidArea(new Dimension(0, spacing)));
		}
		pnlContent.add(comp);
	}
	
	public void setContentBackground(Color color) {
		pnlContent.setBackground(color);
	}
}
