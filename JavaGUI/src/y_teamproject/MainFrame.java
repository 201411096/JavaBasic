package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class MainFrame extends JFrame implements ActionListener{
	JTabbedPane tabbedPane = new JTabbedPane();
	String tabbedPaneName [] = {"메인메뉴", "사이드/음료", "토핑", "세트메뉴"}; // tabbedPane에 사용하는 문자열
	MenuPanel menuPanel[] = new MenuPanel[4];
	OrderPanel orderPanel;
	JButton buttonArray [] = new JButton[16]; // 메뉴틀 버튼
	ImageIcon imageIconArray[] = new ImageIcon[16]; // 메뉴 버튼들에 들어가는 이미지
	Menu menu = new Menu();
	int menuCount [] = new int[menu.menuNameList.length];
	//orderPanel에 추가할 내용
	JTextArea orderTextArea = new JTextArea();
	JButton orderButton = new JButton("주문");

	public MainFrame() {
		for(int i=0; i<imageIconArray.length; i++) // 이미지 로딩 &버튼에 연결
		{
			imageIconArray[i] = new ImageIcon("src/y_teamproject/imgs/" + i +".png");
			buttonArray[i] = new JButton(imageIconArray[i]);
			buttonArray[i].setBackground(Color.white);
		}
		for(int i=0; i<menuPanel.length; i++) //tabbedPane에 붙이는 메뉴패널들에 버튼 추가
			menuPanel[i] = new MenuPanel(buttonArray[i*4], buttonArray[i*4+1], buttonArray[i*4+2], buttonArray[i*4+3]);
		
		orderTextArea.setPreferredSize(new Dimension(400, 540));
		orderPanel = new OrderPanel(orderTextArea, orderButton);
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventConn() {
		for(int i=0; i<buttonArray.length; i++)
			buttonArray[i].addActionListener(this);
 
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
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
					total+= menu.menuCostList[i]*menuCount[i];
				temp.append("-------------------------------------------------------\n");
				temp.append("합계 : \t\t" + total);
				
//				다이얼로그 생성해야됨
				JFrame frame = new JFrame();
				JLabel label = new JLabel("      메뉴                                         수량                                가격         ");
				frame.add(label, BorderLayout.NORTH);
				frame.add(new JTextArea(temp.toString()));
				frame.setVisible(true);
				frame.setSize(400, 400);
				frame.setLocation(800, 400);
				
				for(int i=0; i<menuCount.length; i++)
					menuCount[i]=0;
				orderTextArea.setText(null);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton)e.getSource();
		String s = "";
		for(int i=0; i<buttonArray.length; i++)
		{
			if(jb == buttonArray[i])
			{
				menuCount[i]++;
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
