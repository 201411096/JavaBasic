package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;
import t_teamproject.teamproject_02.view.button.MenuButton;
import t_teamproject.teamproject_02.view.panel.ProductMenuListPanel;
import t_teamproject.teamproject_02.vo.Employee;

public class CalculationFrame extends JFrame{
	Employee employee; // 로그인한 정보
	
	JMenuBar jmenubar;
	JMenu menu;
	JMenuItem jmenuitem1;
	JMenuItem jmenuitem2;
	
	JTabbedPane jtabbepedPane;
	String jtabbedPaneItem [] = {"메인메뉴", "사이드메뉴", "음료수", "세트메뉴"}; //tabbedpane에 들어갈 이름
	ProductMenuListPanel menuPanelList [] = new ProductMenuListPanel[4];

	ProductManagementDao pmimpl;
	ProductDao pimpl;
	JPanel rightPanel;
	JList calculationList;
	JButton orderButton, cancelButton;
	
	int shoppingCart [][]; //0부분이 pid 1부분이 개수
	int productCount [][]; //0부분이 pid 1부분이 개수
	
	public CalculationFrame(Employee employee) {
		this.employee = employee;
		display();
		connectDB();
		initializeProductCountAndshoppingCart();
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
		jtabbepedPane.setBounds(0, 0, 1500, 960);
		for(int i=0; i<menuPanelList.length; i++)
		{
			menuPanelList[i] = new ProductMenuListPanel(this, jtabbedPaneItem[i]);
			jtabbepedPane.addTab(jtabbedPaneItem[i], menuPanelList[i]);
		}
		
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBounds(1500, 0, 420, 960);
		
			JPanel right_north_panel = new JPanel();
			right_north_panel.setLayout(new GridLayout(1, 3));
			right_north_panel.add(new JLabel("메뉴", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("수량", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("가격", SwingConstants.CENTER));
			JPanel right_center_panel = new JPanel();
			calculationList = new JList();
			JPanel right_south_panel = new JPanel();
			right_south_panel.setLayout(new GridLayout(1,2));
			orderButton = new JButton("주문");
			cancelButton = new JButton("전체취소");
			right_south_panel.add(orderButton);
			right_south_panel.add(cancelButton);
		rightPanel.add(right_north_panel, BorderLayout.NORTH);
		rightPanel.add(right_center_panel, BorderLayout.CENTER);
		rightPanel.add(right_south_panel, BorderLayout.SOUTH);
						
		setLayout(null);
		add(jtabbepedPane);
		add(rightPanel);		
		
		setTitle("계산 화면");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		jmenuitem1.addActionListener(new ActionListener() { //메뉴바의 메뉴아이템 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectFrame(employee);
				dispose();
			}
		});
		jmenuitem2.addActionListener(new ActionListener() { //메뉴바의 메뉴아이템 이벤트
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		MenuButtonHandler me = new MenuButtonHandler();
		for(int i=0; i<menuPanelList.length ; i++) {
			for(int j=0; j<menuPanelList[i].getMenuButtonList().length; j++) {
				menuPanelList[i].getMenuButtonList()[j].addActionListener(me);
			}
		}
	}
	class MenuButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuButton b = (MenuButton) e.getSource();
			int pid = b.getP().getId();
			for(int i=0; i<shoppingCart[0].length; i++)
			{
				if(shoppingCart[0][i]==pid)
				{
					shoppingCart[1][i]++;
					System.out.println(shoppingCart[0][i] + " : " + shoppingCart[1][i]);
				}
					
			}
		}
	}
	public void connectDB() {
		try {
			pmimpl = new ProductManagementDaoImpl();
			pimpl = new ProductDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initializeProductCountAndshoppingCart() {
		try {
			ArrayList<ArrayList> arrayList = pmimpl.getPidCountFromproduct();
			productCount = new int[2][arrayList.size()];
			shoppingCart = new int[2][arrayList.size()];
			for(int i=0; i<arrayList.size(); i++)
			{
				productCount[0][i]=(int)arrayList.get(i).get(0);
				productCount[1][i]=(int)arrayList.get(i).get(1);
				shoppingCart[0][i]=(int)arrayList.get(i).get(0);
			}
		for(int i=0; i<arrayList.size(); i++)
			{
//				System.out.println(productCount[0][i] + " : " + productCount[1][i]);
				System.out.println(shoppingCart[0][i] + " : " + shoppingCart[1][i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Employee getEmployee() { //로그인 세션 관리에 사용
		return employee;
	}
	public void setEmployee(Employee employee) { //로그인 세션 관리에 사용
		this.employee = employee;
	}	
}
