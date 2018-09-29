package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import javax.swing.tree.MutableTreeNode;

/**
 * <code>public abstract class Element</code> which extends
 * <code>Observable</code> and implements
 * <code> Serializable, MutableTreeNode, Actions </code><br>
 * Purpose of class is generalization of all types of the elements whit common
 * characteristics.
 * 
 * @author Dejan Radmanovic
 * @see Observable
 * @see Serializable
 * @see MutableTreeNode
 * @see Actions
 */
public abstract class Element extends Observable implements Serializable, MutableTreeNode, Actions {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * an <code>protected MutableTreeNode</code> which represents slot in which
	 * the element is placed into.
	 */
	protected MutableTreeNode slot;

	/**
	 * an <code>protected String</code> which represents the name of the
	 * element.
	 */
	protected String name;

	/**
	 * an <code>protected ArrayList of Elements</code><br>
	 * So as the element is recursive composite component we need a list of
	 * other elements inside the element
	 */
	protected ArrayList<Element> elements;

	
	
	/**
	 * an default constructor for the <code>Element</code>
	 * 
	 * @param name
	 *            an <code>String</code> of an <code>Element</code> that will be
	 *            created
	 * @param slot
	 *            an parent <code>Slot</code> in which <code>Element</code> will
	 *            be placed into.
	 */
	public Element(String name, Slot slot) {
		this.name = name;
		this.slot = slot;
		elements = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public void rename(String newName) {
		name = newName;
		setChanged();
		notifyObservers(Actions.RENAME);
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

	public ArrayList<Element> getElements() {
		return elements;
	}
	
	public MutableTreeNode getSlot() {
		return slot;
	}
}
