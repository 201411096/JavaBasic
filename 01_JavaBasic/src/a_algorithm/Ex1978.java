package a_algorithm;

import java.util.Scanner;



public class Ex1978 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int number = scanner.nextInt();
		int number_array [] = new int[number];
		int answer=0;
		
		for(int i=0; i<number_array.length; i++)
		{
			number_array[i]=scanner.nextInt();
		}
		
		for(int i=0; i<number_array.length; i++)
		{
			int temp = number_array[i]-1; //자신을 제외한 작은 수를 나누기 위해 -1
			if(temp==0) continue; // number_array[i]==1인경우 다음 값으로
			boolean isSosu = true;
			while(temp!=1) { // 1을 나누기 전에 반복문 종료
				if(number_array[i]%temp==0) //자신을 제외한 작은 수 중에 나누어 떨어지면 소수가 아님
				{
					isSosu=false;
					break;
				}
				temp--;
			}
			if(isSosu==true)
				answer++;
		}
		System.out.println(answer);
	}
}

