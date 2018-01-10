import java.io.File;
import java.net.HttpCookie;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.sikuli.script.Key;
import org.sikuli.script.Screen;

public class Account {
	
	private Screen screen = new Screen();
	private String proxy;
	private String email;
	private String user;
	private String pass = "password";
	private Timer nuts = new Timer();
	private Timer pirates = new Timer();
	private Timer bubbles = new Timer();
	private Timer towers = new Timer();
	private Timer cat = new Timer();
	private Timer threeMinute = new Timer();
	private void initTimers() {
		nuts.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() {if(nutsTimeout > 0){
			    	  nutsTimeout--;}
			      }
			    },0,1000);
		pirates.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() {if(piratesTimeout > 0){
			    	  piratesTimeout--;}
			      }
			    },0,1000);
		bubbles.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() {if(bubblesTimeout > 0){
			    	  bubblesTimeout--;}
			      }
			    },0,1000);
		towers.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() {if(towersTimeout > 0){
			    	  towersTimeout--;}
			      }
			    },0,1000);
		cat.scheduleAtFixedRate(
			    new TimerTask() {
			      public void run() {if(catTimeout > 0){
			    	  catTimeout--;}
			      }
			    },0,1000);
		threeMinute.scheduleAtFixedRate(
			    new TimerTask() {
				      public void run() {if(threeMinuteTimeout > 0){
				    	  threeMinuteTimeout--;}
				      }
				    },0,1000);
	}
	private int threeMinuteTimeout = 0;
	private int nutsTimeout = 0;
	private int piratesTimeout = 0;
	private int bubblesTimeout = 0;
	private int towersTimeout = 0;
	private int catTimeout = 0;
	public int bux = -1;
	public int credits = 0;
	public boolean checkCurrency = true;
	public int bux() {
		WebElement odometer = window.findElementByXPath("//*[@id='user_credits']/div");
		List<WebElement> digits = odometer.findElements(By.className("odometer-value"));
		String creditStr = "";
		int currentScroll = Integer.parseInt(executeScript("return window.pageYOffset"));
		executeScript("window.scrollTo(0, 0);");
		for(WebElement digit : digits) {
			creditStr+=digit.getText();
		}
		credits = Integer.parseInt(creditStr);
		odometer = window.findElementByXPath("//*[@id='ow_credits']/div");
		digits = odometer.findElements(By.className("odometer-value"));
		String buxStr = "";
		for(WebElement digit : digits) {
			buxStr+=digit.getText();
		}
		executeScript("window.scrollTo(0," + currentScroll + ")");
		bux = Integer.parseInt(buxStr);
		return bux;
	}
	public int credits() {
		WebElement odometer = window.findElementByXPath("//*[@id='user_credits']/div");
		List<WebElement> digits = odometer.findElements(By.className("odometer-value"));
		String creditStr = "";
		int currentScroll = Integer.parseInt(executeScript("return window.pageYOffset"));
		executeScript("window.scrollTo(0, 0);");
		for(WebElement digit : digits) {
			creditStr+=digit.getText();
		}
		executeScript("window.scrollTo(0," + currentScroll + ")");
		return Integer.parseInt(creditStr);
	}
	public void refillBuxAndWithdraw() {
		boolean test = false;
		executeScript("window.scrollTo(0, 0);");
		if(this.bux() < 30) {
			test = true;
			window.findElementByXPath("//*[@id='ow_credits']/div").click();
			executeScript("window.scrollTo(0, 700);");
			Bot.wait(500);
			executeScript("document.getElementsByClassName('form-control')[0].setAttribute('value','')");
			window.findElementByXPath("//*[@id='feature']/div/div/div/div[2]/div/div/form/div[1]/input").sendKeys("200" + Keys.ENTER);
			
		}
		int credits = this.credits();
		if(credits>24000) {
			test = true;
			this.goTo("http://gamefaucet.com/withdraw");
			executeScript("window.scrollTo(0,800);");
			int withdrawAmount;
			int creditsICanWithdraw;
			try {
			creditsICanWithdraw = Integer.parseInt(window.findElementByXPath("//*[@id=\"withdraw-middle\"]/div/div/div[3]/div/div[2]/div/div[1]/div/div/div/div").getText());
			if(20000 > creditsICanWithdraw) {
				withdrawAmount = 0;
			}else if(credits > creditsICanWithdraw) {
				withdrawAmount = creditsICanWithdraw;
			}else{
				withdrawAmount = credits;
			}
			}catch(Exception e) {
				withdrawAmount = 0;
			}
			window.findElement(By.name("amount")).clear();
			window.findElement(By.name("amount")).sendKeys(Integer.toString(withdrawAmount));
			window.findElementByXPath("//*[@id=\"withdraw_faucetbox\"]/form/div[2]/div/div[2]/button").click();
		}
		if(test==true) {
			this.goTo("http://gamefaucet.com/games");
		}
		executeScript("window.scrollTo(0,800)");
	}
	public String currentlyPlaying = "";
	public void setNutsTimeout(int time) {
		nutsTimeout = time + 2;
	}
	public void setPiratesTimeout(int time) {
		piratesTimeout = time + 2;
	}
	public void setBubblesTimeout(int time) {
		bubblesTimeout = time + 2;
	}
	public void setTowersTimeout(int time) {
		towersTimeout = time + 2;
	}
	public void setCatTimeout(int time) {
		catTimeout = time + 10;
	}
	public void setThreeMinuteTimeout(int time) {
		this.threeMinuteTimeout = time;
	}
	public int nutsTimeout() {
		return nutsTimeout;
	}
	public int piratesTimeout() {
		return piratesTimeout;
	}
	public int bubblesTimeout() {
		return bubblesTimeout;
	}
	public int towersTimeout() {
		return towersTimeout;
	}
	public int catTimeout() {
		return catTimeout;
	}
	public int threeMinuteTimeout() {
		return this.threeMinuteTimeout;
	}
	public ChromeDriver window;
	//Constructors
	Account(String user, String email, String vpnAddress, String pass) {
		this.user = user;
		this.email = email;
		this.proxy = vpnAddress;
		this.pass = pass;
		initTimers();
	}
	Account(String user, String email, String pass,boolean ph) {
		this.user = user;
		this.email = email;
		this.pass = pass;
		initTimers();
	}
	Account(String user, String email, String vpnAddress) {
		this.user = user;
		this.email = email;
		this.proxy = vpnAddress;
		initTimers();
	}
	Account(String user, String email) {
		this.user = user;
		this.email = email;
		this.proxy = "";
		initTimers();
	}
	
	public void goTo(String url) {
		try {
			window.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			this.window.get(url);
			Bot.wait(500);
		}catch(WebDriverException exception) {
			while(true){
				try{
					window.findElement(By.tagName("body")).sendKeys("Keys.ESCAPE");
			break;}catch(Exception e){}
			}
//			smsService.sendMessage("Having a hard time loading something.");
//			String newTab = "";
//			String originalHandle = window.getWindowHandle();
//			screen.keyDown(Key.CTRL);
//			screen.type("t");
//			screen.keyUp();
//		    for(String handle : window.getWindowHandles()) {
//		        if (handle.equals(originalHandle)) {
//		            window.switchTo().window(handle);
//		            window.close();
//		        }else{
//		        	newTab = handle;
//		        }
//		    }
//			this.goTo(url);
		}
	}
	public String executeScript(String cmd) {
		//try{
			this.window.executeScript(cmd);
			if(cmd.indexOf("return")!=0) {
				return null;
			}else{
				return this.window.executeScript(cmd).toString();
			}
		//}catch(WebDriverException e) {
			//return executeScript(cmd);
		//}
	}
	public void scrollTo(int scrollTo) {
		try {
			this.window.executeScript("window.scrollTo(0,"+scrollTo+")");
		}catch(Exception e) {
			scrollTo(scrollTo);
		}
	}
	public void start() {
		//Open New Chrome Window
		ChromeOptions options = new ChromeOptions();
		String arguments = "--max_old_space_size 1600 --proxy-server=";
		arguments = arguments + this.proxy;
		options.addArguments(arguments);
		options.addExtensions(new File(Bot.dir + "popup.crx")); //Extension to easily block popups
		options.addExtensions(new File(Bot.dir + "refer.crx")); //Extension to easily spoof referrer
		options.addExtensions(new File(Bot.dir + "adblock.crx"));
		String newTab = "";
		if(this.proxy != "") {
			options.addExtensions(new File(Bot.dir + "proxyAutoAuth.crx")); //Auto Auth for proxies
			options.addExtensions(new File(Bot.dir + "webrtc.crx")); //Extension to block webRTC IP leaking in Chrome
		}
		//Gets rid of all but one tab Goes to login page and enlarges window
        try {
	    	window = new ChromeDriver(options);
	    	window.manage().window().maximize();
			screen.type("proxyUser");
			screen.keyDown(Key.TAB);
			screen.keyUp();
			screen.type("proxyPass");
			screen.keyDown(Key.ENTER);
			screen.keyUp();
			screen.keyDown(Key.CTRL);
			screen.type("t");
			screen.keyUp();
			String ori = window.getWindowHandle();
		    for(String handle : window.getWindowHandles()) {
		        if (handle.equals(ori)) {
		            window.switchTo().window(handle);
		            window.close();
		        }else{
		        	newTab = handle;
		        }
		    }
			window.switchTo().window(newTab);
			this.goTo("chrome-extension://ggmdpepbjljkkkdaklfihhngmmgmpggp/options.html");
			window.findElementById("login").sendKeys("proxyUser"); //proxy username
			window.findElementById("password").sendKeys("proxyPass"); //proxy password
			window.findElementById("save").click();
			this.goTo("chrome-extension://hnkcfpcejkafcihlgbojoidoihckciin/chrome/content/options.html");
		    window.executeScript("window.scrollTo(0,400)");
		    window.findElementById("import").click();
		    WebElement inputField = window.findElementById("import_input");
		    String str = "[{\"id\":\"defaultAction\",\"val\":\"dummy\",\"type\":\"normal\",\"filter\":\"\",\"is3rd\":true},{\"id\":1498421063254,\"val\":\"https://gamefaucet.com/games/nuts/play\",\"type\":\"specific\",\"filter\":\"http://mellowads.com/09iqT\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true},{\"id\":1498421813859,\"val\":\"https://gamefaucet.com/games/pirates/play\",\"type\":\"specific\",\"filter\":\"http://mellowads.com/0Pc59\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true},{\"id\":1498422890718,\"val\":\"https://gamefaucet.com/games/bubble_shooter/play\",\"type\":\"specific\",\"filter\":\"http://mellowads.com/9ngWc\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true},{\"id\":1498422899153,\"val\":\"https://gamefaucet.com/games/flapcat/play\",\"type\":\"specific\",\"filter\":\"http://mellowads.com/2dkzu\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true},{\"id\":1498422904609,\"val\":\"https://gamefaucet.com/games/towerblocks/play\",\"type\":\"specific\",\"filter\":\"http://mellowads.com/2emBk\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true},{\"id\":1498422910785,\"val\":\"\",\"type\":\"normal\",\"filter\":\"\",\"isregexp\":false,\"is3rd\":false,\"isfrom\":true,\"isto\":true}]";
		    window.executeScript("arguments[0].value = '" + str + "'", inputField);
		    window.findElementById("import").click();
		    window.switchTo().alert().accept();
	    } catch (WebDriverException e1) {smsService.sendMessage("There was an error setting up.");e1.printStackTrace();}
	    String originalHandle = window.getWindowHandle();
	    for(String handle : window.getWindowHandles()) {
	        if (!handle.equals(originalHandle)) {
	            window.switchTo().window(handle);
	            window.close();
	        }
	    }
	    window.switchTo().window(originalHandle);
		    //Attempts to login
	    Cloudflare bypass = new Cloudflare("http://gamefaucet.com/");
	    List<HttpCookie> cookies = bypass.scrape();
	    window.manage().addCookie(new Cookie(cookies.get(0).getName(),cookies.get(0).getValue()));
	    window.manage().addCookie(new Cookie(cookies.get(1).getName(),cookies.get(1).getValue()));
	    window.manage().addCookie(new Cookie(cookies.get(2).getName(),cookies.get(2).getValue()));
	    Bot.wait(5500);
	    while(true) {
	try {this.goTo("http://gamefaucet.com/login");
	window.findElement(By.name("username")).sendKeys(user);
	window.findElement(By.name("email")).sendKeys(email);
	window.findElement(By.name("password")).sendKeys(this.pass + Keys.ENTER);
	break;
	}catch(WebDriverException e) {
	screen.keyDown(Key.CTRL);
	screen.type("t");
	screen.keyUp();
	originalHandle = window.getWindowHandle();
    for(String handle : window.getWindowHandles()) {
        if (handle.equals(originalHandle)) {
            window.switchTo().window(handle);
            window.close();
        }else{
        	newTab = handle;
        }
    }
	window.switchTo().window(newTab);
	}}
			this.goTo("http://gamefaucet.com/games");
executeScript("window.scrollTo(0,800);");
//Records initial timeouts
	Bot.wait(200);
Bot.recordTimeout(this);
	}
	public void focus() {
		while(true) {
		try{
			this.screen.keyDown(Key.WIN);
			this.screen.type("d");
			this.screen.keyUp(Key.WIN);
			Bot.wait(100);
		executeScript("alert('')");
		Bot.wait(100);
		window.switchTo().alert().accept();
		Bot.wait(100);
		break;
		}catch(WebDriverException e) {continue;}
		}
	}
}