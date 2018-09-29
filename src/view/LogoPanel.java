package view;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//import model.Parameters;

@SuppressWarnings("serial")
public class LogoPanel extends JPanel {
	/**
	 * 
	 * 
	 * This JPanel draws the logo image over itself.
	 */
	private BufferedImage image;
	private Image image1;

	public LogoPanel(Integer width, Integer height, Integer id) {
		setSize(width, height);
		try {
			if (id == 1) {
				image = ImageIO.read(new File("resource/logo_transp.png"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			/*
			 * TODO if(id==2){ System.out.println(Parameters.get().getLogo());
			 * image = ImageIO.read(new
			 * File("resource/"+Parameters.get().getLogo())); image1 =
			 * image.getScaledInstance(getWidth(),getHeight(),Image.SCALE_SMOOTH
			 * ); }
			 */
			if (id == 3) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate3_scaled.png"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 4) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate1_scaled.png"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 5) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate2.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 6) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate4.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 7) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate4_lemurred.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 8) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate1_lionned.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 9) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate3_slothhed.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 10) {
				image = ImageIO.read(new File("resource/teammates/pebkac_teammate2_mouseed.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
			if (id == 11) {
				image = ImageIO.read(new File("resource/buggs_bunny.jpg"));
				image1 = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
			}
		} catch (IOException ex) {
			System.err.println("Error 404: The file could not be found.");
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image1, 0, 0, this);
	}
}
