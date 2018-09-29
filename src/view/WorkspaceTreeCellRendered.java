package view;

import java.awt.Component;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import main.Utils;
import model.CircleElement;
import model.ComplexElement;
import model.Element;
import model.GeDocument;
import model.GraphicElement;
import model.ImageElement;
import model.Page;
import model.Project;
import model.RectangleElement;
import model.Slot;
import model.SoundElement;
import model.TextElement;
import model.VideoElement;
import model.Workspace;

/**
 * <code>public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer</code>
 * This class is used for setting the icons to the tree view depending on what
 * is showing
 * 
 * @author Teodora Mladenovic
 * 
 * @see DefaultTreeCellRenderer
 *
 */
@SuppressWarnings("serial")
public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer {

	/**
	 * Represents the <code>JTree</code> which icons will be changed by this
	 * class
	 * 
	 * @see JTree
	 * @see WorkspaceTreeCellRendered
	 */
	@SuppressWarnings("unused")
	private JTree tree;

	public WorkspaceTreeCellRendered() {
		// setOpaque(true);

	}

	@SuppressWarnings("null")
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,
			int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

		this.tree = tree;

		// Setting Workspace icon
		if (value instanceof Workspace) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/workspace-icon.png")));
			setToolTipText(value.toString());

			// Setting project icon
		} else if (value instanceof Project) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/project-icon.png")));
			setToolTipText(value.toString());

			// Setting GeDocument icon
		} else if (value instanceof GeDocument) {
			if (((GeDocument) value).getOriginal() != null) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/document-icon-shortcut.png")));
				setToolTipText(value.toString());
			} else {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/document-icon.png")));
				setToolTipText(value.toString());
			}

			// Setting page icon
		} else if (value instanceof Page) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/page-icon.png")));
			setToolTipText(value.toString());

		}

		// setting slot icon
		else if (value instanceof Slot) {
			Slot slot = (Slot) value;
			Element child = (Element) slot.getChildAt(0);
			if (child == null) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/empty-slot-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof ComplexElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/complex-element-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof SoundElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/sound-element-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof TextElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/text-element-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof VideoElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/video-element-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof ImageElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/image-element-icon.png")));
				setToolTipText(value.toString());
			}
			if (child instanceof GraphicElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/graphic-element-icon.png")));
				setToolTipText(value.toString());
			}

		} // Setting Element icons
		else if (value == null) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/empty-slot-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof ComplexElement) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/complex-element-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof SoundElement) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/sound-element-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof TextElement) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/text-element-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof VideoElement) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/video-element-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof ImageElement) {
			setIcon(Utils.getScaledIcon(getClass().getResource("/resource/image-element-icon.png")));
			setToolTipText(value.toString());
		} else if (value instanceof GraphicElement) {
			if (value instanceof CircleElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/lepsi_krug.png")));
				setToolTipText(value.toString());
			} else if (value instanceof RectangleElement) {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/lepsi_kvadrat.png")));
				setToolTipText(value.toString());
			} else {
				setIcon(Utils.getScaledIcon(getClass().getResource("/resource/graphic-element-icon.png")));
				setToolTipText(value.toString());
			}
		}

		return this;
	}

}
