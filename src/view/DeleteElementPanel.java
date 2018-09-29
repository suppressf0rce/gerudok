package view;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.MutableTreeNode;

import control.DeleteNodeEvent;
import main.Utils;
import model.Actions;
import model.GeDocument;
import model.Project;

/**
 * <code>public class DeleteElementPanel extends JPanel</code><br>
 * This class represents the panel on which will be shwon all the options for
 * user to choose from for different type of deletion.
 * 
 * @author Dejan Radmanovic
 * @see JPanel
 */
@SuppressWarnings("serial")
public class DeleteElementPanel extends JPanel {

	/**
	 * An reference of <code>JRadioButton</code> so it can be later accessed<br>
	 * Represents to check whether delete selected item button is active or not
	 * 
	 * @see JRadioButton
	 */
	private JRadioButton rbDeleteSelectedItem;

	/**
	 * An reference of <code>JRadioButton</code> so it can be later accessed<br>
	 * Represents to check whether delete all references item button is active
	 * or not
	 * 
	 * @see JRadioButton
	 */
	private JRadioButton rbDeleteAllReferences;

	/**
	 * An reference of <code>JCheckBox</code> so it can be later accessed<br>
	 * Represents to check whether saved to draft item button is active or not
	 * 
	 * @see JCheckBox
	 */
	private JCheckBox chckbxSaveToDraft;

	/**
	 * An default constructor which will create the panel.
	 * 
	 * @param action
	 *            an instance of <code>Actions</code> on which will be delete be
	 *            performed on
	 * @param references
	 *            an <code>ArrayList of MutablTreeNode</code> is an array of all
	 *            references where is original passed action
	 * @param dialog
	 *            an reference to the <code>DialogFrame</code> in which will be
	 *            this panel placed into
	 * 
	 * @see Utils#traverseV2(javax.swing.tree.TreeModel, Object, Object,
	 *      ArrayList)
	 * @see DialogFrame
	 */
	public DeleteElementPanel(Actions action, ArrayList<MutableTreeNode> references, DialogFrame dialog) {
		setBorder(new EmptyBorder(10, 10, 10, 10));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		ButtonGroup group = new ButtonGroup();
		rbDeleteSelectedItem = new JRadioButton("Delete Selected Item");
		GridBagConstraints gbc_chckbxDeleteSelectedItem = new GridBagConstraints();
		gbc_chckbxDeleteSelectedItem.anchor = GridBagConstraints.WEST;
		gbc_chckbxDeleteSelectedItem.insets = new Insets(0, 0, 10, 0);
		gbc_chckbxDeleteSelectedItem.gridx = 0;
		gbc_chckbxDeleteSelectedItem.gridy = 0;
		group.add(rbDeleteSelectedItem);
		add(rbDeleteSelectedItem, gbc_chckbxDeleteSelectedItem);

		rbDeleteAllReferences = new JRadioButton("Delete All References");
		GridBagConstraints gbc_chckbxDeleteAllReferences = new GridBagConstraints();
		gbc_chckbxDeleteAllReferences.anchor = GridBagConstraints.WEST;
		gbc_chckbxDeleteAllReferences.insets = new Insets(0, 0, 10, 0);
		gbc_chckbxDeleteAllReferences.gridx = 0;
		gbc_chckbxDeleteAllReferences.gridy = 1;
		group.add(rbDeleteAllReferences);
		if (references.size() > 0)
			add(rbDeleteAllReferences, gbc_chckbxDeleteAllReferences);

		chckbxSaveToDraft = new JCheckBox("Save to Draft");
		GridBagConstraints gbc_chckbxSaveToDraft = new GridBagConstraints();
		gbc_chckbxSaveToDraft.insets = new Insets(0, 0, 10, 0);
		gbc_chckbxSaveToDraft.anchor = GridBagConstraints.WEST;
		gbc_chckbxSaveToDraft.gridx = 0;
		gbc_chckbxSaveToDraft.gridy = 2;
		if (action instanceof Project || action instanceof GeDocument) {
			add(chckbxSaveToDraft, gbc_chckbxSaveToDraft);
		}
		rbDeleteAllReferences.addActionListener(e -> {
			if (rbDeleteAllReferences.isSelected())
				chckbxSaveToDraft.setEnabled(true);
			else {
				if (references.size() > 1)
					chckbxSaveToDraft.setEnabled(true);
				else
					chckbxSaveToDraft.setEnabled(false);
			}
		});
		rbDeleteSelectedItem.addActionListener(e -> {
			if (references.size() > 1)
				chckbxSaveToDraft.setEnabled(true);
			else
				chckbxSaveToDraft.setEnabled(false);
		});
		rbDeleteSelectedItem.setSelected(true);

		Box horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.anchor = GridBagConstraints.EAST;
		gbc_horizontalBox.gridx = 0;
		gbc_horizontalBox.gridy = 5;
		add(horizontalBox, gbc_horizontalBox);

		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new DeleteNodeEvent(action, references, dialog, this));
		horizontalBox.add(btnFinish);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			dialog.dispose();
		});
		horizontalBox.add(btnCancel);

		for (MutableTreeNode ref : references) {
			System.out.println(ref);
		}

	}

	public JRadioButton getRbDeleteSelectedItem() {
		return rbDeleteSelectedItem;
	}

	public JRadioButton getRbDeleteAllReferences() {
		return rbDeleteAllReferences;
	}

	public JCheckBox getChckbxSaveToDraft() {
		return chckbxSaveToDraft;
	}

}
