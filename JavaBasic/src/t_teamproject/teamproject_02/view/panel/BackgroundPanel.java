package t_teamproject.teamproject_02.view.panel;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class BackgroundPanel extends JPanel{
	Image image;
	public BackgroundPanel(Image image) {
		this.image=image;
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
	
	
}
