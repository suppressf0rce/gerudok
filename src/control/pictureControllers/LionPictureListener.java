package control.pictureControllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.BunnyFrameListener;
import model.AboutModel;
import view.BunnyFrame;
import view.LogoPanel;
/**
 * A class that implements a MouseListener interface and changes in between a Lion and a user picture whenever pressed.
 * @author Stefan
 *
 */
public class LionPictureListener implements MouseListener {
	/**
	 * An instance of LogoPanel that LionPictureListener belongs to.
	 * @author Stefan
	 */
	private LogoPanel logoPanel;
	/**
	 * A Constructor that takes a LogoPanel as an argument.
	 * @author Stefan
	 * @param logoPanel
	 */
	public LionPictureListener(LogoPanel logoPanel){
		this.logoPanel = logoPanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		if(!AboutModel.getInstance().getIsLion()){

			logoPanel.add(new LogoPanel(125, 172, 8));
			logoPanel.repaint();
			AboutModel.getInstance().setIsLion(true);
		}
		else{
			logoPanel.add(new LogoPanel(125, 172, 4));
			logoPanel.repaint();
			AboutModel.getInstance().setIsLion(false);
		}
		//System.out.println("Lion: "+Settings.getInstance().getIsLion());
		if(AboutModel.getInstance().getIsSloth() && AboutModel.getInstance().getIsLion() && AboutModel.getInstance().getIsLemur() && AboutModel.getInstance().getIsMouse()){
			BunnyFrame bf = new BunnyFrame();
			bf.addWindowListener(new BunnyFrameListener());
		}
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
