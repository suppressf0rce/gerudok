package control;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
/**
 * This class implements a MouseListener. It converts String into URL and opens it via browser.
 * @author Stefan
 *
 */
public class LabelVisitUsListener implements MouseListener{
	/**
	 * An URL to which the url String will be converted
	 * @author Stefan
	 */
	private URL url;
	/**
	 * An URI to which the URL will be converted.
	 */
	private URI uri=null;
	/**
	 * A Constructor that takes url String as a parameter. Its responsibility is to convert String to URL and URL to URI.
	 * @author Stefan
	 * @param url
	 */
	public LabelVisitUsListener(String url) {
		try {
			this.url = new URL(url);
			try {
				this.uri = this.url.toURI();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				System.out.println("Could not convert URL to URI");
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.out.println("Could not convert String to URL");
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try
        {
			Desktop.getDesktop().browse(uri);
        }
        catch (Exception z)
        {
            z.printStackTrace();
        }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
