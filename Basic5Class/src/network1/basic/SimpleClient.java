package network1.basic;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient {
	
	public final static int PORT = 5000;
//	public final static String HOST = "192.168.0.13";
	public final static String HOST = "192.168.0.20";
	
	public static void main( String args[] ) {
		
		Socket client = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		// 콘솔 입력을 받기 위해
		Scanner stdin = new Scanner( System.in );
		try{ 
		// 1. 서버에 연결하기 위해 소켓 생성
		client = new Socket(HOST, PORT);	
		// 2. 소켓의 입출력 스트림 얻기
		in = new DataInputStream(client.getInputStream());
		out = new DataOutputStream(client.getOutputStream());
		// 3. 키보드 입력 받아 서버에 전송
		System.out.println("짝꿍에게 인사말을 ->");
		out.writeUTF(stdin.nextLine());
		// 4. 서버로부터 응답을 받음
		System.out.println(in.readUTF());
	
		
		// 5. 입출력 스트림의 닫음 // 소켓먼저 생성 -> 그 이후 dataInputStream, dataOutputStream.. 일 경우 나중에 생성한 것부터 닫기
		in.close();
		out.close();
		client.close(); // 소켓을 마지막으로 닫기
			
			
		} catch ( Exception ex ) {
			System.out.println("Error writing.." + ex );	
		}
	}	
}