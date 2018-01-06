package bike.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import bike.inventory.Inventory;
import bike.products.*;

public class AccessoryGui extends JFrame {
	
	private Dimension minWindow = new Dimension(400,400);
	private LinkedHashMap<Product, Integer> accList = new LinkedHashMap<Product, Integer>();
	private Inventory inventory;

	
	public AccessoryGui(LinkedHashMap<Product, Integer> linkedList){
	
	this.accList = linkedList;
	this.buildUI();
	this.buildComponents();
	this.setVisible(true);
	//this.pack();
	}
	
	public void buildUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setMinimumSize(minWindow);
		setTitle("Accessory Page");
		pack();
//		//set up top panel
//		JPanel 
////		JPanel topPanel = new JPanel(new GridLayout(1,2));
////		JPanel choicePanel = new JPanel(new FlowLayout());
////		JRadioButton buy = new JRadioButton("Buy");
////		JRadioButton rent = new JRadioButton("Rent");
////		topPanel.add(choicePanel);
////		choicePanel.add(buy);
////		choicePanel.add(rent);
//		
//		
////		TitledBorder choiceTitle = BorderFactory.createTitledBorder("Buy or Rent?");
////		choicePanel.setBorder(choiceTitle);
//		
////		
////		set up middle panel
////		JPanel middlePanel = new JPanel(new GridLayout(2,1));
//	
		
		JPanel filter = new JPanel(new GridLayout(2,1));
		
		
		TitledBorder topTitle = BorderFactory.createTitledBorder("Accessory Filter");
		filter.setBorder(topTitle);
		getContentPane().add(filter,BorderLayout.WEST);
//		JPanel gender = new JPanel(new GridLayout(1,2));
//		JCheckBox male = new JCheckBox("Male");
//		JCheckBox female = new JCheckBox("Female");
//		
//		gender.add(male);
//		gender.add(female);
//      filter.add(gender);
		
		
		
		Set<Product> accSet = accList.keySet();
		
		JPanel modelPanel = new JPanel(new GridLayout(accList.size(),1));
		JPanel makePanel = new JPanel (new GridLayout(accList.size(),1));
		Iterator<Product> it = accSet.iterator();
	
		while(it.hasNext())
		{
			Product next = it.next();
			if(next instanceof Accessory){
			
			JCheckBox newMake = new JCheckBox(next.getMake());
			makePanel.add(newMake);
			JCheckBox newModel = new JCheckBox(next.getModel());
			modelPanel.add(newModel);
			
			}
		}
		JScrollPane modelScroll = new JScrollPane(modelPanel);
		//modelPanel.add(modelScroll);
		JScrollPane makeScroll = new JScrollPane(makePanel);
		//makePanel.add(makeScroll);
		
		TitledBorder modelTitle = BorderFactory.createTitledBorder("Models");
		modelPanel.setBorder(modelTitle);
		TitledBorder makeTitle = BorderFactory.createTitledBorder("Makes");
		makePanel.setBorder(makeTitle);
		
		filter.add(makePanel);
		filter.add(modelPanel);
		JPanel pnlProducts = new JPanel(new FlowLayout(FlowLayout.LEFT));
		Product product = new Helmet(new Cost(29,99), "Number" , "Make", "Model");
		ProductPanel pnlProduct = new ProductPanel(product);
		add(filter,BorderLayout.WEST);
		pnlProducts.add(pnlProduct);
		add(pnlProducts, BorderLayout.CENTER);
		
		this.pack();
	}	

	public void buildComponents(){
			
	}
	
}
