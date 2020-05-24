package k_interface;

import java.util.Scanner;

public class PizzaStore implements DeliveryStore{
	private String orderList [];
	private Food menuList[] = {(new Food("Cheese", 10000)), (new Food("PineApple", 20000)), (new Food("Meatball", 30000)), (new Food("Apple", 40000)), (new Food("FoodE", 50000))};
//	private Food menuList[] = new Food[5];
	@Override
	public void setOrderList(String[] str) {
		orderList = new String [str.length];
		for(int i=0; i<orderList.length; i++)
		{
			orderList[i]=str[i];
		}
	}

	@Override
	public int getTotalPrice() {
		int sum=0;
		for(int i=0; i<orderList.length; i++)
		{
			for(int j=0; j<menuList.length; j++)
			{
				if(orderList[i].equals(menuList[j].name))
					sum+=menuList[j].price;
			}
		}
		return sum;
	}
	
//	public void setMenuList() {
//		Scanner scanner = new Scanner(System.in);
//		int cnt=0;
//		for(int i=0; i<menuList.length; i++)
//		{
//			if(menuList[i]!=null)
//				cnt++;
//		}
//		System.out.println((cnt+1) + "번째 menu의 이름과 가격 입력 :");
//		menuList[cnt]= new Food(scanner.next(), scanner.nextInt());
//	}
//	public void printMenuList() {
//		for(int i=0; i<menuList.length; i++)
//		{
//			if(menuList[i]==null)
//				continue;
//			System.out.println(menuList[i].name + menuList[i].price);
//		}		
//	}
}
