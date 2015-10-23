import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	private Move lastMove = null;
	private int nextTurn = 0;
	private Player[] players = new Player[2];
	private Board board;
	Player winner = null;
	
	/**
	 * Konfiguruje plansze i uzytkownikow
	 * @param boardSize rozmiar planszy
	 * @param winSize rozmiar ciagu potrzebny do wygrania
	 */
	public Game(int boardSize, int winSize) {
		board = new Board(boardSize, winSize);
		Random rand = new Random();
		nextTurn = rand.nextBoolean() ? 1 : 0;
		Scanner sc = new Scanner(System.in);
		System.out.print("Imię gracza 1: ");
		players[0] = new Player(sc.next(), Field.State.CIRCLE);
		System.out.println("Witaj "+players[0].getName()+", twój symbol to: O");
		System.out.print("Imię gracza 2 (wpisz \"komputer\" jeśli chcesz zagrać z komputerem): ");
		String tmp = sc.next();
		if(tmp.equalsIgnoreCase("komputer")) {
			players[1] = new Player(Field.State.CROSS);
			System.out.println("Symbol komputera to: X");
		} else {
			players[1] = new Player(tmp, Field.State.CROSS);
			System.out.println("Witaj "+players[1].getName()+", twój symbol to: X");
		}
		System.out.println();
	}
	
	/**
	 * Uruchamia petle gry
	 */
	public void startGame() {
		while(winner == null && board.getAvailableFieldAmount() > 0) {
			int x = -1, y = -1;
			if(!getCurrentPlayer().isComputer()) {
				System.out.println("Podaj wiersz");
				Scanner sc = new Scanner(System.in);
				try {
					x = sc.nextInt();
				} catch(InputMismatchException e) {
					continue;
				}
				x--;
				System.out.println("Podaj kolumne");
				try {
					y = sc.nextInt();
				} catch(InputMismatchException e) {
					continue;
				}
				y--;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {}
				Move m = getComputerMove();
				x = m.getX();
				y = m.getY();
			}
			if(board.selectField(x, y, getCurrentPlayer())) {
				getCurrentPlayer().setLastMove(new Move(x, y));
				winner = board.getWinner(getCurrentPlayer());
				if(winner == null) {
					if(nextTurn == 0)
						nextTurn = 1;
					else
						nextTurn = 0;
				}
				System.out.println(this);
			} else {
				if(!getCurrentPlayer().isComputer()) {
					System.out.println("Złe dane");
					System.out.println(this);
				}
			}
			
		}
	}
	
	/**
	 * Zwraca obiekt gracza, ktory w tej chwili ma swoja kolej w rozgrywce
	 */
	public Player getCurrentPlayer() {
		return players[nextTurn];
	}
	
	/**
	 * Losuje punkt na planszy
	 */
	private Move getComputerMove() {
		Random rd = new Random();
		return new Move(rd.nextInt(board.getBoardSize()), rd.nextInt(board.getBoardSize()));
	}
	
	/**
	 * Zwraca string planszy i dodatkowe informacje o rozgrywce
	 */
	@Override
	public String toString() {
		String str = board.toString();
		if(winner == null && board.getAvailableFieldAmount() > 0) {
			str += "\nNastępny ruch gracza: "+ players[nextTurn].getName() 
							+ " (" + (players[nextTurn].getSymbol() == Field.State.CIRCLE ? "O" : "X")+")";
		} else if (winner == null && board.getAvailableFieldAmount() == 0) {
			str += "\nREMIS!!!";
		} else if (winner != null) {
			str += "\nZwycięża "+winner.getName() + " (" 
						+ (players[nextTurn].getSymbol() == Field.State.CIRCLE ? "O" : "X")+")! Gratulacje!!!";
		}
		return str;
	}
}
