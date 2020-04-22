package t_teamproject.teamproject_02.sendmail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public SendMail() {}
	public void sendMail(String user, String password, String to, String mainContent) {		//메일, 메일 비밀번호, 받는 사람 메일 주소, 메일 내용을 받아와서 메일 전송
		String host = "smtp.naver.com";		
		  Properties props = new Properties();
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.auth", "true");
		  Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(user, password);
		   }
		  });
		  // Compose the message
		  try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(user));
		   message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		   // Subject
		   message.setSubject("subway.. Java Mail Test");
		   // Text
		   message.setText(mainContent);
		   // send the message
		   Transport.send(message);
		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
}