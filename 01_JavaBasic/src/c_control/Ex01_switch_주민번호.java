package c_control;

public class Ex01_switch_주민번호 {

	public static void main(String[] args) {
		String id = "951230-1534567";
		char pos = id.charAt(8); // '2'
		String home = "";
		
		switch(pos) {
		
		case '0' : home="서울"; break;
		case '1' : home="인천/부산"; break;
		case '2' : home="경기도"; break;
		default : home="한국인"; break;
		}
		System.out.println("출신지-" + home);
		

	}

}
