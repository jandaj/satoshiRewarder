import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class smsService implements Runnable {
	//private String userName;
	//private String password;
	private ArrayList<Account> accounts;
	public smsService(String name, String pass,ArrayList<Account> accounts) {
		//userName = name;
		//password = pass;
		this.accounts = accounts;
	}
	public static void sendMessage(String message) {
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "pop3");
	    properties.put("mail.pop3s.host", "pop.gmail.com");
	    properties.put("mail.pop3s.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    Session session = Session.getDefaultInstance(properties);
	    try{
		    Transport t = session.getTransport("smtp");
		    MimeMessage greeting = new MimeMessage(session);
	        greeting.setSubject(message);
	        greeting.setText(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date()));
	        greeting.setFrom(new InternetAddress("notificationEmail@example.com"));
	        greeting.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1234567890@tmomail.net"));
	        Address[] add = {new InternetAddress("1234567890@tmomail.net")};
	        t.connect("notificationEmail@example.com", "password");
	        t.sendMessage(greeting,add);
	        t.close();
	    }catch(Exception e) {
	    	e.printStackTrace(System.out);
	    }
	}
	public void run() {
		System.out.println("SMS Service Started...");
		Properties properties = new Properties();
		properties.put("mail.store.protocol", "pop3");
	    properties.put("mail.pop3s.host", "pop.gmail.com");
	    properties.put("mail.pop3s.port", "995");
	    properties.put("mail.pop3.starttls.enable", "true");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.host", "smtp.gmail.com");
	    properties.put("mail.smtp.port", "587");
	    Session session = Session.getDefaultInstance(properties);
	    try{
		    Transport t = session.getTransport("smtp");
		    MimeMessage greeting = new MimeMessage(session);
	        greeting.setSubject("Satoshi Gamer Initialized.");
	        greeting.setText(" ");
	        greeting.setFrom(new InternetAddress("notificationEmail@example.com"));
	        greeting.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1234567890@tmomail.net"));
	        Address[] add = {new InternetAddress("1234567890@tmomail.net")};
	        t.connect("notificationEmail@example.com", "password");
	        t.sendMessage(greeting,add);
	        t.close();
	        while(true) {
	        	Store store = session.getStore("pop3s");
			    store.connect("pop.gmail.com", "notificationEmail@example.com","password");
	        	Folder folder = store.getFolder("INBOX");
	        	folder.open(Folder.READ_WRITE);
	        	Message[] messages = folder.getMessages();
	        	if (messages.length != 0) {
	        			Message message = messages[0];
	        			if(!(InternetAddress.toString(message.getFrom()).equals("+11234567890@tmomail.net"))) {
	        				message.setFlag(Flags.Flag.DELETED, true);
	        			}else{
	        				Multipart content = (Multipart)message.getContent();
	        				String text = (String)(content.getBodyPart(1).getContent());
	        				if(text.toLowerCase().equals("status")) {
	        					t = session.getTransport("smtp");
	        					MimeMessage reply = new MimeMessage(session);
	        					reply.setSubject("CURRENT AMOUNT OF CREDITS");
	        					int totalCredits = 0;
	        					for(Account acc:accounts) {
	        						totalCredits+=acc.credits;
	        					}
	        					reply.setText("/ " + totalCredits);
	        					reply.setFrom(new InternetAddress("notificationEmail@example.com"));
	        					reply.setRecipients(Message.RecipientType.TO,InternetAddress.parse("1234567890@tmomail.net"));
	        					add[0] = new InternetAddress("1234567890@tmomail.net");
	        					t.connect("notificationEmail@example.com", "password");
	        					t.sendMessage(reply,add);
	        					t.close();
	        					message.setFlag(Flags.Flag.DELETED, true);
	        				}else{
	        					message.setFlag(Flags.Flag.DELETED, true);
	        				}
		            }
	        	}
	        	folder.close(true);
	        	store.close();
	        	Thread.sleep(5000);
	        }
	    }catch(Exception e) {
	    	e.printStackTrace(System.out);
	    }
	}
}