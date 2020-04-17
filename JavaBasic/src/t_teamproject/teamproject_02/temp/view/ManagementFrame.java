package t_teamproject.teamproject_02.temp.view;

import java.awt.Color;

import javax.swing.JFrame;

import t_teamproject.teamproject_02.temp.vo.Employee;

public class ManagementFrame extends JFrame{
	Employee employee;
	
	public ManagementFrame(Employee employee) {
		this.employee = employee;
		display();
		eventProc();
	}
	public void display() {
		setTitle("매장 관리 창");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setBackground(Color.white);
	}
	public void eventProc() {
		
	}
}
