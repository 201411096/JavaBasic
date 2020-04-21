package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import t_teamproject.teamproject_02.jfreechart.BarChartProductCount;
import t_teamproject.teamproject_02.view.panel.EmployeeManageMentPanel;
import t_teamproject.teamproject_02.view.panel.ProductCountGraphPanel;
import t_teamproject.teamproject_02.view.panel.ProductManagementPanel;
import t_teamproject.teamproject_02.view.panel.SalesManagementGraphPanel;
import t_teamproject.teamproject_02.vo.Employee;

public class ManagementFrame extends JFrame{
	Employee employee;
	JMenuBar jmenubar;
	JMenu menu;
	JMenuItem jmenuitem1;
	JMenuItem jmenuitem2;
	JTabbedPane jtabbepedPane;
	String jtabbedPaneItem [] = {"직원관리", "제품관리", "재고관리", "매출관리"};
	EmployeeManageMentPanel employeeManageMentPanel;
	ProductManagementPanel productManagementPanel;
	ProductCountGraphPanel productCountGraphPanel;
	SalesManagementGraphPanel salesManagementGraphPanel;
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
		productCountGraphPanel = new ProductCountGraphPanel(this);
		salesManagementGraphPanel = new SalesManagementGraphPanel();
		jtabbepedPane.addTab(jtabbedPaneItem[0], employeeManageMentPanel);
		jtabbepedPane.addTab(jtabbedPaneItem[1], productManagementPanel);
		jtabbepedPane.addTab(jtabbedPaneItem[2], productCountGraphPanel);
		jtabbepedPane.addTab(jtabbedPaneItem[3], salesManagementGraphPanel);
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
		jtabbepedPane.addChangeListener(new ChangeListener() { //탭 선택 시 새로고침 효과			
			@Override
			public void stateChanged(ChangeEvent e) {
				if(jtabbepedPane.getSelectedIndex() ==2 )
				{
					productCountGraphPanel.renewal();
				}else if(jtabbepedPane.getSelectedIndex() ==3 )
				{
					salesManagementGraphPanel.renewal();
				} 	
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
