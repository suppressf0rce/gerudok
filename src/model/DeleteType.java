package model;

/**
 * This <code>ENUM</code> represents different deletion types<br>
 * <ul>
 * <li>COMPLETE_DELETE</li> = represents delete of all references if node is
 * shared
 * <li>DELETE_JUST_SELECTED</li> = represents delete of just selected node no
 * matter if it is shared
 * <li>UNBIND</li> = when you want to delete the node, but not completely just
 * save it to draft
 * </ul>
 * 
 * @author Dejan Radmanovic
 *
 */
public enum DeleteType {
	COMPLETE_DELETE, DELETE_JUST_SELECTED, UNBIND;
}
