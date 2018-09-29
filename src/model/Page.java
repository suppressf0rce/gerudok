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
 * <code> public class Page</code> extends <code>{@link Observable}</code> and
 * implements
 * <code>{@link Serializable}, {@link MutableTreeNode}, {@link Actions}</code>
 * <br>
 * This class represents the model of all the pages inside the GeRuDok.
 * 
 * @author Dejan Radmanovic
 * @see Observable
 * @see Serializable
 * @see MutableTreeNode
 * @see Actions
 */
public class Page extends Observable implements Serializable, MutableTreeNode, Actions {
	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>private String</code> which represents the name of the page.
	 */
	private String name;

	/**
	 * an <code>ArrayList of Slots</code> which contains all the slots inside
	 * the page.
	 * 
	 * @see Slot
	 */
	private ArrayList<Slot> slots;

	/**
	 * an <code>ArrayList of GeDocuments</code> of all GeDocuments which is this
	 * page inserted into.
	 * 
	 * @see GeDocument
	 */
	private ArrayList<GeDocument> geDocuments;

	/**
	 * An default Constructor for the Page
	 * 
	 * @param name
	 *            an <code>String</code> which represents the name of the new
	 *            Page.
	 */
	public Page(String name) {
		slots = new ArrayList<>();
		geDocuments = new ArrayList<>();
		this.name = name;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return slots.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return slots.size();
	}

	@Override
	public TreeNode getParent() {
		return geDocuments.get(0);
	}

	@Override
	public int getIndex(TreeNode node) {
		return slots.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		if (slots.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLeaf() {
		if (slots.size() > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		// TODO Auto-generated method stub
		return Collections.enumeration(slots);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		slots.add((Slot) child);
	}

	@Override
	public void remove(int index) {
		slots.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		slots.remove(node);
	}

	@Override
	public void setUserObject(Object object) {

	}

	@Override
	public void removeFromParent() {
		geDocuments.clear();
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		geDocuments.add((GeDocument) newParent);
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		Slot p;
		if (node != null)
			p = (Slot) node;
		else
			p = new Slot();
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
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
				e.printStackTrace();
			}
		}
		setChanged();
		notifyObservers(Actions.DELETE);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Slot> getSlots() {
		return slots;
	}
}
