package e_method;

public class Ex03_인자와반환3 {
	public static void main(String[] args) {
		
		int arr[] = add();
		int sum=0;
		for(int i=0; i<arr.length; i++)
			sum+=arr[i];
		System.out.println("합: " + sum);
	}
	static int [] add() {
		//입력데이터
		int a=10, b=20;
		int arr [] = {a, b};
		int arr2[][] ;
		return arr;
	}
}
