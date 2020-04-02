package y_teamproject;

import java.util.ArrayList;

public class Menu {
	String name;
	int cost;
	String menuNameList [] = {"엽기떡볶이", "엽기닭볶음탕", "뼈없는 닭발", "국물닭발", "주먹김밥", "계란찜", "공기밥", "음료", "떡추가", "오뎅추가", "치즈추가", "햄추가", "A set", "B set", "C set", "Family set"};
	int menuCostList [] = {14000, 24000, 15000, 15000, 2000, 2000, 1000, 1000, 1000, 1000, 3000, 1000, 17000, 19000, 21000, 28000};
	public Menu(){
	}
	public Menu(String name, int cost){
		this.name=name;
		this.cost=cost;
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
	public ArrayList<Integer> getRankArray(int [] cnt){
		ArrayList<Integer> rankArrayList = new ArrayList<Integer>(); // 많은 순서대로 인덱스 저장
		for(int i=0; i<cnt.length-1; i++)
		{
			int max_idx=i;
			for(int j=0; j<cnt.length-1-i; j++)
			{
				
			}
		}
		return rankArrayList;
	}
}
