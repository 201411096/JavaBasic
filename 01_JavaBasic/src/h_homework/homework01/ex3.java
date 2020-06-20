package h_homework.homework01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class ex3{

	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			fr = new FileReader("src/ngramCheck.txt");
			br = new BufferedReader(fr);
			fw = new FileWriter("src/ngramOut.txt");
			bw = new BufferedWriter(fw);
			
			String answer="";
			while(true) {	//입력을 받아서 가공하는 부분
				int paragraph = Integer.parseInt(br.readLine());
				if(paragraph==0) 
					break;
				else {
					answer+="Paragraph #"+paragraph+"\n";	//문장 개수만큼 문단의 첫문장을 형식에 맞춰서 만들어줌 ex) Paragraph #4
				}
				for(int i=0; i<paragraph; i++) {
					String temp = br.readLine();
					temp = temp.replaceAll("[.]", " ");
					temp = temp.replaceAll("[,]", " ");
					answer+=temp+"\n";
				}
				answer+="\n"; // 문단별로 한줄씩 띄워줌
			}
			bw.write(answer);
			bw.flush();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
				fw.close();
				bw.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}
		
	}
}

