package f_innerclass;

class Outer
{
	class Inner
	{
		void naJabara(){
			System.out.println("잡아보슈~~");
		}
	}
}

public class InnerTest {
	public static void main(String[] args) 
	{
		Outer outer = new Outer();
		Outer.Inner in = outer.new Inner();
//		Outer.Inner in = new Outer.Inner();
		in.naJabara();
//		Outer.Inner.naJabara();
		
	}
}


