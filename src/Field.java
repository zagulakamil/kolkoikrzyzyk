/**
 * Klasa reprezentujaca pole w planszy
 */
public class Field {
	
	/**
	 * Trzy typy pola
	 */
	public enum State {
		EMPTY, CROSS, CIRCLE
	}
	
	/**
	 * wartosc pola
	 */
	State value = State.EMPTY;
	
	/**
	 * getter
	 */
	public State getState() {
		return value;
	}
	
	/**
	 * setter
	 */
	public void setValue(State value) {
		this.value = value;
	}
	
	/**
	 * Zwraca true jesli pole jest pusty (czyli mozemy je zaznaczyc), false w przeciwnym wypadku
	 */
	public boolean isAvailable() {
		if(getState() == State.EMPTY)
			return true;
		return false;
	}
	
	/**
	 * Reprezentacja stringowa pola
	 */
	@Override
	public String toString() {
		switch(getState()) {
			case EMPTY:
				return "_";
			case CIRCLE:
				return "O";
			case CROSS:
				return "X";
			default:
				return "_";
		}
	}
}
