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
	EmployeeManageMentPanel employeeManageMentPanel;		//	직원관리 패널
	ProductManagementPanel productManagementPanel;			//	제품관리 패널
	ProductCountGraphPanel productCountGraphPanel;			//	제품재고관리 패널
	SalesManagementGraphPanel salesManagementGraphPanel;	//	매출요약 패널
	public ManagementFrame(Employee employee) {
		this.employee = employee;
		display();
		eventProc();
	}
	
	public void display() {									// jmenubar 구성 시작
		jmenubar = new JMenuBar();
		menu = new JMenu("메뉴");
		jmenuitem1 = new JMenuItem("선택 화면");
		jmenuitem2 = new JMenuItem("로그아웃");
		menu.add(jmenuitem1);
		menu.add(jmenuitem2);								// jmenubar 구성 끝
		
		
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
		jmenuitem1.addActionListener(new ActionListener() {	// jmenubar의 선택화면 클릭시 현재 화면을 닫고 선택화면 프레임을 염(로그인 세션 관리를 위해 맨 처음 로그인시에 받아뒀던 로그인 정보도 같이 넘김 
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectFrame(employee);
				dispose();
			}
		});
		jmenuitem2.addActionListener(new ActionListener() { // jmenubar의 로그인화면 클릭시 현재 화면을 닫고 로그인화면 프레임을 염
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		jtabbepedPane.addChangeListener(new ChangeListener() { // 탭 선택 시 해당 패널	화면 재구성	 함수 호출	
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
