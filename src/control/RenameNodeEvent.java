package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.Actions;
import view.DialogFrame;
import view.MainFrame;
import view.RenameNodePanel;

/**
 * <code>public class RenameNodeEvent implements ActionListener</code><br>
 * This class handles action when the user wants to rename the node on the tree.
 * 
 * @author Dejan Radmanovic
 * @see Actions#rename(String)
 *
 */
public class RenameNodeEvent implements ActionListener {

	/**
	 * An instance of the <ode>Actions</code> which will be renamed.
	 * 
	 * @see Actions
	 */
	private Actions action;

	/**
	 * An instance of the <code>RenameNodePanel</code> which contains the
	 * information about the new name for the action
	 * 
	 * @see RenameNodePanel
	 */
	private RenameNodePanel newName;

	/**
	 * An instance of <code>DialogFrame</code> which will be used to handle the
	 * close dialog action.
	 * 
	 * @see DialogFrame
	 */
	private DialogFrame dialog;

	/**
	 * An default constructor for the <code>RenameNodeEvent</code>
	 * 
	 * @param action
	 *            An instance of the <code>Actions</code> which will be renamed.
	 * @param newName
	 *            An instance of the <code>RenameNodePanel</code> which contains
	 *            the information about the new name for the action
	 * @param dialog2
	 *            An instance of <code>DialogFrame</code> which will be used to
	 *            handle the close dialog action.
	 * 
	 * @see Actions
	 * @see RenameNodePanel
	 * @see DialogFrame
	 */
	public RenameNodeEvent(Actions action, RenameNodePanel newName, DialogFrame dialog2) {
		this.action = action;
		this.newName = newName;
		this.dialog = dialog2;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (newName.getTextField().getText().isEmpty()) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(), "Name field cannot be empty. Please try again.",
					"Rename error", JOptionPane.ERROR_MESSAGE);
		} else {
			action.rename(newName.getTextField().getText());
			dialog.dispose();
		}
	}

}
