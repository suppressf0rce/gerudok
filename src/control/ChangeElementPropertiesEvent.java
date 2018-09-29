package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Element;
import model.GraphicElement;
import model.Slot;
import model.TextElement;
import view.TextEditorFrame;
import view.WorkFrame;
/**
 * A class that implements ActionListener. It contains an element which is either going to be re-displayed as a Graphical or Text element.
 * @author Stefan
 *
 */
public class ChangeElementPropertiesEvent implements ActionListener {
	/**
	 * An Element that is going to be redisplayed.
	 * @author Stefan
	 * 
	 */
	private Element element;
	/**
	 * A Constructor that takes an Element as a parameter.
	 * @param element
	 * @author Stefan
	 */
	public ChangeElementPropertiesEvent(Element element) {
		this.element = element;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (element instanceof TextElement) {
			new TextEditorFrame((TextElement) element, (Slot) element.getSlot());
		}else if(element instanceof GraphicElement){
			if(((GraphicElement) element).getParent() instanceof GraphicElement)
			{
				new WorkFrame((GraphicElement) ((GraphicElement) element).getParent());
			}else{
				new WorkFrame((GraphicElement) element);
			}

		}

	}

}
