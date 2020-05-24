package x_animatedGif;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.JPanel;

public class Mypanel extends JPanel {
	Image image;

	Mypanel() throws MalformedURLException {
		image = Toolkit.getDefaultToolkit().createImage("src\\x_animatedGif\\a.gif"); 
		}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}

}
