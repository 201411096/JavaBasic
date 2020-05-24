package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import t_teamproject.teamproject_02.sendmail.SendMail;

public class SendMailFrame extends JFrame{	//	메일 전송 확인 화면
	String productNameList [];
	int productCountList [];
	int productPriceList [];
	int totalPrice=0;
	
	JTextField idTextField; // from 보내는 사람 주소
	JPasswordField passwordField; // 보내는 사람 메일의 비밀번호
	JTextField toIdTextField; // to 받는 사람 메일 주소
	JButton sendButton;
	
	SendMail sendMail = null;
	
	//OrderListFrame으로부터 제품이름배열, 제품개수배열, 제품가격배열, 총가격을 받아옴
	public SendMailFrame(String[] productNameList, int[] productCountList, int[] productPriceList, int totalPrice) {

		this.productNameList = productNameList;
		this.productCountList = productCountList;
		this.productPriceList = productPriceList;
		this.totalPrice = totalPrice;
		sendMail = new SendMail();
		display();
		eventProc();
	}
	public void display() {
		setLayout(null);
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(3,2));
		wrapperPanel.add(new JLabel("메일id "));
		idTextField = new JTextField();
		wrapperPanel.add(idTextField);

		wrapperPanel.add(new JLabel("메일pw "));
		passwordField = new JPasswordField();
		wrapperPanel.add(passwordField);
		
		wrapperPanel.add(new JLabel("받는 사람 메일 주소"));
		toIdTextField = new JTextField();
		wrapperPanel.add(toIdTextField);
		
		sendButton = new JButton("메일 전송");
		
		wrapperPanel.setBounds(75, 50, 300, 100);
		sendButton.setBounds(125, 200, 200, 50);
		
		add(wrapperPanel);
		add(sendButton);
		
		setTitle("메일 전송 화면");
		setBounds(750, 400, 450, 300);
		setVisible(true);
	}
	public void eventProc() {
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendMail.sendMail(idTextField.getText(), String.valueOf(passwordField.getPassword()), toIdTextField.getText(), makeMainContent());
				JOptionPane.showMessageDialog(null, "메일이 전송됬습니다.");
				dispose();
			}
		});
	}
	/* 함수이름 : makeMainContent
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : OrderListFrame으로부터 받은 값들로 메일의 내용에 들어갈 String을 구성해주는 함수
	 */
	public String makeMainContent() {
		StringBuffer sb = new StringBuffer();
		sb.append("------------------------------\n");
		sb.append("Subway 영수증 메일 안내\n");
		sb.append("------------------------------\n");
		for(int i=0; i<productNameList.length; i++)
		{
			sb.append(i + "번쨰 상품\n 이름 : " + productNameList[i] +"\n");
			sb.append("가격 : "  + productCountList[i] +"\n");
			sb.append("제품 가격 총합 : " + productPriceList[i] +"\n");
			sb.append("------------------------------\n");
		}
		sb.append("------------------------------\n");
		sb.append("구매내역 가격 총합 : " + totalPrice +"\n");
		sb.append("------------------------------");
		
		return sb.toString();
	}
}
