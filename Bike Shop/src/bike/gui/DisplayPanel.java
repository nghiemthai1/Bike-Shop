package bike.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

public class DisplayPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	static final Color THEME_DARK = new Color(24, 24, 24);
	static final Color FONT_DARK = new Color(215, 215, 215);
	static final Color FORM_THEME = Color.WHITE;
	final Font font;
	
	public DisplayPanel(int fontSize) {
		font = new Font("Dialog", Font.BOLD, fontSize);
	}
	
	public Color getTheme() {
		return THEME_DARK;
	}
	
	public Color getDarkFont() {
		return FONT_DARK;
	}
	
	public static JPanel createHeaderPanel(String text) {
		JPanel pnlHeader = new JPanel();
		pnlHeader.setBackground(Color.WHITE);
		pnlHeader.setBorder(new EtchedBorder());
		JLabel lblHeader = new JLabel(text);
		lblHeader.setFont(new Font("Dialog", Font.PLAIN, 25));
		pnlHeader.add(lblHeader);
		return pnlHeader;
	}
	
	public static JLabel createMissingLabel(String text) {
		JLabel lblMissing = new JLabel(text);
		lblMissing.setFont(new Font("Dialog", Font.ITALIC, 30));
		lblMissing.setForeground(Color.gray);
		return lblMissing;
	}
}