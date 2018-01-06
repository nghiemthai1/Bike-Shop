package bike.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import bike.inventory.Shop;

public class GreetingView extends BikeView {
	
	private ImagePanel pnlGreeting = new ImagePanel();
	
	public GreetingView(Shop shop) {
		super(shop);
		pnlGreeting.setImage(ImagePanel.createSystemImage("greeting.jpg"));
	}
	
	public void addViewTransitions() {
		pnlGreeting.addKeyListener(new KeyAdapter() {
	    	@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					getFrame().accept((new ProductsView(getShop())));
				}
			}
		});
	}
	
	public void displayView() {
		getFrame().add(pnlGreeting);
		pnlGreeting.setFocusable(true);
		pnlGreeting.requestFocusInWindow();
	}
}
