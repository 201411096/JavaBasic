package k_interface;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Test {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PizzaStore ps = new PizzaStore();
		System.out.print("메뉴 입력 : ");
		StringTokenizer st = new StringTokenizer(scanner.nextLine());
		String orderList [] = new String[st.countTokens()]; 

		for(int i=0; i<orderList.length; i++)
		{
			orderList[i]=st.nextToken();
		}
		ps.setOrderList(orderList);
		System.out.println("총합 : " + ps.getTotalPrice());
			
		

	}

}
