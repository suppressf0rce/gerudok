package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Actions;

/**
 * <code>public class ExportElementListener implements ActionListener</code><br>
 * This class handles the action when user wants to export the element outside
 * from the the GeRuDok project into system files.
 * 
 * @author Dejan Radmanovic
 *
 * @see Actions#save(String)
 */
public class ExportElementListener implements ActionListener {

	/**
	 * An instance of the <code>Actions</code> which represents the action you
	 * want to export from the GeRuDok.
	 * 
	 * @see Actions
	 */
	private Actions action;

	/**
	 * An default constructor for the <code>ExportElementListener</code>
	 * 
	 * @param action
	 *            An instance of the <code>Actions</code> which represents the
	 *            action you want to export from the GeRuDok.
	 * @see Actions
	 */
	public ExportElementListener(Actions action) {
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Project File", "proj"));
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			if (fileChooser.getSelectedFile() != null) {
				action.save(fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}
}
