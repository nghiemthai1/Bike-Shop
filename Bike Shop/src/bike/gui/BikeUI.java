package bike.gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.*;

import bike.inventory.Shop;

public class BikeUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Shop shop;
	private ImagePanel pnlGreeting = new ImagePanel();
	private ImagePanel pnlBackground = new ImagePanel();
	private HeaderPanel pnlHeader = new HeaderPanel();
	private ProductsDisplayPanel pnlDisplay = new ProductsDisplayPanel();
	private LoginPanel pnlLogin = new LoginPanel();
	
	public BikeUI(Shop shop, String[] productTypes) {
		super("Acme Bike Rentals");
		this.shop = shop;
		setUpFrame();
		addComponents();
		addListeners();
		goToGreeting();
		setVisible(true);
	}
	
	private void setUpFrame() {
		setMinimumSize(new Dimension(800, 500));
		setLocationRelativeTo(null);
		//setIconImage(createImage("images/logo.png"));
		pnlGreeting.createSystemImage("greeting.jpg");
		pnlBackground.createSystemImage("background.jpg");
		pnlBackground.setLayout(new BorderLayout());
		add(pnlBackground);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setExtendedState(MAXIMIZED_BOTH);	
	}
	
	private void addComponents() {
		pnlBackground.add(pnlHeader, BorderLayout.NORTH);
		pnlBackground.add(pnlDisplay, BorderLayout.CENTER);
		pnlDisplay.addProductsFromInventory(shop.getInventory());
		JScrollPane scroll = new JScrollPane(pnlDisplay);
		scroll.getVerticalScrollBar().setUnitIncrement(30);
		scroll.getViewport().setOpaque(false);
		scroll.setOpaque(false);
		pnlBackground.add(scroll);
	}
	
	private void addListeners() {
		pnlHeader.getSignInButton().addActionListener(event -> goToLogin());
		pnlHeader.getBackButton().addActionListener(event -> goToGreeting());
		pnlLogin.getBackLabel().addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				goToProducts();
			}
		});
		
		pnlGreeting.addKeyListener(new KeyAdapter() {
	    	@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					goToProducts();
				}
			}
		});
	}
	
	private void goToLogin() {
		getContentPane().removeAll();
		add(pnlLogin);
		repaint();
		revalidate();
	}
	
	private void goToProducts() {
		getContentPane().removeAll();
		add(pnlBackground);
		repaint();
		revalidate();
	}
	
	private void goToGreeting() {
		getContentPane().removeAll();
		add(pnlGreeting);
		pnlGreeting.setFocusable(true);
		pnlGreeting.requestFocusInWindow();
		repaint();
		revalidate();
	}
	
	/** Returns an Image, or null if the path was invalid */
	private Image createImage(String path) {
		URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL).getImage();
		}
		else {
			System.err.println("Could not find image icon file: " + path);
			return null;
		}
	}
}
