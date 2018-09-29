package model;

import java.util.ArrayList;

/**
 * <code>public class Draft</code> is a class which contains an array lists of
 * floating nodes, nodes without parent.
 * 
 * @author Dejan Radmanovic
 *
 */
public class Draft {

	/**
	 * an <code>ArrayList of Projects</code> which are without parent Workspace,
	 * that needs to be assigned.
	 */
	private static ArrayList<Project> draftProjects = new ArrayList<Project>();

	/**
	 * an <code>ArrayList of GeDocumentsd</code> which are without parent
	 * Project, that needs to be assigned.
	 */
	private static ArrayList<GeDocument> draftDocuments = new ArrayList<GeDocument>();

	public static ArrayList<GeDocument> getDraftDocuments() {
		return draftDocuments;
	}

	public static ArrayList<Project> getDraftProjects() {
		return draftProjects;
	}
}
