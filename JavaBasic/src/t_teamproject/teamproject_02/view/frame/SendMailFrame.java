package t_teamproject.teamproject_02.view.frame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class SendMailFrame extends JFrame{
	String productNameList [];
	int productCountList [];
	int productPriceList [];
	int totalPrice=0;
	
	JTextField idTextField;
	JPasswordField passwordField;
	JButton sendButton;
	
	public SendMailFrame(String[] productNameList, int[] productCountList, int[] productPriceList, int totalPrice) {

		this.productNameList = productNameList;
		this.productCountList = productCountList;
		this.productPriceList = productPriceList;
		this.totalPrice = totalPrice;
		
		display();
		eventProc();
	}
	public void display() {
		setLayout(null);
		JPanel wrapperPanel = new JPanel();
		wrapperPanel.setLayout(new GridLayout(2,2));
		wrapperPanel.add(new JLabel("메일id "));
		idTextField = new JTextField();
		wrapperPanel.add(idTextField);

		wrapperPanel.add(new JLabel("메일pw "));
		passwordField = new JPasswordField();
		wrapperPanel.add(passwordField);
		
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
				
			}
		});
	}
	
	

}
