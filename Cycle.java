import java.util.ArrayList;
import java.util.Iterator;

public class Cycle {
	private static Iterator<Account> iterate;
	public static void queue(ArrayList<Account> a) {
		iterate = a.iterator();
		while(true) {
			Account next = next(a);
			next.focus();
			try{
			if(next.currentlyPlaying=="") {
				next.scrollTo(800);
			}
			if(next.threeMinuteTimeout()==0){
			if(next.currentlyPlaying=="" && next.checkCurrency == true){
				next.refillBuxAndWithdraw();
				next.checkCurrency = false;
			}
			if(next.piratesTimeout()==0) {
				if(next.currentlyPlaying=="pirates") {
					Pirates.finishPlay(next);
				}else if(next.currentlyPlaying=="") {
					Pirates.startGame(next);
				}
				next.checkCurrency = true;
			}
			if(next.currentlyPlaying=="" && next.checkCurrency == true){
				next.refillBuxAndWithdraw();
				next.checkCurrency = false;
			}
			if(next.nutsTimeout()==0) {
				if(next.currentlyPlaying=="nuts") {
					Nuts.finishPlay(next);
				}else if(next.currentlyPlaying=="") {
					Nuts.startGame(next);
				}
				next.checkCurrency = true;
			}
			if(next.currentlyPlaying=="" && next.checkCurrency == true){
				next.refillBuxAndWithdraw();
				next.checkCurrency = false;
			}
			if(next.bubblesTimeout()==0) {
				if(next.currentlyPlaying=="") {
					Bubbles.startGame(next);
				}else if(next.currentlyPlaying=="bubbles") {
					Bubbles.finishPlay(next);
				}
				next.checkCurrency = true;
			}
			if(next.currentlyPlaying=="" && next.checkCurrency == true){
				next.refillBuxAndWithdraw();
				next.checkCurrency = false;
			} 
			if(next.catTimeout()==0) {
				if(next.currentlyPlaying=="") {
					Cat.startGame(next);
				}else if(next.currentlyPlaying=="cat") {
					Cat.finishPlay(next);
				}
				next.checkCurrency = true;
			}
			if(next.currentlyPlaying=="" && next.checkCurrency == true){
				next.refillBuxAndWithdraw();
				next.checkCurrency = false;
			}
			if(next.towersTimeout()==0) {
				if(next.currentlyPlaying=="") {
					Towers.startGame(next);
				}else if(next.currentlyPlaying=="towers") {
					Towers.finishPlay(next);
				}
				next.checkCurrency = true;
			}
			}}catch(Exception e) {
				smsService.sendMessage(e.getMessage());
				System.out.println("ACCOUNT : " + next.currentlyPlaying);
				e.printStackTrace();
				next.currentlyPlaying = "";
				try{
				next.goTo("http://gamefaucet.com/games");
				next.scrollTo(800);
					Bot.wait(200);
				Bot.recordTimeout(next);
				}catch(Exception error) {
					next.window.quit();
					next.start();
				}
			}
		}}
	
	private static Account next(ArrayList<Account> a) {
		if (iterate.hasNext()) {
			return iterate.next();
		}else{
			iterate = a.iterator();
			return iterate.next();
		}
	}
}
