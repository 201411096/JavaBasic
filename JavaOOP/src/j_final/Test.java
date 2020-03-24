package j_final;

 class Parent{
	 String field = "부모님꺼";
	 public void jib (){
	  System.out.println("부모님이 만드신거");
	 }
	} 
class Child extends Parent{

}

public class Test {
	 public static void main(String[] args) {
		 Child p = new Child();
		  System.out.println(p.field);
		  p.jib();
	 }
	}
