/*
 * Wrapper Class : 자료형 관련 클래스
 * Byte / Short
 * Character
 * Integer / Long
 * Float / Double
 * 
 * 형변환 관련하여 ....
 * 
 *  	String -> int 형
 *  	Integer.parseInt();
 *  
 *  	int 형 -> String
 *  	String.valueOf(10);
 */
package c_collection;
import java.util.ArrayList;

// ArrayList 동적 배열

class aArrayListEx
{
	public static void main(String[] args) 
	{
		ArrayList data = dataSet();
		
		for(int i=0; i<data.size(); i++)
			System.out.println(data.get(i));
		// dataSet() 안의 변수 값들을 여기서 출력한다면??
		
		
	}

	static ArrayList dataSet()
	{
		String	name = "김태희";
		int		age = 31;
		double	height = 162.3;
		
		ArrayList data = new ArrayList(2);
		data.add(name);
		data.add(age);
		data.add(height);
		
		return data;
	}
}
