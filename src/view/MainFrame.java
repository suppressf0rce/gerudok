package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

/**
 * <code>public class MainFrame extends JFrame</code><br>
 * <br>
 * 
 * This class creates Main frame of the GeRuDok application<br>
 * It contains Menu, Toolbar, JTreeView, and DesktopPane
 * 
 * @author PEBKAC
 *
 */
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	/**
	 * <code>MainForm instance</code><br>
	 * <br>
	 * This holds the instance of the main frame
	 */
	private static MainFrame instance;
	/**
	 * <code>JMenuBar menuBar</code><br>
	 * <br>
	 * This is a menu bar at the top frame
	 */
	private static JMenuBar menu;
	/**
	 * <code>JToolBar toolBar</code><br>
	 * <br>
	 * 
	 * This is the Tool Bar at the top of frame
	 */
	private static JToolBar toolBar;
	/**
	 * <code>JTree tree</code><br>
	 * <br>
	 * This is the JTree at the left side of the frame
	 */
	private static TreeView tree;
	/**
	 * <code>JDesktopPane dekstopPane</code><br>
	 * <br>
	 * This is the Desktop pane that contains all the internal windows<br>
	 * He is placed at the right side of the main window
	 */
	private static DesktopPane desktopPane;

	/**
	 * <code>MainForm</code><br>
	 * <br>
	 * 
	 * This is private constructor used for the singleton implementation
	 */
	private MainFrame() {
	}

	/**
	 * <code>initialize()</code><br>
	 * <br>
	 * 
	 * This method initializes all the components of the MainForm
	 */
	private static void initialize() {
		instance.setSize(800, 600);
		instance.setTitle("GeRuDok");
		instance.setExtendedState(JFrame.MAXIMIZED_BOTH);

		Image icon;
		try {
			icon = ImageIO.read(instance.getClass().getResource("/resource/icon32.png"));
			instance.setIconImage(icon);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		instance.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		instance.setLocationRelativeTo(null);

		instance.setFont(new Font("Tahoma", Font.PLAIN, 12));
		instance.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		instance.getContentPane().setLayout(new BorderLayout(0, 0));

		menu = new MenuBar();
		instance.setJMenuBar(menu);

		toolBar = new JToolBar();
		instance.getContentPane().add(toolBar, BorderLayout.NORTH);

		desktopPane = new DesktopPane();

		tree = new TreeView();
		JScrollPane jsp = new JScrollPane(tree);
		jsp.setPreferredSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 7, HEIGHT));
		instance.getContentPane().add(jsp, BorderLayout.WEST);
		desktopPane.setBackground(Color.lightGray);
		instance.getContentPane().add(desktopPane, BorderLayout.CENTER);

		instance.setVisible(true);

	}

	/**
	 * <code>addInternalFrame(InternalFrame internalFrame)</code><br>
	 * <br>
	 * This method adds InternalFrames into the DesktopPanes
	 * 
	 * @see InternalFrame
	 */
	public void addInternalFrame(InternalFrame internalFrame) {
		desktopPane.add(internalFrame, JDesktopPane.POPUP_LAYER);
		internalFrame.show();
	}

	/**
	 * <code>removeInternalFrame(String title)</code><br>
	 * <br>
	 * This method removes the internal frame for the given title with the
	 * string
	 * 
	 * @param title an <code>String</code> for
	 *            the title for the internal frame that will be removed
	 */
	public void removeInternalFrame(String title) {
		for (int i = 0; i < desktopPane.getAllFrames().length; i++) {
			if (desktopPane.getAllFrames()[i].getTitle().equals(title)) {
				JInternalFrame frame = desktopPane.getAllFrames()[i];
				desktopPane.remove(frame);
			}
		}
	}

	/**
	 * <code>removeInternalFrame(JInternalFrame frame)</code><br>
	 * <br>
	 * 
	 * This method removes the internal frame for the given instance of the
	 * internal frame
	 * 
	 * @param frame an <code>JInternalFrame</code>
	 *            instance of the internal frame
	 */
	public void removeInternalFrame(JInternalFrame frame) {
		boolean foundFrame = false;
		for (JInternalFrame f : desktopPane.getAllFrames()) {
			if (f == frame)
				foundFrame = true;
		}
		if (foundFrame) {
			desktopPane.remove(frame);
		}
	}

	/**
	 * <code></code><br>
	 * <br>
	 * This method check whether the desktop pane contains the specific
	 * internalFrame <br>
	 * for the given tittle
	 * 
	 * @param title an instance of <code>String</code>
	 *            of the given title
	 * @return an boolean<code>boolean</code>
	 */
	public boolean containsInternalFrame(String title) {
		boolean foundFrame = false;
		for (JInternalFrame f : desktopPane.getAllFrames()) {
			if (f.getTitle().equals(title))
				foundFrame = true;
		}
		return foundFrame;
	}

	/**
	 * <code>getInternalFrame(String title)</code><br>
	 * <br>
	 * 
	 * This method returns the specific internal frame depending on the title
	 * 
	 * @param title an instance of the <code>String</code>
	 *            of a title of an Internal frame
	 * @return <code>JInternalFrame</code> the internal frame of the given title
	 *         or a null if not found
	 */
	public JInternalFrame getInternalFrame(String title) {
		for (JInternalFrame f : desktopPane.getAllFrames()) {
			if (f.getTitle().equals(title))
				return f;
		}
		return null;
	}

	/**
	 * <code>internalFrameCount()</code><br>
	 * <br>
	 * 
	 * This method returns the number of the opened internal frames
	 * 
	 * @return <code>int</code> a number of internal frames
	 */
	public int internalFrameCount() {
		return desktopPane.getAllFrames().length;
	}

	// **********************************//
	// * Getters And Setters go below *//
	// **********************************//
	public static synchronized MainFrame getInstance() {
		if (instance == null) {
			instance = new MainFrame();
			initialize();
		}
		return instance;
	}

	public JToolBar getToolBar() {
		return toolBar;
	}

	public TreeView getTree() {
		return tree;
	}

	public DesktopPane getDesktopPane() {
		return desktopPane;
	}

	public JMenuBar getMenu() {
		return menu;
	}

}
