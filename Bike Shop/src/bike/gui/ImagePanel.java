package bike.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import bike.io.RecordsIO;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final String NO_IMAGE = "no_image.jpg";
	private Image image;

	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		if (image != null) {
			this.image = image;
		}
		else {
			this.image = createSystemImage(NO_IMAGE);
		}
	}
	
	public static Image createDirectoryImage(String fileName) {
		Image image = null;
		File imageFile = new File(RecordsIO.getWorkingDirectory() + fileName);
		if (imageFile.exists()) {
			try {
				image = ImageIO.read(imageFile);
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return image;
	}
	
	public static Image createSystemImage(String fileName) {
		Image image = null;
		URL imageURL = ImagePanel.class.getResource("images/" + fileName);
		try {
			image = ImageIO.read(imageURL);
		}
		catch (IOException ex) {
			ex.printStackTrace();
		}
		return image;
	}
	
	protected void adjustSize(int height, int width, boolean scale) {
		setPreferredSize(new Dimension(width, height));
		if (scale) scaleImage(height, width);
	}
	
	public void scaleImage(int height, int width) {
		if (image != null) {
			image = image.getScaledInstance(height, width, Image.SCALE_SMOOTH);
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, this);
	}
}
