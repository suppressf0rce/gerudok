package control.pictureControllers;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import control.BunnyFrameListener;
import model.AboutModel;
import view.BunnyFrame;
import view.LogoPanel;
/**
 * A class that implements a MouseListener interface and changes in between a Mouse and a user picture whenever pressed.
 * @author Stefan
 *
 */
public class MousePictureListener implements MouseListener{
	/**
	 * An instance of LogoPanel that MousePictureListener belongs to.
	 * @author Stefan
	 */
	private LogoPanel logoPanel;
	
	/**
	 * A Constructor that takes a LogoPanel as an argument.
	 * @author Stefan
	 * @param logoPanel
	 */
	public MousePictureListener(LogoPanel logoPanel){
		this.logoPanel = logoPanel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(!AboutModel.getInstance().getIsMouse()){
			logoPanel.add(new LogoPanel(125, 172, 10));
			logoPanel.repaint();

			AboutModel.getInstance().setIsMouse(true);
		}
		else{
			logoPanel.add(new LogoPanel(125, 172, 5));
			logoPanel.repaint();
			AboutModel.getInstance().setIsMouse(false);
		}
		//System.out.println("Mouse: "+Settings.getInstance().getIsMouse());
		
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
		// TODO Auto-generated method stub
		
	}

}
