package t_teamproject_02;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.util.StringTokenizer;

public class Configuration implements Serializable{
	private static Configuration instance;
	
	private String configFilePath = "E:/eclipse-workspace/JavaBasic/JavaBasic/src/t_teamproject_02/config.txt";
//	private boolean isInitialize;
	private int row_seat_num, col_seat_num;
	
	private Configuration() {
	}
	public static synchronized Configuration getInstance() {
		if(instance==null) {
			instance = new Configuration();
		}
		return instance;
	}

	public void getTotalConfiguration() // 파일에서 읽기
	{
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(configFilePath));
			Configuration con = Configuration.getInstance();
			con = (Configuration)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTotalConfiguration() // 파일에 쓰기
	{
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(configFilePath));
			Configuration con = Configuration.getInstance();
			oos.writeObject(con);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
//	public boolean getInitialize() {
//		return isInitialize;
//	}
//	public void setInitialize(boolean isInitialize) {
//		this.isInitialize = isInitialize;
//	}
	public int getRow_seat_num() {
		return row_seat_num;
	}
	public void setRow_seat_num(int row_seat_num) {
		this.row_seat_num = row_seat_num;
	}
	public int getCol_seat_num() {
		return col_seat_num;
	}
	public void setCol_seat_num(int col_seat_num) {
		this.col_seat_num = col_seat_num;
	}	
}
/* 이전에 사용한 방식
	private BufferedReader br;
	private BufferedWriter bw;
	private String config_string_array [] = {"row_seat_num", "col_seat_num"};
public void getTotalConfiguration() // 파일에서 읽기
{
	try {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(configFilePath)));
		String line = "";
		while((line = br.readLine())!=null) {
			System.out.println(line);							//테스트용
			StringTokenizer st = new StringTokenizer(line);
			if(st.nextToken().equals(config_string_array[0]))
				this.setRow_seat_num(Integer.parseInt(st.nextToken()));
			else if(st.nextToken().equals(config_string_array[1]))
				this.setCol_seat_num(Integer.parseInt(st.nextToken()));
		}
		br.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
}
public void setTotalConfiguration() // 파일에 쓰기
{
	try {
		bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(configFilePath)));
		StringBuffer sb = new StringBuffer();
		sb.append(config_string_array[0] + " " + this.getRow_seat_num()+"\n");
		sb.append(config_string_array[1] + " " + this.getCol_seat_num()+"\n");
		System.out.println(sb.toString());
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	} catch (Exception e) {
		e.printStackTrace();
	} 
}
*/