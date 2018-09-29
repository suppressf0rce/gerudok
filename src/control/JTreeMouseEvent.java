package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observer;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.MutableTreeNode;

import main.Utils;
import model.Actions;
import model.Draft;
import model.Element;
import model.GeDocument;
import model.GraphicElement;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;
import view.MainFrame;
import view.TreeView;
import view.WorkFrame;

/**
 * <code>public class JTreeMouseEvent implements MouseListener</code><br>
 * This class handles the mouse actions on the <code>JTree</code><br>
 * It essentially creates the pop up menu on the left click and adds some
 * actions on double right click.
 * 
 * @author Dejan Radmanovic
 * 
 * @see JTree
 * @see MouseListener
 *
 */
public class JTreeMouseEvent implements MouseListener {

	/**
	 * An instance of tree <code>TreeView</code> on which this action will be
	 * handled so we can pass currently selected tree values to the other action
	 * handlers
	 * 
	 * @see TreeView
	 */
	private TreeView tree;

	/**
	 * An instance of the <code>Observer</code> that will be added to the model.
	 * 
	 * @see Observer
	 */
	private Observer o;

	/**
	 * An Default Constructor
	 * 
	 * @param tree
	 *            An instance of tree <code>TreeView</code> on which this action
	 *            will be handled so we can pass currently selected tree values
	 *            to the other action handlers
	 * @param o
	 *            An instance of the <code>Observer</code> that will be added to
	 *            the model.
	 * @see TreeView
	 * @see Observer
	 */
	public JTreeMouseEvent(TreeView tree, Observer o) {
		this.tree = tree;
		this.o = o;
	}

