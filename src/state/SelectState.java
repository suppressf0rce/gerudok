package state;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.Element;
import model.GraphicElement;
import view.Canvas;

/**
 * <code>public class SelectState</code> which extends
 * <code>{@link State}</code><br>
 * This class handles what happens when Select State is currently active on tool
 * bar and set as Current state
 * 
 * @author Branko Vitorovic, Teodora Mladenovic
 * @see State
 * @see StateManager
 *
 */
public class SelectState extends State implements MouseMotionListener {

	int startX;
	int endX;
	int startY;
	int endY;

	private Point clickPoint;

	/**
	 * An default constructor for the <code> SelectState</code>
	 * 
	 * @param canvas
	 *            an <code>Canvas</code> that will be managed by the
	 *            <code>SelectState</code>
	 * @see Canvas
	 */
	public SelectState(Canvas canvas) {
		super(canvas);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);
		startX = e.getX();
		startY = e.getY();

		clickPoint = e.getPoint();
		canvas.setSelectionBounds(null);

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		super.mouseReleased(e);

		clickPoint = null;

		for (Element elm : canvas.getGraphicElement().getElements()) {

			GraphicElement element = (GraphicElement) elm;
			element.setSelected(false);

		}
		for (Element elm : canvas.getGraphicElement().getElements()) {
			GraphicElement element = (GraphicElement) elm;
			if (startX == e.getX() && startY == e.getY()) {
				if (startX > element.getX() && startX < element.getX() + 50 && startY > element.getY()
						&& startY < element.getY() + 50) {
					element.setSelected(true);
					break;
				}
			}

			if (Math.min(startX, e.getPoint().getX()) < element.getX()
					&& Math.max(startX, e.getPoint().getX()) > element.getX()
					&& Math.min(startY, e.getPoint().getY()) < element.getY()
					&& Math.max(startY, e.getPoint().getY()) > element.getY()) {
				element.setSelected(true);
			}

		}
		
		canvas.setSelectionBounds(null);

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point dragPoint = e.getPoint();
		int x = Math.min(clickPoint.x, dragPoint.x);
		int y = Math.min(clickPoint.y, dragPoint.y);
		int width = Math.max(clickPoint.x - dragPoint.x, dragPoint.x - clickPoint.x);
		int height = Math.max(clickPoint.y - dragPoint.y, dragPoint.y - clickPoint.y);
		canvas.setSelectionBounds(new Rectangle(x, y, width, height));
		canvas.repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
