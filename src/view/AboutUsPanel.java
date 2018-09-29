package view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import control.pictureControllers.LemurPictureListener;
import control.pictureControllers.LionPictureListener;
import control.pictureControllers.MousePictureListener;
import control.pictureControllers.SlothPictureListener;

@SuppressWarnings("serial")
/**
 * A JPanel which displays information about PEBKAC.
 * @author Stefan
 *
 */
public class AboutUsPanel extends JPanel {
	public AboutUsPanel() {
		setSize(664, 567);
		setLayout(null);

		LogoPanel panel = new LogoPanel(125, 172, 3);
		panel.setBounds(23, 183, 125, 172);
		add(panel);
		panel.addMouseListener(new SlothPictureListener(panel));
		panel.setLayout(null);

		LogoPanel panel_1 = new LogoPanel(125, 172, 4);
		panel_1.setBounds(183, 183, 125, 172);
		add(panel_1);
		panel_1.addMouseListener(new LionPictureListener(panel_1));
		panel_1.setLayout(null);

		LogoPanel panel_2 = new LogoPanel(125, 172, 5);
		panel_2.setBounds(343, 183, 125, 172);
		add(panel_2);
		panel_2.addMouseListener(new MousePictureListener(panel_2));
		panel_2.setLayout(null);

		LogoPanel panel_3 = new LogoPanel(125, 172, 6);
		panel_3.setBounds(503, 183, 125, 172);
		add(panel_3);
		panel_3.addMouseListener(new LemurPictureListener(panel_3));
		panel_3.setLayout(null);

		LogoPanel lp = new LogoPanel(148, 153, 1);
		lp.setOpaque(false);
		lp.setBounds(250, 11, 148, 153);
		add(lp);

		JTextArea txtrHereGoesThe = new JTextArea();
		txtrHereGoesThe.setEditable(false);
		txtrHereGoesThe.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		txtrHereGoesThe.setLineWrap(true);
		txtrHereGoesThe.setWrapStyleWord(true);
		txtrHereGoesThe.setOpaque(false);
		txtrHereGoesThe.setText(
				"The team was founded on start of the winter semester, 2016. Initially, only Dejan was in the team. Branko and Teodora accompanied him shortly, while Stefan switched from another team a month later.");
		txtrHereGoesThe.setBounds(91, 445, 467, 79);
		add(txtrHereGoesThe);

		JLabel label = new JLabel("");
		label.setBounds(553, 542, 46, 14);
		add(label);
		/*
		 * TODO JLabel lblVisitUsBy = new
		 * JLabel(Utils.getResourceBundle().getString("visitUsHere"));
		 * lblVisitUsBy.addMouseListener(new LabelVisitUsListener(
		 * "https://student.ftn.uns.ac.rs/redmine/projects/tim-dsw-2016-203-5/wiki"
		 * )); lblVisitUsBy.setForeground(Color.BLUE); lblVisitUsBy.setFont(new
		 * Font("Tahoma", Font.PLAIN, 14)); lblVisitUsBy.setBounds(259, 535,
		 * 162, 21); add(lblVisitUsBy);
		 */
		JLabel lblDejanRadmanovic = new JLabel("Dejan Radmanovi\u0107");
		lblDejanRadmanovic.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDejanRadmanovic.setBounds(28, 366, 125, 14);
		add(lblDejanRadmanovic);

		JLabel lblBrankoVitorovic = new JLabel("Branko Vitorovi\u0107");
		lblBrankoVitorovic.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBrankoVitorovic.setBounds(195, 366, 115, 14);
		add(lblBrankoVitorovic);

		JLabel lblTeodoraMladenovi = new JLabel("Teodora Mladenovi\u0107");
		lblTeodoraMladenovi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTeodoraMladenovi.setBounds(343, 366, 125, 14);
		add(lblTeodoraMladenovi);

		JLabel lblStefanCveti = new JLabel("Stefan Cveti\u0107");
		lblStefanCveti.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblStefanCveti.setBounds(523, 366, 115, 14);
		add(lblStefanCveti);

		JLabel lblAboutUs = new JLabel("About us:");
		lblAboutUs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAboutUs.setBounds(62, 418, 129, 14);
		add(lblAboutUs);

		JLabel lblClickOnAll = new JLabel("Click on all four images below for a super cool feature!");
		lblClickOnAll.setBounds(196, 167, 509, 14);
		add(lblClickOnAll);
	}
}
