import org.openqa.selenium.WebDriverException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;

public class Bubbles extends Game{
	private static String type = "bubbles";
	private static Pattern play = new Pattern(Bot.dir + "\\patterns\\bubbles\\play.PNG");
	private static Pattern close = new Pattern(Bot.dir + "\\patterns\\bubbles\\close.PNG");
	
private static void startPlay(Account acc) {
	acc.scrollTo(370);
	try {
		Bot.wait(200);
		Location playSpot = screen.find(play).getTarget();
		screen.click(playSpot);
		Bot.wait(200);
		screen.click(playSpot);
		Bot.wait(1000);
		acc.currentlyPlaying = type;
		acc.setThreeMinuteTimeout(180);
	} catch (FindFailed e) {if(acc.window.getCurrentUrl().equals("http://gf.com/games/bubble_shooter/play")){
		startGame(acc);
		}else{
			goBack(acc,type);
			acc.refillBuxAndWithdraw();
		}
	}
	}
public static void startGame(Account acc) {
			Bot.wait(200);
			acc.goTo("http://gf.com/games/bubble_shooter");
			acc.scrollTo(400);
		try {
			Bot.wait(200);
			acc.window.findElementByXPath("//*[@id='game-page-layout']/div/div/div[2]/div/div[3]/div[2]/form/button").click();
			acc.scrollTo(400);
			Bot.wait(200);
			acc.goTo("https://gf.com/games/bubble_shooter/play");
				Bot.wait(3000);
				acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("bubble_shooter.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\tbubble_shooter.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: bubble_shooter.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: bubble_shooter.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\tbubble_shooter.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\twindow.gameover = true;\r\n\t\t\t\t\t\twindow.location = bubble_shooter.get_result_url()\r\n\t\t\t\t\t}, 200);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\t//bubble_shooter.set_error_info(\"The server did not respond\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");
		}catch(WebDriverException e) {
			acc.goTo("https://gf.com/games/bubble_shooter/play");
			Bot.wait(3000);
			acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("bubble_shooter.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\tbubble_shooter.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: bubble_shooter.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: bubble_shooter.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\tbubble_shooter.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\twindow.gameover = true;\r\n\t\t\t\t\t\twindow.location = bubble_shooter.get_result_url()\r\n\t\t\t\t\t}, 200);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\t//bubble_shooter.set_error_info(\"The server did not respond\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");

			
		}startPlay(acc);
		
}
public static void finishPlay(Account acc) {
		acc.focus();
		Bot.wait(300);
		acc.scrollTo(370);
		Bot.wait(300);
		try {
			screen.click(close);
		} catch (FindFailed e) {throw new WebDriverException();}
		goBack(acc,type);
		acc.currentlyPlaying = "";
	}
}