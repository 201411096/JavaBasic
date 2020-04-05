package network3.chat;

import java.io.*;
import java.util.*;
import java.net.*;

public class ChatServer implements Runnable { 							//  server 구현
	ArrayList vc = new ArrayList();

	public void run() {
		ServerSocket ss = null; 
		try{
			ss = new ServerSocket(1234);								// serverSocket 생성을 한 후에
		}catch( Exception e ) {
			System.out.println(e);
		}
		while(true) {
			try{
				Socket s = ss.accept();									//클라이언트가 접근할 떄마다 소켓 생성
				System.out.println("Client 가 접속시도 :" + s );				//client의 정보가 같이 출력됨 ip / port localport
				ChatService cs = new ChatService(s);
				cs.start();												//chatService를 시작한 후...
				vc.add(cs);												//chatService들을 arraylist에 저장

			} catch( Exception e ) { }
		}
	}  // run ends

	public static void main( String [] arg ) {							// 메인함수
		ChatServer cs = new ChatServer();								// 서버 객체 생성
		new Thread(cs).start();											// 서버 쓰레드 실행
	}

	class ChatService extends Thread {
		String myname = "guest";														//chatservice의 
		BufferedReader in;
		OutputStream out;
		ChatService( Socket s ) {														//chatservice 생성시 client socket을 받음
			try{
				in = new BufferedReader( new InputStreamReader(s.getInputStream()));	//client socket에서 inputstream과 outputstream을 하나씩 받아옴
				out = s.getOutputStream();
			}catch( Exception e ) { }
		}// 생성자 종료

		public void run() {																//chatService의 run함수
			while(true) {
				try{
					String msg = in.readLine();											//msg를 한줄씩 받아옴
					if( msg == null ) return;
					StringTokenizer st = new StringTokenizer(msg);						//msg를 토큰으로 받아옴
					if( st.countTokens() > 1 ) {										//토큰 개수가 하나보다 많으면 계속 반복
						String temp = st.nextToken();									//토큰이 "/name"이면 이름 변경 

						if( temp.equalsIgnoreCase("/name" )) {							
							temp = st.nextToken();										//temp가 "/name"일 경우 바로 다음 토큰을 받고
							putMessageAll(myname + "님의 이름이 " + temp + "으로 바뀌었습니다.");	//이름을 바꿔줌
							myname = temp;

							// 추가2: 멤버 목록 추가
							changeList();

							continue;
						}

						else if( temp.indexOf(">") == 0 ) {
							String towhom = temp.substring(1);
							temp = st.nextToken();
							putMessageTo( towhom, "(속삭임)" + temp );
							continue;
						}

						// 추가	
						// 방에 처음 들어왔을때 멤버들에게 인사
						else if( temp.equalsIgnoreCase("/start" )) {
							myname = st.nextToken();
							putMessageAll(myname + "님이 입장하셨습니다");

							// 추가2: 멤버 목록 보여주기
							changeList();

							continue;
						}

						// 추가3: 클라이언트측에서 종료할때
						else if( temp.equalsIgnoreCase("/exit" )) {

							putMessageAll(myname + "님이 퇴실하셨습니다");

							// 추가4: 멤버목록에서 제거
							vc.remove(this);						
							changeList();

							continue;
						}
					}

					putMessageAll( myname + ">" + msg );

				}catch( Exception ex ) { return; }

			}
		}// run ends

		// 추가2: 멤버 목록 보여주기
		void changeList(){
			String msg = "/member  ";
			for( int i =0 ; i<vc.size() ; i++ ) {
				ChatService cs = (ChatService)vc.get(i);
				msg += cs.myname + " ";
			}

			putMessageAll( msg ) ; //<-------  여기서 \n을 절대 주면 안됨


		}

		void putMessageAll( String msg ) {
			for( int i =0 ; i<vc.size() ; i++ ) {
				ChatService cs = ( ChatService ) vc.get(i);

				try {
					cs.putMessage(msg);
				}catch( Exception e ) {
					vc.remove(i--);
				}
			}
		} // putMessageAll ends

		void putMessageTo( String towhom, String msg ) {
			for( int i=0; i<vc.size() ; i++ ) {
				ChatService cs = ( ChatService ) vc.get(i);
				if( towhom.equalsIgnoreCase( cs.myname )) {
					try{
						cs.putMessage( towhom +">"+ msg);
						break;
					}catch( Exception ex ) { }
				}
			}
		} // putMessageTo ends

		void putMessage( String msg )
				throws Exception {
			out.write( (msg+"\r\n").getBytes() );
		}

	} // ChatService class ends


}// ChatServer class ends
