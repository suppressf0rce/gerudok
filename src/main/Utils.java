package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import model.GeDocument;
import view.InternalFrame;
import view.MainFrame;

public class Utils {

	/**
	 * This method returns the scaled <code>{@link Icon}</code> from the
	 * specified URL path.<br>
	 * 
	 * @author Dejan Radmanovic
	 * @param imageURL
	 *            an <code>{@link URL}</code> url (path) to the icon that needs
	 *            to be scaled;
	 * @return an scaled <code>{@link Icon}</code> which is the dimension 18x18
	 * @see Icon
	 * @see URL
	 */
	public static Icon getScaledIcon(URL imageURL) {
		Icon icon = null;
		if (imageURL != null) {
			ImageIcon imageIcon = new ImageIcon(imageURL);
			Image image = imageIcon.getImage();
			Image newImage = image.getScaledInstance(18, 18, Image.SCALE_SMOOTH);
			icon = new ImageIcon(newImage);
		}
		return icon;
	}

	/**
	 * 
	 * This method cascades all open InternalFrames inside of the DesktopPane.
	 * @author Stefan
	 */
	public static void cascadeWindows() {
		int x = 30;
		int y = 30;
		int c = 1;
		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) {
			if (!i.isShowing())  // skips closed InternalFrames
				continue;
			i.setLocation(x * c, y * c);
			i.toFront();
			c++;
		}

	}

	/**
	 *
	 * This method tiles horizontally all of the open InternalFrames inside of DesktopPane.
	 * @author Stefan
	 */
	public static void tileHorizontally() {
		int counter = 0;
		int width = MainFrame.getInstance().getDesktopPane().getWidth();
		int height = MainFrame.getInstance().getDesktopPane().getHeight();
		int offset;

		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) { // counts how many frames are showing on the screen
			if (i.isShowing())
				counter++;
		}
		if (counter == 0)
			return;
		else
			offset = (int) Math.floor(width / counter); // divides the width by the number of showing frames

		int index = 0;
		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) {
			if (!i.isShowing()) // skips positioning for closed InternalFrames
				continue;
			i.setSize(new Dimension(offset, height));
			i.setLocation(offset * index++, 0);
		}
	}

	/**
	 * This method tiles vertically all of the open InternalFrames inside of DesktopPane.
	 * @author Stefan
	 */
	public static void tileVertically() {
		int counter = 0;
		int width = MainFrame.getInstance().getDesktopPane().getWidth();
		int height = MainFrame.getInstance().getDesktopPane().getHeight();
		int offset;

		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) { // counts how many frames are showing on the screen
			if (i.isShowing()) // skips closed InternalFrames
				counter++;
		}

		if (counter == 0)
			return;
		else
			offset = (int) Math.floor(height / counter);

		int index = 0;
		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) { // positions the open InternalFrames
			if (!i.isShowing())
				continue;
			i.setSize(new Dimension(width, offset));
			i.setLocation(0, offset * index++);
		}
	}

	/**
	 * This method returns the whole root (without decimal remainders) of the first 32 numbers.
	 * @author Stefan
	 */
	public static double getNextWholeRoot(int x) {
		int[] projectsWithWholeRoot = { 1, 4, 9, 16, 25, 36, 49, 64, 81, 100, 121, 144, 169, 196, 225, 256, 289, 324,
				361, 400, 441, 484, 529, 576, 625, 676, 729, 784, 841, 900, 961, 1024 };

		for (int i = 0; i < projectsWithWholeRoot.length; i++) {

			if (x <= projectsWithWholeRoot[i])
				return Math.sqrt(projectsWithWholeRoot[i]);
		}

		return 0;
	}

	/**
	 * This method tiles opened InternalFrames into Grid view.
	 * @author Stefan
	 */
	public static void tileGrid() {
		int noOfCells = 0;
		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) { // counts the number of showing InternalFrames
			if (i.isShowing())
				noOfCells++;
		}
		int desktopPaneWidth = MainFrame.getInstance().getDesktopPane().getWidth();
		int desktopPaneHeight = MainFrame.getInstance().getDesktopPane().getHeight();
		int sqrt = (int) getNextWholeRoot(noOfCells);
		int offsetX;
		int offsetY;
		if (noOfCells == 0)
			return;

		if (noOfCells == 1) {
			Utils.tileHorizontally();
			return;
		}
		if (noOfCells == 2) {
			Utils.tileHorizontally();
			return;
		}

		offsetX = (int) Math.floor(desktopPaneWidth / sqrt);
		offsetY = (int) Math.floor(desktopPaneHeight / sqrt);
		int x = 0; // acts as a row ( X axis)
		int y = 0; // acts as a column ( Y axis)
		
		for (InternalFrame i : MainFrame.getInstance().getDesktopPane().getInternalFrames()) { 
			i.setSize(new Dimension(offsetX, offsetY));
			if (x < sqrt) { // lays out the components in X axis oriented row as long their number is lesser than the first next whole root.
				i.setLocation(x * offsetX, y * offsetY);
				x++;
			} else {  // resets the X axis and increases the Y axis ( this way new InternalFrames are put in a row below than the previous ones were).
				x = 0;
				y++;
				i.setLocation(x * offsetX, y * offsetY);
				x++;

			}
		}
	}

	/**
	 * This is recursive method which traverses through the
	 * <code>TreeModel</code> and finds the same reference inside the model for
	 * the passed node.<br>
	 * After finding and filling it returns the array of all found references.
	 * 
	 * @param model
	 *            an reference to the <code>TreeModel</code>you want to traverse
	 *            through.
	 * @param o
	 *            an <code>Object</code> inside the <code>TreeModel</code> you
	 *            want to begin your search with.
	 * @param node
	 *            an <code>Object</code> represents the reference of the item
	 *            you are searching for inside the <code>TreeModel</code>
	 * @param references
	 *            an <code>ArrayList of MutableTreeNodes</code> which is used
	 *            for recursive purpose. In this Method to work here you
	 *            <strong>must</strong> pass the <strong><i>NULL</i></strong>.
	 * @return an <code>ArrayList of MutableTreeNodes</code> which contains all
	 *         the found references.
	 * @author Dejan Radmanovic
	 * @see TreeModel
	 * @see MutableTreeNode
	 */
	public static ArrayList<MutableTreeNode> traverse(TreeModel model, Object o, Object node,
			ArrayList<MutableTreeNode> references) {
		if (references == null) {
			references = new ArrayList<>();
		}
		int cc;
		cc = model.getChildCount(o);
		for (int i = 0; i < cc; i++) {
			Object child = model.getChild(o, i);
			if (node == child) {
				// System.out.println("Added");
				references.add((MutableTreeNode) child);
			}
			if (model.isLeaf(child)) {
				// System.out.println("Found Leaf: "+child);
			} else {
				// System.out.println("Found container: "+child);
				traverse(model, child, node, references);
			}
		}
		return references;
	}

	/**
	 * This is another version of
	 * {@link #traverse(TreeModel, Object, Object, ArrayList)}<br>
	 * It is recursive method which traverses through the <code>TreeModel</code>
	 * and finds the reference with same original inside the model for the
	 * passed node.<br>
	 * After finding and filling it returns the array of all found references.
	 * 
	 * @param model
	 *            an reference to the <code>TreeModel</code>you want to traverse
	 *            through.
	 * @param o
	 *            an <code>Object</code> inside the <code>TreeModel</code> you
	 *            want to begin your search with.
	 * @param node
	 *            an <code>Object</code> represents the reference of the item
	 *            you are searching for inside the <code>TreeModel</code>
	 * @param references
	 *            an <code>ArrayList of MutableTreeNodes</code> which is used
	 *            for recursive purpose. In this Method to work here you
	 *            <strong>must</strong> pass the <strong><i>NULL</i></strong>.
	 * @return an <code>ArrayList of MutableTreeNodes</code> which contains all
	 *         the found references.
	 * @author Dejan Radmanovic
	 * @see TreeModel
	 * @see MutableTreeNode
	 */
	public static ArrayList<MutableTreeNode> traverseV2(TreeModel model, Object o, Object node,
			ArrayList<MutableTreeNode> references) {
		if (references == null) {
			references = new ArrayList<>();
		}
		int cc;
		cc = model.getChildCount(o);
		for (int i = 0; i < cc; i++) {
			Object child = model.getChild(o, i);

			if (child instanceof GeDocument) {
				if (((GeDocument) child).getOriginal() == node) {
					references.add((MutableTreeNode) child);
				}
			}

			if (model.isLeaf(child)) {
			} else {
				traverseV2(model, child, node, references);
			}
		}
		return references;
	}

	/**
	 * This method builds the <code>TreePath</code> for the passed
	 * <code>TreeNode</code>
	 * 
	 * @param treeNode
	 *            an <code>MutableTreeNode</code> for which you want to build
	 *            <code>TreePath</code>
	 * @return an <code>TreePath</code> for the passed
	 *         <code>MutableTreeNode</code>
	 * @author Stefan Cvetic, Dejan Radmanovic
	 * @see MutableTreeNode
	 * @see TreePath
	 */
	public static TreePath getPath(MutableTreeNode treeNode) {
		List<Object> nodes = new ArrayList<Object>();
		if (treeNode != null) {
			nodes.add(treeNode);
			treeNode = (MutableTreeNode) treeNode.getParent();
			while (treeNode != null) {
				nodes.add(0, treeNode);
				treeNode = (MutableTreeNode) treeNode.getParent();
			}
		}

		return nodes.isEmpty() ? null : new TreePath(nodes.toArray());
	}

	/**
	 * This method serializes (Saves) the specified object to the specified path
	 * with the specified extension.
	 * 
	 * @param path
	 *            an <code>String</code> which represents the path where you
	 *            want your object to be serialized on the system.
	 * @param object
	 *            an <code>Object</code> which you want to serialize (save).
	 * @param extension
	 *            an <code>String</code> which is the extension you wan to save
	 *            your file with for example .proj(Project1.proj).
	 * @author Dejan Radmanovic
	 */
	public static void serialize(String path, Object object, String extension) {
		ObjectOutputStream out = null;
		FileOutputStream fos = null;

		String finalPath = null;
		if (path.endsWith(extension)) {
			finalPath = path;
		} else
			finalPath = path + extension;

		try {
			fos = new FileOutputStream(finalPath);
			out = new ObjectOutputStream(fos);
			out.writeObject(object);

			fos.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method deserializes, loads object (creates object instance), from
	 * the specified path with the specified extension
	 * 
	 * @param path
	 *            an <code>String</code> from which location you want your
	 *            object to be loaded from
	 * @param extension
	 *            an <code>String</code> which represents the extension of the
	 *            file you want to deserialize
	 * @return an <code>Object</code> which instance is created from the
	 *         specified parameters
	 */
	public static Object deserialize(String path, String extension) {
		FileInputStream fis = null;
		ObjectInputStream in = null;
		Object object = null;

		String finalPath = null;
		if (path != null && !path.isEmpty()) {
			if (path.endsWith(extension))
				finalPath = path;
			else
				finalPath = path + extension;
			File f = new File(finalPath);
			if (f.exists()) {
				try {
					fis = new FileInputStream(finalPath);
					in = new ObjectInputStream(fis);
					object = in.readObject();
				} catch (IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return object;
	}

	/**
	 * This method traverses through the specified model and adds observer for
	 * each element inside the model.<br>
	 * General purpose of the method is reconstructing observers to the
	 * deserialized <code>TreeNode</code> which dosen't have any observers.
	 * 
	 * @param model
	 *            an reference to the <code>TreeModel</code>you want to traverse
	 *            through.
	 * @param o
	 *            an <code>Object</code> inside the <code>TreeModel</code> you
	 *            want to begin your search with.
	 * @param observer
	 *            an <code>Observer</code> you want to add to the
	 *            <code>TreeNodes</code>
	 * @author Dejan Radmanovic
	 * @see Observer
	 * @see TreeModel
	 */
	public static void traverseAndRecounstructObservers(TreeModel model, Object o, Observer observer) {
		int cc;
		cc = model.getChildCount(o);
		for (int i = 0; i < cc; i++) {
			Object child = model.getChild(o, i);
			if (model.isLeaf(child))
				((Observable) child).addObserver(observer);
			else {
				((Observable) child).addObserver(observer);
				traverseAndRecounstructObservers(model, child, observer);
			}
		}
	}

	/**
	 * This method returns the random color.
	 * 
	 * @return an random <code>Color</code>
	 * @author Teodora Mladenovic
	 * @see Color
	 */
	public static Color getRandomColor() {
		Random randomNumber = new Random();
		return new Color(randomNumber.nextFloat(), randomNumber.nextFloat(), randomNumber.nextFloat());
	}
}
