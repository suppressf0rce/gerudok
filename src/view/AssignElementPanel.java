package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import control.AssignElementMouseListener;
import model.Actions;

/**
 * <code>public class AsigneElementPanel</code> extends <code>
 * {@link JPanel}</code><br>
 * This class represents the panel which will be shown when you want to assign
 * element to the slot.<br>
 * It contains all the Element types possible inside the GeRuDok represented by
 * the label and image
 * 
 * @author Dejan Radmanovic
 * @see JPanel
 *
 */
@SuppressWarnings("serial")
public class AssignElementPanel extends JPanel {

	/**
	 * This is the default constructor for the creating the panel.
	 * 
	 * @param instance
	 *            an <code>Action</code> which represents the action, in this
	 *            case slot in which will be the slot placed into.
	 * @param dialog
	 *            an <code>DialogFrame</code>, is a dialog in which will be
	 *            panel be placed into. In this case used as reference for
	 *            closing the frame
	 * @param o
	 *            an indefinite array of <code>Observer...</code> that will be
	 *            added to the newly assigned element.
	 * 
	 * @see Actions
	 * @see DialogFrame
	 * @see Observer
	 */
	public AssignElementPanel(Actions instance, DialogFrame dialog, Observer... o) {

		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 1.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblChooseTheElement = new JLabel("Choose the Element Type to Assign:");
		lblChooseTheElement.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblChooseTheElement = new GridBagConstraints();
		gbc_lblChooseTheElement.insets = new Insets(0, 0, 5, 0);
		gbc_lblChooseTheElement.gridwidth = 3;
		gbc_lblChooseTheElement.gridx = 0;
		gbc_lblChooseTheElement.gridy = 0;
		add(lblChooseTheElement, gbc_lblChooseTheElement);

		JLabel lblTextelementpicture = new JLabel();
		BufferedImage image;
		try {
			image = ImageIO.read(getClass().getResource("/resource/text-element-icon.png"));
			lblTextelementpicture.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblTextelementpicture
				.addMouseListener(new AssignElementMouseListener(lblTextelementpicture, instance, "text", dialog, o));
		GridBagConstraints gbc_lblTextelementpicture = new GridBagConstraints();
		gbc_lblTextelementpicture.anchor = GridBagConstraints.SOUTH;
		gbc_lblTextelementpicture.insets = new Insets(10, 10, 5, 10);
		gbc_lblTextelementpicture.gridx = 0;
		gbc_lblTextelementpicture.gridy = 1;
		add(lblTextelementpicture, gbc_lblTextelementpicture);

		JLabel lblGraphicelementpicture = new JLabel();
		try {
			image = ImageIO.read(getClass().getResource("/resource/graphic-element-icon.png"));
			lblGraphicelementpicture.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblGraphicelementpicture.addMouseListener(
				new AssignElementMouseListener(lblGraphicelementpicture, instance, "graphic", dialog, o));
		GridBagConstraints gbc_lblGraphicelementpicture = new GridBagConstraints();
		gbc_lblGraphicelementpicture.anchor = GridBagConstraints.SOUTH;
		gbc_lblGraphicelementpicture.insets = new Insets(10, 10, 5, 10);
		gbc_lblGraphicelementpicture.gridx = 1;
		gbc_lblGraphicelementpicture.gridy = 1;
		add(lblGraphicelementpicture, gbc_lblGraphicelementpicture);

		JLabel lblImageelement = new JLabel();
		try {
			image = ImageIO.read(getClass().getResource("/resource/image-element-icon.png"));
			lblImageelement.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblImageelement.addMouseListener(new AssignElementMouseListener(lblImageelement, instance, "image", dialog, o));
		GridBagConstraints gbc_lblImageelement = new GridBagConstraints();
		gbc_lblImageelement.anchor = GridBagConstraints.SOUTH;
		gbc_lblImageelement.insets = new Insets(10, 10, 5, 10);
		gbc_lblImageelement.gridx = 2;
		gbc_lblImageelement.gridy = 1;
		add(lblImageelement, gbc_lblImageelement);

		JLabel lblTextElement = new JLabel("Text Element");
		lblTextElement.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblTextElement = new GridBagConstraints();
		gbc_lblTextElement.insets = new Insets(0, 0, 5, 5);
		gbc_lblTextElement.gridx = 0;
		gbc_lblTextElement.gridy = 2;
		add(lblTextElement, gbc_lblTextElement);

		JLabel lblGraphicElement = new JLabel("Graphic Element");
		lblGraphicElement.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblGraphicElement = new GridBagConstraints();
		gbc_lblGraphicElement.insets = new Insets(0, 0, 5, 5);
		gbc_lblGraphicElement.gridx = 1;
		gbc_lblGraphicElement.gridy = 2;
		add(lblGraphicElement, gbc_lblGraphicElement);

		JLabel lblImageElement = new JLabel("Image Element");
		GridBagConstraints gbc_lblImageElement = new GridBagConstraints();
		gbc_lblImageElement.insets = new Insets(0, 0, 5, 0);
		gbc_lblImageElement.gridx = 2;
		gbc_lblImageElement.gridy = 2;
		add(lblImageElement, gbc_lblImageElement);

		JLabel lblVideoelementpicture = new JLabel();
		try {
			image = ImageIO.read(getClass().getResource("/resource/video-element-icon.png"));
			lblVideoelementpicture.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblVideoelementpicture
				.addMouseListener(new AssignElementMouseListener(lblVideoelementpicture, instance, "video", dialog, o));
		GridBagConstraints gbc_lblVideoelementpicture = new GridBagConstraints();
		gbc_lblVideoelementpicture.anchor = GridBagConstraints.SOUTH;
		gbc_lblVideoelementpicture.insets = new Insets(10, 10, 5, 10);
		gbc_lblVideoelementpicture.gridx = 0;
		gbc_lblVideoelementpicture.gridy = 3;
		add(lblVideoelementpicture, gbc_lblVideoelementpicture);

		JLabel lblSoundelementpicture = new JLabel();
		try {
			image = ImageIO.read(getClass().getResource("/resource/sound-element-icon.png"));
			lblSoundelementpicture.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblSoundelementpicture
				.addMouseListener(new AssignElementMouseListener(lblSoundelementpicture, instance, "sound", dialog, o));
		GridBagConstraints gbc_lblSoundelementpicture = new GridBagConstraints();
		gbc_lblSoundelementpicture.anchor = GridBagConstraints.SOUTH;
		gbc_lblSoundelementpicture.insets = new Insets(10, 10, 5, 10);
		gbc_lblSoundelementpicture.gridx = 1;
		gbc_lblSoundelementpicture.gridy = 3;
		add(lblSoundelementpicture, gbc_lblSoundelementpicture);

		JLabel lblComplexelementpicture = new JLabel();
		try {
			image = ImageIO.read(getClass().getResource("/resource/complex-element-icon.png"));
			lblComplexelementpicture.setIcon(new ImageIcon(image.getScaledInstance(128, 128, Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lblComplexelementpicture.addMouseListener(
				new AssignElementMouseListener(lblComplexelementpicture, instance, "complex", dialog, o));
		GridBagConstraints gbc_lblComplexelementpicture = new GridBagConstraints();
		gbc_lblComplexelementpicture.anchor = GridBagConstraints.SOUTH;
		gbc_lblComplexelementpicture.insets = new Insets(10, 10, 5, 10);
		gbc_lblComplexelementpicture.gridx = 2;
		gbc_lblComplexelementpicture.gridy = 3;
		add(lblComplexelementpicture, gbc_lblComplexelementpicture);

		JLabel lblVideoElement = new JLabel("Video Element");
		GridBagConstraints gbc_lblVideoElement = new GridBagConstraints();
		gbc_lblVideoElement.insets = new Insets(0, 0, 0, 5);
		gbc_lblVideoElement.gridx = 0;
		gbc_lblVideoElement.gridy = 4;
		add(lblVideoElement, gbc_lblVideoElement);

		JLabel lblSoundElement = new JLabel("Sound Element");
		GridBagConstraints gbc_lblSoundElement = new GridBagConstraints();
		gbc_lblSoundElement.insets = new Insets(0, 0, 0, 5);
		gbc_lblSoundElement.gridx = 1;
		gbc_lblSoundElement.gridy = 4;
		add(lblSoundElement, gbc_lblSoundElement);

		JLabel lblComplexElement = new JLabel("Complex Element");
		GridBagConstraints gbc_lblComplexElement = new GridBagConstraints();
		gbc_lblComplexElement.gridx = 2;
		gbc_lblComplexElement.gridy = 4;
		add(lblComplexElement, gbc_lblComplexElement);

	}

}
