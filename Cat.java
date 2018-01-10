import org.openqa.selenium.WebDriverException;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;

public class Cat extends Game{
	private static String type = "cat";
	private static Pattern volume = new Pattern(Bot.dir + "\\patterns\\cat\\volume.PNG");
	//private static Pattern bckgrd = new Pattern(Bot.dir + "\\cat\\bckgrd.PNG");
	public static void startGame(Account acc) {
			Bot.wait(200);
			acc.goTo("http://gamefaucet.com/games/flapcat");
			acc.scrollTo(400);
		try {
			Bot.wait(200);
			acc.window.findElementByXPath("//*[@id='game-page-layout']/div/div/div[2]/div/div[3]/div[2]/form/button").click();
			acc.scrollTo(400);
			Bot.wait(200);
			acc.goTo("https://gamefaucet.com/games/flapcat/play");
				Bot.wait(3000);
				acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("flapcat.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\tflapcat.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\tif (flapcat.is_gameover) {\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\tif (!flapcat.session_ended) {\r\n\t\t\tflapcat.set_session('end')\r\n\t\t}\r\n\t\t\r\n\t\tflapcat.is_gameover = true;\r\n\t\t\r\n\t\t//console.log(\"gameover: \" + score)\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: flapcat.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: flapcat.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\tflapcat.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\t//console.log('game over request finished')\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\t//console.log(data.result.livesLeft)\r\n\t\t\t\t\t\r\n\t\t\t\t\t//if (data.result.livesLeft <= 0) {\r\n\t\t\t\t\t\twindow.location = flapcat.get_result_url()\r\n\t\t\t\t\t//}\r\n\t\t\t\t\t\r\n\t\t\t\t\t$('#gameoverBox').removeClass('hidden');\r\n\t\t\t\t\t\r\n\t\t\t\t\t$('#resultFinalScore').html(data.result.finalScore);\r\n\t\t\t\t\t$('#resultScore').html(data.result.score);\r\n\t\t\t\t\t$('#resultLevelBonus').html(data.result.levelBonus);\r\n\t\t\t\t\t$('#resultLivesLeft').html(data.result.livesLeft);\r\n\t\t\t\t\t$('#resultLevelsPlayed').html(window.last_level_ended);\r\n\t\t\t\t\t\r\n\t\t\t\t\twindow.gameover = true\r\n\t\t\t\t\t\r\n\t\t\t\t\tupdateGlobalStats()\r\n\t\t\t\t\t\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\t//location.reload();\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\tflapcat.set_session('reset')\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\t\tflapcat.session_started = false\r\n\t\t\t\t\t\t\tflapcat.session_ended = false\r\n\t\t\t\t\t\t\tflapcat.has_died = false\r\n\t\t\t\t\t\t\tflapcat.is_gameover = false\r\n\t\t\t\t\t\t\twindow.last_level_set = -1\r\n\t\t\t\t\t\t\twindow.last_level_ended = -1\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\t\tflapcat.set_session('start')\r\n\t\t\t\t\t\t}, 200)\r\n\t\t\t\t\t}, 2000);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\tflapcat.set_error_info(\"You have to wait to play this game again\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");
			}catch(WebDriverException e) {
			acc.goTo("https://gamefaucet.com/games/flapcat/play");
				Bot.wait(3000);
				acc.executeScript("window.adBlock = false");
			acc.executeScript("window.gameover = true");
			acc.executeScript("flapcat.game_over = function(score) {\r\n\t\tif (window.adBlock == true) {\r\n\t\t\tflapcat.adblock()\r\n\t\t\t\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\tif (flapcat.is_gameover) {\r\n\t\t\treturn false;\r\n\t\t}\r\n\t\t\r\n\t\tif (!flapcat.session_ended) {\r\n\t\t\tflapcat.set_session('end')\r\n\t\t}\r\n\t\t\r\n\t\tflapcat.is_gameover = true;\r\n\t\t\r\n\t\t//console.log(\"gameover: \" + score)\r\n\t\t\r\n\t\t$.ajax({\r\n\t\t\turl: flapcat.apiURL.game_over,\r\n\t\t\ttype: \"POST\",\r\n\t\t\tdata: { score : Math.floor(Math.random()*40) + 750, bet_prefix: flapcat.betPrefix},\r\n\t\t\tdataType: \"json\",\r\n\t\t\ttimeout: 30000,\r\n\t\t\tsuccess: function(data){\r\n\t\t\t\tif (!data.success) {\r\n\t\t\t\t\tflapcat.set_error_info(data.message);\r\n\t\t\t\t\treturn false;\r\n\t\t\t\t}\r\n\t\t\t\t\r\n\t\t\t\t//console.log('game over request finished')\r\n\t\t\t\t\r\n\t\t\t\tif (data.result.end) {\r\n\t\t\t\t\t//console.log(data.result.livesLeft)\r\n\t\t\t\t\t\r\n\t\t\t\t\t//if (data.result.livesLeft <= 0) {\r\n\t\t\t\t\t\twindow.location = flapcat.get_result_url()\r\n\t\t\t\t\t//}\r\n\t\t\t\t\t\r\n\t\t\t\t\t$('#gameoverBox').removeClass('hidden');\r\n\t\t\t\t\t\r\n\t\t\t\t\t$('#resultFinalScore').html(data.result.finalScore);\r\n\t\t\t\t\t$('#resultScore').html(data.result.score);\r\n\t\t\t\t\t$('#resultLevelBonus').html(data.result.levelBonus);\r\n\t\t\t\t\t$('#resultLivesLeft').html(data.result.livesLeft);\r\n\t\t\t\t\t$('#resultLevelsPlayed').html(window.last_level_ended);\r\n\t\t\t\t\t\r\n\t\t\t\t\twindow.gameover = true\r\n\t\t\t\t\t\r\n\t\t\t\t\tupdateGlobalStats()\r\n\t\t\t\t\t\r\n\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\t//location.reload();\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\tflapcat.set_session('reset')\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\tsetTimeout(function() {\r\n\t\t\t\t\t\t\tflapcat.session_started = false\r\n\t\t\t\t\t\t\tflapcat.session_ended = false\r\n\t\t\t\t\t\t\tflapcat.has_died = false\r\n\t\t\t\t\t\t\tflapcat.is_gameover = false\r\n\t\t\t\t\t\t\twindow.last_level_set = -1\r\n\t\t\t\t\t\t\twindow.last_level_ended = -1\r\n\t\t\t\t\t\t\r\n\t\t\t\t\t\t\tflapcat.set_session('start')\r\n\t\t\t\t\t\t}, 200)\r\n\t\t\t\t\t}, 2000);\r\n\t\t\t\t}\r\n\t\t\t},\r\n\t\t\terror: function(data) {\r\n\t\t\t\tflapcat.set_error_info(\"You have to wait to play this game again\");\r\n\t\t\t}\r\n\t\t});\r\n\t}");
		}startPlay(acc);
}
	private static void startPlay(Account acc) {
	acc.scrollTo(329);
	try {
		Bot.wait(200);
		Location volumeSpot = screen.find(volume).getTarget();
		screen.click(volumeSpot);
		Bot.wait(200);
		screen.click(volumeSpot);
		acc.currentlyPlaying = type;
		acc.setThreeMinuteTimeout(200);
	} catch (FindFailed e) {if(acc.window.getCurrentUrl().equals("http://gamefaucet.com/games/cat/play")){
		startGame(acc);
		}else{
			goBack(acc,type);
			acc.refillBuxAndWithdraw();
		}
	}
	}
	public static void finishPlay(Account acc) {
	acc.focus();
	Bot.wait(300);
	acc.scrollTo(329);
	Bot.wait(300);
	acc.executeScript("window.game_over = flapcat.game_over");
	acc.executeScript("flapcat.game_over(300)");
		Bot.wait(5000);
	goBack(acc,type);
	acc.currentlyPlaying = "";
}
}