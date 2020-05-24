package a_algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Ex5086{
	static int compare(int num1, int num2) {
		if(num1>num2)
			return 1;
		else
			return -1;
	}
	static void printRelationShip(int num1, int num2) {
		int bNum=-1;
		int sNum=-1;
		int bNum_idx=0;
		if(compare(num1, num2)==1)
		{
			bNum=num1;
			sNum=num2;
			bNum_idx=1;
		}else {
			bNum=num2;
			sNum=num1;
			bNum_idx=2;
		}
		if(bNum==0 && sNum==0)
			System.out.print("");
		else if(bNum%sNum==0)
		{
			if(bNum_idx==1)
				System.out.println("multiple");
			else
				System.out.println("factor");
		}
		else {
			System.out.println("neither");
		}
			
	}
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int compNum1=-1, compNum2=-2;
		while(compNum1!=compNum2)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			compNum1 = Integer.parseInt(st.nextToken());
			compNum2 = Integer.parseInt(st.nextToken());
			printRelationShip(compNum1, compNum2);			
		}
	}
}

