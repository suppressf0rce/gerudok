package model;

import java.awt.Color;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code> public class GraphicElement</code> extends
 * <code>{@link Element}</code><br>
 * Represents all the graphic elements inside the GeRuDok
 * 
 * @author Dejan Radmanovic, Branko Vitorovic, Teodora Mladenovic
 * @see Element
 */
public class GraphicElement extends Element {

	/**
	 * <code>protected int</code> of X position of graphic element
	 */
	protected int x;

	/**
	 * <code>protected int</code> of Y position of graphic element
	 */
	protected int y;

	/**
	 * <code>protected Color</code> of the color of graphic element
	 * 
	 * @see Color
	 */
	protected Color color;

	/**
	 * <code> protected boolean</code> if element is selected
	 */

	protected boolean isSelected = false;

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the <code>GraphicElement</code>
	 * 
	 * @param slot
	 *            an <code>Slot</code> in which will be created Element placed
	 *            into.
	 * @param name
	 *            an <code>String</code> which represents the name of the
	 *            Created element.
	 * 
	 * @see Slot
	 */
	public GraphicElement(Slot slot, String name) {
		super(name, slot);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child instanceof GraphicElement)
			elements.add((Element) child);
	}

	@Override
	public void remove(int index) {
		elements.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		elements.remove(node);
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromParent() {
		slot = null;
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		slot = newParent;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return elements.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return elements.size();
	}

	@Override
	public TreeNode getParent() {
		return slot;
	}

	@Override
	public int getIndex(TreeNode node) {
		return elements.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		if (elements.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLeaf() {
		if (elements.size() > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return Collections.enumeration(elements);
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		GraphicElement p;
		if (node != null)
			p = (GraphicElement) node;
		else
			p = new GraphicElement(null, "Graphic Element");
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Color getColor() {
		return color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
