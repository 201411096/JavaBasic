package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import t_teamproject.teamproject_02.vo.Employee;

public class SelectFrame extends JFrame{
	JButton lb, rb;
	Employee employee;
	public SelectFrame(Employee employee) {	//Frame 생성시 LoginFrame(로그인 창)으로부터 로그인 정보(직원정보)를 받아옴
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
		rb.addActionListener(new ActionListener() { // 매장관리의 경우 로그인한 직원의 직급이 ADMIN이거나 MANAGER인 경우에만 접근이 가능
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