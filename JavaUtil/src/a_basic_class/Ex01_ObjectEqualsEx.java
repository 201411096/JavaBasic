package a_basic_class;

public class Ex01_ObjectEqualsEx
{
	public static void main(String[] args)
	{
		 StudentA  a = new StudentA("012345", "송혜교");
		 StudentA  b = new StudentA("012345", "송혜교");
		 //Object의 equals() : ==
		 
		 if( a.equals(b)) { // a == b // 주소값이 같은지
			 System.out.println("객체 a와 b는 같다");
		 }else {
			 System.out.println("객체 a와 b는 다르다");
		 }

		 System.out.println( a  ); // println( Object x );
		 System.out.println( b  ); // System.out.println(b.toString())과 동일
	}
}

class StudentA extends Object
{
	String  hakbun, name;
	StudentA( String  hakbun, String name)
	{
		this.hakbun = hakbun;
		this.name = name;
	}	
	public String toString() {
		return hakbun + "/" + name;
	}

}