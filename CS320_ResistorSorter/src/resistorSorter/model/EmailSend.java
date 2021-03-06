package resistorSorter.model;

import java.util.*;
import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.*;
import javax.activation.*;

public class EmailSend {
	
	public static boolean sendMail(String from, String password, String message, String to[], String email){
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.user", from);
		props.put("mail.smtp.password", password);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, null);
		
		MimeMessage mimeMessage = new MimeMessage(session);
		try{
			mimeMessage.setFrom(new InternetAddress(from));
			//get address if recipients
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for(int i=0; i<to.length; i++){
				toAddress[i] = new InternetAddress(to[i]);
			}
			for(int i=0; i <toAddress.length; i++){
				mimeMessage.addRecipient(RecipientType.TO, toAddress[i]);
			}
			//add subject
			mimeMessage.setSubject("ADMIN REQ: "+email);
			//Set message
			mimeMessage.setText(message);
			Transport transport = session.getTransport("smtp");
			transport.close();
			transport = session.getTransport("smtp");
			transport.connect(host,from,password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			return true;
			
		}catch(MessagingException me){
			me.printStackTrace();
		}
		
		
		return false;
	}
}
