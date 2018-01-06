package bike.inventory;

import java.util.LinkedHashMap;
import java.util.function.Predicate;
import java.util.stream.Stream;

import bike.products.Product;

/**
 * Inventory class keep tracks of all the store's products. 
 * @author nghiemthai1
 *
 */
public class Inventory {
			
	private LinkedHashMap<Product, Integer> products;
	
	public Inventory() {
		products = new LinkedHashMap<Product, Integer>();
	}
	
	public Inventory(LinkedHashMap<Product, Integer> productMap) {
		this.products = productMap;
	}

	public LinkedHashMap<Product, Integer> getProducts() {
		return products;
	}
	
	public Stream<Product> getProductsAsStream() {
		return products.keySet().stream();
	}

	public void setProducts(LinkedHashMap<Product, Integer> products) {
		this.products = products;
	}
	
	public void addProduct(Product product, int amount) {
		products.put(product, amount);
	}
	
	public void removeProduct(Product product) {
		products.remove(product);
	}
	
	public void increaseInventory(Product product) {
		products.put(product, products.get(product) + 1);
	}
	
	public void decreaseInventory(Product product) {
		products.put(product, products.get(product) - 1);
		
	}
	
	public boolean inStock(Product product) {
		boolean in = true;
		if (products.get(product) == 0) {
			in = false;
		}
		return in;
	}
	
	public void listInventory() {
		products.keySet()
        		.forEach(product -> 
        		System.out.println(
        				product + " \n\tIn Stock: " + products.get(product)));
	}
	
	public Stream<Product> filterInventory(Predicate<Product> filter) {
		return getProductsAsStream().filter(filter);
	}
}
