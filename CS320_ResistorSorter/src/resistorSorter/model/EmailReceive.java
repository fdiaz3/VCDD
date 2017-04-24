package resistorSorter.model;


import java.io.IOException;  
import java.util.Properties;  
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;  
import javax.mail.NoSuchProviderException;  
import javax.mail.Session;
import javax.mail.Store;

import com.sun.mail.pop3.POP3Store;  

import resistorSorter.persist.*;

public class EmailReceive {
	
	public static String[] check(String host, String storeType, String user, String password){
	    try {

	        //create properties field
	        Properties properties = new Properties();

	        properties.put("mail.pop3.host", host);
	        properties.put("mail.pop3.port", "995");
	        properties.put("mail.pop3.starttls.enable", "true");
	        Session emailSession = Session.getDefaultInstance(properties);
	    
	        //create the POP3 store object and connect with the pop server
	        Store store = emailSession.getStore("pop3s");

	        store.connect(host, user, password);

	        //create the folder object and open it
	        Folder emailFolder = store.getFolder("INBOX");
	        emailFolder.open(Folder.READ_ONLY);

	       
	        // retrieve the messages from the folder in an array and print it
	        Message[] messages = emailFolder.getMessages();
	        System.out.println("messages.length---" + messages.length);
	        String[] requests = new String[messages.length];
	        
	        for (int i = 0, n = messages.length; i < n; i++) {
	           Message message = messages[i];
	           System.out.println("---------------------------------");
	           System.out.println("Email Number " + (i + 1));
	           System.out.println("Subject: " + message.getSubject());
	           System.out.println("From: " + message.getFrom()[0]);
	           System.out.println("Text: " + message.getContent().toString());
	           requests[i] = message.getSubject();
	        }
	        
	        //close the store and folder objects
	        emailFolder.close(false);
	        store.close();
	        return requests;
	        
	        } catch (NoSuchProviderException e) {
	           e.printStackTrace();
	        } catch (MessagingException e) {
	           e.printStackTrace();
	        } catch (Exception e) {
	           e.printStackTrace();
	        }
		return null;
	  
	     }
	
}
