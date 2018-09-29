package state;

import view.Canvas;
import view.WorkFrame;

/**
 * <code>public class StateManager</code><br>
 * This class contains all the possible states, and manages them, by calling the
 * method {@link #setCurrentState(State)}
 * 
 * @author Branko Vitorovic, Teodora Mladenovic
 *
 */
public class StateManager {

	/**
	 * <code>private State</code> represents currently selected, active, state
	 * that can be be changed by calling {@link #setCurrentState(State)}
	 * 
	 * @see State
	 * @see #setCurrentState(State)
	 */
	private State currentState;

	/**
	 * <code>private CircleState</code> this variable represents a
	 * <code>CircleState</code>, when a Create circle is active on toolbar, it
	 * becomes active state
	 * 
	 * @see CircleState
	 */
	private CircleState circleState;

	/**
	 * <code>private RectnagleState</code> this variable represents a
	 * <code>RectangleState</code>, when Create rectangle is active on toolbar,
	 * it becomes active state.
	 * 
	 * @see RectangleState
	 */
	private RectangleState rectangleState;

	/**
	 * <code> private SelectState</code> this variable represents a
	 * <code>SelectState</code>, when Selection is active on toolbar, it becomes
	 * active state
	 * 
	 * @see SelectState
	 */
	private SelectState selectState;

	/**
	 * <code>private Canvas</code> this variable represents a
	 * <code>Canvas (JPanel)</code>, which <code>StateManager</code> manages.
	 * 
	 * @see Canvas
	 */
	private Canvas canvas;

	/**
	 * This is default constructor for the <code>StateManager</code>
	 * 
	 * @param canvas
	 *            an <code>Canvas (JPanel)</code>, which
	 *            <code>StateManager</code> will be managing.
	 * @param workframe
	 *            an <code>Workfame</code> which, contains canvas and toolbar,
	 *            that <code>StateManger</code> will be managing.
	 */
	public StateManager(Canvas canvas, WorkFrame workframe) {
		circleState = new CircleState(canvas);
		rectangleState = new RectangleState(canvas);
		selectState = new SelectState(canvas);
		currentState = selectState;
		canvas.addMouseListener(currentState);
		canvas.addMouseMotionListener(selectState);
		this.canvas = canvas;
	}

	public State getCurrentState() {
		return currentState;
	}

	/**
	 * This method sets the current State.<br>
	 * <strong><i>Use example:</i></strong>
	 * <code>setCurrentState(manager.getSelectState())</code>
	 * 
	 * @param newState
	 *            an new<code>State</code> which will be set as current state.
	 */
	public void setCurrentState(State newState) {
		canvas.removeMouseListener(currentState);
		
		if(currentState instanceof SelectState)
			canvas.removeMouseMotionListener(selectState);
		
		this.currentState = newState;
		canvas.addMouseListener(newState);
		
		if(currentState instanceof SelectState)
			canvas.addMouseMotionListener(selectState);
	}

	public CircleState getCircleState() {
		return circleState;
	}

	public RectangleState getRectangleState() {
		return rectangleState;
	}

	public SelectState getSelectState() {
		return selectState;
	}

}
