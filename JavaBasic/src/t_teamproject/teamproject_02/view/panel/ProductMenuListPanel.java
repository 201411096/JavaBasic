package t_teamproject.teamproject_02.view.panel;

import java.awt.GridLayout;
import java.util.ArrayList;

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
		
	}
	public void connectDB() {
		try {
			productDaoModel = new ProductDaoImpl();
			productManageMentModel = new ProductManagementDaoImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
