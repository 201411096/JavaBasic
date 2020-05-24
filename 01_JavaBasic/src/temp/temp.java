package temp;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;

public class temp extends JFrame{
	temp(){
		JList jl = new JList();
		JButton b = new JButton();
		Vector datalist = new Vector();
		datalist.add(10);
		datalist.add(20);
		jl.setListData(datalist);
		add(jl, BorderLayout.CENTER);
		b.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				datalist.add(30);
				jl.setListData(datalist);	
			}
		});
		jl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				datalist.remove(jl.getSelectedIndex());
				jl.setListData(datalist);
			}
		});
		add(b, BorderLayout.SOUTH);
		setVisible(true);
		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public static void main(String[] args) {
		temp t = new temp();
	}
}
