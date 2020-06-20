package h_homework.homework01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

//민서 과제 파일 입출력
public class ex{

	public static void main(String[] args){
		FileReader fr;
		FileWriter fw;
		BufferedReader br;
		BufferedWriter bw;
		try {
			fr = new FileReader("src/ngramCheck.txt");
			br = new BufferedReader(fr);
			fw = new FileWriter("src/ngramOut.txt");
			bw = new BufferedWriter(fw);
			String test="";
			while(true) {
				String temp = br.readLine();
				System.out.println(temp);
				if(temp==null) break;
				test+=temp+"\n";
			}
			bw.write(test);
			bw.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

