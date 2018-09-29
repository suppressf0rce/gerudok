package control;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import javax.swing.JComponent;

import model.Actions;
import model.ComplexElement;
import model.GraphicElement;
import model.ImageElement;
import model.Slot;
import model.SoundElement;
import model.TextElement;
import model.VideoElement;
import view.DialogFrame;
import view.MainFrame;
import view.TextEditorFrame;
import view.WorkFrame;

/**
 * <code>public class AssignElementMouseListener implements MouseListener</code>
 * <br>
 * This class handles mouse event operation on the AssignElementPanel.<br>
 * It tell what to do when specific component is pressed. <br>
 * Which element will be assigned to the specified slot
 * 
 * @author Dejan Radmanovic
 *
 */
public class AssignElementMouseListener implements MouseListener {

	/**
	 * An instance of <code>JComponent</code> so the background on hover can be
	 * changed
	 * 
	 * @see Jcomponent
	 */
	private JComponent component;

	/**
	 * An instance of <code>Actions</code> on this case an <code>Slot</code> on
	 * which element will be assigned.
	 * 
	 * @see Actions
	 * @see Slot
	 */
	private Actions instance;

	/**
	 * An <code>String</code> which contains which type of element will be
	 * assigned to the slot.
	 */
	private String element;

	/**
	 * An <code>array of Observer</code> that will be added to the new element.
	 */
	private Observer[] o;

	/**
	 * An instance of <code>DialogFrame</code> on which will be panel placed so
	 * we can handle close dialog action.
	 */
	private DialogFrame dialog;

	/**
	 * Default constructor
	 * 
	 * @param component
	 *            An instance of <code>JComponent</code> so the background on
	 *            hover can be changed
	 * @param instance
	 *            An instance of <code>Actions</code> on this case an
	 *            <code>Slot</code> on which element will be assigned.
	 * @param element
	 *            An <code>String</code> which contains which type of element
	 *            will be assigned to the slot.
	 * @param d
	 *            An instance of <code>DialogFrame</code> on which will be panel
	 *            placed so we can handle close dialog action.
	 * @param o
	 *            An <code>array of Observer</code> that will be added to the
	 *            new element.
	 * 
	 * @see JComponent
	 * @see Actions
	 * @see Slot
	 * @see DialogFrame
	 * @see Observer
	 */
	public AssignElementMouseListener(JComponent component, Actions instance, String element, DialogFrame d,
			Observer... o) {
		this.component = component;
		component.setOpaque(true);
		component.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.instance = instance;
		this.element = element;
		this.o = o;
		this.dialog = d;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch (element) {
		case "complex": {
			instance.addChild(new ComplexElement((Slot) instance, "Complex Element"), null, o);
			break;
		}
		case "sound": {
			instance.addChild(new SoundElement((Slot) instance, "Sound Element"), null, o);
			break;
		}
		case "video": {
			instance.addChild(new VideoElement((Slot) instance, "Video Element"), null, o);

			break;
		}
		case "graphic": {
			GraphicElement element = new GraphicElement((Slot) instance, "Graphic Element");
			instance.addChild(element, null, o);
			new WorkFrame(element);

			break;
		}
		case "text": {

			TextElement tempElement = new TextElement("Text Element", (Slot) instance);
			Slot tempSlot = (Slot) MainFrame.getInstance().getTree().getLastSelectedPathComponent(); // currently
																										// selected
																										// component
			new TextEditorFrame(tempElement, tempSlot);

			instance.addChild(tempElement, null, o);
			break;
		}
		case "image": {
			instance.addChild(new ImageElement((Slot) instance, "Image Element"), null, o);
			break;
		}
		}
		dialog.dispose();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		component.setBackground(Color.LIGHT_GRAY);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		component.setBackground(null);
	}

}
