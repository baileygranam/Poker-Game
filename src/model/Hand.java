package model;

import java.util.Collections;
import java.util.Vector;

/**
 * Model of a hand for a card game - cards can be added, discarded,
 * and put in order.
 * @author Christopher Finkle
 * @author Tierney Irwin
 */

public class Hand
{
	private int myMaxNumberCards;
	protected Vector<Card> myCards;

	public Hand(int maxNum)
	{
		myMaxNumberCards = maxNum;
		myCards = new Vector<Card>(0);
	}

	/**
	 * If hand can still accept more cards, and card is unique, adds card.
	 * @param card: the card to be added
	 * @return whether or not operation suceeded
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public boolean add(Card card)
	{
		boolean contains = false;
		for(Card c : myCards)
		{
			if(c.getSuit()==card.getSuit() && c.getType()==card.getType())
			{
				contains = true;
			}
		}
		
		if(contains || myCards.size()==myMaxNumberCards)
		{
			return false;
		}
		
		myCards.add(card);
		return true;
	}

	/**
	 * Removes flagged cards. Intended to be used by Player
	 * @return cards discarded
	 * 
	 * @author Christopher FInkle
	 * @author Tierney Irwin
	 */
	public Vector<Card> discard()
	{
		Vector<Card> discards = new Vector<Card>(0);
		for(int j=0; j<myCards.size(); j++)
		{
			if(myCards.get(j).isSelected())
			{
				discards.add(myCards.remove(j));
				j--;
			}
		}
		return discards;
	}
	/**
	 * Flags cards to be removed, then removes them.
	 * @param indices: vector of indices of cards to be removed
	 * @return a vector containing the discarded cards
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	
	public Vector<Card> discard(Vector<Integer> indices)
	{
		Vector<Card> discards = new Vector<Card>(0);
		for(int i = 0; i<indices.size(); i++)
		{
			myCards.get(indices.get(i)).toggleSelected();
		}
		for(int j=0; j<myCards.size(); j++)
		{
			if(myCards.get(j).isSelected())
			{
				discards.add(myCards.remove(j));
				j--;
			}
		}
		return discards;
	}

	/**
	 * lists cards in hand
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public String toString()
	{
		String str = "Hand containing";
		for(Card c : myCards)
		{
			str = str + " " + c.toString();
		}
		return str;
	}

	/**
	 * Puts cards in order from least value to greatest
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public void orderCards()
	{
		Collections.sort(myCards);
	}

	public int getNumberCardsInHand()
	{
		return myCards.size();
	}

	public Vector<Card> getCards()
	{
		return myCards;
	}
}
