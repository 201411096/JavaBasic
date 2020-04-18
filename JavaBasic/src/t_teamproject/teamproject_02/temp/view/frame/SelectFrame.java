package t_teamproject.teamproject_02.temp.view.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import t_teamproject.teamproject_02.temp.vo.Employee;

public class SelectFrame extends JFrame{
	JButton lb, rb;
	Employee employee;
	public SelectFrame(Employee employee) {
		this.employee = employee;
		display();
		eventProc();
	}
	public void display() {
		lb = new JButton("계산");
		lb.setBackground(Color.white);
		rb = new JButton("매장 관리");
		rb.setBackground(Color.white);
		
		setLayout(new GridLayout(1, 2));
		add(lb);
		add(rb);
		setBounds(750, 400, 450, 300);
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		lb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new CalculationFrame(employee);
				dispose();
			}
		});
		rb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(employee.getPosition().equals("ADMIN") || employee.getPosition().equals("MANAGER"))
				{
					new ManagementFrame(employee);
					dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "접근 권한이 없습니다.");
			}
		});
	}
}
