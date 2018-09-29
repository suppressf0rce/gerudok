package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import control.PageTabListener;
import model.Actions;
import model.GeDocument;
import model.Page;

/**
 * <code>public class PageTabbedPane extends JTabbedPane implements Observer</code>
 * <br>
 * A custom <code>{@link JTabbedPane}</code> instance that visually represents
 * <code>{@link Page}s</code> as panels in the
 * <code>{@link GeDocumentTabbedPane}</code>. It is responsible for insertion of
 * <code>{@link Page}</code> panels into
 * <code>{@link GeDocumentTabbedPane}</code> and their deletion.
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class PageTabbedPane extends JTabbedPane implements Observer {

	/**
	 * A GeDocument that PageTabbedPane represents.
	 * @author Stefan
	 * 
	 */
	private GeDocument geDocument;

	/**
	 * An ArrayList of PagePanels that PageTabbedPane displays.
	 * @author Stefan
	 */
	private ArrayList<PagePanel> panels = new ArrayList<PagePanel>();

	/**
	 * A boolean that contains information whether the PageTabbedPane is selected or not.
	 * @author Stefan
	 * 
	 */
	private boolean isSelected = false;

	/**
	 * A Constructor that takes a GeDocument as an argument and adds PagePanels internally if GeDocument has any.
	 * @author Stefan
	 */
	public PageTabbedPane(GeDocument g) {
		this.geDocument = g;
		geDocument.addObserver(this);
		this.addChangeListener(new PageTabListener(this));
		this.isSelected = true;
		if (geDocument.getPages().size() != 0)
			addPagesInternally();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof GeDocument) {
			if ((int) arg == Actions.INSERT) {
				PagePanel p = new PagePanel(((GeDocument) o).getPages().get(((GeDocument) o).getPages().size() - 1));
				p.setName(p.getPage().getName());
				p.getPage().addObserver(this);
				this.add(p);
				panels.add(p);
			}
		}
		if (o instanceof Page) {
			if ((int) arg == Actions.DELETE) {
				PagePanel temp = null;
				for (PagePanel p : panels) {
					if ((Page) o == p.getPage()) {
						temp = p;
					}
				}
				this.remove(temp);
				panels.remove(temp);
			}
		}

	}

	/**
	 * A method that adds PagePanels to the PagedTabbedPane based on the Pages that GeDocument contains. Usually being called when a Project has been
	 * imported or a GeDocument has been shared.
	 * @author Stefan
	 */
	private void addPagesInternally() {
		for (Page page : geDocument.getPages()) {
			PagePanel p = new PagePanel(page);
			p.setName(page.getName());
			page.addObserver(this);
			page.addObserver(MainFrame.getInstance().getTree());
			this.add(p);
			panels.add(p);
		}
	}

	public GeDocument getGeDocument() {
		return geDocument;
	}

	public ArrayList<PagePanel> getPanels() {
		return panels;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
