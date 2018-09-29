package view;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.Actions;
import model.Page;
import model.Slot;
import net.miginfocom.swing.MigLayout;

/**
 * <code>public class PagePanel extends JPanel implements Observer</code><br>
 * A custom <code>{@link JPanel}</code> that visually represents Page. <br>
 * It has a list of slotPanels that it contains and since it is an observer,<br>
 * it is responsible for insertion and deletion of <code>{@link Slot}s</code>
 * from itself.
 * 
 * @author Stefan
 *
 */
@SuppressWarnings("serial")
public class PagePanel extends JPanel implements Observer{
	/**
	 * A Page instance that the PagePanel displays.
	 * @author Stefan
	 */
	private Page page;
	
	/**
	 * An ArrayList of slotPanels that the PagePanel displays.
	 * @author Stefan
	 */
	private ArrayList<SlotPanel> slotPanels = new ArrayList<SlotPanel>();
	
	/**
	 * A constructor that takes a Page as its argument and adds SlotPanels manually if it contains any Slots.
	 * @author Stefan
	 * @param page
	 */
	public PagePanel(Page page){
		this.page = page;
		page.addObserver(this);
		this.setLayout(new MigLayout("debug"));
	
		if(page.getSlots().size()!=0){
			addSlotPanelsInternally();
		}
	}
	public Page getPage() {
		return page;
	}
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Page){
			if((int)arg == Actions.INSERT){
				Slot temp = page.getSlots().get(page.getSlots().size()-1);
				temp.addObserver(this);
				SlotPanel sp = new SlotPanel(temp);
				slotPanels.add(sp);
				this.add(sp,"push,grow,wrap");
				this.repaint();
				
				System.out.println("Adding slot"+temp);
			}
		}
		
		if(o instanceof Slot){

			if((int)arg == Actions.DELETE){
				SlotPanel temp = null;
				for(SlotPanel sp : slotPanels){
					if(sp.getSlot() == (Slot)o){
						temp = sp;
						break;
					}
				}
				this.remove(temp);
				slotPanels.remove(temp);
				this.repaint();
			}
		}
		
	}
	public ArrayList<SlotPanel> getSlotPanels() {
		return slotPanels;
	}

	/**
	 * This method makes SlotPanels based on the Slot(s) in the Page. It is usually being called when a non-empty page has been passed as an argument (
	 * in case of a Project being imported or a GeDocument being shared).
	 * @author Stefan
	 */
	private void addSlotPanelsInternally() {
		for (Slot s : page.getSlots()) {
			SlotPanel temp = new SlotPanel(s);
			slotPanels.add(temp);
			this.add(temp, "push,grow,wrap");
			this.repaint();
		}
	}
}
