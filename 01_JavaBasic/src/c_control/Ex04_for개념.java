package c_control;

public class Ex04_for개념 {

	public static void main(String[] args) {
		//0. 인사말 5번 출력하기
//		for(int i=0; i<5; i++) {
//			System.out.println("안녕하세요");
//		}
//		int sum=0;
//		for(int i=1; i<=100; i++)
//		{
//			sum+=i;
//		}
//		System.out.println("합=" + sum);
//		
//		int even=0, odd=0;
//		for(int i=1; i<=100; i++)
//		{
//			if(i%2==0)
//				even+=i;
//			else
//				odd+=i;
//		}
//		System.out.println("짝수의 합은 " + even + " 홀수의 합은 " + odd);
		//3. A~Z 출력
		for(char ch='a' ; ch<='z' ; ch++ ) {
			System.out.print(ch + " ");
		}
		System.out.println();
		//4. A~Z 출력 두개씩 건너뛰기
		for(char ch='a' ; ch<='z' ; ch+=2 ) {
			System.out.print(ch + " ");
		}	
		System.out.println();
		//5. Z~A 출력
		for(char ch='Z'; ch>='A'; ch--) {
			System.out.print(ch + " ");
		}
	}

}
