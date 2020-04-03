package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class MainFrame extends JFrame implements ActionListener{
	private JTabbedPane tabbedPane = new JTabbedPane();
	private String tabbedPaneName [] = {"메인메뉴", "사이드/음료", "토핑", "세트메뉴"}; // tabbedPane에 사용하는 문자열
	private MenuPanel menuPanel[] = new MenuPanel[4]; // tabbedPane에 들어가는 패널들
	
	//메인화면으로 사용할  
	private ImageIcon mainScreen = new ImageIcon("src/y_teamproject/imgs/main.jpg");
	private JButton mainScreenButton = new JButton();
	private MainScreenPanel m;
	
	private OrderPanel orderPanel;					   // 우측에 붙는 패널
	private JButton buttonArray [] = new JButton[16]; // 메뉴틀 버튼
	private ImageIcon imageIconArray[] = new ImageIcon[16]; // 메뉴 버튼들에 들어가는 이미지
	private ImageIcon mostImageIconArray[] = new ImageIcon[16]; // 가장 많이 팔린 메뉴에 들어가는 이미지
	private Menu menu = new Menu();
	private int menuCount [] = new int[menu.getMenuNameList().length]; // 판매 갯수 세는 배열
	private int totalmenuCount [] = new int[menu.getMenuNameList().length]; // 총 판매 갯수 세는 배열
	
	//orderPanel에 추가할 내용
	private JTextArea orderTextArea = new JTextArea(); // 주문 내역이 나오는 textArea
	private JButton orderButton = new JButton("주문"); // 주문 버튼
	private JButton totalCancelButton = new JButton("전체취소"); // 주문 전체 취소 버튼
	private LineBorder lineBorder = new LineBorder(new Color(165,165,165), 3); // 테두리 효과에 사용하는 테두리
	private JButton salesSummaryButton = new JButton("판매 순위"); // 판매 순위 버튼

	private JButton prevButton;	//이전 버튼에 대한 내용
	private JButton mostButton;	//오늘 가장 많이 팔린 메뉴
	private int most_idx = -1;
	
	public MainFrame() {
		for(int i=0; i<imageIconArray.length; i++) // 이미지 로딩 &버튼에 연결
		{
			imageIconArray[i] = new ImageIcon("src/y_teamproject/imgs/" + i +".png"); // 각 이미지들을 불러옴
			mostImageIconArray[i] = new ImageIcon("src/y_teamproject/imgs/0_" +i+".jpg" ); // 가장 많이 팔린 메뉴의 이미지들
			buttonArray[i] = new JButton(imageIconArray[i]);					      // 각 버튼들에 이미지 아이콘 추가
			buttonArray[i].setBackground(Color.white);								  // 버튼들의 색 변경
		}
		for(int i=0; i<menuPanel.length; i++) //tabbedPane에 붙이는 메뉴패널들에 버튼 추가
		{
			menuPanel[i] = new MenuPanel(buttonArray[i*4], buttonArray[i*4+1], buttonArray[i*4+2], buttonArray[i*4+3]); // 메뉴 패널마다 4개의 버튼들을 붙임
		}
		orderTextArea.setPreferredSize(new Dimension(420, 540));														// 주문 내역이 나오는 영역 크기 설정
		orderPanel = new OrderPanel(orderTextArea, orderButton, totalCancelButton, salesSummaryButton);					// 주문 패널 생성(주문 내역 textArea, 주문 버튼, 주문취소 버튼, 판매순위버튼)
		orderPanel.setBorder(lineBorder);																				// 주문 패널의 테두리 설정
	}
	public void display() {
		setLayout(new BorderLayout());						// mainFrame의 layout 설정
		mainScreenButton.setIcon(mainScreen); 				//메인화면 버튼에 이미지 세팅
		m = new MainScreenPanel(mainScreenButton, mainScreen);//
		tabbedPane.add(m, "메인화면");	
		
		for(int i=0; i<menuPanel.length; i++)				
			tabbedPane.add(menuPanel[i], tabbedPaneName[i]);// 메뉴 패널 4개와 그 이름들을 담은 배열들을 tabbedPane에 추가 
		add(tabbedPane); //tabbedPane을 mainFrame에 부착
		add(orderPanel, BorderLayout.EAST);					// order패널을 mainFrame 동쪽에 부착
		setTitle("엽기떡볶이");									// mainFrame의 title 지정
		setSize(1920, 1080);								// mainFrame의 size 지정

		setBackground(new Color(255, 255, 255));			// mainFrame의 color 변경
		setVisible(true);									// mainFrame이 눈에 보이도록..
		setExtendedState(JFrame.MAXIMIZED_BOTH); 			// 프로그램 시작시 mainFrame 최대화
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// mainFrame의 창을 닫으면 프로그램 종료
		
	}
	public void eventConn() {								// 이벤트들을 연결해주는 메소드
		for(int i=0; i<buttonArray.length; i++)				// 버튼 16개 크기 만큼 반복
		{
			buttonArray[i].addActionListener(this);			// 버튼 16개에 MainFrame에서 구현한 이벤트를 추가
			buttonArray[i].addMouseListener(new MouseAdapter() { // 버튼으로 들어갈 때 메뉴 배경색 바뀜
				@Override
				public void mouseEntered(MouseEvent e) {			// 마우스가 버튼의 범위에 들어올 경우
					JButton jb = (JButton)e.getSource();
					jb.setBackground(new Color(120, 120, 120)); // 버튼 색깔 변경
				}
				@Override
				public void mouseExited(MouseEvent e) {				// 마우스가 버튼의 범위에서 벗어날 경우
					JButton jb = (JButton)e.getSource();
//					if(jb!=mostButton)
						jb.setBackground(Color.white);					// 버튼의 색깔을 원래의 색깔(하얀색)로 변경
//					else
//						jb.setBackground(Color.red);
				}
			});
		}
		orderButton.addActionListener(new ActionListener() { 		// 주문 버튼 이벤트 리스너
			@Override
			public void actionPerformed(ActionEvent e) {	
				int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", null, JOptionPane.YES_NO_OPTION);	//주문 버튼을 누르면 yes/no option만을 가진 confirm다이얼로그를 띄움
				if(result==JOptionPane.YES_OPTION) // yes버튼을 누를경우만 실행
				{
					int total =0;				// 합계 계산에 사용할 변수
					String ms [] = menu.makeMenuString(menuCount);	// 각 메뉴별 메뉴의 이름, 개수, 개수*가격을 string 배열로 받아오는 함수
					StringBuffer temp = new StringBuffer();			// String 배열로 받아온 값을 하나로 합칠 StringBuffer
					for(int i=0; i<ms.length; i++)					// String 배열의 크기만큼 반복
					{
						if(ms[i]!=null)								// String 배열의 요소값이 널이 아닐 경우만 실행
						{
							temp.append(ms[i]);						// null이 아닌 String배열 요소의 값들을 Stringbuffer인 temp에 합침
							temp.append("\n");						// 각 요소별 개행
						}	
					}
					for(int i=0; i<menuCount.length; i++)
					{
						total+=menu.getMenuCostList()[i]*menuCount[i]; //주문 총합 계산
						totalmenuCount[i]+=menuCount[i]; 		   //메뉴별 하루? 판매 갯수 추가
						menuCount[i]=0;							   //메뉴별 개수 초기화
					}
					OrderListFrame orderListFrame = new OrderListFrame(total, temp.toString()); //주문 내역과 주문 가격의 총합을 담는 프레임을 생성해서 띄움
					
					orderTextArea.setText(null); // orderTextArea 영역을 초기화
					prevButton.setBorder(null); // 이전 버튼의 테두리 초기화
					prevButton = null;			// 이전 버튼을 담는 변수 초기화
//					if(mostButton!=null)
//						mostButton.setBackground(Color.white); // 이전의 가장 잘 팔렸었던 메뉴는 원래 색으로
					if(mostButton!=null)
						mostButton.setIcon(imageIconArray[most_idx]); // 이전의 가장 잘 팔렸었던 메뉴는 원래 이미지로
					most_idx = menu.getMaxIdx(totalmenuCount); // 가장 잘팔린 메뉴의 인덱스를 받아옴
					
					mostButton = buttonArray[menu.getMaxIdx(totalmenuCount)]; //가장 잘팔린 버튼 저장
//					mostButton.setBackground(Color.red); // 가장 잘 팔리고 있는 메뉴 색깔 변경
					mostButton.setIcon(mostImageIconArray[menu.getMaxIdx(totalmenuCount)]);
					tabbedPane.setSelectedIndex(0); // 주문 버튼 완료 시 메인 화면으로 돌아감
				}
			}
		});
		totalCancelButton.addActionListener(new ActionListener() { //주문 취소 버튼 이벤트 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "전부 취소하시겠습니까?", null, JOptionPane.YES_NO_OPTION); //주문 취소 버튼을 누를 시 yes_no option만을 가지고 있는 confirmDialog를 띄움
				if(result==JOptionPane.YES_OPTION)// yes버튼을 눌렀으면
				{
					for(int i=0; i<menuCount.length; i++)
						menuCount[i]=0;				// 각 메뉴별 개수를 담는 배열을 전부 초기화
					orderTextArea.setText(null);	// 주문 내역 창을 초기화
					prevButton.setBorder(null); // 이전 버튼의 테두리 초기화
					prevButton = null;			// 이전 버튼을 담는 변수 초기화
					tabbedPane.setSelectedIndex(0); // 주문 버튼 완료 시 메인 화면으로 돌아감
				}
			}
		});
		salesSummaryButton.addActionListener(new ActionListener() {	//매출 요약 버튼을 클리했을 경우 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesSummaryFrame salesSummaryFrame = new SalesSummaryFrame(totalmenuCount); // 매출 요약창을 만들어서 띄움(메뉴별 하루? 판매 갯수를 담는 배열을 같이 보냄) 
			}
		});
		mainScreenButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1); // 메인 화면 버튼 클릭 시 메인 메뉴 탭으로 넘어감
				
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) { //음식 아이콘에 붙는 리스너(메인프레임의 리스너)
		JButton jb = (JButton)e.getSource();
		String s = "";
		for(int i=0; i<buttonArray.length; i++)
		{
			if(jb == buttonArray[i])			//이벤트가 발생한 버튼과 같은 버튼을 찾기
			{
				menuCount[i]++;					//메뉴별 개수를 담는 배열에 저장
				//이전 버튼에 대한 내용으로 저장하는 부분
				if(prevButton !=null)			
				{
					prevButton.setBorder(null); // 이전 버튼이었던 곳의 테두리를 없앰
				}
				prevButton = jb;				// 이전 버튼에 이벤트 발생한 버튼의 내용을 넣음
				jb.setBorder(new LineBorder((Color.blue), 5)); //이벤트가 발생한 버튼의 테두리 색깔을 변경
			}
		}
		// 주문 정보에 입력
		String ms [] = menu.makeMenuString(menuCount);  // 각 메뉴별 메뉴의 이름, 개수, 개수*가격을 string 배열로 받아오는 함수
		StringBuffer temp = new StringBuffer();   		// String 배열로 받아온 값을 하나로 합칠 StringBuffer
		for(int i=0; i<ms.length; i++)					// String 배열의 크기만큼 반복
		{
			if(ms[i]!=null)								// String 배열의 요소값이 널이 아닐 경우만 실행
			{
				temp.append(ms[i]);						// null이 아닌 String배열 요소의 값들을 Stringbuffer인 temp에 합침
				temp.append("\n");						// 각 요소별 개행
			}	
		}
		orderTextArea.setText((temp).toString());		//메뉴의 이름과 갯수 가격을 주문 내역 창에 갱신
	}
}
