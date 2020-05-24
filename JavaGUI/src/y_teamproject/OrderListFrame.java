package y_teamproject;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class OrderListFrame extends JFrame{ // 주문 내역을 보여주는 화면
	private JLabel labelList [] = new JLabel[4];
	private String labelListName [] = {"메뉴", "수량", "가격", "합계: "};
	private JPanel panelList [] = new JPanel[2]; // 0번 panel이 상단 1번 panel이 하단
	private JButton receiptbutton = new JButton("영수증 출력");
	public OrderListFrame(int total, String temp) {
		for(int i=0; i<labelList.length-1; i++)
			labelList[i] = new JLabel(labelListName[i], SwingConstants.CENTER);		//레이블에 메뉴, 수량 가격을 적은 후 가운데 정렬
		labelList[3] = new JLabel(labelListName[3] + total, SwingConstants.CENTER);  // frame의 하단에 붙일, 합계가 들어 있는 레이블
		
		for(int i=0; i<panelList.length; i++)
			panelList[i] = new JPanel();
		panelList[0].setLayout(new GridLayout(1, 3));
		for(int i=0; i<3; i++)
			panelList[0].add(labelList[i]);		// 상단에 붙일 패널에 메뉴 수량 가격이 적혀있는 레이블 3개 부착
		add(panelList[0], BorderLayout.NORTH);  // 메뉴 수량 가격 레이블이 들어있는 패널을 frame의 상단에 부착
		panelList[1].setLayout(new GridLayout(2, 1));
		panelList[1].add(labelList[3]);
		panelList[1].add(receiptbutton);
		add(panelList[1], BorderLayout.SOUTH);
		add(new JTextArea(temp));				// 주문 내역이 담길 jtextarea에 생성자의 매개변수로 받아온 string값으로 초기화
		setVisible(true);
		setSize(400, 400);
		setLocation(800, 400);
		setTitle("주문내역");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		receiptbutton.addActionListener(new ActionListener() { // 영수증 파일을 생성하고 주문내역 창을 종료함
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss"); 
				Date time = new Date();
				String timeStr = format.format(time);
				String fileName = "receipt_" +timeStr.substring(2, 14); // 파일의 이름을 현재 날짜와 시간을 포함해서 출력
				try {
					FileWriter fw = new FileWriter(fileName);
					StringTokenizer st = new StringTokenizer(temp);
					StringBuilder sb = new StringBuilder();
					sb.append("----------------------\n");
					sb.append(fileName+"\n");
					sb.append("----------------------\n");
					sb.append("메뉴/수량/가격\n");
					sb.append("----------------------\n");
					int cnt =0;
					while(st.hasMoreTokens()) //temp로 넘겨받은 내용의 공백들을 없애고 "/"로 구분
					{
						sb.append(st.nextToken());
						sb.append("/"); // 각 토큰을 "/"로 구분
						cnt++;
						if(cnt==3) //토큰 3개마다 개행
						{
							sb.append("\n");
							cnt=0;
						}	
					}
					sb.append("----------------------\n");
					fw.write(sb.toString());
					fw.write("합계 : " + total);
					fw.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				dispose(); // 주문내역 프레임을 종료
			}
		});
	}
}
