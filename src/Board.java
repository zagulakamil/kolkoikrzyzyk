/**
 * Klasa reprezentujaca plansze
 */
public class Board {
	
	/**
	 * tablica pol (plansza)
	 */
	private Field [][] fields;
	/**
	 * dlugosc ciagu potrzebnego do wygrania
	 */
	private int winSize;
	
	/**
	 * Tworzy plansze
	 * @param size - wielkosc planszy
	 * @param winSize - wielkosc ciagu potrzebnego do wygrania
	 */
	public Board(int size, int winSize) {
		this.winSize = winSize;
		fields = new Field[size][size];
		for(int i=0; i<size;i++)
			for(int j=0; j<size; j++)
				fields[i][j] = new Field();
	}
	
	/**
	 * Zapisuje do stringu wyglad planszy
	 */
	@Override
	public String toString() {
		String str = "\t";
		for(int i=0; i<fields.length; i++) {
			str += i+1 +".\t";
		}
		str += "\n";
		for(int i=0; i<fields.length; i++) {
			str += (i+1) + ".\t";
			for(int j=0; j<fields[i].length; j++) {
				str += fields[i][j] + "\t";
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Zwraca ile jest pustych pol w planszy
	 */
	public int getAvailableFieldAmount() {
		int amount = 0;
		for(int i=0;i<fields.length;i++)
			for(int j=0;j<fields[i].length;j++)
				if(fields[i][j].isAvailable())
					amount++;
		return amount;
	}
	
	/**
	 * Metoda ktora sprawdza czy jest jakis zwyciezca
	 */
	public Player getWinner(Player p) {
		if(p.getLastMove() != null) {
			int x = p.getLastMove().getX();
			int y = p.getLastMove().getY();
			int streak = 1;
			// to the left from last move
			for(int i=y-1; i>=0; i--) {
				if(fields[x][i].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			// to the right from last move
			for(int i=y+1; i<fields.length; i++) {
				if(fields[x][i].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			
			// reset streak
			streak = 1;
			// to the top from last move
			for(int i=x-1; i>=0; i--) {
				if(fields[i][y].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			// to the bottom from last move
			for(int i=x+1; i<fields.length; i++)
				if(fields[i][y].getState() == p.getSymbol())
					streak++;
				else break;
			if(winSize <= streak) {
				return p;
			}
			// reset streak
			streak = 1;
			// diagonal from last move to left bottom
			for(int i=x+1, j=y-1; i<fields.length && j<fields.length && i >= 0 && j >= 0; i++, j--) {
				if(fields[i][j].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			// diagonal from last move to right top
			for(int i=x-1, j=y+1; i<fields.length && j<fields.length && i >= 0 && j >= 0; i--, j++) {
				if(fields[i][j].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			// reset streak
			streak = 1;
			// diagonal from last move to left top
			for(int i=x-1, j=y-1; i<fields.length && j<fields.length && i >= 0 && j >= 0; i--, j--) {
				if(fields[i][j].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
			// diagonal from last move to right bottom
			for(int i=x+1, j=y+1; i<fields.length && j<fields.length && i >= 0 && j >= 0; i++, j++) {
				if(fields[i][j].getState() == p.getSymbol())
					streak++;
				else break;
			}
			if(winSize <= streak) {
				return p;
			}
		}
		return null;
	}
	
	/**
	 * Zwraca wielkosc tablicy (rozmiar planszy)
	 */
	public int getBoardSize() {
		if(fields == null) return 0;
		return fields.length;
	}
	
	/**
	 * Zaznacza pole wybrane przez uzytkownika, zwraca true jesli sie udalo, false jesli nie
	 */
	public boolean selectField(int x, int y, Player player) {
		try {
			if(fields[x][y].isAvailable()) {
				fields[x][y].setValue(player.getSymbol());
				return true;
			} else {
				return false;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}
}
