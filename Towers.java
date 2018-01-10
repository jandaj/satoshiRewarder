import org.openqa.selenium.WebDriverException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;

public class Towers extends Game{
	private static String type = "towers";
	private static Pattern close = new Pattern(Bot.dir + "\\patterns\\towers\\close.PNG").similar((float)0.9);
	private static Pattern plane = new Pattern(Bot.dir + "\\patterns\\towers\\plane.PNG").similar((float)0.9);
	private static Pattern play = new Pattern(Bot.dir + "\\patterns\\towers\\play.PNG").similar((float)0.9);
	public static void startGame(Account acc) {
			Bot.wait(200);
			acc.goTo("http://gf.com/games/towerblocks");
		acc.scrollTo(400);
		try {
			Bot.wait(200);
			acc.window.findElementByXPath("//*[@id='game-page-layout']/div/div/div[2]/div/div[3]/div[2]/form/button").click();
			Bot.wait(200);
			acc.goTo("https://gf.com/games/towerblocks/play");
				Bot.wait(3000);
				acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("towerblocks.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\ttowerblocks.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: towerblocks.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: towerblocks.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\ttowerblocks.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\twindow.gameover = true;\r\n\t\t\t\t\t\twindow.location = towerblocks.get_result_url()\r\n\t\t\t\t\t}, 200);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\t//towerblocks.set_error_info(\"The server did not respond\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");
		}catch(WebDriverException e) {
			acc.goTo("https://gf.com/games/towerblocks/play");
				Bot.wait(3000);
				acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("towerblocks.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\ttowerblocks.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: towerblocks.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: towerblocks.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\ttowerblocks.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\twindow.gameover = true;\r\n\t\t\t\t\t\twindow.location = towerblocks.get_result_url()\r\n\t\t\t\t\t}, 200);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\t//towerblocks.set_error_info(\"The server did not respond\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");
			}
		startPlay(acc);
	}
	private static void startPlay(Account acc) {
		acc.scrollTo(329);
		try {
			Bot.wait(500);
			Location playSpot = screen.find(play).getTarget();
			screen.click(playSpot);
			Bot.wait(1500);
			screen.click(playSpot);
			acc.currentlyPlaying = type;
			acc.setThreeMinuteTimeout(180);
		} catch (FindFailed e) {
			if(acc.window.getCurrentUrl().equals("http://gf.com/games/towerblocks/play")){
				startGame(acc);
				}else{
					goBack(acc,type);
					acc.refillBuxAndWithdraw();
				}}
	}
	public static void finishPlay(Account acc) {
		acc.focus();
		try {
			screen.click(plane);
			Bot.wait(2000);
			acc.scrollTo(329);
			Bot.wait(300);
			screen.click(close);
			Bot.wait(5000);
		} catch (FindFailed e) {throw new WebDriverException();}
		goBack(acc,type);
		acc.currentlyPlaying = "";
	}
}