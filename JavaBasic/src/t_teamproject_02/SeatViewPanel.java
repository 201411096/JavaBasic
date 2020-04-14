package t_teamproject_02;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatViewPanel extends JPanel implements Runnable{
	int time=0;
	JLabel l;
	public SeatViewPanel(int timer) {
		this.time=timer;
		l=new JLabel();
		add(l);
		setBackground(Color.white);
		setPreferredSize(new Dimension(100, 100));
	}

	@Override
	public void run() {
		while(true) {
			try {
				if(this.time==0)
					break;
				Thread.sleep(1000);
				l.setText(Integer.toString(time));
				time--;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	

}
