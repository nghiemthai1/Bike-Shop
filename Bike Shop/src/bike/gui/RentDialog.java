package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class RentDialog extends JDialog {
	
	public static final Font RENT_FONT = new Font("Dialog", Font.BOLD, 20);
	private RentDuration duration;
	private JPanel pnlSpin = new JPanel(new GridLayout(2, 2));
	private BikeSpinner spnDays = new BikeSpinner(0, 0, 30, RENT_FONT);
	private BikeSpinner spnHours = new BikeSpinner(0, 0, 23, RENT_FONT);
	private BikeButton btnSubmit = 
			new BikeButton("Rent It!", RENT_FONT, ProductShoppingPanel.RENT_COLOR);
	
	public RentDialog(Frame owner, Component comp) {
		super(owner, "Rent Duration", true);
		setUpFrame();
		addComponents();
		addListeners();
		pack();
		setLocationRelativeTo(comp);
		setVisible(true);
	}
	
	private void setUpFrame() {
		setLayout(new BorderLayout());
		setResizable(false);
		getContentPane().setBackground(Color.WHITE);
		setIconImage(ImagePanel.createSystemImage("logo.png"));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
	private void addComponents() {
		JLabel lblDays = new JLabel("Days");
		lblDays.setFont(RENT_FONT);
		JLabel lblHours = new JLabel("Hours");
		lblHours.setFont(RENT_FONT);
		pnlSpin.add(lblDays);
		pnlSpin.add(lblHours);
		pnlSpin.add(spnDays);
		pnlSpin.add(spnHours);
		add(pnlSpin, BorderLayout.NORTH);
		add(btnSubmit, BorderLayout.SOUTH);
	}
	
	private void addListeners() {
		btnSubmit.addActionListener(event -> {
			setDuration();
			dispose();
		});
	}
	
	private RentDuration getDuration() {
		return duration;
	}
	
	private void setDuration() {
		Long days = new Long((int) spnDays.getValue());
		Long hours = new Long((int) spnHours.getValue());
		duration = new RentDuration(days, hours);
	}
	
	public static RentDuration showRentDialog(Frame owner, Component comp) {
		return new RentDialog(owner, comp).getDuration();
	}
}
