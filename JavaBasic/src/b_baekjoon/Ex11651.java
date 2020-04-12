package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex11651{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int inputCase = Integer.parseInt(br.readLine());
		Point2 pointArray [] = new Point2[inputCase];
		for(int i=0; i<pointArray.length; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			pointArray[i] = new Point2(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Arrays.sort(pointArray);
		for(int i=0; i<pointArray.length; i++)
		{
			bw.write(pointArray[i].toString());
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
class Point2 implements Comparable<Point2>{
	int x;
	int y;
	public Point2(int x, int y){
		this.x=x;
		this.y=y;
	}
	@Override
	public String toString() {
		return this.x + " " + this.y + "\n";
	}
	
	@Override
	public int compareTo(Point2 o) {
		if(this.y==o.y) 				//y값이 같다면 x값을 기준으로 오름차순
			return this.x-o.x;
		else							//y값이 다르다면 y값을 기준으로 오름차순
			return this.y-o.y;
	}
		
}

