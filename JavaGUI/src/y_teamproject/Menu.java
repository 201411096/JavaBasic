package y_teamproject;

import java.util.ArrayList;

public class Menu {
	String name;
	int cost;
	int count;
//	ArrayList<Menu> menuList = new ArrayList<Menu>();
	String menuNameList [] = {"main1", "main2", "main3", "main4", "side1", "side2", "side3", "side4", "topping1", "topping2", "topping3", "topping4", "setA", "setB", "setC", "setD"};
	int menuCostList [] = {14000, 24000, 15000, 15000, 2000, 2000, 1000, 1000, 1000, 1000, 3000, 1000, 17000, 19000, 21000, 28000};
	public Menu(){
	}
	public Menu(String name, int cost){
		this.name=name;
		this.cost=cost;
	}
	public Menu(String name, int cost, int count){
		this.name=name;
		this.cost=cost;
		this.count=count;
	}
	public String[] makeMenuString(int [] cnt) {
		String s[] = new String[16];
		int stringCnt=0;
		for(int i=0; i<cnt.length; i++) {
			if(cnt[i]!=0)
				s[stringCnt++]= menuNameList[i] + "\t" + cnt[i] + "\t" + menuCostList[i]*cnt[i];
		}
		
		return s;
	}
	
	
//	public void makeMenuList() {
//		for(int i=0; i<menuNameList.length; i++)
//			menuList.add(new Menu(menuNameList[i], menuCostList[i]));
//	}
}
