package t_teamproject.teamproject_02.temp.view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class RegisterFrame extends JFrame{
	public RegisterFrame() {
		display();
		eventProc();
	}
	public void display() {
		setTitle("직원 등록 창");
		setBounds(750, 400, 450, 300);
		setVisible(true);
		setBackground(Color.white);
	}
	public void eventProc() {
		
	}
}
