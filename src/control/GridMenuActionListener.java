package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import main.Utils;
/**
 * A class that implements ActionListener interface. It is used to call tileGrid() method from the Utils class.
 * @author Stefan
 *
 */
public class GridMenuActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Utils.tileGrid();
		
	}
	
}
