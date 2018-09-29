package state;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import view.Canvas;

/**
 * <code>public class State</code> which implements
 * <code>{@link MouseListener}</code><br>
 * This class is used as generalization for other states.
 * 
 * @author Branko Vitorovic, Teodora Mladenovic
 * @see MouseListener
 */
public class State implements MouseListener {

	/**
	 * This is the <code>Canvas</code> which will be managed by the
	 * <code>StateManager</code>
	 * 
	 * @see Canvas
	 * @see StateManager
	 */
	protected Canvas canvas;

	/**
	 * This is default constructor for the State
	 * @param canvas an <code>Canvas</code> that will be managed by the State
	 * @see Canvas
	 */
	public State(Canvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
