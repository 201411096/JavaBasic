package h_inherit;

/*
 * overloading : 동일한 함수명으로 인자의 자료형과 갯수가 다른 함수들.. //은 접근지정자까지는 같지 않아도 됨
 * 
 * overriding : 상속관계에서 인자와 반환값까지 동일한 함수들..
 *

 */

public class Test {

	public static void main(String[] args) {
//		Umma u = new Umma();
//		u.gene();
//		u.job();
		
//		Ddal d = new Ddal();
//		d.gene();
//		d.study();
//		
//		//*
//		d.job();
		
//		//2. 부모 변수에 자식 객체 생성
//		Umma dal = new Ddal();
//		//dal.study(); 변수가 부모 변수면 자식 함수는 사용하지 못함
//		dal.job();
//		dal.gene(); // 부모 변수여도 같은 이름의 자식 함수가 있다면 자식 함수 사용
		
		//3. 객체임을 확인
//		Ddal d = new Ddal();
//		if(d instanceof Ddal) // 클래스와 객체 간의 관계를 물어보는 연산자 : instanceof
//			System.out.println("딸 객체");
//		if(d instanceof Umma)
//			System.out.println("부모 객체");
		
		//4. 형변환
		//(casting) : 기본형, 상속관계의 클래스
		
		Umma a = new Umma();
//		Ddal b = (Ddal)a; // 컴파일 오류는 뜨지 않지만 실행하면 에러 발생 (메모리를 조금 잡아놓았는데 많이 사용하려고해서..?)
		Ddal c = new Ddal();
		Umma d = (Umma)c; //up-casting
		Ddal e = (Ddal)d; //down-casting
		
		//--------------------------------------------------예시
//		class Animal {}
//		 
//		class Dog extends Animal {}
//		 
//		class Cat extends Animal {}
//		 
//		class Test
//		{
//		    public static void main(String[] args)
//		    {
//		        Animal a = new Animal();
//		        Dog d = new Dog();
//		 
//		        Animal a2 = d; // OK, since Dog IS-A Animal // 부모 변수에 자식 객체를 넣는 것은 가능 (동물 변수에 강아지 객체)
//		        Dog d2 = a; // not OK; what if a is a Cat? // 자식 변수에 부모 변수를 넣는 것은 안됨 (강아지 객체를 동물 변수에는 안됨)
//		    }
//		}

		
	}

}
