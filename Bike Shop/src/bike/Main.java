package bike;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import bike.io.RecordsIO;
import bike.products.*;
import bike.inventory.Shop;
import bike.gui.*;

public class Main {

	public static void main(String[] args) {
		if (! RecordsIO.setWorkingDirectory("C://Users//nghiemthai1//OneDrive//college pakage//OOPDA//Bike Shop Files")) {
			System.exit(0);
		}
		List<Product> prototypes = createPrototypes();
		Shop shop = new Shop();
		try {
			RecordsIO.generateInventory(shop.getInventory().getProducts(), prototypes, 
					"Inventory Records.csv");
		}
		catch (InvalidProductException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(),
					"Invalid Product in Records", JOptionPane.ERROR_MESSAGE);
		}
		BikeFrame frame = new BikeFrame("Acme Bike Rentals");
		frame.accept(new GreetingView(shop));
		frame.setVisible(true);
	}
	
	public static List<Product> createPrototypes() {
		List<Product> prototypes = new ArrayList<Product>();
		Cost cost = new Cost(1,99);
		prototypes.add(new MountainBike(cost, "", "", "", false));
		prototypes.add(new BMX(cost, "", "", "", false));
		prototypes.add(new TandemBike(cost, "", "", "", false));
		prototypes.add(new Cruiser(cost, "", "", "", false));
		prototypes.add(new Unicycle(cost, "", "", "", false));
		prototypes.add(new Helmet(cost, "", "", ""));
		prototypes.add(new Light(cost, "", "", ""));
		prototypes.add(new AirPump(cost, "", "", ""));
		return prototypes;
	}	
}