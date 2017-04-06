package model;
import java.awt.Image;

/**
 * Deck class constructs the deck of the poker game 
 * and deals with the mechanics involved solely with 
 * the deck of the game.
 * 
 * @author Tierney Irwin
 * @author Christopher Finkle
 * 
 * Due Date: 21 March 2016
 */
import java.util.Collections;
import java.util.Vector;

import javax.swing.ImageIcon;

public class Deck
{
	private Vector<Card> myCards;
	private final int myFullDeckSize = 52;

	/**
	 * Deck constructor initializes the cards contained 
	 * in the deck as a vector of cards, constructs and 
	 * shuffles that deck made.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public Deck()
	{
		myCards=new Vector<Card>();
		constructDeck();
		shuffle();
	}

	/**
	 * Method constructDeck() is used to create 
	 * the 52 card deck out of all the values listed 
	 * in the enumerations CardSuit and CardType.
	 * 
	 * @return true creating the deck correctly.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public boolean constructDeck()
	{
		for(CardSuit suit: CardSuit.values())
		{
			for(CardType type: CardType.values())
			{
				Image myCardImage = getImage(suit.getSuit().charAt(0), type.getType());
				myCards.add(new Card(suit,type,myCardImage));
			}
		}
		return true;
	}
	
	/**
	 * Method to retrieve the image of the card using the suit and type.
	 */
	public Image getImage(char suit, int type)
	{
		String myCardName = (type + "" + suit);
		ImageIcon myCardImageIcon = new ImageIcon("src/images/"+myCardName+".gif");
		Image myCardImage = myCardImageIcon.getImage();
		return myCardImage;
	}
	

	/**
	 * Method draw() is used to take a card from the deck, 
	 * thus removing its values from the deck currently in use.
	 * 
	 * @return null/card depending on if the deck is empty or 
	 * if there is a card that can be returned.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public Card draw()
	{
		if(myCards.isEmpty())
		{
			return null;
		}
		else
		{
			Card card = myCards.remove(0);
			return card;
		}
	}

	/**
	 * Method shuffle() is used to shuffle the vector of cards making up the deck.
	 * 
	 * @return true having the deck properly shuffled.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public boolean shuffle()
	{
		Collections.shuffle(myCards);
		return true;
	}
	
	/**
	 * Method getFullDeckSize() returns the integer amount of a full deck.
	 * 
	 * @return myFullDeckSize the size of a full deck, which is 52.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public int getFullDeckSize()
	{
		return myFullDeckSize;
	}
	
	/**
	 * Method getCards() returns the vector of cards making up the current deck.
	 * 
	 * @return myCards all the cards currently in the deck.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public Vector<Card> getCards()
	{
		return myCards;
	}

	/**
	 * Method toString() creates a user friendly look into the deck and its contents.
	 * 
	 * @return String of the deck and its contents.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public String toString()
	{
		String str = "Deck has "+getCards().size()+"cards containing: ";
		for(Card c : this.myCards)
		{
			str = str +" "+ c.toString();
		}
		return str;
	}

	/**
	 * Method clone() clones the currently deck into myCloneDeck to avoid aliasing.
	 * 
	 * @return myCloneDeck replica of current deck in use.
	 * 
	 * @author Tierney Irwin
	 * @author Christopher Finkle
	 *
	 */
	public Object clone()
	{
		Deck myCloneDeck = new Deck();
		for(int i = 0;i<myFullDeckSize;i++)
		{
			myCloneDeck.getCards().insertElementAt(myCards.elementAt(i), i);
		}
		return myCloneDeck;
	}
}
