package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import model.Actions;
import model.GeDocument;
import model.Project;
import view.DialogFrame;
import view.MainFrame;
import view.ShareDocumentPanel;

/**
 * <code>public class ShareNodeEvent implements ActionListener</code><br>
 * This class handles action when the user wants to share the specific action
 * with another parent
 * 
 * @author Dejan Radmanovic
 * @see Actions#share(javax.swing.tree.MutableTreeNode, java.util.Observer...)
 */
public class ShareNodeEvent implements ActionListener {

	/**
	 * An instance of the <code>Actions</code> that will be shared with another
	 * parent.
	 * 
	 * @see Actions
	 */
	private Actions actionToShare;

	/**
	 * An instance of the <code>DialogFrame</code> so we can handle the close
	 * action on the dialog
	 * 
	 * @see DialogFrame
	 */
	private DialogFrame dialog;

	/**
	 * An instance of <code>ShareDocumentPanel</code> used for accessing the
	 * information of the panel which contains the list of the selected projects
	 * that user wants to share his document with
	 * 
	 * @see ShareDocumentPanel
	 */
	private ShareDocumentPanel panel;

	/**
	 * An default constructor for the <code>ShareDocumentPanel</code>
	 * 
	 * @param actionToShare
	 *            An instance of the <code>Actions</code> that will be shared
	 *            with another parent.
	 * @param dialog
	 *            An instance of the <code>DialogFrame</code> so we can handle
	 *            the close action on the dialog
	 * @param panel
	 *            An instance of <code>ShareDocumentPanel</code> used for
	 *            accessing the information of the panel which contains the list
	 *            of the selected projects that user wants to share his document
	 *            with
	 * 
	 * @see Actions
	 * @see DialogFrame
	 * @see ShareDocumentPanel
	 */
	public ShareNodeEvent(Actions actionToShare, DialogFrame dialog, ShareDocumentPanel panel) {
		this.actionToShare = actionToShare;
		this.dialog = dialog;
		this.panel = panel;
	}

	@SuppressWarnings("static-access")
	@Override
	public void actionPerformed(ActionEvent e) {

		boolean containsAction = false;
		ArrayList<Project> projects = new ArrayList<>(panel.getList().getSelectedValuesList());
		for (Project p : projects) {
			for (GeDocument doc : p.getGeDocuments()) {
				if (doc == (GeDocument) actionToShare)
					containsAction = true;
			}
		}

		if (!containsAction) {
			for (Project p : projects) {
				actionToShare.share(p, MainFrame.getInstance().getTree());
				dialog.setVisible(false);
				dialog.dispose();
			}
		} else {
			JOptionPane option = new JOptionPane();
			option.showMessageDialog(MainFrame.getInstance(),
					"You cant share Document with project that already contains that document!");
		}

	}

}
