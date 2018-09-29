package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;

import model.Actions;
import model.Draft;
import model.Slot;
import view.AssignElementPanel;
import view.ChooseFromDraftPanel;
import view.DialogFrame;

/**
 * <code>public class AddChildEvent implements ActionListener</code><br>
 * This class handles action when you want to add node to the other node<br>
 * 
 * @author Dejan Radmanovic
 * @see Actions
 */
public class AddChildEvent implements ActionListener {

	/**
	 * an instance of <code>Actions</code> on which will be new node added.
	 * 
	 * @see Actions
	 */
	private Actions instance;

	/**
	 * An instance of <code>ChooseFromDraftPanel</code> panel on which is list
	 * of draft elements<br>
	 * if user wants to add element from draft.<br>
	 * <strong><i>Use:</i></strong> if you don't want to add element from draft
	 * pass <code><strong>NULL</strong></code>.
	 * 
	 * @see ChooseFromDraftPanel
	 */
	private ChooseFromDraftPanel panel;

	/**
	 * An array of <code>Observers</code> which will be added to the newly
	 * created child.
	 * 
	 * @see Observer
	 */
	private Observer[] o;

	/**
	 * And default constructor
	 * 
	 * @param selectedInstance
	 *            an instance of <code>Actions</code> on which will be new node
	 *            added.
	 * @param panel
	 *            An instance of <code>ChooseFromDraftPanel</code> panel on
	 *            which is list of draft elements<br>
	 *            if user wants to add element from draft.<br>
	 *            <strong><i>Use:</i></strong> if you don't want to add element
	 *            from draft pass <code><strong>NULL</strong></code>.
	 * @param o
	 *            An indefinite array of <code>Observers</code> which will be
	 *            added to the newly created child.
	 * 
	 * @see Actions
	 * @see ChooseFromDraftPanel
	 * @see Observer
	 */
	public AddChildEvent(Actions selectedInstance, ChooseFromDraftPanel panel, Observer... o) {
		this.instance = selectedInstance;
		this.o = o;
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (instance instanceof Slot) {
			DialogFrame dialog = new DialogFrame("Choose Element");
			dialog.getContentPane().add(new AssignElementPanel(instance, dialog, o));
			dialog.pack();
			dialog.setVisible(true);
		} else if (panel == null)
			instance.addChild(null, null, o);
		else {
			for (Object doc : panel.getList().getSelectedValuesList()) {
				instance.addChild((MutableTreeNode) doc, null);
				Draft.getDraftDocuments().remove(doc);
				Draft.getDraftProjects().remove(doc);
			}
			panel.getDialog().dispose();
		}
	}

}
