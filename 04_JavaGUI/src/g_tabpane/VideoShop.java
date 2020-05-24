package g_tabpane;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class VideoShop extends JFrame{
	VideoAdmin video;
	RentAdmin rent;
	CustomerAdmin customer;
	
	public VideoShop() {
		video = new VideoAdmin();
		rent = new RentAdmin();
		customer = new CustomerAdmin();
		JTabbedPane pane = new JTabbedPane();
		
		pane.add("비디오관리",video);
		pane.add("대여관리",rent);
		pane.add("고객관리",customer);
		add(pane);
		setSize(800,600);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new VideoShop();
	}
}
