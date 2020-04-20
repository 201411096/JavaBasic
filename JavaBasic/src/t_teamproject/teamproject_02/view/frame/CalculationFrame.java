package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;
import t_teamproject.teamproject_02.view.panel.CalculationPanel;
import t_teamproject.teamproject_02.view.panel.ProductMenuListPanel;
import t_teamproject.teamproject_02.vo.Employee;
import t_teamproject.teamproject_02.vo.Product;

public class CalculationFrame extends JFrame{
	Employee employee; // 로그인한 정보
	
	JMenuBar jmenubar;
	JMenu menu;
	JMenuItem jmenuitem1;
	JMenuItem jmenuitem2;
	
	JTabbedPane jtabbepedPane;
	String jtabbedPaneItem [] = {"메인메뉴", "사이드메뉴", "음료수", "세트메뉴"}; //tabbedpane에 들어갈 이름
	ProductMenuListPanel menuPanelList [] = new ProductMenuListPanel[4];

	HashMap<Integer, Integer> productCart2 = new HashMap<Integer, Integer>(); //장바구니(제품, 개수)
	HashMap<Product, Integer> productCart = new HashMap<Product, Integer>(); //장바구니(제품, 개수)
	HashMap<String, Integer> productCount = new HashMap<String, Integer>(); //재고(이름, 개수)
	ProductManagementDao pmimpl;
	ProductDao pimpl;
	CalculationPanel calculationPanel;
//	JPanel rightPanel;
	JList calculationList;
	
	public CalculationFrame(Employee employee) {
		this.employee = employee;
		display();
		connectDB();
		getProductCountFromDB(productCount); // 재고량 초기화
		initiallizeProductCart(productCart); // 쇼핑카트 초기화
		initiallizeProductCart2(productCart2);
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
		
//		right_panel = new JPanel();
		calculationPanel = new CalculationPanel();
		calculationPanel.setBounds(1500, 0, 420, 960);
				
		setLayout(null);
		add(jtabbepedPane);
		add(calculationPanel);
		
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
	}
	public void connectDB() {
		try {
			pmimpl = new ProductManagementDaoImpl();
			pimpl = new ProductDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void getProductCountFromDB(HashMap<String, Integer> productCount) { //현재 재고를 받아옴
		ArrayList<ArrayList> list = pmimpl.productCount();
		for(int i=0; i<list.size(); i++) {
			int cnt = (int)(list.get(i).get(0)); //count값
			String productName = (String)(list.get(i).get(1)); //제품이름값			
			productCount.put(productName, cnt);
		}
//		테스트 해보는 부분
//		Iterator it = productCount.keySet().iterator();
//		while(it.hasNext()) {
//			String key = (String)it.next();
//			int value = (int)productCount.get(key);
//			System.out.println(key + " " + value);	
//		}
	}
	public void initiallizeProductCart2(HashMap<Integer, Integer> productCart) {
		ArrayList<Product> list = pmimpl.getAllProduct();
		for(int i=0; i<list.size(); i++)
		{
			productCart.put(list.get(i).getId(), 0);		// product의 값들을 전부 0으로 초기화
		}
//		테스트 해보는 부분
//		Iterator it = productCart.keySet().iterator();
//		while(it.hasNext()) {
//			Product p = (Product)it.next();
//			int value = (int)productCart.get(p);
//			System.out.println(p.toString() + "\n 값 확인-------------\n " + value);
//		}
	}
	
	public void initiallizeProductCart(HashMap<Product, Integer> productCart) {
		ArrayList<Product> list = pmimpl.getAllProduct();
		for(int i=0; i<list.size(); i++)
		{
			productCart.put(list.get(i), 0);		// product의 값들을 전부 0으로 초기화
		}
//		테스트 해보는 부분
//		Iterator it = productCart.keySet().iterator();
//		while(it.hasNext()) {
//			Product p = (Product)it.next();
//			int value = (int)productCart.get(p);
//			System.out.println(p.toString() + "\n 값 확인-------------\n " + value);
//		}
	}

	public Employee getEmployee() { //로그인 세션 관리에 사용
		return employee;
	}
	public void setEmployee(Employee employee) { //로그인 세션 관리에 사용
		this.employee = employee;
	}
	public HashMap<Product, Integer> getProductCart() {
		return productCart;
	}
	public void setProductCart(HashMap<Product, Integer> productCart) {
		this.productCart = productCart;
	}
	public HashMap<Integer, Integer> getProductCart2() {
		return productCart2;
	}
	public void setProductCart2(HashMap<Integer, Integer> productCart2) {
		this.productCart2 = productCart2;
	}
	
}
