package t_teamproject.teamproject_02.temp.view.frame;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import t_teamproject.teamproject_02.temp.dao.ProductDao;
import t_teamproject.teamproject_02.temp.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.temp.dao.ProductManagementDao;
import t_teamproject.teamproject_02.temp.dao.ProductManagementDaoImpl;

public class ProductPurchaseFrame extends JFrame{
	ProductManagementDao productManagementmodel;
	ProductDao productDaoModel;
	String comboBoxMenuArray [];
	JComboBox comboBoxMenu;
	JTextField jtextFieldCount;
	JButton confirmButton;
	public ProductPurchaseFrame(){
		connectDB();
		ArrayList<String> temp = productDaoModel.selectProductNameOrderByName();
		comboBoxMenuArray = temp.toArray(new String [temp.size()]);
		display();
		eventProc();
	}
	public void display() {
		setLayout(null);
		
		comboBoxMenu = new JComboBox(comboBoxMenuArray);		
		jtextFieldCount = new JTextField();		
		confirmButton = new JButton("제품 주문");

		comboBoxMenu.setBounds(100, 100, 200, 50);		
		jtextFieldCount.setBounds(100, 200, 200, 50);		
		confirmButton.setBounds(100, 300, 200, 50);
		
		add(comboBoxMenu);
		add(jtextFieldCount);
		add(confirmButton);
		setTitle("제품 주문 창");
		setBounds(750, 200, 400, 500);
		setVisible(true);
		setBackground(Color.white);
	}
	public void eventProc() {
		confirmButton.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				purchaseProduct();
			}
		});
	}
	public void connectDB() {
		try {
			productDaoModel = new ProductDaoImpl();
			productManagementmodel = new ProductManagementDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void purchaseProduct() {
		int cnt = Integer.parseInt(jtextFieldCount.getText());
		String name = comboBoxMenu.getSelectedItem().toString();
		
		try {
			int result = productManagementmodel.purchaseProductByName(name, cnt);
			
			if(result == 0 ) {
				JOptionPane.showMessageDialog(null, "제품 주문 완료");
			}else {
				JOptionPane.showMessageDialog(null, "에러 발생");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
