package model;

/**
 * <code>public class RectnagleElement</code> which extends <code>{@link GraphicElement}</code>
 * <br>
 * This is class which represents the model of our Rectangle graphic element in
 * our GeRuDok
 * 
 * @author Branko Vitorovic, Teodora Mladenovci
 * @see GraphicElement
 *
 */
public class RectangleElement extends GraphicElement {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>private static int</code> that represents the size of the rectangle
	 * element
	 */
	private static int size = 50;

	/**
	 * Default constructor for the RectangleElement
	 * 
	 * @param name
	 *            an <code>String</code> which represents the name of the new
	 *            RectangleElement
	 */
	public RectangleElement(String name) {
		super(null, name);
	}

	public static int getSize() {
		return size;
	}

}
