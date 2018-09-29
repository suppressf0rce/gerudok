package state;

import java.awt.event.MouseEvent;

import main.Utils;
import model.Element;
import model.GraphicElement;
import model.RectangleElement;
import view.Canvas;
import view.MainFrame;

/**
 * <code>public class RectangleState</code> which extends
 * <code>{@link State}</code><br>
 * This class handles what happens when <code>RectangleState</code> is currently active on tool
 * bar and set as Current state
 * 
 * @author Branko Vitorovic, Teodora Mladenovic
 * @see State
 * @see StateManager
 *
 */
public class RectangleState extends State{

	/**
	 * An default constructor for the <code> RectangleState</code>
	 * 
	 * @param canvas
	 *            an <code>Canvas</code> that will be managed by the
	 *            <code>RectangleState</code>
	 * @see Canvas
	 */
	public RectangleState(Canvas canvas) {
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
		RectangleElement rectangle = new RectangleElement("Rectangle "+canvas.getGraphicElement().getChildCount());
		rectangle.setX(e.getX()-25);
		rectangle.setY(e.getY()-25);
		rectangle.setColor(Utils.getRandomColor());
		
		canvas.getGraphicElement().addChild(rectangle, null, MainFrame.getInstance().getTree());
		canvas.repaint();
	}
	
	}
