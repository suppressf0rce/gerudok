package view;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JDesktopPane;

import control.PageTabListener;
import model.Actions;
import model.GeDocument;
import model.Page;
import model.Project;
import model.Slot;
import model.Workspace;

/**
 * <code>public class DesktopPane extends JDesktopPane implements Observer</code>
 * <br>
 * A custom <code>JDesktoPane</code> instance which visually represents a
 * desktop area whose<br>
 * purpose is to display projects as internalframes. Since it is an observable
 * <br>
 * it is responsible for creation and deletion of internal frames. It is also
 * <br>
 * responsible for showing/focusing projects (internal frames), gedocuments<br>
 * <code>(GeDocumentTabbedPane)</code> and pages<code>(PageTabbedPane)</code>.
 * 
 * @author Stefan
 * 
 * @see GeDocumentTabbedPane
 * @see PageTabListener
 * @see JDesktopPane
 */
@SuppressWarnings("serial")
public class DesktopPane extends JDesktopPane implements Observer {

	/**
	 * This array list contains all the InternalFrames that are existent in the DesktopPane.
	 * @author Stefan
	 * 
	 */
	private static ArrayList<InternalFrame> internalFrames = new ArrayList<InternalFrame>();

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Workspace) {
			if (arg instanceof Integer) {
				if ((int) arg == Actions.INSERT) {
					InternalFrame inf = new InternalFrame(((Workspace) o).getLastChild(), internalFrames.size());
					this.add(inf);
					internalFrames.add(inf);
					inf.show();
				}
			}
		}
		if (o instanceof Project) {
			if (arg instanceof Integer) {
				if ((int) arg == Actions.DELETE) {
					InternalFrame temp = null;
					for (InternalFrame i : internalFrames) {
						if (i.getProject() == (Project) o) {

							i.hide();
							i.getProject().setState(false);
							temp = i;
							this.remove(i);
							i.dispose();
						}
					}
					internalFrames.remove(temp);
				}
				if ((int) arg == Actions.RENAME) {
					for (InternalFrame i : internalFrames) {
						if (i.getProject() == (Project) o) {
							i.setTitle(((Project) o).getName());
						}
					}
				}
			}
		}
	}

	public ArrayList<InternalFrame> getInternalFrames() {
		return internalFrames;
	}

	/**
	 * This method brings InternalFrame which contains selected TreeView's selected project to the front and selects it.
	 * It takes a Project instance as an argument.
	 * @author Stefan
	 * @param p
	 */
	public static void focusInternalFrame(Project p) {
		for (InternalFrame i : internalFrames) {
			if (i.getProject() == p) {
				try {
					i.getProject().setState(true);
					i.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method brings InternalFrame which contains TreeView's selected project to front.
	 * It takes a Project instance as an argument.
	 * @author Stefan
	 * @param p 
	 */
	public static void showInternalFrame(Project p) {
		for (InternalFrame i : internalFrames) {
			if (i.getProject() == p) {
				i.show();
				try {
					i.getProject().setState(true);
					i.setSelected(true);
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method brings InternalFrame whose Project contains TreeView's selected document to the front and selects its tabbedPane tab, accordingly.
	 * @author Stefan
	 * @param g
	 */
	public static void showGeDocument(GeDocument g) {
		for (InternalFrame i : internalFrames) {
			GeDocumentTabbedPane p = i.getTabbedPane();
			for (PageTabbedPane z : p.getTabbedPanes()) {
				if (z.getGeDocument() == g) {
					p.grabFocus();
					g.setState(true);
					i.getProject().setState(false);
					try {
						i.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// System.out.println("focus");
					p.setSelectedIndex(p.getTabbedPanes().indexOf(z));
				}
			}
		}
	}

	/**
	 * This method searches for TreeView's selected page in the InternalFrame's project and once it's found it brings it to the front, and selects GeDocumentTabbedPane's
	 * and PageTabbedPane's tabs accordingly. 
	 * @author Stefan
	 * @param page
	 */
	public static void showPage(Page page) {
		for (InternalFrame i : internalFrames) {
			GeDocumentTabbedPane p = i.getTabbedPane();
			for (PageTabbedPane ptp : p.getTabbedPanes()) {
				for (PagePanel pp : ptp.getPanels()) {
					if (pp.getPage() == page) {

						ptp.grabFocus();
						i.getProject().setState(false);
						try {
							i.setSelected(true);
						} catch (Exception e) {

						}
						ptp.getGeDocument().setState(false);

						p.setSelectedIndex(p.getTabbedPanes().indexOf(ptp));
						ptp.setSelectedIndex(ptp.getPanels().indexOf(pp));

					}
				}
			}
		}
	}

	/**
	 * This method searches for TreeView's selected slot in the InternalFrame's project and once it's found it brings it to the front and selects GeDocumentTabbedPane's
	 * and PageTabbedPane's tabs accordingly.
	 * @author Stefan
	 * @param slot
	 */
	public static void showSlot(Slot slot) {

		for (InternalFrame i : internalFrames) {
			GeDocumentTabbedPane p = i.getTabbedPane();

			for (PageTabbedPane ptp : p.getTabbedPanes()) {
				// System.out.println(ptp.getName());
				for (PagePanel pp : ptp.getPanels()) {
					// System.out.println(pp.getName());
					for (SlotPanel s : pp.getSlotPanels()) {
						// System.out.println("zzzzzzz"+s.getSlot());
						if (s.getSlot() == slot) {
							// System.out.println("FOUND IT!");
							ptp.grabFocus();
							i.getProject().setState(false);
							try {

								i.setSelected(true);
							} catch (Exception e) {

							}
							ptp.getGeDocument().setState(false);
							ptp.setSelected(false);

							p.setSelectedIndex(p.getTabbedPanes().indexOf(ptp));
							ptp.setSelectedIndex(ptp.getPanels().indexOf(pp));

							// System.out.println("pp"+pp.getName());
							// System.out.println("ptp"+ptp.getName());
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * This method searches for a SlotPanel based on the Slot argument, and once its found it returns a SlotPanel instance.
	 * @author Stefan
	 * @param slot
	 * @return
	 */
	public static SlotPanel getCallingSlot(Slot slot) {

		for (InternalFrame i : internalFrames) {
			GeDocumentTabbedPane p = i.getTabbedPane();

			for (PageTabbedPane ptp : p.getTabbedPanes()) {
				// System.out.println(ptp.getName());
				for (PagePanel pp : ptp.getPanels()) {
					// System.out.println(pp.getName());
					for (SlotPanel s : pp.getSlotPanels()) {
						// System.out.println("zzzzzzz"+s.getSlot());
						if (s.getSlot() == slot) {
							return s;
						}

					}
				}
			}
		}
		return null;
	}
}