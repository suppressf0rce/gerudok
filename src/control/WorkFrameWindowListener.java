package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.tree.MutableTreeNode;

import model.Slot;
import view.Canvas;
import view.MainFrame;
import view.SlotPanel;
/**
 * A custom WindowListener. Its responsibility is to set canvas as a content to a SlotPanel
 * @author Stefan
 *
 */
public class WorkFrameWindowListener implements WindowListener	{
	/**
	 * A Slot based on which the SlotPanel will be called.
	 * @author Stefan
	 */
	private Slot s;
	/**
	 * A Canvas that the SLotPanel will contain.
	 * @author Stefan
	 */
	private Canvas c;
	/**
	 * A Constructor which takes a Canvas and a MutableTreeNode as parameters.
	 * @param canvas
	 * @param slot
	 * @author Stefan
	 */
	public WorkFrameWindowListener(Canvas canvas, MutableTreeNode slot) {
		this.s = (Slot) slot;
		this.c = canvas;
	}
 
	
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		SlotPanel sp = MainFrame.getInstance().getDesktopPane().getCallingSlot(s);
		sp.setContent(c);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
