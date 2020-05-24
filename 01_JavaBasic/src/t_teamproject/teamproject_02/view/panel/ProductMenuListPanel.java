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
	public ProductMenuListPanel(CalculationFrame calculationFrame, String menuGroupName){ //상위 컴포넌트(CalculationFrame)의 요소를 쓰기 위해서 인자로 받아와서 저장
		this.calculationFrame=calculationFrame;
		this.menuGroupName=menuGroupName;
		connectDB();
		displayComponentLoad();
		display();
		
	}
	/* 함수이름 : displayComponentLoad
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 메뉴그룹별로 메뉴들을 받아와서 productlist에 저장
	 * 			ㄴ 패널 생성시 메인메뉴 패널, 사이드메뉴패널, 음료수메뉴패널등으로 지정을해서 생성을 하게 되는데
	 * 			ㄴ 이 그룹에 해당하는 메뉴들을 붙이기 위한 함수 (ex) 메인메뉴 패널에 메인메뉴들을 버튼으로 붙임)
	 * 함수실행시기 : 화면구성 직전에 호출
	 */
	public void displayComponentLoad() {	
		productList = productDaoModel.searchProductByGroupNameAsc(this.menuGroupName);
	}
	/* 함수이름 : display
	 * 인자값 : 없음
	 * 반환값 : 없음
	 * 함수설명 : 메뉴그룹별로 불러온 메뉴의 갯수만큼 메뉴버튼 배열의 크기를 동적으로 할당해서 생성
	 * 			ㄴ 각각의 메뉴버튼에 해당하는 메뉴들의 정보를 불러와서 메뉴버튼을 생성(메뉴버튼 생성시 해당 메뉴의 정보도 같이 들어감)
	 * 			ㄴ 패널에 메뉴버튼을 붙임
	 */
	public void display() {
		setLayout(new GridLayout(0, 3));	// 3열이 넘어가면 자동으로 다음행이 생성됨 (아이템이 1~3개일경우 1행 3열로 배치, 아이템이 4~6개일 경우 2행 3열로 배치....)
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
