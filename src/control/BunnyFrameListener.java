package control;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import view.MainFrame;

/**
 * A class that implements a WindowListener. It is being set as always-on-top during its creation, and that action ceases once the Frame it listens to is closed.
 * @author Stefan
 *
 */

public class BunnyFrameListener implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		MainFrame.getInstance().setEnabled(false);
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		MainFrame.getInstance().setEnabled(true);
		MainFrame.getInstance().setAlwaysOnTop(true);
		
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
		
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
