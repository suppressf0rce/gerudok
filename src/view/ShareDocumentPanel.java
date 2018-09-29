package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import control.ShareNodeEvent;
import model.Actions;
import model.GeDocument;
import model.Project;
import model.Workspace;

/**
 * <code>public class ShareDocumentPanel extends JPanel</code><br>
 * This class represents panel which will be shown when user wants to share <code>{@link GeDocument}</code><br>
 * It contains an <code>JList</code> of all projects for user to choose from.
 * @author Dejan Radmanovic
 * @see GeDocumentTabbedPane
 * @see Project
 * @see JList
 */
@SuppressWarnings("serial")
public class ShareDocumentPanel extends JPanel{

	/**
	 * an reference of the <code>JList</code> of the all projects inside the current workspace, so it can be accessed later on
	 * @see Project
	 */
	private JList<Project> list;
	
	/**
	 * An default constructor which will create the panel.
	 * @param documentToShare an instance of <code>Action</code> in this case document which will be shared with another project
	 * @param dialog an instance of <code>DialogFrame</code> in which will be panel placed into.
	 * @param tree an instace of <code>JTree</code> so it can get from tree all the projects inside
	 * 
	 * @see Actions
	 * @see DialogFrame
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ShareDocumentPanel(Actions documentToShare, DialogFrame dialog, TreeView tree) {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setBorder(new EmptyBorder(10, 0, 0, 0));
		add(horizontalBox, BorderLayout.SOUTH);
		
		Component horizontalGlue = Box.createHorizontalGlue();
		horizontalBox.add(horizontalGlue);
		
		JButton btnOk = new JButton("OK");
		horizontalBox.add(btnOk);
		
		Component horizontalStrut = Box.createHorizontalStrut(10);
		horizontalBox.add(horizontalStrut);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e->{
			dialog.setVisible(false);
			dialog.dispose();
		});
		horizontalBox.add(btnCancel);

		ArrayList<Project> projects = ((Workspace)tree.getModel().getRoot()).getProjects();
		
		list = new JList(projects.toArray());
		add(list, BorderLayout.CENTER);
		
		btnOk.addActionListener(new ShareNodeEvent(documentToShare, dialog, this));
	}
	
	public JList<Project> getList() {
		return list;
	}
	
}
