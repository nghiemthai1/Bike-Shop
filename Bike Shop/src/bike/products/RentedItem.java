package bike.products;

import java.time.LocalDateTime;

public class RentedItem {
	private Product product;
	private LocalDateTime returnTime;
	
	public RentedItem(Product product, long days, long hours){
		this.product = product;
		returnTime = LocalDateTime.now().plusHours(hours).plusDays(days);
	}

	public Product getProduct() {
		return product;
	}

	public LocalDateTime getDueTime() {
		return returnTime;
	}
}