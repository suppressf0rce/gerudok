package control.pictureControllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.BunnyFrameListener;
import model.AboutModel;
import view.BunnyFrame;
import view.LogoPanel;
/**
 * A class that implements a MouseListener interface and changes in between a Lemur and a user picture whenever pressed.
 * @author Stefan
 *
 */
public class LemurPictureListener implements MouseListener {
	private LogoPanel logoPanel;
	/**
	 * A Constructor that takes a LogoPanel as an argument.
	 * @author Stefan
	 * @param logoPanel
	 */
	public LemurPictureListener(LogoPanel logoPanel) {
		this.logoPanel = logoPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@SuppressWarnings("static-access")
	@Override
	public void mousePressed(MouseEvent e) {

		if (!AboutModel.getInstance().getIsLemur()) {
			logoPanel.add(new LogoPanel(125, 172, 7));
			logoPanel.repaint();
			AboutModel.getInstance().setIsLemur(true);
		} else {
			logoPanel.add(new LogoPanel(125, 172, 6));
			logoPanel.repaint();
			AboutModel.getInstance().setIsLemur(false);
		}
		if (AboutModel.getInstance().getInstance().getIsSloth() && AboutModel.getInstance().getInstance().getIsLion()
				&& AboutModel.getInstance().getInstance().getIsLemur()
				&& AboutModel.getInstance().getInstance().getIsMouse()) {
			BunnyFrame bf = new BunnyFrame();
			bf.addWindowListener(new BunnyFrameListener());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		return;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		return;

	}

	@Override
	public void mouseExited(MouseEvent e) {
		return;

	}

}
