package t_teamproject.teamproject_02.view.panel;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;

import javax.swing.JPanel;

public class BackgroundAnimatedPanel extends JPanel{
	Image image;

	public BackgroundAnimatedPanel(String path) throws MalformedURLException {	//인자로 받은 경로의 이미지를 가져옴
//		image = Toolkit.getDefaultToolkit().createImage("src\\t_teamproject\\teamproject_02\\imgs\\background\\loginFrameBackground.gif");
		image = Toolkit.getDefaultToolkit().createImage(path);
		}

	public void paintComponent(Graphics g) {	//	이미지가 있다면 계속 그려줌( 이미지가 움직이는 이미지였다면 원본처럼 움직임)
		super.paintComponent(g);
		if (image != null) {
			g.drawImage(image, 0, 0, this);
		}
	}
}
