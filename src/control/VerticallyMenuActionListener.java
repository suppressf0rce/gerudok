package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Utils;
/**
 * A class that implements ActionListener. It is used to call tileVertically method from Utils class.
 * @author Stefan
 *
 */
public class VerticallyMenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Utils.tileVertically();
	}

}
