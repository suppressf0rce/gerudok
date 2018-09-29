package view;

import javax.swing.JDialog;

/**
 * <code>public class DialogFrame extends JDialog</code><br>
 * This is class that represents internal modal dialog on which you can place
 * custom panel.<br>
 * And it is shown inside the instance of the main form and you can't do
 * anything until you close the dialog.
 * 
 * @author Dejan Radmanovic
 *
 * @see JDialog
 */
@SuppressWarnings("serial")
public class DialogFrame extends JDialog {

	/**
	 * Default constructor which will create the dialog with specified title.
	 * 
	 * @param title
	 *            an <code>String</code> of the new title of the JDialog
	 */
	public DialogFrame(String title) {
		this.setTitle(title);
		this.setSize(300, 200);
		this.setModal(true);
		this.setLocationRelativeTo(MainFrame.getInstance());
		this.setIconImage(MainFrame.getInstance().getIconImage());
		this.setResizable(false);
	}

}
