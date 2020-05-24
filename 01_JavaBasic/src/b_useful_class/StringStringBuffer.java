package b_useful_class;

public class StringStringBuffer {
	public static void main(String[] args) {
		//0.기본형
		int a = 10;
		a+=5;
		System.out.println(a);
		
		//1.String 참조형
		String s = new String("안녕"); // s는 stack 영역에, "안녕"은 heap 영역에
//		String s = "안녕";
		s += "하세요"; // heap 영역에 "안녕하세요"를 새로 생성, 기존 것은 끊김(그대로 남아있지만..?)
		System.out.println(s);
		
		//2.StringBuffer 
		StringBuffer sb = new StringBuffer("행복한");
		//StringBuffer sb2 = "행복한"; // new 없이 사용가능한 클래스는 string만 가능
//		sb=sb+"목요일"; // 기본형처럼 연산이 가능한 클래스도 string만 가능
		sb.append("목요일");
		System.out.println(sb);
	}
}
