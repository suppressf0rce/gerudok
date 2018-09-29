package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code>public class Slot</code> which extends the
 * <code>{@link Observable}</code> and implements
 * <code>{@link Serializable},{@link MutableTreeNode}, {@link Actions}</code>
 * <br>
 * This Class represents the model of the Slot in our GeRuDok
 * 
 * @author Dejan Radmanovic
 *
 */
public class Slot extends Observable implements Serializable, MutableTreeNode, Actions {
	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * an <code>ArrayList of Pages</code> which contains all the pages where is Slot placed into.
	 * @see Page
	 */
	private ArrayList<Page> pages;
	
	/**
	 * an <code>Element</code> which represents element which slot contains
	 * @see Element
	 */
	private Element element;

	/**
	 * Default Constructor for the slot 
	 */
	public Slot() {
		pages = new ArrayList<>();
		element = null;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return element;
	}

	@Override
	public int getChildCount() {
		if (element != null)
			return 1;
		else
			return 0;
	}

	@Override
	public TreeNode getParent() {
		return pages.get(0);
	}

	@Override
	public int getIndex(TreeNode node) {
		return 0;
	}

	@Override
	public boolean getAllowsChildren() {
		if (element != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLeaf() {
		if (element == null)
			return true;
		else
			return false;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		ArrayList<Element> tmp = new ArrayList<>();
		tmp.add(element);
		return Collections.enumeration(tmp);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		element = (Element) child;
	}

	@Override
	public void remove(int index) {
		element = null;
	}

	@Override
	public void remove(MutableTreeNode node) {
		element = null;
	}

	@Override
	public void setUserObject(Object object) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeFromParent() {
		pages.clear();
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		pages.add((Page) newParent);
	}

	@Override
	public String toString() {
		int index = getParent().getIndex(this) + 1;
		return "Slot " + index;
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {

		for (Observer ob : o) {
			((Element) node).addObserver(ob);
		}
		insert(node, 0);
		node.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
	}

	@Override
	public void delete(DeleteType delType, ArrayList<MutableTreeNode> references) {
		if (delType == DeleteType.DELETE_JUST_SELECTED) {
			((MutableTreeNode) getParent()).remove(this);
		} else if (delType == DeleteType.COMPLETE_DELETE) {
			((MutableTreeNode) getParent()).remove(this);
			for (MutableTreeNode node : references) {
				((MutableTreeNode) node.getParent()).remove(node);
			}
			try {
				finalize();
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		setChanged();
		notifyObservers(Actions.DELETE);
	}
	public Element getElement() {
		return element;
	}
}
