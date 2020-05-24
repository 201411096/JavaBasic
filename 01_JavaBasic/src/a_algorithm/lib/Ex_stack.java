package a_algorithm.lib;

import java.util.Stack;

public class Ex_stack {
	public static void main(String[] args) {
		Stack<Integer> s = new Stack<Integer>();
		
		System.out.println("비어 있는지 확인" + s.empty());
		System.out.println("스택 사이즈 확인 " +s.size());
//		System.out.println(s.peek());
		s.push(1);
		System.out.println(s.peek());		
		System.out.println("스택 사이즈 확인 " +s.size());
		s.push(3);
		System.out.println(s.peek());
		System.out.println("스택 사이즈 확인 " +s.size());
		s.push(2);
		System.out.println(s.peek());
		System.out.println("스택 사이즈 확인 " +s.size());
		System.out.println("비어 있는지 확인" + s.empty());
		// 1 3 2
		System.out.println(s.pop());
		System.out.println("스택 사이즈 확인 " +s.size());
		System.out.println(s.pop());
		System.out.println("스택 사이즈 확인 " +s.size());
		System.out.println(s.pop());
		System.out.println("스택 사이즈 확인 " +s.size());
		//2 3 1 (들어간 순서의 반대로 나옴)
	}
}
