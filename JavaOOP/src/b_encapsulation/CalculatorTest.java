package b_encapsulation;

import java.util.Scanner;

public class CalculatorTest {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CalculatorExpr c = new CalculatorExpr();
		String s="y";
		
		while(s.equals("y")|| s.equals("Y"))
		{
			System.out.println("숫자 2개 입력 : ");
			c.setNum1(scanner.nextInt());
			c.setNum2(scanner.nextInt());	
			scanner.nextLine();
			System.out.println("덧셈 : " + c.getAddition());
			System.out.println("뺄셈 : " + c.getSubtraction());
			System.out.println("곱셈 : " + c.getMultiplication());
			System.out.println("나눗셈 : " + c.getDivision());
			System.out.println("continue?");
			s=scanner.nextLine();
		}
	}
}
