package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import control.WorkFrameWindowListener;
import model.GraphicElement;
import state.StateManager;

/**
 * <code>public class WorkFrame extends  {@link JFrame}</code><br>
 * This class creates frame that is essentially a GraphicEditor. <br>
 * It opens when the chosen element is graphic, and user wants to edit that
 * graphic element.
 * 
 * 
 * 
 * @author PEBKAC
 * 
 */

@SuppressWarnings("serial")
public class WorkFrame extends JFrame {

	/**
	 * an instance of <code>Canvas</code> that will be placed inside the
	 * <code>WorkFrame</code> to edit the graphic element.
	 * 
	 * @see Canvas
	 */
	private Canvas canvas;

	/**
	 * an instance of <code>GraphicElement</code> that will be edited inside the
	 * graphic editor.
	 * 
	 * @see GraphicElement
	 */
	private GraphicElement graphicElement;

	public WorkFrame(GraphicElement graphicElement) {
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.setSize(600, 600);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		canvas = new Canvas(graphicElement);
		StateManager manager = new StateManager(canvas, this);
		this.add(BorderLayout.WEST, new MyToolBar(manager));
		getContentPane().add(canvas);
		setSize(450, 450);
		this.graphicElement = graphicElement;
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		this.addWindowListener(new WorkFrameWindowListener(new Canvas(graphicElement), graphicElement.getSlot()));
	}

	public JPanel getPanel() {
		return canvas;
	}

	public GraphicElement getGraphicElement() {
		return graphicElement;
	}

}
