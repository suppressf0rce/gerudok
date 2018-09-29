package model;

import java.util.ArrayList;
import java.util.Observer;

import javax.swing.tree.MutableTreeNode;

/**
 * This interface represents all the actions that can happen on the model.<br>
 * Those actions are: Inserting, Renaming, Deleting, Saving, Unbinding, Changing workspace, Sharing.<Br>
 * 
 * 
 * @author Dejan Radmanovic
 *
 */
public interface Actions {

	/**
	 * Static integer that is used for informing Observers of Insert action that happened to the model so it can be properly handled.
	 */
	public static final int INSERT = 1;
	
	/**
	 * Static integer that is used for informing Observers of Rename action that happened to the model so it can be properly handled.
	 */
	public static final int RENAME = 2;
	
	/**
	 * Static integer that is used for informing Observers of Delete action that happened to the model so it can be properly handled.
	 */
	public static final int DELETE = 3;
	
	/**
	 * Static integer that is used for informing Observers of Unbind action that happened to the model so it can be properly handled.
	 */
	public static final int UNBIND = 4;
	
	/**
	 * Static integer that is used for informing Observers of Save action that happened to the model so it can be properly handled.
	 */
	public static final int SAVE = 5;
	
	/**
	 * Static integer that is used for informing Observers of Import action that happened to the model so it can be properly handled.
	 */
	public static final int IMPORT = 6;
	
	/**
	 * Static integer that is used for informing Observers of Change Workspace action that happened to the model so it can be properly handled.
	 */
	public static final int CHANGEWS = 7;
	
	/**
	 * Static integer that is used for informing Observers of Share action that happened to the model so it can be properly handled.
	 */
	public static final int SHARE = 8;
	
	/**
	 * Static integer that is used for informing Observers of Element's changed property that happened to the model so it can be properly handled.
	 */
	public static final int PROP_CHANGE = 9;
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * General purpose of method is to insert another child which exists or is it yet to be created
	 * @param node <code>MuatbleTreeeNode</code> this parameter is used when you want to insert already existing child into the other node,<br>
	 * or pass <code>NULL</code> if you want to create a new one
	 * @param name <code>String</code>, this parameter is used when you want to assign a name to the objec, or if you want name to be auto genereted pass null.
	 * @param o <code>Observer...</code>, this parameter is an array of indefinite size of observers that will be assigned to the node
	 */
	public default void addChild(MutableTreeNode node, String name, Observer... o){};
	
	/**
	 * This method is Overridable later on by the class which implements Actions interface or be left default which is empty<br>
	 * Purpose of the method is, as its name suggests, to rename the node which implements Actions Interface<br>
	 * @param newName an <code>String</code> of a new name that will be set.
	 */
	public default void rename(String newName){};
	
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * General purpose of method is to delete the node which implements this interface with different type of deletion.
	 * @param deleteType an <code>ENUM</code> of different types of deletion.
	 * @param references an <code>ArrayList of MutableTreeNode</code> is a list of all shared nodes references used for delete all type.<br>
	 * @see DeleteType
	 */
	public default void delete(DeleteType deleteType, ArrayList<MutableTreeNode> references){};
	
	/**
	 * This method is Overrideable latter on by the class which implements
	 * Actions interface or be left default which is empty.<br>
	 * Purpose of method is to Unbind the node or "Let it lose" (Let it free
	 * with no parent). When you want to delete the node, but not completely
	 * just save it to draft
	 * 
	 * @param references
	 *            an <code>ArrayList of MutableTreeNode</code> is a list of
	 *            multiple references if you want to unbind multiple references
	 *            at once.
	 */
	public default void unbind(ArrayList<MutableTreeNode> references){};
	
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * General Purpose of this method is to save node to the specified path and serialize component.
	 * @param path <code>String</code> of a path where that node will be serialized (Saved).
	 */
	public default void save(String path){};
	
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * Purpose of the method is to import already serialized, saved, Node from the specified path and assign an observers to it.
	 * @param path <code>String</code> of a path where is node serialized(Saved).
	 * @param o <code>Observer...</code>, this parameter is an array of indefinite size of observers that will be assigned to the node
	 */
	public default void importChild(String path, Observer... o){};
	
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * General purpose of the method is to load workspace from the specified path and return a instance of loaded Workspace.
	 * @param pathToNewWorkspace <code>String</code> of the path to the workspace that will be loaded.
	 * @return an instance of loaded <code>Workspace</code>
	 */
	public default Workspace changeWorkspace(String pathToNewWorkspace){return null;};
	
	/**
	 * This method is Overrideable latter on by the class which implements Actions interface or be left default which is empty.<br>
	 * Purpose of method is to share the action with another parent, or create shortcut of the current action to the Another parent action.
	 * @param anotherParrent an instance of<code>Actions</code> interface that action will be shared with.
	 */
	public default void share(MutableTreeNode anotherParrent, Observer... o){};
	
	
}
