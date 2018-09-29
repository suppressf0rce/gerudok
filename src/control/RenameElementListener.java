package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.tree.MutableTreeNode;

import view.DialogFrame;
import view.RenameNodePanel;
/**
 * This class is a custom ActionListener. It pops a dialog in which it asks user to
 *  rename a MutableTreeNode which is being displayed in TreeView.
 * @author Dejan
 *
 */
public class RenameElementListener implements ActionListener{
	/**
	 * An instance of MutableTreeNode this listener manipulates.
	 * @author Dejan
	 */
	private MutableTreeNode node;
	
	/**
	 * A constructor that takes in MutabledTreeNode as a parameter.
	 * @param node
	 */
	public RenameElementListener(MutableTreeNode node) {
		this.node = node;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DialogFrame dialog = new DialogFrame("Rename");
		dialog.setContentPane(new RenameNodePanel(dialog, node));
		dialog.pack();
		dialog.setVisible(true);
	}

}
