package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import main.Utils;

/**
 * <code>public class Project</code> extends <code>{@link Document}</code> and
 * implements <code>{@link MutableTreeNode}, {@link Actions}</code><br>
 * This class represents the model of the projects of our GeRuDok.
 * 
 * @author Dejan Radmanovic, Stefan Cvetic
 * @see MutableTreeNode
 * @see Actions
 * @see Document
 */
public class Project extends Document implements MutableTreeNode, Actions {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>private String</code> of the path where is project currently
	 * serialized (Saved).
	 */
	private String path;

	/**
	 * <code>private Workspace</code> which represents parent workspace which
	 * contains Project
	 * 
	 * @see Workspace
	 */
	@SuppressWarnings("unused")
	private Workspace parentWorkspace;

	/**
	 * <code>private ArrayList of Workspaces</code> which contains project.
	 * 
	 * @see Workspace
	 */
	private ArrayList<Workspace> workspaces;

	/**
	 * <code>private ArrayList of GeDocuments</code> which contains all the
	 * documents of this project.
	 * 
	 * @see GeDocument
	 */
	private ArrayList<GeDocument> geDocuments;

	/**
	 * <code>private int</code> which stores the number of created documents
	 * inside this project.
	 */
	private int createdDocuments;

	/**
	 * <code>private boolean</code> that contains the value if the project is
	 * changed and wheter it needs to be saved or not.
	 */
	private boolean isChanged = true;

	/**
	 * A boolean that contains information whether the project is being currently selected or not.
	 * @author Stefan
	 */
	private boolean state = false;

	/**
	 * Default constructor for the Project
	 * 
	 * @param name
	 *            an <code>String</code> which represents the name of the new
	 *            project
	 */
	public Project(String name) {
		super(name);
		workspaces = new ArrayList<>();
		geDocuments = new ArrayList<>();
		state = true;
		path = null;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return geDocuments.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return geDocuments.size();
	}

	@Override
	public TreeNode getParent() {
		if (workspaces.size() > 0) {
			return workspaces.get(0);
		} else
			return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return geDocuments.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		if (geDocuments.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isLeaf() {
		if (geDocuments.size() > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return Collections.enumeration(geDocuments);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		geDocuments.add((GeDocument) child);
	}

	@Override
	public void remove(int index) {
		geDocuments.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		geDocuments.remove(node);
	}

	@Override
	public void setUserObject(Object object) {

	}

	@Override
	public void removeFromParent() {
		workspaces.clear();
		parentWorkspace = null;
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		workspaces.clear();
		workspaces.add((Workspace) newParent);
		parentWorkspace = (Workspace) newParent;
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		if (geDocuments.size() == 0 && Draft.getDraftDocuments().size() == 0) {
			createdDocuments = 1;
		}
		GeDocument p;
		if (node != null) {
			p = (GeDocument) node;
		} else
			p = new GeDocument(generateGeDocumentName());
		if (o != null) {
			for (Observer ob : o) {
				p.addObserver(ob);
			}
		}
		insert(p, 0);
		p.setParent(this);
		isChanged = true;
		setChanged();
		notifyObservers(Actions.INSERT);
	}

	@Override
	public void rename(String newString) {
		name = newString;
		setChanged();
		isChanged = true;
		notifyObservers(Actions.RENAME);
	}

	@Override
	public void delete(DeleteType delType, ArrayList<MutableTreeNode> references) {
		if (delType == DeleteType.DELETE_JUST_SELECTED) {
			((MutableTreeNode) getParent()).remove(this);
		} else if (delType == DeleteType.COMPLETE_DELETE) {
			for (MutableTreeNode node : references) {
				((MutableTreeNode) node.getParent()).remove(node);
			}
			this.removeFromParent();
			try {
				finalize();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		} else {
			unbind(references);

		}

		setChanged();
		isChanged = true;
		notifyObservers(Actions.DELETE);
	}

	@Override
	public void unbind(ArrayList<MutableTreeNode> references) {
		for (MutableTreeNode node : references) {
			((MutableTreeNode) node.getParent()).remove(node);
		}
		Draft.getDraftProjects().add(this);
		this.removeFromParent();

		setChanged();
		isChanged = true;
		notifyObservers(Actions.UNBIND);
	}

	@Override
	public void save(String path) {
		this.path = path;
		Utils.serialize(path, this, ".proj");
		isChanged = false;
	}

	public ArrayList<GeDocument> getGeDocuments() {
		return geDocuments;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	private String generateGeDocumentName() {
		int found = 0;
		if (Draft.getDraftDocuments().size() == 0) {
			createdDocuments = 1;
		}

		while (true) {
			found = 0;
			while (found == 0) {
				for (GeDocument g : geDocuments) {
					if (g.getName().equals("Document " + createdDocuments)) {
						createdDocuments++;
						found = 1;
						break;
					}
				}
				if (found == 0) {
					for (GeDocument g : Draft.getDraftDocuments()) {
						if (g.getName().equals("Document " + createdDocuments)) {
							createdDocuments++;
							found = 1;
						}
					}
					if (found == 0)
						return "Document " + createdDocuments;
				}
			}
		}

	}

	public String getPath() {
		return path;
	}

	public boolean isChanged() {
		return isChanged;
	}

}
