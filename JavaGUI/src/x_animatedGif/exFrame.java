package x_animatedGif;
import java.awt.GridLayout;
import java.awt.Image;
import java.net.MalformedURLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class exFrame extends JFrame{
	Image image;
	public exFrame() {
		Mypanel m = null;
		try {
			m = new Mypanel();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//setLayout(new GridLayout()); gridlayout은 안먹힘
		add(m);
		setVisible(true);
		setSize(1920, 1080);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new exFrame();
	}
}