	@SuppressWarnings("static-access")
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			Object lastPathComponent = null;
			try {
				lastPathComponent = tree.getSelectionPath().getLastPathComponent();
			} catch (Exception z) {

			}
			if (lastPathComponent instanceof Project)
				MainFrame.getInstance().getDesktopPane()
						.showInternalFrame((Project) tree.getSelectionPath().getLastPathComponent());
		}
		if (SwingUtilities.isLeftMouseButton(e)) {
			Object lastPathComponent = null;
			try {
				lastPathComponent = tree.getSelectionPath().getLastPathComponent();
			} catch (Exception z) {

			}
			if (lastPathComponent instanceof Project) {
				MainFrame.getInstance().getDesktopPane()
						.focusInternalFrame((Project) tree.getSelectionPath().getLastPathComponent());

			}
			if (lastPathComponent instanceof GeDocument) {
				MainFrame.getInstance().getDesktopPane()
						.showGeDocument((GeDocument) tree.getSelectionPath().getLastPathComponent());
			}
			if (lastPathComponent instanceof Page) {
				MainFrame.getInstance().getDesktopPane()
						.showPage((Page) tree.getSelectionPath().getLastPathComponent());
			}
			if (lastPathComponent instanceof Slot) {
				MainFrame.getInstance().getDesktopPane()
						.showSlot((Slot) tree.getSelectionPath().getLastPathComponent());
			}
			if (e.getClickCount() == 2) {
				if (lastPathComponent instanceof GraphicElement) {
					if (((GraphicElement) lastPathComponent).getParent() instanceof GraphicElement) {
						new WorkFrame((GraphicElement) ((GraphicElement) lastPathComponent).getParent());
					} else {
						new WorkFrame((GraphicElement) lastPathComponent);
					}

				}
			}
		}
		if (SwingUtilities.isRightMouseButton(e)) {
			JPopupMenu popupMenu = new JPopupMenu();
			int row = tree.getClosestRowForLocation(e.getX(), e.getY());
			tree.setSelectionRow(row);
			if (tree.getSelectionPath().getLastPathComponent() instanceof Workspace) {
				if (Draft.getDraftProjects().size() == 0) {
					JMenuItem addProject = new JMenuItem("Add New Project");
					addProject.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
					addProject.addActionListener(
							new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o,
									MainFrame.getInstance().getDesktopPane()));
					popupMenu.add(addProject);
				} else {
					JMenu menuAdd = new JMenu("Add Project");
					menuAdd.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
					JMenuItem addNewProj = new JMenuItem("New Project");
					addNewProj.addActionListener(
							new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
					menuAdd.add(addNewProj);
					JMenuItem addFromDraft = new JMenuItem("From Draft");
					addFromDraft.addActionListener(
							new AddChildFromDraftEvent(true, (Actions) tree.getSelectionPath().getLastPathComponent()));
					menuAdd.add(addFromDraft);
					popupMenu.add(menuAdd);
				}
				JMenuItem importProj = new JMenuItem("Import Project");
				importProj.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/import-icon.png")));
				importProj.addActionListener(
						new ImportElementListener((Actions) tree.getSelectionPath().getLastPathComponent()));
				popupMenu.add(importProj);
				popupMenu.addSeparator();
				JMenuItem change = new JMenuItem("Change Workspace");
				change.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/cross-arrows-icon.png")));
				change.addActionListener(new ChangeWorkspaceEventListener());
				popupMenu.add(change);
			} else if (tree.getSelectionPath().getLastPathComponent() instanceof Project) {
				MainFrame.getInstance().getDesktopPane()
						.focusInternalFrame((Project) tree.getSelectionPath().getLastPathComponent());
				if (Draft.getDraftDocuments().size() == 0) {
					JMenuItem addDocument = new JMenuItem("Add New Document");
					addDocument.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
					addDocument.addActionListener(
							new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
					popupMenu.add(addDocument);
				} else {
					JMenu menuAdd = new JMenu("Add Document");
					menuAdd.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
					JMenuItem addNewProj = new JMenuItem("New Document");
					addNewProj.addActionListener(
							new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
					menuAdd.add(addNewProj);
					JMenuItem addFromDraft = new JMenuItem("From Draft");
					addFromDraft.addActionListener(new AddChildFromDraftEvent(false,
							(Actions) tree.getSelectionPath().getLastPathComponent()));
					menuAdd.add(addFromDraft);
					popupMenu.add(menuAdd);
				}
				popupMenu.addSeparator();
				JMenuItem rename = new JMenuItem("Rename Project");
				rename.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/edit-icon.png")));
				rename.addActionListener(
						new RenameElementListener((MutableTreeNode) tree.getSelectionPath().getLastPathComponent()));
				popupMenu.add(rename);
				JMenuItem export = new JMenuItem("Export Project");
				export.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/export-icon.png")));
				export.addActionListener(
						new ExportElementListener((Actions) tree.getSelectionPath().getLastPathComponent()));
				popupMenu.add(export);
				JMenuItem delete = new JMenuItem("Delete Project");
				delete.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/remove-icon.png")));
				delete.addActionListener(new DeleteElementListener(tree));
				popupMenu.add(delete);
			} else if (tree.getSelectionPath().getLastPathComponent() instanceof GeDocument) {
				MainFrame.getInstance().getDesktopPane()
						.showGeDocument((GeDocument) tree.getSelectionPath().getLastPathComponent());
				JMenuItem addPage = new JMenuItem("Add Page");
				addPage.addActionListener(
						new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
				addPage.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
				popupMenu.add(addPage);
				popupMenu.addSeparator();
				JMenuItem rename = new JMenuItem("Rename Document");
				rename.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/edit-icon.png")));
				rename.addActionListener(
						new RenameElementListener((MutableTreeNode) tree.getSelectionPath().getLastPathComponent()));
				popupMenu.add(rename);
				JMenuItem share = new JMenuItem("Share Document");
				share.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/share.png")));
				share.addActionListener(new ShareElementListener(tree));
				popupMenu.add(share);
				JMenuItem delete = new JMenuItem("Delete Document");
				delete.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/remove-icon.png")));
				delete.addActionListener(new DeleteElementListener(tree));
				popupMenu.add(delete);
			} else if (tree.getSelectionPath().getLastPathComponent() instanceof Page) {
				JMenuItem addSlot = new JMenuItem("Add Slot");
				addSlot.addActionListener(
						new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
				addSlot.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
				popupMenu.add(addSlot);
				popupMenu.addSeparator();
				JMenuItem delete = new JMenuItem("Delete Page");
				delete.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/remove-icon.png")));
				delete.addActionListener(new DeleteElementListener(tree));
				popupMenu.add(delete);
			} else if (tree.getSelectionPath().getLastPathComponent() instanceof Slot) {
				if (((Slot) tree.getSelectionPath().getLastPathComponent()).getChildCount() == 0) {
					JMenuItem asaignElement = new JMenuItem("Assign Element");
					asaignElement.addActionListener(
							new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
					asaignElement.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
					popupMenu.add(asaignElement);
					popupMenu.addSeparator();
				}
				JMenuItem delete = new JMenuItem("Delete Slot");
				delete.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/remove-icon.png")));
				delete.addActionListener(new DeleteElementListener(tree));
				popupMenu.add(delete);
			} else if (tree.getSelectionPath().getLastPathComponent() instanceof Element) {
				JMenuItem addElement = new JMenuItem("Add New Element");
				addElement.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/add-icon.png")));
				addElement.addActionListener(
						new AddChildEvent((Actions) tree.getSelectionPath().getLastPathComponent(), null, o));
				popupMenu.add(addElement);
				popupMenu.addSeparator();
				JMenuItem rename = new JMenuItem("Rename Element");
				rename.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/edit-icon.png")));
				rename.addActionListener(
						new RenameElementListener((MutableTreeNode) tree.getSelectionPath().getLastPathComponent()));
				popupMenu.add(rename);
				JMenuItem delete = new JMenuItem("Delete Element");
				delete.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/remove-icon.png")));
				delete.addActionListener(new DeleteElementListener(tree));
				popupMenu.add(delete);
				JMenuItem properties = new JMenuItem("Change Element Properties");
				properties.addActionListener(
						new ChangeElementPropertiesEvent((Element) tree.getSelectionPath().getLastPathComponent()));
				properties.setIcon(Utils.getScaledIcon(getClass().getResource("/resource/properties-icon.png")));
				popupMenu.add(properties);
			}
			popupMenu.show(e.getComponent(), e.getX(), e.getY());
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
