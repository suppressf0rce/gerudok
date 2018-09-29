package control.pictureControllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.BunnyFrameListener;
import model.AboutModel;
import view.BunnyFrame;
import view.LogoPanel;
/**
 * A class that implements a MouseListener interface and changes in between a Sloth and a user picture whenever pressed.
 * @author Stefan
 *
 */
public class SlothPictureListener implements MouseListener {
	/**
	 * An instance of LogoPanel that SlothPictureListener belongs to.
	 * @author Stefan
	 */
	private LogoPanel logoPanel;
	
	/**
	 * A Constructor that takes a LogoPanel as an argument.
	 * @author Stefan
	 * @param logoPanel
	 */
	public SlothPictureListener(LogoPanel logoPanel) {
		this.logoPanel = logoPanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

		
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if(!AboutModel.getInstance().getIsSloth()){

			logoPanel.add(new LogoPanel(125, 172, 9));
			logoPanel.repaint();
			AboutModel.getInstance().setIsSloth(true);
		}
		else{
			logoPanel.add(new LogoPanel(125, 172, 3));
			logoPanel.repaint();
			AboutModel.getInstance().setIsSloth(false);
		}
		//System.out.println("Sloth: "+Settings.getInstance().getIsSloth());
		if(AboutModel.getInstance().getIsSloth() && AboutModel.getInstance().getIsLion() && AboutModel.getInstance().getIsLemur() && AboutModel.getInstance().getIsMouse()){
			BunnyFrame bf = new BunnyFrame();
			bf.addWindowListener(new BunnyFrameListener());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	
		
	}

}
