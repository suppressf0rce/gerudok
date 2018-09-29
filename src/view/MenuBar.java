package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import control.CascadeMenuActionListener;
import control.GridMenuActionListener;
import control.HorizontallyMenuActionListener;
import control.VerticallyMenuActionListener;

/**
 * TODO: Stefan: napisati dokumentaciju
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {
	public MenuBar() {
		JMenu fileMenu = new JMenu("File");
		this.add(fileMenu);

		JMenu editMenu = new JMenu("Edit");
		this.add(editMenu);

		JMenu viewMenu = new JMenu("View");
		this.add(viewMenu);

		JMenuItem cascadeMenuItem = new JMenuItem("Cascade Windows");
		cascadeMenuItem.addActionListener(new CascadeMenuActionListener());

		JMenuItem horizontallyMenuItem = new JMenuItem("Tile Windows Horizontally");
		horizontallyMenuItem.addActionListener(new HorizontallyMenuActionListener());

		JMenuItem verticallyMenuItem = new JMenuItem("Tile Windows Vertically");
		verticallyMenuItem.addActionListener(new VerticallyMenuActionListener());

		JMenuItem gridMenuItem = new JMenuItem("Tile into a Grid");
		gridMenuItem.addActionListener(new GridMenuActionListener());

		viewMenu.add(cascadeMenuItem);
		viewMenu.add(horizontallyMenuItem);
		viewMenu.add(verticallyMenuItem);
		viewMenu.add(gridMenuItem);

		JMenu langMenu = new JMenu("Language");
		this.add(langMenu);

		JMenu helpMenu = new JMenu("About");
		//helpMenu.addMenuListener(new AboutListener());
		this.add(helpMenu);
	}
}
