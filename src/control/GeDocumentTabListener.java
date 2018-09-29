package control;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.MutableTreeNode;

import main.Utils;
import model.GeDocument;
import view.GeDocumentTabbedPane;
import view.MainFrame;
/**
 * A Custom-made ChangeListener which tries to find GeDocument in the tree based on its selected tab.
 * 
 * How it works: When a tab is selected, it tries to find the GeDocument it represents in jtree and select it.
 * 
 * If it fails to find the component, it just exits the method.
 * @author Stefan
 *
 */
public class GeDocumentTabListener implements  ChangeListener{
	/** A GeDocumentTabbedPane that this GeDocumentTabListener "listens" to.
	 * @author Stefan
	 */
	private GeDocumentTabbedPane tb;
	
	/**
	 * A Constructor that takes in a GeDocumentTabbedPane as a parameter.
	 * @param tb
	 */
	public GeDocumentTabListener(GeDocumentTabbedPane tb) {
		this.tb = tb;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		GeDocument g = null;
		
		try{ // in case there is no selected tab
			g = tb.getProject().getGeDocuments().get(tb.getSelectedIndex());
		}
		catch(Exception zz){
			return;
		}
		
		MutableTreeNode root = (MutableTreeNode)MainFrame.getInstance().getTree().getModel().getRoot();
		ArrayList<MutableTreeNode> mtn = Utils.traverse(MainFrame.getInstance().getTree().getModel(), root, g, null);
		
		MutableTreeNode temp = mtn.get(0);
		
		if(g.isState()==false){ // in case a GeDocument is not directly selected on the JTree.  (for e.g. if GeDoc's child is selected, this won't be executed.)
			g.setState(true);
		}
		else{
		MainFrame.getInstance().getTree().setSelectionPath(Utils.getPath(temp));
		//System.out.println(new TreePath(temp));
		
		}
	}

}
