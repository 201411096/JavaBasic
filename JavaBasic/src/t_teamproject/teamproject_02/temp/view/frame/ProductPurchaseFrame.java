package t_teamproject.teamproject_02.temp.view.frame;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import t_teamproject.teamproject_02.temp.dao.ProductDao;
import t_teamproject.teamproject_02.temp.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.temp.dao.ProductManagementDao;
import t_teamproject.teamproject_02.temp.dao.ProductManagementDaoImpl;

public class ProductPurchaseFrame extends JFrame{
	ProductManagementDao productManagementmodel;
	ProductDao productDaoModel;
	String comboBoxMenuArray [];
	JComboBox comboBoxMenu;
	public ProductPurchaseFrame(){
		connectDB();
		ArrayList<String> temp = productDaoModel.selectProductNameOrderByName();
		comboBoxMenuArray = temp.toArray(new String [temp.size()]);
		display();
		eventProc();
	}
	public void display() {
		comboBoxMenu = new JComboBox(comboBoxMenuArray);
		add(comboBoxMenu);
		setTitle("제품 주문 창");
		setBounds(750, 200, 400, 500);
		setVisible(true);
		setBackground(Color.white);
	}
	public void eventProc() {
		
	}
	public void connectDB() {
		try {
			productDaoModel = new ProductDaoImpl();
			productManagementmodel = new ProductManagementDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
