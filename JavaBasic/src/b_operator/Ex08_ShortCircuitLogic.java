 package b_operator;

public class Ex08_ShortCircuitLogic {

	public static void main(String[] args) {
		//short circuit logic : 일반 논리
		//하나의 조건으로 전체 조건이 판명되면 나머지 조건을 실행하지 않습니다.
		int a=3;
		
		// if( 3 & 4) -> 이진 논리
		// if( 3>1 & 4>1) -> 일반 논리를 대신함
		
		if(a>3 & ++a>3)
		{
			System.out.println("조건만족");
		}
		System.out.println("A=" + a);
		
		if(a>1 | ++a>3) {
			System.out.println("조건만족2");
		}
		System.out.println("A=" + a);
	}

}
