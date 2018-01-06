package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bike.products.Bike;

public class BikeShopGUI extends JFrame {
	//private JTextField BikeNumber, rentDeposit, rentDuration, firstName, lastName, CreditNum, CVV;
	private ArrayList<Bike> bikeList;
	public BikeShopGUI(){	
		this.setUpFrame();
		this.setUpComponents();		
		this.setVisible(true);			
		this.pack();
		
	}

	private void setUpFrame() {
		
		//this.setSize(800, 400);
		this.setTitle("Bike Rental");
		//this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		}
	
	private void setUpComponents(){
		
		JLabel genderComboLabel = new JLabel("Gender:");
		JLabel typeComboLabel = new JLabel("Type:");
		JLabel makeComboLabel = new JLabel("Make:");
		JLabel modelComboLabel = new JLabel("Model:");
		JLabel numComboLabel = new JLabel("Bike Number:");
		
		JComboBox<String> type  = new JComboBox<String>();
		JComboBox<String> make = new JComboBox<String>();
		JComboBox<String> model = new JComboBox<String>();
		JComboBox<String> number = new JComboBox<String>(); 
		JComboBox<String> gender = new JComboBox<String>();
		
		JTextField bikeNum = new JTextField(10);
		JTextField bikeChoice = new JTextField(10);
		
		JButton formSubmit = new JButton("Submit");
		JButton numSubmit = new JButton("Submit");
		
		JLabel rentLabel = new JLabel("Please choose the bike you want: ");
		JLabel numLabel = new JLabel("If you know the bike number insert it here: ");
		JLabel bikeLabel = new JLabel("This is the bike you have chosen: ");
		
		//set up subpanels
		JPanel botLeft = new JPanel(new GridLayout(2,1));
		JPanel botRight = new JPanel(new FlowLayout(FlowLayout.CENTER));

		//JPanel middle1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 75,0));
		//JPanel middle2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 75,0));
		JPanel middle1 = new JPanel(new GridLayout());
		JPanel middle2 = new JPanel(new GridLayout());
		
		//set up top panel
		JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
		top.setBackground(Color.CYAN);
		top.add(numLabel);
		top.add(bikeNum);
		top.add(numSubmit);
		
		
		// Set Up Middle Panel
		JPanel middle = new JPanel(new GridLayout(2,5));
		JPanel midLeft = new JPanel(new GridLayout(1,1));
		middle.add(middle1);
		middle.add(middle2);
		
		
		//JPanel midRight = new JPanel(new GridLayout());
		middle.setBackground(Color.WHITE);
		
		//midLeft.setSize(200, 50);
		
		midLeft.add(rentLabel);
		middle1.add(genderComboLabel);
		middle2.add(gender);

		middle1.add(typeComboLabel);
		middle2.add(type);

		middle1.add(makeComboLabel);
		middle2.add(make);

		middle1.add(modelComboLabel);
		middle2.add(model);

		middle1.add(numComboLabel);
		middle2.add(number);
		
		//set up bottom panel
		
		JPanel bottom = new JPanel(new BorderLayout());
		
	
		//bottom.setBackground(Color.CYAN);
		
		
		
		botLeft.add(bikeLabel);
		botLeft.add(bikeChoice);
		botRight.add(formSubmit);
		
		bottom.add(botLeft, BorderLayout.WEST);
		bottom.add(botRight, BorderLayout.CENTER);
		
		this.add(top,  BorderLayout.NORTH);
		this.add(midLeft, BorderLayout.WEST);
		this.add(middle, BorderLayout.CENTER);
		this.add(bottom, BorderLayout.SOUTH);
		
	}	
	

}
	

