package h_homework.homework01;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

public class Main{
	
	public static void addToStringList(ArrayList<String> stringList, String temp) {
		StringTokenizer st = new StringTokenizer(temp);
		while(st.hasMoreTokens()) {
			stringList.add(st.nextToken());
		}
	}
	public static String findUnigram(ArrayList<String> stringList) {
		String bigram="";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0; i<stringList.size(); i++) { // 단어마다 확인을 해줌
			for(int j=0; j<stringList.get(i).length(); j++) { //단어의 길이만큼 확인
				String key = Character.toString(stringList.get(i).charAt(j));
				if(!map.containsKey(key)) {
					map.put(key, 1);	// 해당 값이 없다면 1(카운트)
				}else {
					map.put(key, map.get(key)+1);	// 해당 값이 없다면 ++(카운트)
				}	
			}
		}
		Iterator<String> it = map.keySet().iterator();
		String max_cnt_key = it.next();
		while(it.hasNext()) {
			String key = it.next();
			if(map.get(key) > map.get(max_cnt_key) )
				max_cnt_key=key;
			else if(map.get(key)==map.get(max_cnt_key)) { // 둘이 같다면
				int result = key.compareTo(max_cnt_key);
				if(result>0)	// 사전순서대로 따졌을때 key가 현재 단어보다 빠른 경우
					max_cnt_key=key;
				// 같거나 현재 단어가 빠를 경우는 변경하지 않아도 됨
			}
		}
		bigram=max_cnt_key;
		return bigram;
	}
	
	public static String findBigram(ArrayList<String> stringList) {
		String bigram="";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0; i<stringList.size(); i++) { // 단어마다 확인을 해줌
			for(int j=0; j<stringList.get(i).length()-1; j++) { //단어의 길이만큼 확인
				String key = (stringList.get(i).substring(j, j+2));
				if(!map.containsKey(key)) {
					map.put(key, 1);	// 해당 값이 없다면 1(카운트)
				}else {
					map.put(key, map.get(key)+1);	// 해당 값이 없다면 ++(카운트)
				}	
			}
		}
		Iterator<String> it = map.keySet().iterator();
		String max_cnt_key = it.next();
		while(it.hasNext()) {
			String key = it.next();
			if(map.get(key) > map.get(max_cnt_key) )
				max_cnt_key=key;
			else if(map.get(key)==map.get(max_cnt_key)) { // 둘이 같다면
				int result = key.compareTo(max_cnt_key);
				if(result>0)	// 사전순서대로 따졌을때 key가 현재 단어보다 빠른 경우
					max_cnt_key=key;
				// 같거나 현재 단어가 빠를 경우는 변경하지 않아도 됨
			}
		}
		bigram=max_cnt_key;
		return bigram;
	}
	
	public static String findTrigram(ArrayList<String> stringList) {
		String bigram="";
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0; i<stringList.size(); i++) { // 단어마다 확인을 해줌
			for(int j=0; j<stringList.get(i).length()-2; j++) { //단어의 길이만큼 확인
				String key = (stringList.get(i).substring(j, j+3));
				if(!map.containsKey(key)) {
					map.put(key, 1);	// 해당 값이 없다면 1(카운트)
				}else {
					map.put(key, map.get(key)+1);	// 해당 값이 없다면 ++(카운트)
				}	
			}
		}
		Iterator<String> it = map.keySet().iterator();
		String max_cnt_key = it.next();
		while(it.hasNext()) {
			String key = it.next();
			if(map.get(key) > map.get(max_cnt_key) )
				max_cnt_key=key;
			else if(map.get(key)==map.get(max_cnt_key)) { // 둘이 같다면
				int result = key.compareTo(max_cnt_key);
				if(result>0)	// 사전순서대로 따졌을때 key가 현재 단어보다 빠른 경우
					max_cnt_key=key;
				// 같거나 현재 단어가 빠를 경우는 변경하지 않아도 됨
			}
		}
		bigram=max_cnt_key;
		return bigram;
	}

	public static void main(String[] args){
		FileReader fr = null;
		FileWriter fw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		
		String answer="";
		try {
			//fr = new FileReader("src/ngramCheck.txt");
			fr = new FileReader("src/ngramData.txt");
			br = new BufferedReader(fr);
			fw = new FileWriter("src/ngramOut.txt");
			bw = new BufferedWriter(fw);	
			
			//입력을 받아서 가공하는 부분
			while(true) {
				ArrayList<String> stringList = new ArrayList<String>();
				int paragraph = Integer.parseInt(br.readLine());
				String unigram="";
				String bigram="";
				String trigram="";
				if(paragraph==0) 
					break;
				for(int i=0; i<paragraph; i++) {
					String temp = br.readLine();
					temp = temp.replaceAll("[.]", " ");
					temp = temp.replaceAll("[,]", " ");
					addToStringList(stringList ,temp); // 한줄을 공백단위로 끊어서 나온 단어들을 arrayList에 담아주는 함수
				}//한 문단 단위의 단어가 arrayList에 전부 담김
				unigram = findUnigram(stringList);
				System.out.println("unigram 확인:" + unigram);
				bigram = findBigram(stringList);
				System.out.println("bigram 확인:" + bigram);
				trigram = findTrigram(stringList);
				System.out.println("trigram 확인:" + trigram);
				//bigram;
				//trigram;
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

