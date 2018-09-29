package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JTabbedPane;

import control.GeDocumentTabListener;
import model.Actions;
import model.GeDocument;
import model.Project;

/**
 * <code>public class GeDocumentTabbedPane extends JTabbedPane implements Observer</code>
 * <br>
 * A custom <code>{@link JTabbedPane}</code> that visually represents
 * GeDocuments in InternalFrame.<br>
 * It holds a model of project whose <code>{@link GeDocument}s</code> it
 * represents, <br>
 * and a list of <code>{@link PageTabbedPane}</code> objects that it displays.
 * <br>
 * <br>
 * 
 * As it is an Observer, as well, it is responsible for: <br>
 * <ul>
 * <li>insertion of <code>{@link PageTabbedPane}</code> whenever its project had a <code>{@link GeDocument}</code> added
 * inside it,</li>
 * <li>renaming (title change) of <code>{@link PageTabbedPane}</code> whenever one of the <code>{@link GeDocument}s</code>
 * inside of it changes name.</li>
 * <li>deletion of a <code>{@link PageTabbedPane}</code> whenever its <code>{@link GeDocument}</code> has been deleted,
 * respectfully.</li>
 * </ul>
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class GeDocumentTabbedPane extends JTabbedPane implements Observer {
	
	/**
	 * Instance of Project whose GeDocuments GeDocumentTabbedPane represents.
	 * @author Stefan
	 */
	private Project project;
	
	/**
	 * A list of PageTabbedPane's that GeDocumentTabbedPane displays.
	 * @author Stefan
	 */
	private ArrayList<PageTabbedPane> tabbedPanes = new ArrayList<PageTabbedPane>();

	/**
	 * Creates a new GeDocumentTabbedPane instance having a Project passed to it, and if project already has GeDocuments, it adds them manually.
	 * @author Stefan
	 */
	public GeDocumentTabbedPane(Project project) {
		this.project = project;
		project.addObserver(this);
		this.addChangeListener(new GeDocumentTabListener(this));

		if (project.getGeDocuments().size() != 0) {
			addGeDocumentsInternally();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Project) {
			if (arg instanceof Integer) {
				if ((int) arg == Actions.INSERT) {
					PageTabbedPane p = new PageTabbedPane(
							project.getGeDocuments().get(project.getGeDocuments().size() - 1));
					p.setTabPlacement(JTabbedPane.LEFT);
					p.setName(p.getGeDocument().getName());
					tabbedPanes.add(p);
					this.add(p.getName(), p);
					p.getGeDocument().addObserver(this);
				}
			}
		}
		if (o instanceof GeDocument) {
			if (arg instanceof Integer) {
				if ((int) arg == Actions.RENAME) {
					for (PageTabbedPane p : tabbedPanes) {
						if ((GeDocument) o == p.getGeDocument()) {
							this.setTitleAt(tabbedPanes.indexOf(p), p.getGeDocument().getName());
						}
					}
				}
				if ((int) arg == Actions.DELETE) {
					PageTabbedPane temp = null;
					for (PageTabbedPane p : tabbedPanes) {
						if ((GeDocument) o == p.getGeDocument()) {
							this.remove(p);
							temp = p;
						}
					}
					tabbedPanes.remove(temp);

				}
			}
		}
	}

	/**
	 * Method which adds GeDocuments manually to the GeDocumentTabbedPane. This happens in case a Project is being imported or a GeDocument is being shared.
	 * @author Stefan
	 */
	private void addGeDocumentsInternally() {

		for (GeDocument g : project.getGeDocuments()) {
			PageTabbedPane p = new PageTabbedPane(g);
			p.setTabPlacement(JTabbedPane.LEFT);
			p.setName(g.getName());
			tabbedPanes.add(p);
			this.add(p.getName(), p);
			g.addObserver(this);
		}
	}

	public ArrayList<PageTabbedPane> getTabbedPanes() {
		return tabbedPanes;
	}

	public Project getProject() {
		return project;
	}
}
