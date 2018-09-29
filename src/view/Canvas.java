package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Area;

import javax.swing.JPanel;

import model.CircleElement;
import model.Element;
import model.GraphicElement;
import model.RectangleElement;

/**
 * <code>public class Canvas extends {@link JPanel}</code><br>
 * This class represents a JPanel (Canvas) on which will be passed graphic
 * element drawn on.
 * 
 * @author Teodora Mladenovic, Branko Vitorovic
 * @see GraphicElement
 * @see JPanel
 *
 */
@SuppressWarnings("serial")
public class Canvas extends JPanel {

	/**
	 * An reference of <code>GraphicElement</code> which will be drawn on the
	 * <code>Canvas</code>
	 * 
	 * @see GraphicElement
	 */
	private GraphicElement graphicElement;

	/**
	 * an instance of <code>Rectangle</code> Used for drawing the selection rectangle.
	 * @see Rectangle
	 */
	private Rectangle selectionBounds;

	/**
	 * This is the default constructor for the <code>Canvas</code>
	 * 
	 * @param graphicElement
	 *            represents the <code>GraphicElement</code> which will be drawn
	 *            on the <code>Canvas</code>
	 * @see GraphicElement
	 */
	public Canvas(GraphicElement graphicElement) {
		this.graphicElement = graphicElement;
		setOpaque(false);
	}

	@SuppressWarnings("static-access")
	@Override
	public void paint(Graphics arg0) {
		super.paint(arg0);
		Graphics2D g2 = (Graphics2D) arg0;
		for (Element e : graphicElement.getElements()) {
			if (e instanceof RectangleElement) {
				g2.setColor(((RectangleElement) e).getColor());
				if (((RectangleElement) e).isSelected()) {
					g2.setColor(Color.BLACK);
				}

				g2.fillRect(((RectangleElement) e).getX(), ((RectangleElement) e).getY(),
						((RectangleElement) e).getSize(), ((RectangleElement) e).getSize());
			} else if (e instanceof CircleElement) {
				g2.setColor(((CircleElement) e).getColor());
				if (((CircleElement) e).isSelected()) {
					g2.setColor(Color.BLACK);
				}

				g2.fillOval(((CircleElement) e).getX(), ((CircleElement) e).getY(), ((CircleElement) e).getSize(),
						((CircleElement) e).getSize());
			}
		}
		this.repaint();
	}

	public GraphicElement getGraphicElement() {
		return graphicElement;
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		g2d.setColor(new Color(255, 255, 255, 128));

		Area fill = new Area(new Rectangle(new Point(0, 0), getSize()));
		if (selectionBounds != null) {
			fill.subtract(new Area(selectionBounds));
		}
		g2d.fill(fill);
		if (selectionBounds != null) {
			g2d.setColor(Color.BLACK);
			g2d.draw(selectionBounds);
		}
		g2d.dispose();
	}

	public Rectangle getSelectionBounds() {
		return selectionBounds;
	}

	public void setSelectionBounds(Rectangle selectionBounds) {
		this.selectionBounds = selectionBounds;
	}

}
