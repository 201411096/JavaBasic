package c_control;

import java.util.Calendar;

public class Ex01_if_주민번호 {

	public static void main(String[] args) {
		String id = "201230-3234567";
		char sung = id.charAt(7);
		//sung 변수에 값이 1이거나 3이면 남자 출력 그렇지 않고 값이 2이거나 4면 여자 출력
		if(sung == '1' || sung=='3' || sung=='9')
		{
			System.out.println("남자");
		}else if(sung == '2'|| sung=='4'||sung=='0')
		{
			System.out.println("여자");
		}
		String temp = id.substring(0, 2); 
		int temp2 = Integer.parseInt(temp);
		
		//올해 연도 구하기
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int age=0;
		if(sung=='1' || sung=='2')
		{
			age= year- (1900+temp2)+1;
		} else if(sung=='3' || sung=='4')
		{
			age= year- (2000+temp2)+1;
		} else if(sung=='9' || sung=='0')
		{
			age= year- (1800+temp2)+1;
		}
			
			 
		
		System.out.println("나이: "+ age);
	}
}
