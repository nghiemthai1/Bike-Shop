package bike.gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.TitledBorder;

import bike.products.Accessory;
import bike.products.Product;

public class AccessoryPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Dimension minWindow = new Dimension(200,200);
	private LinkedHashMap<Product, Integer> accList = new LinkedHashMap<Product, Integer>();

	
	public AccessoryPanel(LinkedHashMap<Product, Integer> linkedList){
	
		this.accList = linkedList;
		setMinimumSize(minWindow);
		
		JPanel filter = new JPanel();
		filter.setLayout(new BoxLayout(filter, BoxLayout.Y_AXIS));
		
		TitledBorder topTitle = BorderFactory.createTitledBorder("Accessory Filter");
		filter.setBorder(topTitle);
		
		Predicate<Product> isAccessory = product -> product instanceof Accessory;
		
		Set<Product> accSet = accList.keySet().stream().filter(isAccessory).collect(Collectors.toSet());
		
		HashSet<String> makeSet = new HashSet<String>();
		Iterator<Product> itMake = accSet.iterator();
		
		while(itMake.hasNext())
		{
			Product next = itMake.next();
			makeSet.add(next.getMake());
		}
		
		HashSet<String> typeSet = new HashSet<String>();
		Iterator<Product> itType = accSet.iterator();
		
		while(itType.hasNext())
		{
			Product next = itType.next();
			typeSet.add(next.getProductType());
		}
		
		JPanel typePanel = new JPanel(new GridLayout(typeSet.size(),1));
		JPanel modelPanel = new JPanel(new GridLayout(accSet.size(),1));
		JPanel makePanel = new JPanel (new GridLayout(makeSet.size(),1));
		Iterator<Product> itAcc = accSet.iterator();
	
		while(itAcc.hasNext())
		{
			Product next = itAcc.next();
			if(next instanceof Accessory){
			JCheckBox newModel = new JCheckBox(next.getModel());
			modelPanel.add(newModel);
			}
		}
		
		makeSet.forEach(make->makePanel.add(new JCheckBox(make)));
//		
//		JScrollPane modelScroll = new JScrollPane(modelPanel);
//		JScrollPane makeScroll = new JScrollPane(makePanel);
//
//		
		TitledBorder modelTitle = BorderFactory.createTitledBorder("Models");
		modelPanel.setBorder(modelTitle);
		TitledBorder makeTitle = BorderFactory.createTitledBorder("Makes");
		makePanel.setBorder(makeTitle);
		TitledBorder typeTitle = BorderFactory.createTitledBorder("Type");
		typePanel.setBorder(typeTitle);
		filter.add(typePanel);
		filter.add(makePanel);
		filter.add(modelPanel);
		
		add(filter);
	}	

	
}
