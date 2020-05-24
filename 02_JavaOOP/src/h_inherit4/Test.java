package h_inherit4;

import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Language l=null;
		String y = "Y";
		while(y.equals("Y") || y.equals("y"))
		{
			Scanner scanner = new Scanner(System.in);
			System.out.println("언어를 선택하세요 (1.한국어 2.영어 3.일본어)");
			int lan = scanner.nextInt();
			switch(lan){
				case 1: l = new Korean(); break; 
				case 2: l = new English(); break;
				case 3: l = new Japanese(); break;
			}
			System.out.println("메세지를 선택하세요 (1.인사말 2.자기소개 3.하고픈말)");
			int sel = scanner.nextInt();
			switch(sel) {
				case 1: l.printHello();  break;
				case 2: l.printIntroduce();  break;
				case 3: l.printWhatIWantToSay();  break;
			}
			System.out.println("다시하시겠습니까? (Y/N)");
			y=scanner.next();
		}
		System.out.println("프로그램 종료");
		
	}
}
