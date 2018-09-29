package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.AddChildEvent;
import model.Actions;
import model.Draft;
import model.GeDocument;
import model.Project;

/**
 * <code>public class ChoseFromDraftPanel extends {@link JPanel}</code><br>
 * This class represents the panel on which will be shown draft projects or
 * documents for the user to chose from
 * 
 * @author Dejan Radmanovic
 * @see JPanel
 * @see GeDocument
 * @see Project
 */
@SuppressWarnings("serial")
public class ChooseFromDraftPanel extends JPanel {

	/**
	 * An <code>JList of Objects</code> which represents the reference to the
	 * <code>JList</code> component so it can be accessed later on.
	 * 
	 * @see JList
	 */
	private JList<Object> list;

	/**
	 * An reference to the <code>DialogFrame</code> in which will be this panel
	 * placed into. Used for adding action for the closing dialog.
	 * 
	 * @see DialogFrame
	 */
	private DialogFrame dialog;

	/**
	 * Default constructor which will create the panel.
	 * 
	 * @param isProject
	 *            an <code>boolean</code> which represents whether user is
	 *            picking from draft project or document.<br>
	 *            <strong><i>Use:</i></strong> if you want to pick from draft
	 *            projects pass <code><strong><i>true</i></strong></code><br>
	 *            or if you want to pick from draft documents pass
	 *            <code><strong><i>false</i></strong></code>
	 * @param dialog
	 *            an reference of <code>DialogFrame</code> in which will be this
	 *            panel placed into
	 * @param instance
	 *            an instance of <code>Actions</code> interface on which will be
	 *            element added into.
	 * 
	 * @see Project
	 * @see GeDocument
	 * @see DialogFrame
	 * @see Actions
	 */
	public ChooseFromDraftPanel(boolean isProject, DialogFrame dialog, Actions instance) {
		this.dialog = dialog;
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));

		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EmptyBorder(10, 0, 0, 0));
		add(horizontalBox, BorderLayout.SOUTH);

		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);

		JButton btnFinish = new JButton("Finish");
		horizontalBox.add(btnFinish);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			dialog.dispose();
		});
		horizontalBox.add(btnCancel);

		if (isProject) {
			list = new JList<>(Draft.getDraftProjects().toArray());
		} else
			list = new JList<>(Draft.getDraftDocuments().toArray());

		add(list, BorderLayout.CENTER);

		btnFinish.addActionListener(new AddChildEvent(instance, this));

	}

	public JList<Object> getList() {
		return list;
	}

	public DialogFrame getDialog() {
		return dialog;
	}
}
