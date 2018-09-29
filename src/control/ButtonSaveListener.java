package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.Slot;
import model.TextElement;
import view.MainFrame;
import view.SlotPanel;
import view.TextEditorFrame;
import view.TextPanel;

/**
 * A class that implements ActionListener interface. Once pressed, it sets the
 * text from JTextArea to the element.
 * 
 * @author Stefan
 *
 */
public class ButtonSaveListener implements ActionListener {
	/**
	 * An instance of TextEditorFrame that this listener disables.
	 * 
	 * @author Stefan
	 */
	private TextEditorFrame tef;
	/**
	 * An instance of TextElement that ButtonSaveListener manipulates.
	 * 
	 * @author Stefan
	 */
	private TextElement element;
	/**
	 * A Slot for whom SlotPanel will be called.
	 */
	private Slot slot;
	/**
	 * A JTextArea whose text TextElement gets
	 */
	private JTextArea jta;

	/**
	 * A Constructor that gets TextEditorFrame, TextElement, Slot and a
	 * JTextArea as parameters.
	 * 
	 * @param tef
	 * @param element
	 * @param slot
	 * @param jta
	 */
	public ButtonSaveListener(TextEditorFrame tef, TextElement element, Slot slot, JTextArea jta) {
		this.element = element;
		this.slot = slot;
		this.jta = jta;
		this.tef = tef;

	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {
		element.setText(jta.getText());
		SlotPanel temp = MainFrame.getInstance().getDesktopPane().getCallingSlot(slot);

		temp.setContent((JPanel) new TextPanel(element.getText()));
		System.out.println(temp.getParent());
		temp.getParent().revalidate();

		tef.dispose();
	}

}
