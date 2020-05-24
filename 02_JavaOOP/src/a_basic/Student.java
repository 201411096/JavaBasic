package a_basic;

public class Student {
	//멤버 변수(member field)
	String name;
	int kor, eng, math;
	private int total;
	private double avg;
	//멤버 함수 (member method / member function)
	int calTotal() {
		total = kor + eng+ math;
		return total;
	}
	
	double calAverage() {
		avg = (double)total/3;
		return avg;
	}
	
	
}
