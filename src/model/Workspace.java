package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import main.Utils;

/**
 * <code>public class Workspace</code> extends <code>{@link Document}</code> and
 * implements <code>{@link MutableTreeNode}, {@link Actions}</code><br>
 * This class represents the model of workspace in our GeRudok.
 * 
 * @author Dejan Radmanovic, Stefan Cvetic
 * @see Document
 * @see MutableTreeNode
 * @see Actions
 */
public class Workspace extends Document implements MutableTreeNode, Actions {

	/**
	 * Version 0.1
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * an <code>private ArrayList of Projects</code> which contains all the
	 * projects of the workspace
	 * 
	 * @see Project
	 */
	private ArrayList<Project> projects;

	/**
	 * an<code>private int</code> which counts the number of createdProjects
	 * inside the workspace
	 */
	private int createdProjects;

	/**
	 * Default constructor for the Workspace
	 * 
	 * @param name
	 *            an <code> String</code> which will represent the name of new
	 *            Worskapce
	 */
	public Workspace(String name) {
		super(name);
		projects = new ArrayList<>();
	}

	@Override
	public TreeNode getChildAt(int childIndex) {
		return projects.get(childIndex);
	}

	@Override
	public int getChildCount() {
		return projects.size();
	}

	@Override
	public TreeNode getParent() {
		return null;
	}

	@Override
	public int getIndex(TreeNode node) {
		return projects.indexOf(node);
	}

	@Override
	public boolean getAllowsChildren() {
		if (projects.size() > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean isLeaf() {
		if (projects.size() > 0)
			return false;
		else
			return true;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Enumeration children() {
		return Collections.enumeration(projects);
	}

	@Override
	public void insert(MutableTreeNode child, int index) {
		projects.add((Project) child);
	}

	@Override
	public void remove(int index) {
		projects.remove(index);
	}

	@Override
	public void remove(MutableTreeNode node) {
		projects.remove(node);
	}

	@Override
	public void setUserObject(Object object) {

	}

	@Override
	public void removeFromParent() {

	}

	@Override
	public void setParent(MutableTreeNode newParent) {

	}

	public Project getLastChild() {
		return projects.get(projects.size() - 1);
	}

	private String generateProjectName() {
		int found = 0;
		if (Draft.getDraftProjects().size() == 0) {
			createdProjects = 1;
		}

		while (true) {
			found = 0;
			while (found == 0) {
				for (Project p : projects) {
					if (p.getName().equals("Project " + createdProjects)) {
						createdProjects++;
						found = 1;
						break;
					}
				}
				if (found == 0) {
					for (Project p : Draft.getDraftProjects()) {
						if (p.getName().equals("Project " + createdProjects)) {
							createdProjects++;
							found = 1;
						}
					}
					if (found == 0)
						return "Project " + createdProjects;
				}
			}
		}

	}

	@Override
	public void addChild(MutableTreeNode node, String name, Observer... o) {
		Project p;
		if (projects.size() == 0 && Draft.getDraftProjects().size() == 0) {
			createdProjects = 1;
		}
		if (node != null) {
			p = (Project) node;
		} else
			p = new Project(generateProjectName());
		if (o != null) {
			for (Observer ob : o) {
				p.addObserver(ob);
			}
		}
		insert(p, 0);
		p.setParent(this);
		setChanged();
		notifyObservers(Actions.INSERT);
//		System.out.println(p.countObservers());

	}

	@Override
	public void importChild(String path, Observer... o) {
		Project p = null;
		p = (Project) Utils.deserialize(path, ".proj");
		if (p != null) {
			System.out.println("Imported: " + p);
			addChild(p, null, o);
		} else {
			System.out.println("Error on import");
		}

		setChanged();
		notifyObservers(Actions.IMPORT);
	}

	@Override
	public void save(String path) {
		FileOutputStream fos = null;
		PrintWriter pw = null;
		String finalPath = null;
		if (path.endsWith(".ws"))
			finalPath = path;
		else
			finalPath = path + ".ws";
		try {
			fos = new FileOutputStream(finalPath);
			pw = new PrintWriter(fos);
			for (Project p : projects) {
				pw.println(p.getPath());
			}
			pw.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Workspace changeWorkspace(String pathToNewWorkspace) {
		File f = new File(pathToNewWorkspace);
		Workspace newWorkspace = new Workspace("Workspace");
		if (f.exists()) {
			FileInputStream fis = null;
			Scanner scn = null;
			try {
				fis = new FileInputStream(pathToNewWorkspace);
				scn = new Scanner(fis);
				while (scn.hasNextLine()) {
					String newLine = scn.nextLine();
					newWorkspace.importChild(newLine);
					System.out.println(newLine);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return newWorkspace;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

}
