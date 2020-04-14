package t_teamproject_02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class SeatViewPanel extends JPanel{
	Configuration config;
	int height=1500;
	int width=900;
	SeatPanel panelList [];
	public SeatViewPanel() {
		config = Configuration.getInstance();
		int col = config.getCol_seat_num();
		int row = config.getRow_seat_num();
		GridLayout layout = new GridLayout(row, col, (height-row*100)/row, (width-row*100)/col);
		setLayout(layout);
		panelList = new SeatPanel[col*row];
		for(int i=0; i<panelList.length; i++)
		{
			panelList[i] = new SeatPanel(10);
			add(panelList[i]);
			new Thread(panelList[i]).start();
		}
		
		
		
		setBackground(Color.white);
		setPreferredSize(new Dimension(height, width));
	}



}
