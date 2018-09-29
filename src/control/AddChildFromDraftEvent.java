package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Actions;
import view.ChooseFromDraftPanel;
import view.DialogFrame;

/**
 * <code>public class AddChildFromDraftEvent implements ActionListener</code>
 * <br>
 * This class handles action when user wants to add element from the draft<br>
 * and it essentially shows the <code>DialogFrame</code> and adds
 * <code>ChooseFromDraftPanel</code> to it.
 * 
 * @author Dejan Radmanovic
 * 
 * @see ChooseFromDraftPanel
 * @see DialogFrame
 *
 */
public class AddChildFromDraftEvent implements ActionListener {

	/**
	 * An instance of <code>boolean</code> that tells whether user is adding
	 * project from draft or document
	 */
	private boolean isProject;

	/**
	 * An instance of <code>Actions</code> on which will be draft project add
	 * into.
	 * 
	 * @see Actions
	 */
	private Actions instance;

	/**
	 * Default constructor
	 * 
	 * @param isProject
	 *            An instance of <code>boolean</code> that tells whether user is
	 *            adding project from draft or document
	 * @param instance
	 *            An instance of <code>Actions</code> on which will be draft
	 *            project add into.
	 * @see Actions
	 */
	public AddChildFromDraftEvent(boolean isProject, Actions instance) {
		this.isProject = isProject;
		this.instance = instance;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		DialogFrame dialog = new DialogFrame("Add From Draft");
		dialog.setContentPane(new ChooseFromDraftPanel(isProject, dialog, instance));
		dialog.pack();
		dialog.setVisible(true);
	}

}
