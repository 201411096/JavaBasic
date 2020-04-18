package t_teamproject.teamproject_02.temp.view.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

import t_teamproject.teamproject_02.temp.view.panel.EmployeeManageMentPanel;
import t_teamproject.teamproject_02.temp.view.panel.ProductManagementPanel;
import t_teamproject.teamproject_02.temp.vo.Employee;

public class ManagementFrame extends JFrame{
	Employee employee;
	JMenuBar jmenubar;
	JMenu menu;
	JMenuItem jmenuitem1;
	JMenuItem jmenuitem2;
	JTabbedPane jtabbepedPane;
	String jtabbedPaneItem [] = {"직원관리", "제품관리"};
	EmployeeManageMentPanel employeeManageMentPanel;
	ProductManagementPanel productManagementPanel;
	public ManagementFrame(Employee employee) {
		this.employee = employee;
		display();
		eventProc();
	}
	public void display() {
		jmenubar = new JMenuBar();
		menu = new JMenu("메뉴");
		jmenuitem1 = new JMenuItem("선택 화면");
		jmenuitem2 = new JMenuItem("로그아웃");
		menu.add(jmenuitem1);
		menu.add(jmenuitem2);
		
		
		jmenubar.add(menu);
		setJMenuBar(jmenubar);
		
		jtabbepedPane = new JTabbedPane();
		employeeManageMentPanel = new EmployeeManageMentPanel(this);
		productManagementPanel = new ProductManagementPanel(this);
		jtabbepedPane.addTab(jtabbedPaneItem[0], employeeManageMentPanel);
		jtabbepedPane.addTab(jtabbedPaneItem[1], productManagementPanel);
		/*
		 	jtabbedPane에 붙어야할 내용
		 */
		add(jtabbepedPane);
		setTitle("매장 관리 창");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		jmenuitem1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectFrame(employee);
				dispose();
			}
		});
		jmenuitem2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
