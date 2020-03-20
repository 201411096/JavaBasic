package d_array;

import java.util.Calendar;

public class Ex03_요일 {
	
	public static void main(String[] args) {
//		char [] day2 = {'일', '월', '화', '수', '목', '금', '토'};
		String [] day = {"일", "월", "화", "수", "목", "금", "토"}; 
		Calendar c = Calendar.getInstance();
		int y = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		int d = c.get(Calendar.DATE);
		System.out.print(y + "년" + (m+1) + "월" + d + "일 ");
		int h = c.get(Calendar.HOUR_OF_DAY);
		int mi = c.get(Calendar.MINUTE);
		int s = c.get(Calendar.SECOND);
		System.out.print(h + ":" + mi + ":" + s + " ");
		System.out.println(day[c.get(Calendar.DAY_OF_WEEK)-1]+ "요일");
	}
}
