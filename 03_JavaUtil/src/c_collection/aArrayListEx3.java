package c_collection;

import java.util.ArrayList;
import java.util.Scanner;

public class aArrayListEx3 {
	
	static ArrayList method() {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Student> list = new ArrayList<Student>();
		
		for(int i=0; i<3; i++)
			list.add(new Student(scanner.next(), scanner.nextInt()));
		
		return list;
	}
	
	public static void main(String[] args) {
		ArrayList<Student> list = method();
		for(Student data : list)
			System.out.println(data); // toString을 하지 않아도 자동으로 실행
		//여기서 출력
	
	}

}

//----------------------------------------------------------
class Student{
	String name;
	int age;
	Student(String name, int age){
		this.name = name;
		this.age = age;
	}
	public String toString() {
		return name +"학생은 " +  age + "세 입니다.";
	}
}
