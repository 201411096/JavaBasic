package b_baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JFrame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import javax.swing.JFrame;

public class Ex1931  extends JFrame{
	static int findAnswer(Time [] t) {
		int answer=0;
		int current_time=0;		
		for(int i=0; i<t.length; i++)
		{
			if(current_time>t[i].start) //현재 시간이 시작시간보다 더 클 경우 넘어감 (회의불가능)
				continue;
			current_time = t[i].end; // 끝나는 시간으로 이미 정렬이 되있기 떄문에 처음 만나게 되는 끝시간을 받음
			answer++;
		}
		return answer;
	}	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int inputCaseNum = Integer.parseInt(br.readLine());
		Time t [] = new Time[inputCaseNum];
		for(int i=0; i<inputCaseNum; i++) // 입력값 받음
		{
			t[i] = new Time();
			StringTokenizer st = new StringTokenizer(br.readLine());
			t[i].start = Integer.parseInt(st.nextToken());
			t[i].end = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(t); //끝나는 시간이 이른 순서대로, 끝나는 시간이 같으면 시작하는 시간이 빠른 순서대로 정렬

		bw.write(Integer.toString(findAnswer(t))); //정답 출력
		bw.flush();		
		bw.close();
		br.close();
	}
}
class Time implements Comparable<Time>{
	int start;
	int end;
	@Override
	public int compareTo(Time o) { // end값이 이른 순으로 정렬, end값이 같으면 start가 빠른 순으로 정렬
		if(this.end==o.end)
			return this.start-o.start;
		else
			return this.end-o.end;
	}
}
/*
비교시 양수가 나오면 this를 뒤로 보내는 형태
comparable------------------------------
CompareTo() 구현
this.__ - parameter.__  // ascending order (오름차순) 1,2,3 //  가,나,다 // a,b,c
parameter.__ - this.__ // descending order (내림차순) 3,2,1 // 다,나,가 // c,b,a
comparator------------------------------
Compare() 구현 
compare(type o1, type 02){
return o1-o2;   // ascending order (오름차순) 1,2,3 //  가,나,다 // a,b,c
//return o2-o1; // desceding order (내림차순) 3,2,1 // 다,나,가 // c,b,a
}
--------------------------------------------------------------------------------------------------------------------- 
두가지 조건에서 비교

class MyComparator implements Comparator<Point> {
  @Override
  public int compare(Point p1, Point p2) {
    if (p1.x > p2.x) {
      return 1; // x에 대해서는 오름차순
    }
    else if (p1.x == p2.x) {
      if (p1.y < p2.y) { // y에 대해서는 내림차순
        return 1;
      }
    }
    return -1;
  }
}
// main에서 사용법
List<Point> pointList = new ArrayList<>();
pointList.add(new Point(x, y));
MyComparator myComparator = new MyComparator();
Collections.sort(pointList, myComparator);
 */

