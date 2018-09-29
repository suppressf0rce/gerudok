package view;

import java.awt.Dimension;

import javax.swing.JInternalFrame;

import control.InternalFrameCustomListener;
import model.Project;

/**
 * <code>public class InternalFrame extends JInternalFrame</code><br>
 * <br>
 * This class extends <code>{@link JInternalFrame}</code> and is used to be a
 * internal frame that is inside the <code>DesktopPane</code>
 * 
 * @author PEBKAC
 *
 * @see DesktopPane
 *
 */
@SuppressWarnings("serial")
public class InternalFrame extends JInternalFrame {

	/**
	 * An instance of the Project that InternalFrame represents.
	 * @author Stefan
	 */
	private Project project;

	/**
	 * A JTabbedPane instance that this InternalFrame displays.
	 */
	private GeDocumentTabbedPane tabbedPane;

	/**
	 * Static offsets so frames wouldn't be on top of each other
	 */
	static final int xOffset = 30, yOffset = 30;

	/**
	 * A Constructor that takes a Project that it belongs to, and a number of already existent InternalFrames so they can be auto cascaded on creation.
	 * It automatically creates and adds a GeDocumentTabbedPane to its content.
	 * @author Stefan
	 */
	public InternalFrame(Project p, int numberOfOpenFrames) {
		this.project = p;
		this.setTitle(p.getName());
		this.setSize(new Dimension(300, 300));
		this.setFrameIcon(null);

		this.setResizable(true);
		this.setMaximizable(true);
		this.setResizable(true);
		this.setLocation(xOffset * numberOfOpenFrames, yOffset * numberOfOpenFrames);
		this.setClosable(true);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.addInternalFrameListener(new InternalFrameCustomListener(this.getProject()));

		tabbedPane = new GeDocumentTabbedPane(p);
		this.add(tabbedPane);

	}

	public Project getProject() {
		return project;
	}

	public GeDocumentTabbedPane getTabbedPane() {
		return tabbedPane;
	}
}
