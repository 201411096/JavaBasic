package t_teamproject_02;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SeatPanel extends JPanel implements Runnable{
	private int time=0;
	private JLabel l;
	public SeatPanel(int timer) {
		this.time=timer;
		l=new JLabel();
		add(l);
		setBackground(new Color(150, 150, 150));
		setPreferredSize(new Dimension(50, 50)); // 적용 안되는 듯
	}

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				l.setText(Integer.toString(time));
				time--;
				if(this.time==0)
				{
					synchronized(this) {
						wait();
					}
					
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public JLabel getL() {
		return l;
	}

	public void setL(JLabel l) {
		this.l = l;
	}
}
