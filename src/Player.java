/**
 * Klasa reprezentujaca gracza
 */
public class Player {
	private String name;
	private Field.State symbol;
	private Move lastMove;
	private boolean isComputer;
	
	/**
	 * Tworzy gracza (czlowiek)
	 * @param name - nazwa
	 * @param symbol - symbol (x/o)
	 */
	public Player(String name, Field.State symbol) {
		this.name = name;
		this.symbol = symbol;
		this.isComputer = false;
	}
	
	/**
	 * Tworzy gracza (komputer)
	 * @param symbol - (x/o)
	 */
	public Player(Field.State symbol) {
		this.isComputer = true;
		this.name = "Komputer";
		this.symbol = symbol;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Zwraca symbol gracza
	 * @return
	 */
	public Field.State getSymbol() {
		return symbol;
	}
	
	/**
	 * Zwraca ostatni ruch gracza
	 * @return
	 */
	public Move getLastMove() {
		return lastMove;
	}
	
	/**
	 * Ustawia ostatni ruch gracza
	 * @param move
	 */
	public void setLastMove(Move move) {
		this.lastMove = move;
	}
	
	/**
	 * Zwraca true jest gracz jest komputerem
	 */
	public boolean isComputer() {
		return this.isComputer;
	}
}
