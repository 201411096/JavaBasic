package a_datatype;

import java.util.Scanner;

public class Exam {

	public static void main(String[] args) {
		int age;
		double height;
		String name;
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println("나이 ->");
//		age = scanner.nextInt();
//		System.out.println("키 ->");
//		height = scanner.nextDouble();
		System.out.println("이름->");
		
		name = scanner.nextLine();
		System.out.println(name);
//		System.out.println("나이 : " + age + " 키 : " + height + "이름 : " + name);
		
	}

}
