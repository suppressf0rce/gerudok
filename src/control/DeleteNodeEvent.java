package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.tree.MutableTreeNode;

import model.Actions;
import model.DeleteType;
import view.DeleteElementPanel;
import view.DialogFrame;
import view.TreeView;

/**
 * <code>public class DeleteNodeEvent implements ActionListener</code><br>
 * This class handles action when user wants to delete the selected action from
 * the tree<br>
 * 
 * @see TreeView
 * @see Actions#delete(DeleteType, ArrayList)
 */
public class DeleteNodeEvent implements ActionListener {

	/**
	 * An instance of <code>Actions</code> which user wants to delete from the
	 * model
	 * 
	 * @see Actions
	 */
	private Actions action;

	/**
	 * An instance of <code>ArrayList of MutableTreeNodes</code> which
	 * represents all the references of the selected action for complete delete
	 * 
	 * @see DeleteType
	 */
	private ArrayList<MutableTreeNode> references;

	/**
	 * An instance of <code>DialogFrmae</code> so we can handle the close dialog
	 * when we are finished deleting the action.
	 * 
	 * @see DialogFrame
	 */
	private DialogFrame dialog;

	/**
	 * An instance of <code>DeleteElementPanel</code> so we can access the
	 * elements of this panel so we can get the list of selected projects for
	 * deletion.
	 * 
	 * @see DeleteElementPanel
	 */
	private DeleteElementPanel panel;

	/**
	 * Default constructor for the <code>DeleteElementPanel</code>
	 * 
	 * @param action
	 *            An instance of <code>Actions</code> which user wants to delete
	 *            from the model
	 * @param references
	 *            An instance of <code>ArrayList of MutableTreeNodes</code>
	 *            which represents all the references of the selected action for
	 *            complete delete
	 * @param dialog
	 *            An instance of <code>DialogFrmae</code> so we can handle the
	 *            close dialog when we are finished deleting the action.
	 * @param panel
	 *            An instance of <code>DeleteElementPanel</code> so we can
	 *            access the elements of this panel so we can get the list of
	 *            selected projects for deletion.
	 * @see Actions
	 * @see DeleteType
	 * @see DialogFrame
	 * @see DeleteElementPanel
	 */
	public DeleteNodeEvent(Actions action, ArrayList<MutableTreeNode> references, DialogFrame dialog,
			DeleteElementPanel panel) {
		this.action = action;
		this.references = references;
		this.dialog = dialog;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (panel.getRbDeleteSelectedItem().isSelected()) {
			if (panel.getChckbxSaveToDraft().isSelected())
				action.delete(DeleteType.UNBIND, references);
			else
				action.delete(DeleteType.DELETE_JUST_SELECTED, references);
		} else {
			if (panel.getChckbxSaveToDraft().isSelected())
				action.delete(DeleteType.UNBIND, references);
			else
				action.delete(DeleteType.COMPLETE_DELETE, references);
		}
		dialog.dispose();
	}

}
