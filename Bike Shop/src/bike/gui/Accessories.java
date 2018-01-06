package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class Accessories extends JFrame {

	private Dimension minWindow = new Dimension(400,400);
	
	public Accessories(){
		
		
	this.buildUI();
	this.buildComponents();
	this.setVisible(true);
	
	}
	
	public void buildUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setMinimumSize(minWindow);
		setTitle("Accessory Page");
		pack();
		//set up top panel
		
		JPanel topPanel = new JPanel(new GridLayout(1,2));
		JPanel choicePanel = new JPanel(new FlowLayout());
		JRadioButton buy = new JRadioButton("Buy");
		JRadioButton rent = new JRadioButton("Rent");
		topPanel.add(choicePanel);
		choicePanel.add(buy);
		choicePanel.add(rent);
		
		TitledBorder topTitle = BorderFactory.createTitledBorder("Accessory Page");
		topPanel.setBorder(topTitle);
		TitledBorder choiceTitle = BorderFactory.createTitledBorder("Buy or Rent?");
		choicePanel.setBorder(choiceTitle);
		getContentPane().add(topPanel,BorderLayout.NORTH);
		
		//set up middle panel
		JPanel middlePanel = new JPanel(new GridLayout(2,1));
		JPanel comboLabelPanel = new JPanel (new GridLayout(1,3));
		JPanel comboPanel = new JPanel (new GridLayout(1,3));
		
		JComboBox Gender = new JComboBox();
		JComboBox Type = new JComboBox();
		JComboBox Model = new JComboBox();
		
//		ComboPanel.add(Gender);
//		ComboPanel.add(Type);
//		ComboPanel.add(Model);
				
	}	

	public void buildComponents(){
			
	}
	
	
}
