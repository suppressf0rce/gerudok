package model;

/**
 * <code>public class CircleElement extends GraphicElement</code> This class
 * represents Circle which is type of Graphic element and it is drawn
 * accordingly with x and y coordinates and size and color.
 * 
 * @author PEBKAC
 * @see GraphicElement
 */
public class CircleElement extends GraphicElement {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor
	 * 
	 * @param name
	 *            an <code>String</code> of a name for new circle
	 */
	public CircleElement(String name) {
		super(null, name);
	}

	/**
	 * An Size of circle, currently static fixed value but can be changed later
	 * on if needed
	 */
	private static final int size = 50;

	public static int getSize() {
		return size;
	}

}
