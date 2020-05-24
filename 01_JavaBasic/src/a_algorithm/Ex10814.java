package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ex10814{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int inputCase = Integer.parseInt(br.readLine());
		Ex10814Sorting array [] = new Ex10814Sorting[inputCase];
		for(int i=0; i<inputCase; i++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			array[i]=new Ex10814Sorting(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		Arrays.sort(array);
		for(int i=0; i<array.length; i++)
		{
			bw.write(Integer.toString(array[i].age)+" "+ array[i].name+"\n");
			bw.flush();
		}
		br.close();
		bw.close();
	}
}
class Ex10814Sorting implements Comparable<Ex10814Sorting>{
	String name;
	int age;
	
	public Ex10814Sorting(int age,String name) {
		this.name=name;
		this.age=age;
	}
	@Override
	public int compareTo(Ex10814Sorting o) {
		return this.age-o.age;
	}
}
