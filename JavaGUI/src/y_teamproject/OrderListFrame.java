package y_teamproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class OrderListFrame extends JFrame{ // 주문 내역을 보여주는 화면
	JLabel labelList [] = new JLabel[4];
	String labelListName [] = {"메뉴", "수량", "가격", "합계: "};
//	JPanel panel = new JPanel(); // 메뉴 수량 가격을 붙인 후에 Frame의 상단에 붙일 패널
	JPanel panelList [] = new JPanel[2]; // 0번 panel이 상단 1번 panel이 하단
	JButton receiptbutton = new JButton("영수증 출력");
	public OrderListFrame(int total, String temp) {
		for(int i=0; i<labelList.length-1; i++)
			labelList[i] = new JLabel(labelListName[i], SwingConstants.CENTER);		//레이블에 메뉴, 수량 가격을 적은 후 가운데 정렬
		labelList[3] = new JLabel(labelListName[3] + total, SwingConstants.CENTER);  // frame의 하단에 붙일, 합계가 들어 있는 레이블
		
		for(int i=0; i<panelList.length; i++)
			panelList[i] = new JPanel();
		panelList[0].setLayout(new GridLayout(1, 3));
//		panel.setLayout(new GridLayout(1, 3));	// 상단에 붙일 패널의 layout을 1행 3열로..
		for(int i=0; i<3; i++)
			panelList[0].add(labelList[i]);		// 상단에 붙일 패널에 메뉴 수량 가격이 적혀있는 레이블 3개 부착
//			panel.add(labelList[i]);			// 상단에 붙일 패널에 메뉴 수량 가격이 적혀있는 레이블 3개 부착
		add(panelList[0], BorderLayout.NORTH);  // 메뉴 수량 가격 레이블이 들어있는 패널을 frame의 상단에 부착
//		add(panel, BorderLayout.NORTH); 		// 메뉴 수량 가격 레이블이 들어있는 패널을 frame의 상단에 부착
		panelList[1].setLayout(new GridLayout(2, 1));
		panelList[1].add(labelList[3]);
		panelList[1].add(receiptbutton);
		add(panelList[1], BorderLayout.SOUTH);
//		add(labelList[3], BorderLayout.SOUTH);	// 합계가 들어있는 레이블을 frame의 하단에 부착
		add(new JTextArea(temp));				// 주문 내역이 담길 jtextarea에 생성자의 매개변수로 받아온 string값으로 초기화
		setVisible(true);
		setSize(400, 400);
		setLocation(800, 400);
		setTitle("주문내역");
		
		receiptbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String fileName = "receipt_" +"1";
				try {
					FileWriter fw = new FileWriter(fileName);
					fw.write(temp);
					
					fw.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				dispose(); // 주문내역 프레임을 종료
			}
		});
		
	}
	
}
