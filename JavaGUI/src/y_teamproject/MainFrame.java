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
	JTabbedPane tabbedPane = new JTabbedPane();
	String tabbedPaneName [] = {"메인메뉴", "사이드/음료", "토핑", "세트메뉴"}; // tabbedPane에 사용하는 문자열
	MenuPanel menuPanel[] = new MenuPanel[4];
	OrderPanel orderPanel;
	JButton buttonArray [] = new JButton[16]; // 메뉴틀 버튼
	ImageIcon imageIconArray[] = new ImageIcon[16]; // 메뉴 버튼들에 들어가는 이미지
	Menu menu = new Menu();
	int menuCount [] = new int[menu.menuNameList.length];
	int totalmenuCount [] = new int[menu.menuNameList.length]; //하루? 판매 갯수 세는 배열
	//orderPanel에 추가할 내용
	JTextArea orderTextArea = new JTextArea();
	JButton orderButton = new JButton("주문");
	JButton totalCancelButton = new JButton("전체취소");
	LineBorder lineBorder = new LineBorder(new Color(165,165,165), 3); // 테두리 효과에 사용하는 테두리
	//이전 버튼에 대한 내용
	JButton prevButton;
	int prevButton_idx;
	//매출 요약 버튼
	JButton salesSummaryButton = new JButton("매출 요약");
	
	public MainFrame() {
		for(int i=0; i<imageIconArray.length; i++) // 이미지 로딩 &버튼에 연결
		{
			imageIconArray[i] = new ImageIcon("src/y_teamproject/imgs/" + i +".png");
			buttonArray[i] = new JButton(imageIconArray[i]);
			buttonArray[i].setBackground(Color.white);
		}
		for(int i=0; i<menuPanel.length; i++) //tabbedPane에 붙이는 메뉴패널들에 버튼 추가
		{
			menuPanel[i] = new MenuPanel(buttonArray[i*4], buttonArray[i*4+1], buttonArray[i*4+2], buttonArray[i*4+3]);
		}
		orderTextArea.setPreferredSize(new Dimension(420, 540));
		orderPanel = new OrderPanel(orderTextArea, orderButton, totalCancelButton, salesSummaryButton);
		orderPanel.setBorder(lineBorder);
	}
	public void display() {
		setLayout(new BorderLayout());
		for(int i=0; i<menuPanel.length; i++)
			tabbedPane.add(menuPanel[i], tabbedPaneName[i]);
		add(tabbedPane);
		add(orderPanel, BorderLayout.EAST);
		setTitle("엽기떡볶이");
		setSize(1920, 1080);

		setBackground(new Color(255, 255, 255));
		setVisible(true);
		setExtendedState(JFrame.MAXIMIZED_BOTH); // 프로그램 시작시 최대화
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public void eventConn() {
		for(int i=0; i<buttonArray.length; i++)
		{
			buttonArray[i].addActionListener(this);
			buttonArray[i].addMouseListener(new MouseAdapter() { // 버튼으로 들어갈 때 메뉴 배경색 바뀜
				@Override
				public void mouseEntered(MouseEvent e) {
					JButton jb = (JButton)e.getSource();
					if(jb.getBackground()==Color.white) // 기본색(하얀색)일때만 변화
						jb.setBackground(new Color(120, 120, 120));
				}
				@Override
				public void mouseExited(MouseEvent e) {
					JButton jb = (JButton)e.getSource();
					jb.setBackground(null);
				}
			});
		}
		orderButton.addActionListener(new ActionListener() { // 주문 버튼 이벤트 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", null, JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{
					int total =0;
					String ms [] = menu.makeMenuString(menuCount);
					StringBuffer temp = new StringBuffer();
					for(int i=0; i<ms.length; i++)
					{
						if(ms[i]!=null)
						{
							temp.append(ms[i]);
							temp.append("\n");
						}	
					}
					for(int i=0; i<menuCount.length; i++)
					{
						total+= menu.menuCostList[i]*menuCount[i]; //주문 총액 계산
						totalmenuCount[i]+=menuCount[i]; //하루? 판매 갯수 추가
					}
					OrderListFrame orderListFrame = new OrderListFrame(total, temp.toString());
					
					for(int i=0; i<menuCount.length; i++)
						menuCount[i]=0;
					orderTextArea.setText(null);
					prevButton.setBorder(null); // 주문완료 이후에 이전 버튼이었던 곳의 테두리를 없애고 나머지는 동일
					prevButton = null;
					prevButton_idx = -1;
				}
				
			}
		});
		totalCancelButton.addActionListener(new ActionListener() { //주문 취소 버튼 이벤트 리스너
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "전부 취소하시겠습니까?", null, JOptionPane.YES_NO_OPTION);
				if(result==JOptionPane.YES_OPTION)
				{
					for(int i=0; i<menuCount.length; i++)
						menuCount[i]=0;
					orderTextArea.setText(null);
				}
			}
		});
		salesSummaryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SalesSummaryFrame salesSummaryFrame = new SalesSummaryFrame(totalmenuCount);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) { //메뉴 버튼들 리스너 
		JButton jb = (JButton)e.getSource();
		String s = "";
		for(int i=0; i<buttonArray.length; i++)
		{
			if(jb == buttonArray[i])
			{
				menuCount[i]++;
				//이전 버튼에 대한 내용으로 저장하는 부분
				if(prevButton ==null)
				{
					prevButton = jb;
					prevButton_idx = i;
					jb.setBorder(new LineBorder((Color.blue), 5));
				}else
				{
					prevButton.setBorder(null); // 이전 버튼이었던 곳의 테두리를 없애고 나머지는 동일
					prevButton = jb;
					prevButton_idx = i;
					jb.setBorder(new LineBorder((Color.blue), 5));
				}
			}
		}
		// 주문 정보에 입력
		String ms [] = menu.makeMenuString(menuCount);
		StringBuffer temp = new StringBuffer();
		for(int i=0; i<ms.length; i++)
		{
			if(ms[i]!=null)
			{
				temp.append(ms[i]);
				temp.append("\n");
			}	
		}
		orderTextArea.setText((temp).toString());
	}
}
