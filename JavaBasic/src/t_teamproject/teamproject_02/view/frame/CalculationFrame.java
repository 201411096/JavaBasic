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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import t_teamproject.teamproject_02.dao.OrderDao;
import t_teamproject.teamproject_02.dao.OrderDaoImpl;
import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;
import t_teamproject.teamproject_02.view.button.MenuButton;
import t_teamproject.teamproject_02.view.panel.ProductMenuListPanel;
import t_teamproject.teamproject_02.vo.Employee;
import t_teamproject.teamproject_02.vo.Product;

public class CalculationFrame extends JFrame{
	Employee employee; // 로그인한 정보
	ArrayList<Product> calProductList;
	
	JMenuBar jmenubar;
	JMenu menu;
	JMenuItem jmenuitem1;
	JMenuItem jmenuitem2;
	
	JTabbedPane jtabbepedPane;
	String jtabbedPaneItem [] = {"메인메뉴", "사이드메뉴", "음료수", "세트메뉴"}; //tabbedpane에 들어갈 이름
	ProductMenuListPanel menuPanelList [] = new ProductMenuListPanel[4];

	ProductManagementDao pmimpl;
	ProductDao pimpl;
	OrderDao orderDaoImpl;
	
	JPanel rightPanel;
	JList calculationList;
	JButton orderButton, cancelButton;
	
	int shoppingCart [][]; //맨앞 0부분이 pid 1부분이 개수
	int productCount [][]; //맨앞 0부분이 pid 1부분이 개수
	String productStringList [];
	
	public CalculationFrame(Employee employee) {
		this.employee = employee;
		connectDB();
		initializeProductCountAndshoppingCart();
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
			calculationList = new JList(makeListStringArray(shoppingCart, productCount));
			right_center_panel.add(calculationList);
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
		cancelButton.addActionListener(new ActionListener() { //취소버튼 구현
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "전체 취소하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
					initializeList();
			}
		});
		orderButton.addActionListener(new ActionListener() { //주문버튼 구현
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
				{
					//계산 내용 관리
					//화면의 재고와 db재고 동기화
					new OrderListFrame(productStringList);
					orderDaoImpl.insertOrder(productStringList, calProductList);
					subProductCountFromShoppingCart();
					initializeList(); //마지막에 쇼핑카트와 jlist내용을 초기화함 (주문버튼에서는 초기화하기 전에 재고 배열도 계산을 해줘야됨)
					
				}
			}
		});
	}
	class MenuButtonHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MenuButton b = (MenuButton) e.getSource();
			Boolean check = false;
			int pid = b.getP().getId();
			for(int i=0; i<shoppingCart[0].length; i++)
			{
				if(shoppingCart[0][i]==pid)
				{
					check=true;
					if(shoppingCart[1][i]==productCount[1][i])
					{
						JOptionPane.showMessageDialog(null, "재고가 부족합니다.");
					}else {
						shoppingCart[1][i]++;
						calculationList.setListData(makeListStringArray(shoppingCart, productCount));
						productStringList=makeListStringArray(shoppingCart, productCount); //외부적으로 관리할 배열	
					}
					
				}	
			}
			if(check==false) // 계산화면을 처음 켯을때부터 재고가 없는 경우
				JOptionPane.showMessageDialog(null, "재고가 부족합니다.");
		}
	}
	public void connectDB() {
		try {
			pmimpl = new ProductManagementDaoImpl();
			pimpl = new ProductDaoImpl();
			orderDaoImpl = new OrderDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	public void initializeProductCountAndshoppingCart() { //처음 프레임 화면을 만들기전에만 실행
		try {
			calProductList = pmimpl.getAllProduct();							//제품리스트도 가져옴
			ArrayList<ArrayList> arrayList = pmimpl.getPidCountFromproduct();
			productCount = new int[2][arrayList.size()];
			shoppingCart = new int[2][arrayList.size()];
			for(int i=0; i<arrayList.size(); i++)
			{
				productCount[0][i]=(int)arrayList.get(i).get(0);
				productCount[1][i]=(int)arrayList.get(i).get(1);
				shoppingCart[0][i]=(int)arrayList.get(i).get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String[] makeListStringArray(int [][] shoppingCart, int[][] productCount) {
		int arrayLength=0;
		for(int i=0; i<shoppingCart[0].length; i++) //리스트에 들어갈 배열 길이를 정함
		{
			if(shoppingCart[1][i]!=0)
				arrayLength++;
		}
		String array [] = new String[arrayLength];
		int arrayCnt=0;
		for(int i=0; i<shoppingCart[0].length; i++)
		{
			if(shoppingCart[1][i]!=0)
			{
				Product p = null;
				for(int j=0; j<calProductList.size(); j++)
				{
					if(calProductList.get(j).getId()==shoppingCart[0][i]) //쇼핑카드의 pid와 일치하는 product정보를 가져옴
						p=calProductList.get(j);
				}
				array[arrayCnt]=new String(p.getName() + "                                    " + shoppingCart[1][i] + "                                     " + p.getPrice()*shoppingCart[1][i]);
				arrayCnt++;
			}
		}
		return array;
	}
	public void initializeList() { //쇼핑카트와 jlist의 data를 비워줌
		for(int i=0; i<shoppingCart[1].length; i++)
		{
			shoppingCart[1][i]=0; //쇼핑카트를 0으로 전부 초기화
			productStringList = new String[0]; //쇼핑리스트를 담은 스트링을 초기화
			calculationList.setListData(productStringList);
		}
	}	
	public void subProductCountFromShoppingCart(){ //주문시에 재고를 관리하는 배열에서 쇼핑카트의 개수만큼 -를 해줌
		for(int i=0; i<shoppingCart[1].length; i++)
		{
			productCount[1][i]-=shoppingCart[1][i]; //재고에서 쇼핑카트의 개수만큼 빼줌
		}
	}	
	public Employee getEmployee() { //로그인 세션 관리에 사용
		return employee;
	}
	public void setEmployee(Employee employee) { //로그인 세션 관리에 사용
		this.employee = employee;
	}	
}
