import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.sikuli.basics.Settings;


public class Bot {
	public static String dir = "LIB_DIR";
	public static ArrayList<Account> accounts = new ArrayList<Account>();//Which accounts need to be botted.
	public static Scanner keyboardInput = new Scanner(System.in);
	
	private final static String GMAILNAME = "notificationEmail@example.com";
	private final static String GMAILPASSWORD = "password";
	//POPULATE / ADD ACCOUNTS HERE!!!!!!!!!!!!//
		private static void populateAccounts() {
//		accounts.add(new Account("account1","email1@example.com","ip:port"));
//		accounts.add(new Account("account2","email2@example.com","ip:port"));
//		accounts.add(new Account("account3","email3@example.com","ip:port"));
//		.....
	}
	
	public static boolean internetCheck() { //Checks to make sure computer is online.
		try {
			final URL url = new URL("http://gf.com/");
			final URLConnection connect = url.openConnection();
			connect.connect();
			return true;
		}catch(IOException err) {
			smsService.sendMessage("No connection to gf.");
			return false;
		}
	}
	
	private static void setup() { //Setups and goes to 'game' page.
		Settings.OcrTextRead = true;
		Settings.OcrTextSearch = true;
		System.out.println("Satoshi Rewarder");
		System.out.println("[[SETTING UP]]");
		System.out.println("Enter directory of 'lib' (escaped).");
		//dir = keyboardInput.nextLine();
		System.setProperty("webdriver.chrome.driver", dir + "chromedriver.exe");
		populateAccounts();
		System.out.print("Checking Internet Connection...");System.out.println("Done.");
		if(!internetCheck()){System.out.println("No Internet.  Closing...");System.exit(0);}
		System.out.println("Opening Chrome Windows...");
		smsService smsService = new smsService(GMAILNAME,GMAILPASSWORD,accounts);
		Thread sms = new Thread(smsService);
		sms.start();
		Iterator<Account> cycle = accounts.iterator();
		int i = 1;
		while(true) {
			if (cycle.hasNext()) {
				Account temp = cycle.next();
				temp.start();
				System.out.println("Account " + i + " set up.");
				i++;
			}else{
				break;
			}
		}
		accounts.get(0).focus();
		System.out.println("[[SETUP DONE]]");
	}
	
	public static int parseString(String str) { //Parses string into an integer
		String str2 = "";
		for(int i = 0; i<str.length(); i++) {
			if(Character.isDigit(str.charAt(i))) {
				str2+= str.charAt(i);
			}
		}
		if(str2=="") {
			return 0;
		}else{
		return Integer.parseInt(str2);}
	}
	
	public static int parseTimeout(String str) { //Parses String (X mins Y secs) into int (seconds).
		int minutes = 0;
		int indexOfMins = str.indexOf("m");
		if(indexOfMins!=-1){
		String strMinCut = str.substring(0,indexOfMins);
		minutes = parseString(strMinCut);}else{indexOfMins=0;}
		String strSecCut = str.substring(indexOfMins);
		int seconds = parseString(strSecCut);
		return seconds + (minutes * 60);
	}
	
	public static void recordTimeout(Account a) { //Records timeout at game grid page
		//Record Pirates Timeout
		
				try{
					WebElement e = a.window.findElement(By.xpath("//*[@id='game-page-layout']/div/div/div[2]/div[3]/div/div[1]/div/*[@class='portfolio-title']"));
					String text = e.getText();
					a.setPiratesTimeout(Bot.parseTimeout(text.substring(11)));
				}catch(NoSuchElementException e){}
				//Record Nuts Timeout
				try{
					WebElement e = a.window.findElement(By.xpath("//*[@id='game-page-layout']/div/div/div[2]/div[3]/div/div[2]/div/*[@class='portfolio-title']"));
					String text = e.getText();
					System.out.println(text);
					a.setNutsTimeout(Bot.parseTimeout(text.substring(11)));
				}catch(NoSuchElementException e){}
				try{
				//Record Bubbles Timeout
					WebElement e = a.window.findElement(By.xpath("//*[@id='game-page-layout']/div/div/div[2]/div[3]/div/div[3]/div/*[@class='portfolio-title']"));
					String text = e.getText();
					System.out.println(text);
					a.setBubblesTimeout(Bot.parseTimeout(text.substring(11)));
				}catch(NoSuchElementException e){}
				//Record Cat Timeout
				try{
					WebElement e = a.window.findElement(By.xpath("//*[@id='game-page-layout']/div/div/div[2]/div[3]/div/div[4]/div/*[@class='portfolio-title']"));
					String text = e.getText();
					System.out.println(text);
					a.setCatTimeout(Bot.parseTimeout(text.substring(11)));
				}catch(NoSuchElementException e){}
				//Record Towers Timeout
				try{
					WebElement e = a.window.findElement(By.xpath("//*[@id='game-page-layout']/div/div/div[2]/div[3]/div/div[5]/div/*[@class='portfolio-title']"));
					String text = e.getText();
					System.out.println(text);
					a.setTowersTimeout(Bot.parseTimeout(text.substring(11)));
				}catch(NoSuchElementException e){}
	}
	
	public static void wait(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		}catch(Exception ex) {}
	}
	
	public static void main(String []args) {
			setup();
			Cycle.queue(accounts);
	}

}
