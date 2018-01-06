package bike.gui;

import bike.inventory.Shop;

public abstract class BikeView extends View {
	
	private Shop shop;

	public BikeView(Shop shop) {
		this.shop = shop;
	}
	
	public BikeFrame getFrame() {
		return (BikeFrame) getDisplay();
	}
	
	public Shop getShop() {
		return shop;
	}

	public void visit(BikeFrame frame) {
		super.visit(frame);
	}
}
