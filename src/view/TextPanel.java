package view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import net.miginfocom.swing.MigLayout;

/**
 * TODO: Stefan: napisati dokumentaciju
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class TextPanel extends JPanel {

	/**
	 * An instance of JScrollPane that it displays.
	 * 
	 * @author Stefan
	 *
	 */
	private JScrollPane jsp;

	/**
	 * An instance of JTextArea that TextPanel displays.
	 * 
	 * @author Stefan
	 *
	 */
	private JTextArea jta;

	/**
	 * A String which represents the text that the TextPanel displays.
	 * 
	 * @author Stefan
	 *
	 */
	private String text;

	/**
	 * This Constructor is used to create a new TextPanel and display a certain text over it, having the String passed to it.
	 * 
	 * @author Stefan
	 *
	 */
	public TextPanel(String text) {
		this.setLayout(new MigLayout());
		this.text = text;
		jta = new JTextArea(text);
		jta.setEditable(false);
		jta.setLineWrap(true);
		jsp = new JScrollPane(jta);

		this.add(jsp, "push,grow");
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;

	}

}
