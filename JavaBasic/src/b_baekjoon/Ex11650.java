package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex11650{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int inputCase = Integer.parseInt(br.readLine());
		Point pointArray [] = new Point[inputCase];
		for(int i=0; i<inputCase; i++) 			// 입력
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			pointArray[i]= new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pointArray); 				// comparable 인터페이스에 정의한 compareTo함수대로 배열 정렬
		for(int i=0; i<pointArray.length; i++)  // 출력
		{
			bw.write(pointArray[i].toString());
			bw.flush();
		}
		br.close();
		bw.close();
	}
}

class Point implements Comparable<Point>{
	int x;
	int y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
	@Override
	public String toString() {
		return this.x + " " + this.y + "\n";
	}
	@Override
	public int compareTo(Point o) { //this.x - o.x -> 내림차순
		if(this.x==o.x) 			// x값이 같다면 y값을 비교해서 return 
			return this.y-o.y;
		else 						//x값이 같지 않다면 x값으로 비교
			return this.x-o.x;
	}
	
}