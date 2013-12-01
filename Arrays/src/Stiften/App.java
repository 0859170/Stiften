package Stiften;
/**
 * 
 * @author Youri Tjang
 *
 */
// Goede uitwerking! Er zijn nog wel wat verbeteringen mogelijk, maar de stof is je denk ik duidelijk. Zie verder mijn
// comments
public class App {

	/**
	 * App die onze datastructuur test.
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.fillDeck();
		deck.shuffle();
		
		Card card = new Card(Card.suitList.hearts,Card.numberList.two);
		int testIndex = 5;
		deck.insertAt(card, testIndex);
		System.out.print("Sequential search ...");
		int foundIndex = deck.sequentialSearch(card);
		System.out.println((foundIndex == testIndex)?"Win":"Fail");
		
		System.out.print("Binary search...");
		deck.sort();
		foundIndex = deck.binarySearch(card);
		System.out.println(foundIndex);

		
	}
}
