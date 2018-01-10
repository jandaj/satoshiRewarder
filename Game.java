import org.openqa.selenium.NoSuchElementException;
import org.sikuli.script.Screen;

public abstract class Game {
	public static Screen screen = new Screen();
	//Checks for timeouts on game page, parses it, and goes back to game grid
	public static void goBack(Account acc, String type) {
		try{
			Bot.wait(200);
			acc.goTo("http://gamefaucet.com/games");
			acc.scrollTo(800);
			Bot.recordTimeout(acc);
		}catch(NoSuchElementException e){Bot.recordTimeout(acc);}
	}
}
