package c_control;

public class Ex05_while구글입사문제 {
	public static void main(String[] args) {
		int cnt=0;
		for(int i=1; i<=10000; i++)
		{
			int temp=i;
			while(temp!=0) 
			{
				if(temp%10 == 8) cnt++;
				temp/=10;
			}
		}
		System.out.println(cnt);
	}
}
