package model;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code>public class ComplexElement extends Element</code> This class
 * represents a complex elements which handler is not yet implemented but can be
 * easily added later on.<br>
 * Complex element is an advanced element which can accept all type of elements
 * and of course other complex elements
 * 
 * @author Dejan Radmanovic
 * @see Element
 *
 */
public class ComplexElement extends Element {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * A default constructor for circle element
	 * 
	 * @param slot
	 *           an <code>Slot</code> an slot in which will created element be
	 *            placed into.
	 * @param name
	 *           an <code>String</code> of a name of a element.
	 */
	public ComplexElement(Slot slot, String name) {
		super(name, slot);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child instanceof Element)
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
		// TODO Auto-generated method stub
		return Collections.enumeration(elements);
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		Element p;
		if (node != null)
			p = (Element) node;
		else
			p = new ComplexElement(null, "Complex Element");
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
	}

}
