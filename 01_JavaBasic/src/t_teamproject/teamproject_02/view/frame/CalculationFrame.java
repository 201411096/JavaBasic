package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
	
	int shoppingCart [][]; //맨앞 0부분이  pid 1부분이 개수	//shoppingCart[0][]에 pid(제품식별번호)를 담고 shoppingCart[1]은 그 제품을 장바구니에 몇개나 담았는지에 대해 저장
	int productCount [][]; //맨앞 0부분이  pid 1부분이 개수	//productCount[0]에 pid(제품식별번호)를 담고 productCount[1]은 그 제품에 대한 재고의 갯수를 나타냄
	String productStringList [];
	
	public CalculationFrame(Employee employee) {	//	프레임 생성시에 로그인 할 때 받아왔던 직원정보를 그대로 넘겨받음
		this.employee = employee;
		connectDB();								//	db연결
		initializeProductCountAndshoppingCart();	//	구매할 물품의 갯수를 셀 배열과 구매할 물품의 재고를 관리할 배열을 제품 목록에 있는 수만큼 배열 크기 할당 및 초기화 	>> 컴포넌트 동적 배치에도 사용
		display();									//	화면 구성
		eventProc();								//	이벤트 연결
	}
	public void display() {
		jmenubar = new JMenuBar();					//	jmenubar 구성 시작
		menu = new JMenu("메뉴");
		jmenuitem1 = new JMenuItem("선택 화면");
		jmenuitem2 = new JMenuItem("로그아웃");
		menu.add(jmenuitem1);
		menu.add(jmenuitem2);
		jmenubar.add(menu);
		setJMenuBar(jmenubar);						//	jmenubar 구성 끝
		
		jtabbepedPane = new JTabbedPane();
		jtabbepedPane.setBounds(0, 0, 1500, 960);	//	지정된 위치에 지정된 크기만큼 배치
		for(int i=0; i<menuPanelList.length; i++)	//	tabbedPane에 붙일 ProductMenuListPanel들 생성해서 붙임
		{
			menuPanelList[i] = new ProductMenuListPanel(this, jtabbedPaneItem[i]);
			jtabbepedPane.addTab(jtabbedPaneItem[i], menuPanelList[i]);
		}
		
		rightPanel = new JPanel();					//	오른쪽 장바구니 및 주문버튼이 들어갈 패널
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBounds(1500, 0, 420, 960);
		
			JPanel right_north_panel = new JPanel();
			right_north_panel.setLayout(new GridLayout(1, 3));
			right_north_panel.add(new JLabel("메뉴", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("수량", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("가격", SwingConstants.CENTER));
			JPanel right_center_panel = new JPanel();
			calculationList = new JList(makeListStringArray(shoppingCart, productCount));	//shoppingcart와 productcount를 받아서 jlist의 내용으로 사용할 string 배열을 만들어서 삽ㅇ잉ㅂ
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
		setExtendedState(JFrame.MAXIMIZED_BOTH);			//	창 최대화
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		jmenuitem1.addActionListener(new ActionListener() { //	메뉴바의 메뉴아이템 이벤트	-- 현재 창을 닫고 선택 화면으로 돌아감
			@Override
			public void actionPerformed(ActionEvent e) {
				new SelectFrame(employee);
				dispose();
			}
		});
		jmenuitem2.addActionListener(new ActionListener() { //	메뉴바의 메뉴아이템 이벤트	-- 현재 창을 닫고 로그인 화면으로 돌아감
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoginFrame();
				dispose();
			}
		});
		MenuButtonHandler me = new MenuButtonHandler();							//	메뉴버튼들에 붙일 이벤트 핸들러 생성
		for(int i=0; i<menuPanelList.length ; i++) {							//	메뉴버튼을 붙이는 패널의 갯수만큼 반복
			for(int j=0; j<menuPanelList[i].getMenuButtonList().length; j++) {	//	메뉴버튼의 패널에 붙어있는 메뉴버튼의 갯수만큼 해당 메뉴버튼패널에 이벤트 리스너 연결
				menuPanelList[i].getMenuButtonList()[j].addActionListener(me);
			}
		}
		cancelButton.addActionListener(new ActionListener() { 					//	취소버튼 클릭시 이벤트 추가
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "전체 취소하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
					initializeList();											//	구매할 제품 개수를 담고 있는 배열을 초기화하고 jlist의 내용이 되는 배열과 jlist초기화 
			}
		});
		orderButton.addActionListener(new ActionListener() { 					//	주문버튼 구현
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "확인", JOptionPane.YES_NO_OPTION);
				if(result == JOptionPane.YES_OPTION)
				{
					//계산 내용 관리
					//화면의 재고와 db재고 동기화
					new OrderListFrame(productStringList);						//	주문 후 최종확인 창 시작

					orderDaoImpl.insertOrder(productStringList, calProductList);//	제품 주문 완료(소비자에게)
																				//	ㄴ1.	판매항목들을 묶어줄수있는 영수증 테이블에  영수증식별번호와 총가격 삽입 (한 주문에 포함된 여러 메뉴들을 묶어줌)
																				//	ㄴ2.	주문한 메뉴의 개수만큼  각 판매항목당 판매내역을 삽입(제품 번호, 판매갯수, 판매날짜)
																				//	ㄴ3.	식재료 관리 테이블에서 각 판매항목당 판매된 갯수만큼 먼저 입고된 식재료를 삭제(오래된 식재료부터 판매)
					subProductCountFromShoppingCart();							//	화면상에서 관리하는 제품들에 대한 재고를 담는 배열을 판매한 제품만큼 빼줌
					initializeList(); 											//마지막에 shoppingCart(구매할 제품 개수를 담고 있는 배열)와 jlist내용을 초기화함 (주문버튼에서는 초기화하기 전에 재고 배열도 계산을 해줘야됨)
					
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
	public void connectDB() {							//db연결
		try {
			pmimpl = new ProductManagementDaoImpl();
			pimpl = new ProductDaoImpl();
			orderDaoImpl = new OrderDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* 함수이름 : initializeProductCountAndshoppingCart
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 :  모든 제품리스트를 가져와서 제품리스트의 크기만큼 productCount와 shoppingCart의 크기 할당 및 초기화
	 * 			ㄴ 실행시기 : 처음 프레임 화면 구성시에 실행
	 * 			ㄴ 제품의 개수만큼 컴포넌트 동적배치를 위해 사용
	 */
	public void initializeProductCountAndshoppingCart() {
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
	/* 함수이름 : makeListStringArray
	 * 인자값 : shoppingCart(장바구니), productCount(재고 개수를 관리하는 배열)
	 * 반환값 : JList 구성에 사용할 String 배열 (JList 내용)
	 * 함수설명 : JList 구성에 사용할 String 배열을 구성
	 */
	public String[] makeListStringArray(int [][] shoppingCart, int[][] productCount) {	
		int arrayLength=0;
		for(int i=0; i<shoppingCart[0].length; i++) // 장바구니에 갯수가 1개이상인 제품들의 개수를 세서 리스트에 들어갈 배열 길이를 정함(JLIst의 행갯수를 결정)
		{
			if(shoppingCart[1][i]!=0)
				arrayLength++;
		}
		String array [] = new String[arrayLength];	//	Jlist의 행갯수만큼 String 배열 할당(반환할 배열)
		int arrayCnt=0;
		for(int i=0; i<shoppingCart[0].length; i++)
		{
			if(shoppingCart[1][i]!=0)				//	장바구니에 갯수가 1개이상인 제품들에 대해서만 아래 내용 실행
			{
				Product p = null;
				for(int j=0; j<calProductList.size(); j++)
				{
					if(calProductList.get(j).getId()==shoppingCart[0][i]) //쇼핑카드의 pid와 일치하는 product정보를 가져옴
						p=calProductList.get(j);
				}
				array[arrayCnt]=new String(p.getName() + "                                    " + shoppingCart[1][i] + "                                     " + p.getPrice()*shoppingCart[1][i]);
				//	String 배열에 들어갈 String 구성
				arrayCnt++;
			}
		}
		return array;
	}
	/* 함수이름 : initializeList
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 판매 할 물품들을 담은 배열인 shoppingCart와 jlist의 data를 비워줌
	 */
	public void initializeList() { //쇼핑카트와 jlist의 data를 비워줌
		for(int i=0; i<shoppingCart[1].length; i++)
		{
			shoppingCart[1][i]=0; 							//	쇼핑카트를 0으로 전부 초기화
			productStringList = new String[0]; 				//	쇼핑리스트를 담은 스트링을 초기화
			calculationList.setListData(productStringList);	//	 쇼핑리스트를 담는 jlist 재구성
		}
	}
	/* 함수이름 : subProductCountFromShoppingCart
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : productCount(주문시에 재고를 관리하는 배열)에서 shoppingCart(판매 할 물품들을 담은 배열)만큼 -를 해줌
	 */
	public void subProductCountFromShoppingCart(){
		for(int i=0; i<shoppingCart[1].length; i++)
		{
			productCount[1][i]-=shoppingCart[1][i]; 
		}
	}	
	public Employee getEmployee() { 			//로그인 세션 관리에 사용
		return employee;
	}
	public void setEmployee(Employee employee) { //로그인 세션 관리에 사용
		this.employee = employee;
	}	
}
