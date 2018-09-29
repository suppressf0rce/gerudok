package model;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code>public class ImageElement</code> extends <code>{@link Element}</code>
 * <br>
 * Represents all the ImageElements inside the GeRuDok.
 * 
 * @author Dejan Radmanovic
 * @see Element
 */
public class ImageElement extends Element {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor for the <code>ImageElement</code><br>
	 * 
	 * @param slot
	 *            an <code>Slot</code> in which will be ImageElement placed
	 *            into.
	 * @param name
	 *            an <code>String</code> which represents the name of the new
	 *            ImageElement
	 * 
	 * @see Slot
	 */
	public ImageElement(Slot slot, String name) {
		super(name, slot);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child instanceof ImageElement)
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
		return Collections.enumeration(elements);
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		ImageElement p;
		if (node != null)
			p = (ImageElement) node;
		else
			p = new ImageElement(null, "Image Element");
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
	}

}
