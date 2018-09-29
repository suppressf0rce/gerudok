package model;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code>public class TextElement</code> extends <code>{@link Element}</code>
 * <br>
 * This class represents the model of all text elements inside the GeRuDok.
 * 
 * @author Dejan Radmanovic, Stefan Cvetic
 * @see Element
 */
public class TextElement extends Element {
	private String text;
	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This is the default constructor for the TextElement
	 * 
	 * @param name
	 *            an <code>String</code> this is the name for the new text
	 *            element
	 * @param slot
	 *            an <code>Slot</code> in which is our TextElement placed into.
	 */
	public TextElement(String name, Slot slot) {
		super(name, slot);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		if (child instanceof TextElement)
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
		TextElement p;
		if (node != null)
			p = (TextElement) node;
		else
			p = new TextElement("Text Element", null);
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
	}
	
	public String getText() {
		return text;
	}
	public void setText(String content) {
		this.text = content;
		setChanged();
		notifyObservers(Actions.PROP_CHANGE);
	}
}
