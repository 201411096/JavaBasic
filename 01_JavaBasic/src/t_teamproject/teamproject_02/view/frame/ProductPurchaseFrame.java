package t_teamproject.teamproject_02.view.frame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;

public class ProductPurchaseFrame extends JFrame{	//제품입고창(도매점으로부터 제품을 구매해올 떄 사용하는 프레임)
	ProductManagementDao productManagementmodel;
	ProductDao productDaoModel;
	String comboBoxMenuArray [];
	JComboBox comboBoxMenu;
	JTextField jtextFieldCount;
	JButton confirmButton;
	public ProductPurchaseFrame(){
		connectDB();
		ArrayList<String> temp = productDaoModel.selectProductNameOrderByName(); //등록되어 있는 모든 제품의 이름을 가져옴
		comboBoxMenuArray = temp.toArray(new String [temp.size()]);				 //가져온 모든 제품의 이름이 담긴 combobox생성
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
	/* 함수이름 : purchaseProduct
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 제품이름(unique)에 해당하는 제품을 도매점으로부터 구매(입고)
	 */
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
