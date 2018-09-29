package view;

import java.beans.PropertyVetoException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.Actions;
import model.Element;
import model.GraphicElement;
import model.Slot;
import model.TextElement;
import net.miginfocom.swing.MigLayout;

/**
 * <code>public class SlotPanel extends JPanel implements Observer</code><br>
 * A custom <code>{@link JPanel}</code> instance that visually represents
 * <code>{@link Slot}</code>.<br>
 * It's purpose is to display an <code>{@link Element}</code> inside of it as
 * well as insertion and deletion.
 * 
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class SlotPanel extends JPanel implements Observer {
	/**
	 * A Slot instance that SlotPanel contains.
	 * @author Stefan
	 */
	private Slot slot;
	
	/**
	 * A JPanel that this SlotPanel displays.
	 * @author Stefan
	 * 
	 */
	private JPanel content;
	
	/**
	 * This constructor takes a Slot instance as an argument and displays its element as a TextPane or a Canvas.
	 * @author Stefan
	 * @param s
	 */
	
	public SlotPanel(Slot s) {

		this.slot = s;
		this.setLayout(new MigLayout());
		
		Element e = s.getElement();
		
		if (e instanceof TextElement) {
			TextElement te = (TextElement) e;

			this.setContent(new TextPanel(te.getText()));

		}
		if(e instanceof GraphicElement){
			GraphicElement ge = (GraphicElement) e;
			
			this.setContent(new Canvas(ge));
		}
	}

	public Slot getSlot() {
		return slot;
	}

	public JPanel getContent() {
		return content;
	}
	/**
	 * This method sets a JPanel to be displayed on SlotPanel.
	 * @param content
	 */
	public void setContent(JPanel content) {

		if (slot.getElement() != null) {
			slot.getElement().addObserver(this);
			if (slot.getElement().getElements().size() != 0) {
				for (Element e : slot.getElement().getElements()) {
					e.addObserver(this);
				}
			}
		}
		this.removeAll();

		this.content = content;
		this.add(new JScrollPane(content), "push,grow");
		this.repaint();
	}

	@Override
	public void update(Observable o, Object arg) {

		if (o instanceof Element) {

			if ((int) arg == Actions.DELETE) {

				if (o instanceof GraphicElement) {

					if (((GraphicElement) o).getParent() instanceof GraphicElement) {

						this.setContent(new Canvas((GraphicElement) ((GraphicElement) o).getParent()));
					} else {

						this.setContent(new Canvas((GraphicElement) o));

					}

					try {
						((InternalFrame) this.getParent().getParent().getParent().getParent().getParent().getParent()
								.getParent()).setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;

				}
				if (o instanceof TextElement)
					this.removeAll();

				this.repaint();
			}
			if ((int) arg == Actions.PROP_CHANGE) {
				if (o instanceof TextElement) {
					TextElement te = (TextElement) o;
					this.setContent(new TextPanel(te.getText()));
					try {
						((InternalFrame) this.getParent().getParent().getParent().getParent().getParent().getParent()
								.getParent()).setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					;

				}

			}
		}

	}

}
