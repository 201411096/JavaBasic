package io;

import java.io.File;

public class FileTest {
	public static void main(String[] args) {
		File f = new File("a.txt");
		if(f.isFile())
			System.out.println("파일입니다.");
		else if(f.isDirectory()) {
			System.out.println("디렉토리입니다.");
			String [] sub = f.list(); // 디렉토리와 파일 전부를 가져옴
			for(String s : sub) // 배열과 컬렉션일때만 사용 가능
				System.out.println(s);
		}
		f.delete();
	}
	
}
