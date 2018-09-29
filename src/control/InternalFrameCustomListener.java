package control;

import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.text.Position.Bias;
import javax.swing.tree.TreePath;

import model.Project;
import view.MainFrame;

/**
 * A custom InternalFrameListener that selects a project in the TreeView.
 * 
 * @author Stefan
 *
 */
public class InternalFrameCustomListener implements InternalFrameListener {
	/**
	 * A Project that InternalFrameCustomListener uses to select a Project in
	 * the tree.
	 * 
	 * @author Stefan
	 */
	Project p;

	/**
	 * A Constructor that takes Project as a parameter.
	 * 
	 * @author Stefan
	 * 
	 * @param p
	 */
	public InternalFrameCustomListener(Project p) {
		this.p = p;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("static-access")
	@Override
	public void internalFrameActivated(InternalFrameEvent e) {

		TreePath tp = MainFrame.getInstance().getTree().getNextMatch(p.getName(), 0, Bias.Forward);
		if (p.isState()) {
			MainFrame.getInstance().getTree().setSelectionPath(tp);
		}

		else {
			p.setState(true);
			MainFrame.getInstance().getDesktopPane().focusInternalFrame(p);
		}
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub

	}

}
