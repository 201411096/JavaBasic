package c_control;

public class Ex02_if_학번 {

	public static void main(String[] args) {
		String stdNum="2018112111";
		int year=Integer.parseInt(stdNum.substring(0,4));
		String danNum =stdNum.substring(4, 5);
		String dan ="";
		String majorNum = stdNum.substring(5,7);
		String major="";
		if(danNum.equals("1"))
		{
			dan="공대";
//			if(majorNum.equals("11"))
//				major="컴퓨터학과";
//			else if(majorNum.equals("12"))
//				major="소프트웨어학과";
//			else if(majorNum.equals("13"))
//				major="모바일학과";
//			else if(majorNum.equals("22"))
//				major="자바학과";
//			else if(majorNum.equals("33"))
//				major="서버학과";
			switch(majorNum) {
			case "11": major="컴퓨터학과"; 
			break;
			case "12": major="소프트웨어학과"; 
			break;
			case "13": major="모바일학과"; 
			break;
			case "22": major="자바학과"; 
			break;
			case "33": major="서버학과"; 
			break;
			
			}
				
		}else if(danNum.equals("2"))
		{
			dan="사회대";
//			if(majorNum.equals("11"))
//				major="사회학과";
//			else if(majorNum.equals("12"))
//				major="경영학과";
//			else if(majorNum.equals("13"))
//				major="경제학과";
			switch(majorNum) {
			case "11" : major="사회학과"; 
			break;
			case "12" : major="경영학과"; 
			break;
			case "13" : major="경제학과"; 
			break;
			}
		}
		
		System.out.println(stdNum + "는 "+ year +"년도에 입학한 " + dan + " " + major + " 학생입니다." );
		
		

	}

}
