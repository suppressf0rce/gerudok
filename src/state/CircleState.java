package state;

import java.awt.event.MouseEvent;

import main.Utils;
import model.CircleElement;
import model.Element;
import model.GraphicElement;
import view.Canvas;
import view.MainFrame;

/**
 * <code>public class CircleState</code> which extends
 * <code>{@link State}</code><br>
 * This class handles what happens when <code>CircleState</code> is currently active on tool
 * bar and set as Current state
 * 
 * @author Branko Vitorovic, Teodora Mladenovic
 * @see State
 * @see StateManager
 *
 */
public class CircleState extends State {

	/**
	 * An default constructor for the <code> CircleState</code>
	 * 
	 * @param canvas
	 *            an <code>Canvas</code> that will be managed by the
	 *            <code>CircleState</code>
	 * @see Canvas
	 */
	

	
	public CircleState(Canvas canvas) {
		super(canvas);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		super.mousePressed(e);
		
for (Element elm : canvas.getGraphicElement().getElements()){
			
			GraphicElement element = (GraphicElement) elm;
			element.setSelected(false);
			
		}
		
		CircleElement circle = new CircleElement("Circle "+canvas.getGraphicElement().getChildCount());
		circle.setColor(Utils.getRandomColor());
		circle.setX(e.getX()-25);
		circle.setY(e.getY()-25);
		
		canvas.getGraphicElement().addChild(circle, null, MainFrame.getInstance().getTree());
		canvas.repaint();
		
	}

	
	}
