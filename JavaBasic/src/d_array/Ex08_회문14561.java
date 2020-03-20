package d_array;



import java.util.Scanner;
import java.util.StringTokenizer;

public class Ex08_회문14561{
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int testCase = scanner.nextInt();
		scanner.nextLine();
		Long testNum[] = new Long [testCase];
		int testDigit[] = new int [testCase];
		int answer[] = new int[testCase];
		for(int i=0; i<testCase; i++) //테스트케이스 수 만큼 n과 십진수 수를 입력받음
		{
			String str = scanner.nextLine();
			StringTokenizer st = new StringTokenizer(str);
			testNum[i]=Long.parseLong(st.nextToken());
			testDigit[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<testCase; i++)
		{
			answer[i]=1;
			int testNum_digitCnt =0; //n진법에서의 자릿수 변수
			long temp = testNum[i]; //n진법에서의 자릿수를 구하는데 사용할 변수
			while(temp!=0) //n진법에서의 자릿수를 구함
			{
				temp/=testDigit[i];
				testNum_digitCnt++;
			}
			long temp2 = testNum[i];
			long testNum_in_testDigit[] = new long[testNum_digitCnt]; //n진법에서의 자릿수만큼 배열 지정
			for(int j=0; j<testNum_digitCnt; j++) //n진법으로 변환
			{
				testNum_in_testDigit[j] = temp2%testDigit[i];
				temp2/=testDigit[i];
			}
			for(int j=0; j<testNum_digitCnt/2; j++)
			{
				if(testNum_in_testDigit[j]!=testNum_in_testDigit[testNum_digitCnt-j-1])
				{
					answer[i]=0;
				}
					
			}
		}
		for(int i=0; i<testCase; i++)
			System.out.println(answer[i]);
	}
}











