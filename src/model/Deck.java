package model;

import java.util.Collections;
import java.util.Vector;

/**
 * The purpose of the deck class is to simulate a poker deck.
 * This deck is constructed with 52 cards of each combination
 * of the card suit and card types. You can draw a card,
 * clone the deck for comparisons, and shuffle the deck. 
 * 
 * @author BaileyGranam
 * 
 * @due 03/22/2017
 *
 */
public class Deck
{
	private Vector<Card> myCards;
	
	final private static int DECK_SIZE = 52;
	
	/**
	 * Deck constructor is used to call the constructDeck() method
	 * and to initiate the myCards Vector of size (DECK_SIZE).
	 */

	public Deck()
	{
		myCards = new Vector<Card>(DECK_SIZE);
		
		constructDeck();
	}

	/** 
	 * The purpose of the constructDeck() method is to loop through
	 * the enumerations of the CardSuit and CardType classes and
	 * create a card for each combination. It inserts each card
	 * into the vector myCards. At the end it checks to see
	 * if the vector has the correct size of 52.
	 * 
	 * @return true if 52 cards / false if anything else.
	 */
	
	public boolean constructDeck()
	{
		for(CardSuit suit : CardSuit.values())
		{
			for(CardType type : CardType.values())
			{
				Card myCard = new Card(suit, type, null);
				myCards.add(myCard);
			}
		}
		
		if(myCards.size() == DECK_SIZE)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	/**
	 * The purpose of the draw() method is so that when it is 
	 * called the card on the top of the deck is removed and
	 * given to the user. 
	 * 
	 * (The card at index 0 of the vector is given to the user
	 * and removed from the deck of cards).
	 * 
	 * @return myDraw <-- Card on top of the deck
	 */
	public Card draw()
	{
		if(myCards.size() > 0)
		{
			Card myDraw = myCards.lastElement();
			myCards.remove(myDraw);
			return myDraw;
		}
		else 
		{
			return null;
		}
	}

	/**
	 * The purpose of the shuffle() method is to randomize the
	 * deck of poker cards. It works using the Collections 
	 * java utility. The way we check to see if the shuffle
	 * works is that we compare the shuffled deck to a clone
	 * of the original deck.
	 * 
	 * @return true if shuffled / false if not shuffled properly
	 * 
	 */
	
	public boolean shuffle()
	{
		Vector<Card> myOriginalCards = new Vector<Card>();
		myOriginalCards = myCards;
		Collections.shuffle(myCards);
		
		return (myCards != myOriginalCards);
	}

	/**
	 * Output the state of the deck class
	 */
	
	public String toString()
	{
		return "Deck: " + myCards;
	}
	
	/**
	 * Method to clone() the deck for comparisons
	 * such as checking to see that the deck
	 * shuffled.
	 */

	public Object clone()
	{
		Deck myDeckClone = new Deck();
	
		myDeckClone.myCards.removeAllElements();
		
		for(Card card : myCards)
		{
			myDeckClone.myCards.addElement(card);
		}
		
		return myDeckClone;
	}
}
