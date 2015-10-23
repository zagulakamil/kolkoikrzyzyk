import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Klasa startowa, uruchamia petlę gry 
 */
public class Main {

	private static Game game;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int gameSize = 0;
		while(gameSize < 3) {
			System.out.println("Podaj wielkość planszy ( > 2 )");
			try {
				gameSize = sc.nextInt();
			} catch(InputMismatchException e) {
				continue;
			}
		}
		int gameWinSize = 0;
		while(gameWinSize < 2) {
			System.out.println("Podaj długość ciągu po którym następuje wygrana ( > 1 )");
			try {
				gameWinSize = sc.nextInt();
			} catch(InputMismatchException e) {
				continue;
			}
		}
		game = new Game(gameSize, gameWinSize);
		System.out.println(game);
		game.startGame();
	}

}
