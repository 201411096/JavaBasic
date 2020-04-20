package t_teamproject.teamproject_02.view.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

import t_teamproject.teamproject_02.dao.ProductDao;
import t_teamproject.teamproject_02.dao.ProductDaoImpl;
import t_teamproject.teamproject_02.dao.ProductManagementDao;
import t_teamproject.teamproject_02.dao.ProductManagementDaoImpl;
import t_teamproject.teamproject_02.view.button.MenuButton;
import t_teamproject.teamproject_02.view.frame.CalculationFrame;
import t_teamproject.teamproject_02.vo.Product;

public class ProductMenuListPanel extends JPanel{
	CalculationFrame calculationFrame;
	ProductDao productDaoModel;
	ProductManagementDao productManageMentModel;
	
	String menuGroupName;	//메인메뉴인지 사이드메뉴인지 음료수인지..
	MenuButton menuButtonList [];
	ArrayList<Product> productList;
	public ProductMenuListPanel(CalculationFrame calculationFrame, String menuGroupName){
		this.calculationFrame=calculationFrame;
		this.menuGroupName=menuGroupName;
		connectDB();
		displayComponentLoad();
		display();
		eventProc();
		
	}
	public void displayComponentLoad() {
		productList = productDaoModel.searchProductByGroupNameAsc(this.menuGroupName);
	}
	public void display() {
		setLayout(new GridLayout(0, 3));
		menuButtonList = new MenuButton[productList.size()];
		for(int i=0; i<menuButtonList.length; i++)
		{
			menuButtonList[i] = new MenuButton(productList.get(i));
		}
		for(int i=0; i<menuButtonList.length; i++)
		{
			add(menuButtonList[i]);
		}
	}
	public void eventProc() {
		EventHandler eventHandler = new EventHandler();
		for(int i=0; i<menuButtonList.length; i++)
		{
			menuButtonList[i].addActionListener(eventHandler);
		}
		
	}
	class EventHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	}
	public void connectDB() {
		try {
			productDaoModel = new ProductDaoImpl();
			productManageMentModel = new ProductManagementDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public MenuButton[] getMenuButtonList() { //메뉴버튼 리스트 반환
		return menuButtonList;
	}
	public void setMenuButtonList(MenuButton[] menuButtonList) {
		this.menuButtonList = menuButtonList;
	}
	public ArrayList<Product> getProductList() { //메뉴 리스트 반환(사실상 메뉴버튼 리스트에 정보가 다 포함되어 있음)
		return productList;
	}
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
}
