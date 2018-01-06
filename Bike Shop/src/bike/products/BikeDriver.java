package bike.products;

public class BikeDriver {
	
	public static void main(String[] args) {
		Cost c = new Cost(12,04);
		Cost o = new Cost(15,99);
		System.out.println(c.add(o));
	}
}
