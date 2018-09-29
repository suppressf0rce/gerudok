package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import view.MainFrame;

/**
 * <code>public class Main</code><br>
 * This class is the main class that is firstly started at the beginning of the program and it contains the main method
 * @author PEBKAC
 * @see Main#main(String[])
 */
public class Main {

	/**
	 * This method is main method which is firstly initialized at the beginning.<br>
	 * It changes the look and feel of the system, and creates the instance of the MainFrame
	 * @param args an <code>String[]</code> which represents the arguments of the command line
	 * @see MainFrame
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame.getInstance();
		
	}

}
