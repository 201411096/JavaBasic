package network1.basic;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleClient {
	
	public final static int PORT = 5000;
	public final static String HOST = "127.0.0.1";
	
	public static void main( String args[] ) {
		
		Socket client = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		// 콘솔 입력을 받기 위해
		Scanner stdin = new Scanner( System.in );
		

		try{ 
					
		// 1. 서버에 연결하기 위해 소켓 생성
			
			
		// 2. 소켓의 입출력 스트림 얻기
			
		
		// 3. 키보드 입력 받아 서버에 전송
	
			
				
		// 4. 서버로부터 응답을 받음
			
	
		
		// 5. 입출력 스트림의 닫음
			
			
			
		} catch ( Exception ex ) {
			System.out.println("Error writing.." + ex );	
		}
	}	
}