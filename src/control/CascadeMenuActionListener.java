package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Utils;
/**
 * A class that implements ActionListener. It is used for calling cascadeWindows() method from the Utils class.
 * @author Stefan
 *
 */
public class CascadeMenuActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Utils.cascadeWindows();
		
	}

}
