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
	import javax.swing.JScrollPane;
	import javax.swing.JTextField;
	import javax.swing.border.EtchedBorder;
	import javax.swing.border.TitledBorder;

	import bike.products.Bike;
	import bike.products.Product;
	
	public class BikeGui extends JFrame {
		
		private Dimension minWindow = new Dimension(400,400);
		private LinkedHashMap<Product, Integer> bikeList = new LinkedHashMap<Product, Integer>();		
	
//		public static void main(String[] args) {
//			
//			//AccessoryGui AsGui = new AccessoryGui(accList);
//			BikeGui bikeGui = new BikeGui(accList);
//
//		}
		
		public BikeGui(LinkedHashMap<Product,Integer> bikes){
		this.bikeList = bikes;
		this.buildUI();
		this.buildComponents();
		this.setVisible(true);
		
		}
		
		public void buildUI(){
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(new BorderLayout());
			setMinimumSize(minWindow);
			setTitle("bikeessory Page");
			pack();
			//set up top panel
			
			JPanel topPanel = new JPanel(new GridLayout(1,2));
			JPanel choicePanel = new JPanel(new FlowLayout());

			topPanel.add(choicePanel);

			
			TitledBorder topTitle = BorderFactory.createTitledBorder("Bike Page");
			topPanel.setBorder(topTitle);
//			TitledBorder choiceTitle = BorderFactory.createTitledBorder("Buy or Rent?");
//			choicePanel.setBorder(choiceTitle);
//			getContentPane().add(topPanel,BorderLayout.NORTH);
//			
			//set up middle panel
			JPanel middlePanel = new JPanel(new GridLayout(2,1));
			
			JPanel filter = new JPanel(new GridLayout(3,1));
			
			JPanel gender = new JPanel(new GridLayout(2,1));
			JRadioButton male = new JRadioButton("Male");
			JRadioButton female = new JRadioButton("Female");
			
			gender.add(male);
			gender.add(female);
	      filter.add(gender);
			
			
			Set<Product> bikeSet = bikeList.keySet();
			
			JPanel modelPanel = new JPanel(new GridLayout(bikeList.size(),1));
			JPanel makePanel = new JPanel (new GridLayout(bikeList.size(),3));
			Iterator<Product> it = bikeSet.iterator();
		
			while(it.hasNext())
			{
				Product next = it.next();
				if(next instanceof Bike){
				
				JRadioButton newModel = new JRadioButton(next.getModel());
				modelPanel.add(newModel);
				JRadioButton newMake = new JRadioButton(next.getMake());
				makePanel.add(newMake);
				}
			}
			JScrollPane modelScroll = new JScrollPane();
			modelPanel.add(modelScroll);
			JScrollPane makeScroll = new JScrollPane();
			makePanel.add(makeScroll);
			
			
			filter.add(modelPanel);
			filter.add(makePanel);
			getContentPane().add(filter,BorderLayout.WEST);

		}	

		public void buildComponents(){
				
		}
		
	}

