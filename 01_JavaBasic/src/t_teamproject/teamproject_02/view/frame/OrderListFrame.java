package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderListFrame extends JFrame{	//주문 버튼 확인 후 나오는 화면
	JPanel rightPanel;
	JList calculationList;
	
	JButton sendOrderListButton;
	
	String productStringList [];
	String productNameList [];
	int productCountList [];
	int productPriceList [];
	int totalPrice=0;
	
	public OrderListFrame(String productStringList []) { // 프레임 생성시 계산화면에서 JList의 내용이던 String 배열을 받아옴
		this.productStringList=productStringList;		// 영수증에 출력할 제품의 이름과 제품당 갯수와 갯수*제품가격을 담은 배열을 가져옴

		decodeProductStringList();
		display();
		eventProc();
	}
	public void display() {
		rightPanel = new JPanel();
		rightPanel.setLayout(new BorderLayout());
		rightPanel.setBounds(1500, 0, 420, 960);
		
			JPanel right_north_panel = new JPanel();
			right_north_panel.setLayout(new GridLayout(1, 3));
			right_north_panel.add(new JLabel("메뉴", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("수량", SwingConstants.CENTER));
			right_north_panel.add(new JLabel("가격", SwingConstants.CENTER));
			JPanel right_center_panel = new JPanel();
			calculationList = new JList(productStringList);
			right_center_panel.add(calculationList);
			JPanel right_south_panel = new JPanel();
			right_south_panel.setLayout(new GridLayout(2,1));
			
			right_south_panel.add(new JLabel("합계 :" + totalPrice, SwingConstants.CENTER));
			sendOrderListButton = new JButton("메일 전송");
			right_south_panel.add(sendOrderListButton);
		rightPanel.add(right_north_panel, BorderLayout.NORTH);
		rightPanel.add(right_center_panel, BorderLayout.CENTER);
		rightPanel.add(right_south_panel, BorderLayout.SOUTH);
		
		add(rightPanel);
		setTitle("영수증 출력화면");
		setBounds(750, 400, 450, 300);
		setVisible(true);
	}
	public void eventProc() {
		sendOrderListButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {	// 영수증에 사용할 이름 배열, 개수 배열, 가격 배열, 총가격을 이메일 전송프레임으로 보내면서 현재프레임을 종료하면서 메일 전송 프레임 생성
				new SendMailFrame(productNameList, productCountList, productPriceList, totalPrice);
				dispose();
			}
		});
	}
	/* 함수이름 : decodeProductStringList
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 이전 프레임으로부터 받은 productStringList를 제품이름배열, 제품개수배열, 제품가격배열, 총가격으로 파싱하는 함수
	 */
	public void decodeProductStringList() {
		productNameList = new String[productStringList.length];
		productCountList = new int[productStringList.length];
		productPriceList = new int[productStringList.length];
		for(int i=0; i<productStringList.length; i++)
		{
			StringTokenizer st = new StringTokenizer(productStringList[i]);
			productNameList[i] = st.nextToken();
			productCountList[i] = Integer.parseInt(st.nextToken());
			productPriceList[i] = Integer.parseInt(st.nextToken());
			totalPrice+=productCountList[i]*productPriceList[i];
		}
	}
}
