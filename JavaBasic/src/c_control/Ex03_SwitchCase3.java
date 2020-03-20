package c_control;

public class Ex03_SwitchCase3 {
	public static void main(String [] args) {
		int i=2, j=0;
		switch(i) {
		case 2:j+=6; break;
		case 4:j+=1; break;
		default : j+=2; // j = j+2
		case 0: j+=4; break;
			
		}
		System.out.println(j);
	}
}
