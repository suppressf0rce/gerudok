package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import main.Utils;
import model.Page;
import view.MainFrame;
/** 
 * This class implements MouseListener interface. Its responsibility is traverse through TreeView and select a Page in the model based on the Page in its
 * constructor.
 * @author Stefan
 *
 */
public class PanelMouseListener implements MouseListener{
	/**
	 * A Page which mouseClicked method will try to find in TreeView and select it.
	 * @author Stefan
	 */
	private Page p;
	/**
	 * A Constructor that takes Page as a parameter.
	 * @author Stefan
	 * @param p
	 */
	public PanelMouseListener(Page p) {
		this.p = p;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		MutableTreeNode root = (MutableTreeNode)MainFrame.getInstance().getTree().getModel().getRoot();
		ArrayList<MutableTreeNode> mtn = Utils.traverse(MainFrame.getInstance().getTree().getModel(), root, p, null);
		
		MutableTreeNode temp = mtn.get(0);
		
		MainFrame.getInstance().getTree().setSelectionPath(Utils.getPath(temp));
		System.out.println(new TreePath(temp));
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
