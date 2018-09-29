package view;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import control.ButtonSaveListener;
import model.Slot;
import model.TextElement;
import net.miginfocom.swing.MigLayout;

/**
 * This class represents a Text Editor in which the user inputs text and then displays it on the Slot, on close.
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class TextEditorFrame extends JFrame {

	@SuppressWarnings("unused")

	/**
	 * A TextElement that TextEditorFrame displays.
	 * 
	 * @author Stefan
	 *
	 */
	private TextElement element;

	/**
	 * An instance of JButton that TextEditor contains.
	 * 
	 * @author Stefan
	 *
	 */
	private JButton btnSave;

	/**
	 * An instance of JScrollPane that TextEditorFrame displays.
	 * 
	 * @author Stefan
	 *
	 */
	private JScrollPane jsp;

	/**
	 * An instance of JTextArea that TextEditorFrame displays. 
	 * 
	 * @author Stefan
	 *
	 */
	private JTextArea jta;

	/**
	 * An instance of Slot whose TextElement this editor displays/edits.
	 * 
	 * @author Stefan
	 *
	 */
	@SuppressWarnings("unused")
	private Slot parent;

	/**
	 * Instantiates a new instance of TextEditor frame with a TextElement and a Slot being passed to it.
	 * 
	 * @author Stefan
	 *
	 */
	public TextEditorFrame(TextElement element, Slot slot) {
		this.element = element;
		this.parent = slot;

		this.setTitle("Text Editor");
		this.setSize(new Dimension(600, 400));
		this.setLocationRelativeTo(null);
		this.setLayout(new MigLayout());

		jta = new JTextArea();
		jsp = new JScrollPane(jta);

		if (element.getText() != "")
			jta.setText(element.getText());

		btnSave = new JButton("Save");
		btnSave.addActionListener(new ButtonSaveListener(this, element, slot, jta));

		this.add(jsp, "push,grow,wrap");
		this.add(btnSave, "align center");
		this.setAlwaysOnTop(true);

		this.setVisible(true);
	}
}
