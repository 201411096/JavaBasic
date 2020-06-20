package h_homework.homework01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ex4{
	
	public static void addToStringList(ArrayList<String> stringList, String temp) {
		StringTokenizer st = new StringTokenizer(temp);
		while(st.hasMoreTokens()) {
			stringList.add(st.nextToken());
		}
	}

	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		
		String answer="";
		try {
			fr = new FileReader("src/ngramCheck.txt");
			br = new BufferedReader(fr);
			fw = new FileWriter("src/ngramOut.txt");
			bw = new BufferedWriter(fw);	
			
			//입력을 받아서 가공하는 부분
			while(true) {
				ArrayList<String> stringList = new ArrayList<String>();
				int paragraph = Integer.parseInt(br.readLine());
				if(paragraph==0) 
					break;
				for(int i=0; i<paragraph; i++) {
					String temp = br.readLine();
					temp = temp.replaceAll("[.]", " ");
					temp = temp.replaceAll("[,]", " ");
					addToStringList(stringList ,temp); // 한줄을 공백단위로 끊어서 나온 단어들을 arrayList에 담아주는 함수
				}
				for(int i=0; i<stringList.size(); i++) {
					System.out.println(stringList.get(i));
				}
			}
			
			
			
			//출력을 만들어주는 부분
			
			
			
			
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

