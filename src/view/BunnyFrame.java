package view;

import javax.swing.JFrame;



@SuppressWarnings("serial")
public class BunnyFrame extends JFrame {
	public BunnyFrame() {
		setSize(600,600);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		LogoPanel panel = new LogoPanel(584,561,11);
		panel.setBounds(0, 0, 584, 561);
		getContentPane().add(panel);
		setVisible(true);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
