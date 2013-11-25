package Stiften;
import java.util.Arrays;
import java.util.Random;

/**
 * Een deck met Cards
 * 
 * @author Youri Tjang
 *
 */
public class Deck {
	Card[] cardArray;
	Card[] newCards;

	/**
	 * Constructor
	 */
	Deck(){
		cardArray = new Card[52];
	}

	/**
	 * Vult de array met 52 kaarten: 2,3 ... ,10,V,B,K,A van klaveren, schoppen,
	 * harten en ruiten.
	 */
	public void fillDeck() {
		int index = 0;
		for (Card.suitList s : Card.suitList.values()) {
			for (Card.numberList n : Card.numberList.values()) {
				Card newCard = new Card(s,n);
				cardArray[index] = newCard;
				index++;
			}
		}
	}

	/**
	 * Zoals gezegd is dit spel een beetje vreemd. Bijvoorbeeld: spelers kunnen
	 * kaarten toevoegen aan het deck. Hierdoor kan het aantal kaarten groter
	 * worden dan 52.
	 * 
	 * @param card
	 *            een Kaart
	 * @param index
	 *            Op positie
	 */
	public void insertAt(Card card, int index) {
		Card[] newArray = new Card[cardArray.length + 1];
		
		System.arraycopy(cardArray, 0, newArray, 0, index);
		newArray[index] = card;
		System.arraycopy(cardArray, index, newArray, index + 1, cardArray.length - index);
		
		cardArray = newArray;
	}

	/**
	 * Kaarten kunnen ook verwijderd worden uit het deck. delete Haalt de kaart
	 * met een bepaalde index er uit.
	 * 
	 * Merk op: na delete is de array zo groot als het aantal elementen dat er in zit.
	 * 
	 * @param index
	 */
	public void delete(int index) {
		Card[] newArray = new Card[cardArray.length - 1];
		
		for (int i = 0; i < cardArray.length; i++) {
			if (i != index) {
				newArray[newArray.length] = cardArray[i];
			}
		}
		
		cardArray = newArray;
	}

	/**
	 * Schud alle kaarten zodat de volgorde 'willekeurig' is. Hiervoor is het
	 * toegestaan de Java Random generator te gebruiken.
	 * 
	 */
	public void shuffle() {
		Random rand = new Random();
		
		for (int i =0; i < cardArray.length; i++)
        {
            int index = rand.nextInt(cardArray.length);
            Card oldCard = cardArray[index];
            cardArray[index] = cardArray[i];
            cardArray[i] = oldCard;
        }
	}

	/**
	 * Een gegeven kaart moet worden opgezocht in de array, en de index ervan
	 * moet als return worden teruggegeven. Zie [Hubbard p.30]
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart
	 */
	public int sequentialSearch(Card card) {
		int index = 0;
		for (; index < cardArray.length; index++) {
			if (card.getSuit() == cardArray[index].getSuit() && 
				card.getNumber() == cardArray[index].getNumber() ) {
				break;
			}
		}
		return index;
	}

	/**
	 * Legt de kaarten op volgorde. We nemen aan dat een deck op volgorde ligt,
	 * als de volgorde hetzelfde is als na {@link #fillDeck()}
	 */
	public void sort() {
		Card[] newArray = new Card[cardArray.length];
		int index = 0;
		for (Card.suitList s : Card.suitList.values()) {
			for (Card.numberList n : Card.numberList.values()) {
				//check if this card exists and how many times
				for (int i = 0; i < cardArray.length; i++) {
					if (cardArray[i].getSuit() == s && cardArray[i].getNumber() == n) {
						newArray[index] = cardArray[i];
						index++;
					}
				}
			}
		}
		cardArray = newArray;
	}

	/**
	 * Een bepaalde kaart moet worden opgezocht in de gesorteerde array op de
	 * binary search manier zoals besproken in [Hubbart p.31].
	 * 
	 * @param card
	 *            de kaart die gezocht wordt
	 * @return De index van de gevonden kaart
	 */
	public int binarySearch(Card card) {
		int index = Math.round(cardArray.length / 2);
		int previndex = 0;
		int start = 0;
		int end = cardArray.length - 1;
		
		while (cardArray[index].getSuit() != card.getSuit() || cardArray[index].getNumber() != card.getNumber()) {
			index = Math.round((start + end)/2);
			if (index == previndex) {
				index = index + 1;
			}
			
			//first compare suits
			if (card.getSuit().ordinal() > cardArray[index].getSuit().ordinal()) {
				start = index;
			} else if (card.getSuit().ordinal() < cardArray[index].getSuit().ordinal()) {
				end = index;
			} else {
				//now compare number
				if (card.getNumber().ordinal() > cardArray[index].getNumber().ordinal()) {
					start = index;
				} else if (card.getNumber().ordinal() < cardArray[index].getNumber().ordinal()) {
					end = index;
				} else {
					return index;
				}
			}
			previndex = index;
		}
		
		return index;
	}

	
	
	/**
	 *  Pretty-print het deck.
	 */
	@Override
	public String toString() {
		String str = "";
		
		for(int i=0; i<cardArray.length;i++){
			str += cardArray[i];
		}
		return str;
		
	}
}
