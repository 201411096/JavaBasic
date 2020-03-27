package j_final;

class Parent{
	final String field = "부모님꺼";
	final public void jib (){
		System.out.println("부모님이 만드신거");
	}
} 
class Child extends Parent{
	Child(){
//		super.field="내꺼";
	}
//	public void jib() {
//		System.out.println("물려받은 거");
//	}
}

public class Test {
	public static void main(String[] args) {
		Child p = new Child();
		System.out.println(p.field);
		p.jib();
	}
}

/*
 * 1. 4
 * 2. 1, 5 (x) -> 1,2,5
 * 3. 4
 * 4. 1
 * 7. 3
 * 8. 2라인  public abstract void method(){} (x) -> 7라인 Parent p = new Child();
 * 9. ----------------1-------------------
 * 	  7
 * 	  7
 * 	  ----------------2-------------------
 * 	  5
 * 	  5
 *	  ----------------3-------------------
 *	  7
 *	  5
 */