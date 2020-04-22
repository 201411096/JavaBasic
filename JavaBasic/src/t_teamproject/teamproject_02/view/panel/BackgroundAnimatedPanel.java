package t_teamproject.teamproject_02.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.JPanel;

public class BackgroundAnimatedPanel extends JPanel{
	Image image;

	public BackgroundAnimatedPanel(String path) throws MalformedURLException {
//		image = Toolkit.getDefaultToolkit().createImage("src\\t_teamproject\\teamproject_02\\imgs\\background\\loginFrameBackground.gif");
		image = Toolkit.getDefaultToolkit().createImage(path);
		}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}
}
