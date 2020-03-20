package temp;

public class temp {

	public static void main(String[] args) {
		int n=946734444;
		int temp=n;
		int cnt=0;
		while(temp!=0)
		{
			temp/=2;
			cnt++;
		}
		System.out.println(cnt);
	}
}
