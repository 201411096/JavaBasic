package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class OrderListFrame extends JFrame{
	JPanel rightPanel;
	JList calculationList;
	
	JButton sendOrderListButton;
	
	String productStringList [];
	String productNameList [];
	int productCountList [];
	int productPriceList [];
	int totalPrice=0;
	
	public OrderListFrame(String productStringList []) {
		this.productStringList=productStringList;

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
		
	}
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
