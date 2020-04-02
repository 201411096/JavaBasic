package y_teamproject;

import java.util.ArrayList;

public class Menu {
	String name;
	int cost;
	int count; 		// 메뉴 간 매출 정렬에 사용
	int percent; 	// 매출의 몇%인지..
	String menuNameList [] = {"엽기떡볶이", "엽기닭볶음탕", "뼈없는 닭발", "국물닭발", "주먹김밥", "계란찜", "공기밥", "음료", "떡추가", "오뎅추가", "치즈추가", "햄추가", "A set", "B set", "C set", "Family set"};
	int menuCostList [] = {14000, 24000, 15000, 15000, 2000, 2000, 1000, 1000, 1000, 1000, 3000, 1000, 17000, 19000, 21000, 28000};
	public Menu(){
	}
	public Menu(String name, int cost){
		this.name=name;
		this.cost=cost;
	}
	public Menu(String name, int cost, int count) {
		this.name=name;
		this.cost=cost;
		this.count=count;
	}
	public String[] makeMenuString(int [] cnt) {
		String s[] = new String[16];
		int stringCnt=0;
		for(int i=0; i<cnt.length; i++) {
			if(cnt[i]!=0)
				s[stringCnt++]= menuNameList[i] + "\t\t" + cnt[i] + "\t       " + menuCostList[i]*cnt[i];
		}
		return s;
	}
	public Menu[] getRankArray(int [] cnt){ // 매출 갯수가 순서대로 담긴 배열을 받아서 매출순이 큰 순서대로 메뉴 배열을 보냄
		Menu menuArray [] = new Menu[cnt.length];
		for(int i=0; i<cnt.length; i++)
			menuArray[i] = new Menu(menuNameList[i], menuCostList[i], cnt[i]);
		menuBubbleSort(menuArray);
		return menuArray;
	}
	public void menuBubbleSort(Menu [] menu) { // 내림차순 정렬
		for(int i=0; i<menu.length-1; i++)
		{
			for(int j=0; j<menu.length-i-1; j++)
			{
				if(menu[j+1].count>menu[j].count)
				{
					Menu temp = menu[j+1];
					menu[j+1] = menu[j];
					menu[j] = temp;
				}
			}
		}
	}
	public int countSoldProduct(Menu [] menu) { // 하나라도 팔린 메뉴의 개수를 셈 
		int cnt=0;
		for(int i=0; i<menu.length; i++)
		{
			if(menu[i].count!=0)
				cnt++;
		}
		return cnt;
	}
	public void getPercentage(Menu [] menu) { // 메뉴 배열의 요소마다 매출의 %를 계산
		int total =0;
		for(int i=0; i<menu.length; i++)
		{
			total+=menu[i].count;
		}
		for(int i=0; i<menu.length; i++) {
			if(menu[i].count!=0)
				menu[i].percent=menu[i].count*100/total;
		}
	}
}
