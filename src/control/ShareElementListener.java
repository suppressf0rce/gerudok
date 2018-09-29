package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Actions;
import view.DialogFrame;
import view.ShareDocumentPanel;
import view.TreeView;
/**
 * This class is a custom ActionListener. It pops a dialog frame and sets a ShareDocumentPanel instance for its content.
 * @author Dejan
 *
 */
public class ShareElementListener implements ActionListener{
	/**
	 * A JTree instance that this class listens to.
	 * @author Dejan
	 */
	private TreeView tree;
	/**
	 * A constructor that takes a TreeView as a parameter.
	 * @author Dejan
	 * @param tree
	 */
	public ShareElementListener(TreeView tree) {
		this.tree = tree;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		DialogFrame dialog = new DialogFrame("Share with project:");

		dialog.setContentPane(new ShareDocumentPanel((Actions) tree.getSelectionPath().getLastPathComponent(), dialog, tree));
		dialog.pack();
		dialog.setVisible(true);	}

}
