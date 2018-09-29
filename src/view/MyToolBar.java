package view;

import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import state.StateManager;

/**
 * TODO: Teodora, Branko: Napisati dokumentaciju:
 *
 */
@SuppressWarnings("serial")
public class MyToolBar extends JToolBar {

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	JToggleButton selectionBtn = new JToggleButton();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	JToggleButton rectangleBtn = new JToggleButton();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	JToggleButton circleBtn = new JToggleButton();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	JToggleButton copyBtn = new JToggleButton();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	JToggleButton deleteBtn = new JToggleButton();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	ButtonGroup bg = new ButtonGroup();

	/**
	 * TODO: Teodora, Branko: Napisati dokumentaciju:
	 *
	 */
	public MyToolBar(StateManager manager) {

		selectionBtn.addActionListener(e -> {
			manager.setCurrentState(manager.getSelectState());
		});

		rectangleBtn.addActionListener(e -> {
			manager.setCurrentState(manager.getRectangleState());
		});

		circleBtn.addActionListener(e -> {
			manager.setCurrentState(manager.getCircleState());
		});
		bg.add(rectangleBtn);
		bg.add(selectionBtn);
		bg.add(circleBtn);
		bg.add(copyBtn);
		bg.add(deleteBtn);

		this.setOrientation(JToolBar.VERTICAL);

		this.add(rectangleBtn);
		this.add(circleBtn);
		this.add(selectionBtn);
		this.add(copyBtn);
		this.add(deleteBtn);
		// this.setFloatable(false);
		// this.setRollover(true);

		try {
			Image img = ImageIO.read(getClass().getResource("/resource/lepsi_select.png"));
			Image newimg = img.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			selectionBtn.setIcon(new ImageIcon(newimg));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		try {
			Image img2 = ImageIO.read(getClass().getResource("/resource/lepsi_kvadrat.png"));
			Image newimg2 = img2.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			rectangleBtn.setIcon(new ImageIcon(newimg2));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		try {
			Image img3 = ImageIO.read(getClass().getResource("/resource/lepsi_krug.png"));
			Image newimg3 = img3.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			circleBtn.setIcon(new ImageIcon(newimg3));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		try {
			Image img4 = ImageIO.read(getClass().getResource("/resource/copy.png"));
			Image newimg4 = img4.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			copyBtn.setIcon(new ImageIcon(newimg4));
		} catch (Exception ex) {
			System.out.println(ex);
		}

		try {
			Image img5 = ImageIO.read(getClass().getResource("/resource/deletee.png"));
			Image newimg5 = img5.getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH);
			deleteBtn.setIcon(new ImageIcon(newimg5));
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}

}
