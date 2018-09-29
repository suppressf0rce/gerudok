package model;

import java.io.Serializable;
import java.util.Observable;

/**
 * <code>public class Document</code> is a class which extends
 * <code>Observable</code> and implements <code>Serializable</code><br>
 * This class is used for generalization of Project, Workspace and GeDocument
 * 
 * @author Dejan Radmanovic
 * @see Observable
 * @see Serializable
 * @see Project
 * @see GeDocument
 * @see Workspace
 *
 */
public class Document extends Observable implements Serializable {
	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;
	protected String name;

	/**
	 * Default constructor for <code>Document</code>
	 * 
	 * @param name
	 *            an <code>String</code> of a name of Document which will be
	 *            created.
	 */
	public Document(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
