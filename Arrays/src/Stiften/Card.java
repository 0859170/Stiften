package Stiften;
/**
 * 
 * @author Youri Tjang
 *
 */
public class Card {
	/**
	 * Klaver, Schoppen, Harten of Ruiten 
	 * Hoeft niet perse van type Object te zijn,
	 * vul zelf in
	 */
	public enum suitList {
		clubs,
		diamonds,
		hearts,
		spades
	}
	private suitList suit;
	
	/**
	 * 2,3,4,5,6,7,8,9,10,b,v,k,a
	 * Hoeft niet perse van type Object te zijn,
	 * vul zelf in
	 */
	public enum numberList {
		two,
		three,
		four,
		five,
		six,
		seven,
		eight,
		nine,
		ten,
		jack,
		queen,
		king,
		ace
	}
	private numberList number;
	
	public Card(suitList suit,numberList number) {
		this.suit = suit;
		this.number = number;
	}
	
	public suitList getSuit() {
		return suit;
	}

	public void setSuit(suitList suit) {
		this.suit = suit;
	}

	public numberList getNumber() {
		return number;
	}

	public void setNumber(numberList number) {
		this.number = number;
	}

	/**
	 * Pretty-print deze Card als string
	 */
	public String toString(){
		return "Card: "+this.suit+" Number: "+this.number;
	}
}
