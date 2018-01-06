package bike.gui;

import bike.products.Product;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class ProductPanel extends DisplayPanel {

	private Product product;
	private JTextArea txtInfo;
	
	protected ProductPanel(Product product) {
		super(20);
		this.product = product;
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setUpPanel();
		setUpText();
		addComponents();
	}
	
	private void setUpPanel() {
		setBorder(BorderFactory.createEtchedBorder());
		setBackground(Color.WHITE);
	}
	
	private void setUpText() {
		txtInfo = new JTextArea(product.toString());
		txtInfo.setEditable(false);
		txtInfo.setFont(font);
		txtInfo.setBackground(Color.WHITE);
	}
	
	private void addComponents() {
		
	    /* Add Image */	
		ImagePanel pnlImage = new ImagePanel();
		pnlImage.setImage(
				ImagePanel.createDirectoryImage(
						"\\Product Images\\" + product.getProductNumber() + ".jpg"));
		pnlImage.adjustSize(200, 200, true);
		pnlImage.setBackground(Color.WHITE);
		add(pnlImage);
		
		/* Add Text */
		add(txtInfo);
	}
	
	public Product getProduct() {
		return product;
	}
}
