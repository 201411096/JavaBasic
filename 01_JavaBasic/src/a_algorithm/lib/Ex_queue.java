package a_algorithm.lib;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Ex_queue {
	public static void main(String[] args) {
		Queue<Integer> q = new ArrayDeque<Integer>();
		//Queue<Integer> q = new LinkedList<Integer>();
			// ㄴ Ctrl + T를 누르면 해당 인터페이스를 구현한 클래스들을 보여줌
		
		System.out.println("비어 있는지 : " + q.isEmpty());
		System.out.println("사이즈 확인 : " + q.size());
		q.add(5);
		System.out.println(q.peek());
		System.out.println("사이즈 확인 : " + q.size());
		System.out.println("비어 있는지 : " +q.isEmpty());
		q.add(3);
		System.out.println(q.peek());
		System.out.println("사이즈 확인 : " + q.size());
		System.out.println("remove 확인 : "+q.remove()); // 가장 먼저 들어간 값을 반환하고 삭제함
		System.out.println("사이즈 확인 : " + q.size());
		System.out.println(q.peek());

	}
}
