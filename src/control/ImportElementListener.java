package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Actions;
import view.MainFrame;

/**
 * <code>public class ImportElementListener implements ActionListener</code><br>
 * 
 * This class handles the action when the user wants to import specific action from the system file
 * 
 * @author Dejan Radmanovic
 *
 * @see Actions#importChild(String, java.util.Observer...)
 */
public class ImportElementListener implements ActionListener{

	/**
	 * An instance of <code>Actions</code> on which will be node be imported from the system file.
	 * @see Actions#importChild(String, java.util.Observer...)
	 */
	private Actions action;
	
	/**
	 * Default constructor for the <code>ImportElementListener</code>
	 * @param action An instance of <code>Actions</code> on which will be node be imported from the system file.
	 * @see Actions#importChild(String, java.util.Observer...)
	 */
	public ImportElementListener(Actions action) {
		this.action = action;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Project File", "proj"));
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			if(fileChooser.getSelectedFile() != null){
				action.importChild(fileChooser.getSelectedFile().getAbsolutePath(), MainFrame.getInstance().getTree(), MainFrame.getInstance().getDesktopPane());
			}
		}
	}
}
