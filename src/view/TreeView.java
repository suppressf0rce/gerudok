package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import control.JTreeMouseEvent;
import main.Utils;
import model.Actions;
import model.TreeModel;
import model.Workspace;

/**
 * <code>public class TreeView extends JTree implements Observer</code><br>
 * This class represents <code>JTree</code> which is used for displaying the
 * model.<br>
 * 
 * @author Dejan Radmanovic
 * @see JTree
 * @see TreeModel
 *
 */
@SuppressWarnings("serial")
public class TreeView extends JTree implements Observer {

	/**
	 * This variable saves instance of <code>TreePath</code> that is last
	 * selected.<br>
	 * Used for setting the last path when model.reload() is occurred.
	 */
	private TreePath parrentPath;

	public TreeView() {
		WorkspaceTreeCellRendered wstcr = new WorkspaceTreeCellRendered();
		this.setModel(
				new TreeModel(null, true, new Workspace("Workspace"), this, MainFrame.getInstance().getDesktopPane()));
		this.setCellRenderer(wstcr);
		this.addMouseListener(new JTreeMouseEvent(this, this));
		this.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				if (e.getPath() != null)
					parrentPath = e.getPath().getParentPath();
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		DefaultTreeModel model = (DefaultTreeModel) this.getModel();
		model.reload();

		if ((int) arg == Actions.RENAME) {
			this.expandPath(Utils.getPath((MutableTreeNode) o));
			this.setSelectionPath(Utils.getPath((MutableTreeNode) o));
		} else if ((int) arg == Actions.INSERT) {
			this.expandPath(Utils.getPath((MutableTreeNode) o));
			this.setSelectionPath(Utils.getPath((MutableTreeNode) o));
		} else if ((int) arg == Actions.DELETE) {
			this.expandPath(parrentPath);
			this.setSelectionPath(parrentPath);
		} else if ((int) arg == Actions.IMPORT) {
			Utils.traverseAndRecounstructObservers(model, o, this);
			this.expandPath(Utils.getPath((MutableTreeNode) o));
			this.setSelectionPath(Utils.getPath((MutableTreeNode) o));
		}
	}

}
