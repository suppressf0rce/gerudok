package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;

import model.Actions;
import model.Project;
import model.TreeModel;
import model.Workspace;
import view.DesktopPane;
import view.InternalFrame;
import view.MainFrame;

/**
 * <code>public class ChangeWorkspaceEventListener implements ActionListener</code><br>
 * This class handles the change workspace action. It is called when the user wants to change the workspace<br>
 * It loops through all the <code>{@link Project}s</code> checks whether they are saved, and depending on user likings it saves them. <br>
 * After that it asks user whether he wants to save the current workspaces. then it opens the new workspace.
 * @author Dejan Radmanovic
 *
 */
public class ChangeWorkspaceEventListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getModel().getRoot();

		// Saving the projects inside the workspace
		for (Project p : workspace.getProjects()) {
			if (p.getPath() == null || p.getPath().isEmpty()) {
				int dialogResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						p + " project is not saved. Would you like to save it?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					ExportElementListener l = new ExportElementListener((Actions) p);
					l.actionPerformed(null);
				}
			}
			else if (p.isChanged()) {
				int dialogResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
						p + " project is not saved. Would you like to save it?", "Confirmation",
						JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					p.save(p.getPath());
				}
			}
		}

		// Saving the workspace
		int dialogResult = JOptionPane.showConfirmDialog(MainFrame.getInstance(),
				"Would you like to save changes to current workspace?", "Confirmation", JOptionPane.YES_NO_OPTION);
		if (dialogResult == JOptionPane.YES_OPTION) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setAcceptAllFileFilterUsed(false);
			fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Workspace File", "ws"));
			if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				if (fileChooser.getSelectedFile() != null) {
					workspace.save(fileChooser.getSelectedFile().getAbsolutePath());
				}
			}
		}

		// Loading the new workspace
		JFileChooser fileChooser2 = new JFileChooser();
		fileChooser2.setAcceptAllFileFilterUsed(false);
		fileChooser2.addChoosableFileFilter(new FileNameExtensionFilter("Workspace File", "ws"));
		if (fileChooser2.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser2.getSelectedFile() != null) {
				DesktopPane dp = MainFrame.getInstance().getDesktopPane();
				
				// @Dejan hides all the project frames? - Stefan
				for (int j = 0; j < dp.getInternalFrames().size(); j++) {
					@SuppressWarnings("unused")
					InternalFrame temp = null;
					for (InternalFrame i : dp.getInternalFrames()) {
						i.hide();
						i.getProject().setState(false);
						temp = i;
						dp.remove(i);
						i.dispose();
					}
					dp.getInternalFrames().remove(j);
				}
				
				Workspace newWorkspace = workspace.changeWorkspace(fileChooser2.getSelectedFile().getAbsolutePath());
				newWorkspace.addObserver(dp);
				
				MainFrame.getInstance().getTree()
						.setModel(new TreeModel(null, true, newWorkspace,MainFrame.getInstance().getTree()));
				DefaultTreeModel model = (DefaultTreeModel) MainFrame.getInstance().getTree().getModel();
				model.reload();
				
				// Displays the projects as internal frames
				for(Project p: newWorkspace.getProjects()){
					InternalFrame inf = new InternalFrame(p, dp.getInternalFrames().size());
					dp.add(inf);
					dp.getInternalFrames().add(inf);
					inf.show();
				}
			}
		}
	}

}
