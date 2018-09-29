package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.tree.MutableTreeNode;

import control.RenameNodeEvent;
import model.Actions;

/**
 * <code>public class RenameNodePanel extends JPanel</code><br>
 * This class represents panel which will be shown when user wants to rename the
 * specific action.<br>
 * It contains 1 <code>{@link JTextField}</code> and 2
 * <code>{@link JButton}s</code>
 * 
 * @author Dejan Radmanovic
 *
 */
@SuppressWarnings("serial")
public class RenameNodePanel extends JPanel {

	/**
	 * an reference to the <code>JTextField</code>which will be used later on
	 * 
	 * @see JTextField
	 */
	private JTextField textField;

	/**
	 * Default constructor for creating the panel
	 * 
	 * @param dialog
	 *            an reference to the <code>DialogFrame</code> on which will be
	 *            panel be placed into.
	 * @param node
	 *            an reference to the <code>MutableTreeNode</code> which will
	 *            this panel rename\
	 * 
	 * @see MutableTreeNode
	 * @see DialogFrame
	 */
	public RenameNodePanel(DialogFrame dialog, MutableTreeNode node) {

		this.setPreferredSize(new Dimension(350, 70));
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 0, 0, 0, 0 };
		gridBagLayout.rowHeights = new int[] { 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		JLabel lblNewName = new JLabel("New Name:");
		lblNewName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblNewName = new GridBagConstraints();
		gbc_lblNewName.insets = new Insets(0, 0, 10, 10);
		gbc_lblNewName.anchor = GridBagConstraints.EAST;
		gbc_lblNewName.gridx = 0;
		gbc_lblNewName.gridy = 0;
		add(lblNewName, gbc_lblNewName);

		textField = new JTextField(node.toString());
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 10, 0);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		add(textField, gbc_textField);
		textField.setColumns(10);

		Box horizontalBox = Box.createHorizontalBox();
		GridBagConstraints gbc_horizontalBox = new GridBagConstraints();
		gbc_horizontalBox.anchor = GridBagConstraints.EAST;
		gbc_horizontalBox.gridx = 2;
		gbc_horizontalBox.gridy = 2;
		add(horizontalBox, gbc_horizontalBox);

		JButton btnFinish = new JButton("Finish");
		horizontalBox.add(btnFinish);

		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> {
			dialog.dispose();
		});
		horizontalBox.add(btnCancel);
		btnFinish.addActionListener(new RenameNodeEvent((Actions) node, this, dialog));

		textField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnFinish.doClick();
				}
			}
		});

		textField.selectAll();

	}

	public JTextField getTextField() {
		return textField;
	}
}
