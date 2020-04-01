package y_teamproject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame implements ActionListener{
	MenuPanel menuPanel[] = new MenuPanel[4];
	JTabbedPane tabbedPane = new JTabbedPane();
	String tabbedPaneName [] = {"메인메뉴", "사이드/음료", "토핑", "세트메뉴"};
	JButton buttonArray [] = new JButton[16];
	ImageIcon imageIconArray[] = new ImageIcon[16];
	OrderPanel orderPanel;
	Menu menu = new Menu();
	OrderList orderList = new OrderList();

	public MainFrame() {
		for(int i=0; i<imageIconArray.length; i++)
		{
			imageIconArray[i] = new ImageIcon("src/y_teamproject/imgs/" + i +".png");
			buttonArray[i] = new JButton(imageIconArray[i]);
			buttonArray[i].setBackground(Color.white);
		}
		for(int i=0; i<menuPanel.length; i++)
			menuPanel[i] = new MenuPanel(buttonArray[i*4], buttonArray[i*4+1], buttonArray[i*4+2], buttonArray[i*4+3]);
		orderPanel = new OrderPanel();
	}
	public void display() {
		setLayout(new BorderLayout());
		for(int i=0; i<menuPanel.length; i++)
			tabbedPane.add(menuPanel[i], tabbedPaneName[i]);
		add(tabbedPane);
		add(orderPanel, BorderLayout.EAST);
		setTitle("엽기떡볶이");
		setSize(1920, 1080);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventConn() {
		for(int i=0; i<buttonArray.length; i++)
			buttonArray[i].addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton jb = (JButton)e.getSource();
		String s = "";
		for(int i=0; i<buttonArray.length; i++)
		{
			if(jb == buttonArray[i])
			{
				System.out.println(menu.menuNameList[i] + menu.menuCostList[i]);
			}
				
		}
	}

}
