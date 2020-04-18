package t_teamproject.teamproject_02.temp.view.frame;

import java.awt.Color;

import javax.swing.JFrame;

import t_teamproject.teamproject_02.temp.dao.ProductManagementDao;

public class ProductPurchaseFrame extends JFrame{
	ProductManagementDao productManagementmodel;
	
	public ProductPurchaseFrame(){
		display();
		eventProc();
		connectDB();
	}
	public void display() {
		setTitle("제품 주문 창");
		setBounds(750, 200, 400, 500);
		setVisible(true);
		setBackground(Color.white);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void eventProc() {
		
	}
	public void connectDB() {
		
	}
}
