package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.tree.MutableTreeNode;

import main.Utils;
import model.Actions;
import view.DeleteElementPanel;
import view.DialogFrame;
import view.TreeView;

/**
 * <code>public class DeleteElementListener implements ActionListener</code><br>
 * This class handles the action when user wants to delete specific action(node)
 * <br>
 * It shows creates the new <code>DialogFrame</code> with the
 * <code>{@link DeleteElementPanel}</code>.
 * 
 * @author Dejan Radmanovic
 *
 * @see Actions
 * @see DialogFrame
 */
public class DeleteElementListener implements ActionListener {

	/**
	 * an instance of <code>TreeView</code> which will be used for traversing through the tree and getting all the instances of the item.
	 * @see Utils#traverse(javax.swing.tree.TreeModel, Object, Object, ArrayList)
	 */
	private TreeView tree;

	/**
	 * 
	 * @param tree  an instance of <code>TreeView</code> which will be used for traversing through the tree and getting all the instances of the item.
	 * @see Utils#traverse(javax.swing.tree.TreeModel, Object, Object, ArrayList)
	 */
	public DeleteElementListener(TreeView tree) {
		this.tree = tree;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<MutableTreeNode> references = Utils.traverseV2(tree.getModel(), tree.getModel().getRoot(),
				tree.getSelectionModel().getSelectionPath().getLastPathComponent(), null);

		DialogFrame dialog = new DialogFrame("Delete");

		dialog.setContentPane(new DeleteElementPanel(
				(Actions) tree.getSelectionModel().getSelectionPath().getLastPathComponent(), references, dialog));
		dialog.pack();
		dialog.setVisible(true);
	}
}
