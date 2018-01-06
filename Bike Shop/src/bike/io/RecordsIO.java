package bike.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import java.util.AbstractMap.SimpleEntry;

import bike.products.CostFormatException;
import bike.products.InvalidProductException;
import bike.products.Product;

public class RecordsIO {

	public static final String CUSTOMER_DIRECTORY = "Customer Records/";
	private static String workingDirectory;
	
	public static String getWorkingDirectory() {
		return workingDirectory;
	}
	
	public static boolean setWorkingDirectory(String directory) {
		boolean directorySet = false;
		if (directory != null) {
			if (! new File(directory).exists()) {
				JOptionPane.showMessageDialog(null, 
						directory + " is an invalid directory.", "Directory Error",
						JOptionPane.ERROR_MESSAGE);
			}
			else {
				workingDirectory = directory;
				directorySet = true;
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Working directory has not been assigned.",
					"Directory Error", JOptionPane.ERROR_MESSAGE);
		}
		return directorySet;
	}
	
	public static void generateInventory(LinkedHashMap<Product,Integer> inventoryMap, 
		List<Product> prototypes, String fileName) {
		List<String> fileData = RecordParser.parseData(new File(workingDirectory, fileName));
		fileData.forEach(dataLine -> {
			String[] line = dataLine.split("[,]");
			SimpleEntry<Product, Integer> entry = convertToProduct(line, prototypes);
			inventoryMap.put(entry.getKey(), entry.getValue());
		});
	}
	
	private static SimpleEntry<Product, Integer> convertToProduct(String[] line, List<Product> prototypes) {
		Optional<Product> op = prototypes.parallelStream()
										 .filter(prototype -> prototype
										 .getClass()
             		                     .getSimpleName()
             		                     .equals(line[0]))
                                         .findAny();
		if (! op.isPresent()) throw new InvalidProductException(line[0]);
		Product product = null;
		try {
			product = op.get().createProduct(line);	                     
		}
		catch (CostFormatException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Cost in Records", 
					JOptionPane.ERROR_MESSAGE);
		}
		int amountInInventory = Integer.parseInt(line[line.length - 1]);
		return new SimpleEntry<Product, Integer>(product, amountInInventory);
	}
	
	public static File serialize(Object obj, String fileName) {
		File serFile = new File(workingDirectory + fileName + ".ser");
		try (ObjectOutputStream out = 
				new ObjectOutputStream(
				new FileOutputStream(serFile))) {
			out.writeObject(obj);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return serFile;
	}
	
	public static Object deserialize(String fileName) throws FileNotFoundException {
		Object obj = null;
		try (ObjectInputStream in =
				new ObjectInputStream(
				new FileInputStream(
				new File(workingDirectory + fileName + ".ser")))) {
			try {
				obj = in.readObject();
			}
			catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}
		}
		catch (FileNotFoundException ex) {
			throw ex;
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return obj;
	}
}
