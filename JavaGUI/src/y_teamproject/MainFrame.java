package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	//orderPanel에 추가할 내용
	JTextArea orderTextArea = new JTextArea();
	JButton orderButton = new JButton("주문");
	JButton totalCancelButton = new JButton("전체취소");
	LineBorder lineBorder = new LineBorder(new Color(165,165,165), 3); // 테두리

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
		orderPanel = new OrderPanel(orderTextArea, orderButton, totalCancelButton);
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
			buttonArray[i].addActionListener(this);
 
		orderButton.addActionListener(new ActionListener() {
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
						total+= menu.menuCostList[i]*menuCount[i];
					
					JFrame frame = new JFrame();
					JLabel label1 = new JLabel("메뉴",SwingConstants.CENTER);
					JLabel label2 = new JLabel("수량",SwingConstants.CENTER);
					JLabel label3 = new JLabel("가격",SwingConstants.CENTER);
					JPanel panel = new JPanel();
					JLabel label4 = new JLabel("합계: " + total, SwingConstants.CENTER);
					panel.setLayout(new GridLayout(1, 3));
					panel.add(label1);
					panel.add(label2);
					panel.add(label3);

					frame.add(panel, BorderLayout.NORTH);
					frame.add(label4, BorderLayout.SOUTH);
					frame.add(new JTextArea(temp.toString()));
					frame.setVisible(true);
					frame.setSize(400, 400);
					frame.setLocation(800, 400);
					frame.setTitle("주문내역");
					for(int i=0; i<menuCount.length; i++)
						menuCount[i]=0;
					orderTextArea.setText(null);
				}
				
			}
		});
		totalCancelButton.addActionListener(new ActionListener() {
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
