package model;

import java.util.Observer;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * <code>public class TreeModel</code> extends
 * <code>{@link DefaultTreeModel}</code><br>
 * In this class we are connecting our Model with the model of the JTree
 * component
 * 
 * @author Dejan Radmanovic
 *
 */
public class TreeModel extends DefaultTreeModel {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor for the TreeModel<br>
	 * This constructor sets the root of the JTree with a specified TreeNode
	 * 
	 * @param observers
	 *            an <code>Observer</code> which will be added to the root
	 *            component if you don't want observer pass <code>NULL</code>
	 * @param root
	 *            an
	 *            <code>TreeNode</code> is the root tree node that will be set as a root of the tree model.
	 * @param asksAllowsChildren an <code>boolean</code> which indicates whether
	 *            model root component allows children or not.
	 * @param workspace
	 *            an <code>Workspace</code>, if you want to set root node to be
	 *            specific workspace, pass reference else pass <code>NULL</code>
	 * 
	 * @see TreeNode
	 * @see Workspace
	 */
	public TreeModel( TreeNode root, boolean asksAllowsChildren, Workspace workspace,Observer...observers) {
		super(root, asksAllowsChildren);
		
		for(Observer ob: observers){
			workspace.addObserver(ob);
		}
		if (workspace != null)
			this.setRoot(workspace);

	}

}
