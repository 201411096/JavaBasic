package t_teamproject.teamproject_02.temp.view;

import java.awt.Color;

import javax.swing.JFrame;

import t_teamproject.teamproject_02.temp.vo.Employee;

public class CalculationFrame extends JFrame{
	Employee employee;
	
	public CalculationFrame(Employee employee) {
		this.employee = employee;
		display();
		eventProc();
	}
	public void display() {
		setTitle("계산 화면");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setBackground(Color.white);
	}
	public void eventProc() {
		
	}
}
