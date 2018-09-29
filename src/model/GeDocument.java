package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * <code>public class GeDocument</code> which extends
 * <code>{@link Document}</code> and implements
 * <code>{@link MutableTreeNode}, {@link Actions}</code> This class is model for
 * our Documents inside the GeRuDok.
 * 
 * @author Dejan Radmanovic, Stefan Cvetic
 * @see Document
 * @see MutableTreeNode
 * @see Actions
 */
public class GeDocument extends Document implements MutableTreeNode, Actions, Cloneable {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>private ArrayList of Pages</code> which this Document contains.
	 */
	private ArrayList<Page> pages;

	/**
	 * <code>private ArrayList of Projects</code> which contains this Document.
	 */
	private ArrayList<Project> projects;

	/**
	 * <code>private int</code> which stores the number of created pages inside
	 * this document.
	 */
	private int createdPages;

	/**
	 * A boolean that contains information whether the project is being currently selected or not.
	 * @author Stefan
	 */
	private boolean state;
	
	private GeDocument original = null;

	/**
	 * Default constructor for the <code>GeDocument</code>
	 * 
	 * @param name
	 *            an <code>String</code> which represents the name of GeDocument
	 *            instance that will be created
	 */
	public GeDocument(String name) {
		super(name);
		pages = new ArrayList<>();
		projects = new ArrayList<>();
		state = true;
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return pages.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return pages.size();
	}

	@Override
	public TreeNode getParent() {
		if (projects.size() > 0) {
			return projects.get(0);
		} else
			return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return pages.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		if (pages.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean isLeaf() {
		if (pages.size() > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return Collections.enumeration(pages);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		pages.add((Page) child);
	}

	@Override
	public void remove(int index) {
		pages.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		pages.remove(node);
	}

	@Override
	public void setUserObject(Object object) {

	}

	@Override
	public void removeFromParent() {
		projects.clear();
	}

	@Override
	public void setParent(MutableTreeNode newParent) {
		projects.add((Project) newParent);
	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		if (pages.size() == 0)
			createdPages = 1;
		Page p;
		if (node != null)
			p = (Page) node;
		else
			p = new Page(generatePageName());
		for (Observer ob : o) {
			p.addObserver(ob);
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
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
			for(int i = 0; i<projects.size();i++){
				if(projects.get(i).getGeDocuments().contains(this)){
					projects.get(i).remove(this);
					projects.remove(i);
				}
			}
			if(this.getOriginal()==null){
				if(references.size()>0){
					((GeDocument)references.get(0)).setOriginal(null);
				}
			}
		} else if (delType == DeleteType.COMPLETE_DELETE) {
			for(Project p: projects){
				p.remove(this);
			}
			for(MutableTreeNode doc:references){
					((GeDocument)doc).delete(DeleteType.DELETE_JUST_SELECTED, references);
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
		notifyObservers(Actions.DELETE);
	}

	@Override
	public void unbind(ArrayList<MutableTreeNode> references) {
		for (MutableTreeNode node : references) {
			((MutableTreeNode) node.getParent()).remove(node);
			System.out.println(node);
		}
		Draft.getDraftDocuments().add(this);
		this.removeFromParent();

		setChanged();
		notifyObservers(Actions.UNBIND);
	}
	
	@Override
	public void share(MutableTreeNode project, Observer... o){
		if(project instanceof Project){
			try {
				GeDocument clone = (GeDocument) this.clone();
				clone.setOriginal(this);
				((Project) project).addChild(clone, null, o);
				clone.setParent(project);
				setChanged();
				notifyObservers(Actions.SHARE);
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String generatePageName() {
		int found = 0;
		createdPages = 1;
		while (true) {
			found = 0;
			while (found == 0) {
				for (Page p : pages) {
					if (p.getName().equals("Page " + createdPages)) {
						createdPages++;
						found = 1;
						break;
					}
				}
				if (found == 0) {
					return "Page " + createdPages;
				}
			}
		}

	}

	public ArrayList<Page> getPages() {
		return pages;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public GeDocument getOriginal() {
		return original;
	}

	public void setOriginal(GeDocument original) {
		this.original = original;
	}
	
	public ArrayList<Project> getProjects() {
		return projects;
	}
	
}
