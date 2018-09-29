package control;

import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.MutableTreeNode;

import main.Utils;
import model.Page;
import view.MainFrame;
import view.PagePanel;
import view.PageTabbedPane;
/**
 * A custom ChangeListener which tries to select Page in JTree based on its selected tab.
 * If it fails to select it, it just returns back.
 * @author Stefan
 *
 */
public class PageTabListener implements ChangeListener {
	/**
	 * A PageTabbedPane that this PageTabListener "listens" to.
	 * @author Stefan 
	 */
	
	private PageTabbedPane tb;
	/**
	 * A Constructor that takes PageTabbedPane as a parameter.
	 * @author Stefan
	 * @param tb
	 */
	public PageTabListener(PageTabbedPane tb) {
		this.tb = tb;
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		if(!tb.isSelected())
			return;
		PagePanel tempPagePanel = (PagePanel)tb.getSelectedComponent();
		Page p  = null;
		try{ // in case no panel was selected, null would be returned and therefore this block would not get executed.
		p = tempPagePanel.getPage();
		}
		catch(Exception ex){
			return;
		}
		MutableTreeNode root = (MutableTreeNode)MainFrame.getInstance().getTree().getModel().getRoot();
		ArrayList<MutableTreeNode> mtn = Utils.traverse(MainFrame.getInstance().getTree().getModel(), root, p, null);
		
		MutableTreeNode temp = mtn.get(0);
		
		MainFrame.getInstance().getTree().setSelectionPath(Utils.getPath(temp));
		
		
	}

}
