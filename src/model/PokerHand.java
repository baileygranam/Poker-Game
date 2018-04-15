package model;

import java.util.Vector;

import model.CardType;

/**
 * The main purpose of the poker hand class is to evaluate the hand and 
 * determine the ranking of the hand.
 * 
 * @author BaileyGranam
 * 
 * @due 03/22/2017
 *
 */
public class PokerHand extends Hand
{

	private int myNumberCards;
	private int myMaxNumberCards;
	private Hand myHand;

	public PokerHand(int maxNum)
	{
		super(maxNum);
	}

	/**
	 * Large statement to determine the ranking of the poker hand, Goes from
	 * largest to smallest possible rank.
	 * 
	 * @return rank of hand.
	 */
	public PokerHandRanking determineRanking()
	{
		if(isRoyalFlush())
		{
			return PokerHandRanking.ROYAL_FLUSH;
		}
		else if(isStraightFlush())
		{
			return PokerHandRanking.STRAIGHT_FLUSH;
		}
		else if(isFourOfKind())
		{
			return PokerHandRanking.FOUR_OF_KIND;
		}
		else if(isFullHouse())
		{
			return PokerHandRanking.FULL_HOUSE;
		}
		else if(isFlush())
		{
			return PokerHandRanking.FLUSH;
		}
		else if(isStraight())
		{
			return PokerHandRanking.STRAIGHT;
		}
		else if(isThreeOfKind())
		{
			return PokerHandRanking.THREE_OF_KIND;
		}
		else if(isTwoPair())
		{
			return PokerHandRanking.TWO_PAIR;
		}
		else if(isPair())
		{
			return PokerHandRanking.PAIR;
		}
		else
		{
			return PokerHandRanking.HIGH_CARD;
		}
	}
	
	/**
	 * This method takes the current poker hand and compares it to the 
	 * hand being passed through the @param of the method.
	 * 
	 * @param pokerHand
	 * 
	 * @return value of comparison
	 */

	public int compareTo(PokerHand pokerHand)
	{
		if (this.getRanking() < pokerHand.getRanking())
		{
			return -1;
		}
		if (this.getRanking() > pokerHand.getRanking())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}

	public String toString()
	{
		return "Hand: " + myHand;
	}

	/**
	 * Calls the determineRanking() function and gets the rank of the hand.
	 * @return ranking
	 */
	public int getRanking()
	{
		return determineRanking().getRank();
	}
	
	/**
	 * Getter for number of cards
	 * @return getNumberCards
	 */

	public int getNumberCards()
	{
		return myNumberCards;
	}

	/**
	 * Getter for max number of cards.
	 * @return myMaxNumberCards
	 */
	public int getMaxNumberCards()
	{
		return myMaxNumberCards;
	}

	/**
	 * This method checks to see if the Poker Hand has a high card.
	 * There will always be a high card unless the player's hand is
	 * empty.
	 * 
	 * @return The method will return true if there is at least 1 card in
	 * the hand. False if there are no cards.
	 */
	public boolean isHighCard()
	{	
		Vector<Card> myCheck = getCards();

		if(myCheck.size() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * This method checks to see if there is a pair of cards in the
	 * Poker Hand of the same type. This can be checked by looping
	 * through each card in the vector and comparing the type to 
	 * the next card.
	 * 
	 * @return True if there is 1 pair then true, otherwise false.
	 */
	public boolean isPair()
	{
		orderCards();
		Vector<Card> myCheck = getCards();
		for(int i = 0; i < myCheck.size() - 2; i++)
		{
			if(myCheck.get(i).getType() == myCheck.get(i+1).getType())
			{
				return true;
			}
		}

		return false;
	}

	/**
	 * This method checks to see if there are two pairs of cards in the
	 * Poker Hand of the same type. This can be checked by looping
	 * through each card in the vector and comparing the type to 
	 * the next card. If there is a pair then we will increment
	 * myPairs by 1. At the end we will see if myPairs == 2.
	 * 
	 * @return True if there is 2 pairs then true, otherwise false.
	 */
	public boolean isTwoPair()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		int myPairs = 0;

		for(int i = 0; i < myCheck.size() - 2; i++)
		{
			if(myCheck.get(i).getType() == myCheck.get(i+1).getType())
			{
				myPairs += 1;
			}
		}

		if(myPairs == 2) 
		{
			return true;
		}
		else 
		{ 
			return false;
		}
	}

	/**
	 * This method checks to see if there is three cards in the
	 * Poker Hand of the same type. This can be checked by comparing
	 * the first three cards in the hand. If the fourth card is also
	 * the same type then it is 4 of a kind and does not pass the
	 * 3 of a kind test.
	 * 
	 * @return True if there is 3 of a kind then true, otherwise false.
	 */

	public boolean isThreeOfKind()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		if(myCheck.get(0).getType() == myCheck.get(1).getType() 
				&& myCheck.get(1).getType() == myCheck.get(2).getType()
				&& myCheck.get(2).getType() != myCheck.get(3).getType())
		{
			return true;
		}

		return false;
	}

	/**
	 * This method checks to see if there are five cards of which
	 * all are in incremental sequence such as 2, 3, 4, 5, 6. This 
	 * is evaluated by looping through and seeing if value at index
	 * (i) + 1 is equal to the value at index (i + 1).
	 * 
	 * @return True if there is a straight, otherwise false.
	 */
	public boolean isStraight()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		for(int i = 0; i < myCheck.size() - 1; i++)
		{
			if(myCheck.get(i).getType()+1 != myCheck.get(i+1).getType())
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * This method checks to see if there are five cards of which
	 * all are of the same suit such as 5 cards all hearts. This 
	 * is evaluated by looping through and seeing if suit at index
	 * (i) is equal to the suit at index (i + 1). It also checks
	 * to make sure that it is not a sequence. If it were then it 
	 * would be a straight flush.
	 * 
	 * @return True if there is a flush, otherwise false.
	 */
	public boolean isFlush()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		for(int i = 0; i < myCheck.size() - 1; i++)
		{
			if(myCheck.get(i).getSuit() != myCheck.get(i+1).getSuit()
					|| myCheck.get(i).getType()+1 == myCheck.get(i+1).getType())
			{
				return false;
			}
		}

		return true;
	}

	/**
	 * Test to see if the Poker Hand has both three of a kind and a pair.
	 * We check both possibility such that you can have 1 pair 1 three of 
	 * a kind or reversed.
	 * 
	 * @return True if there is a full house, otherwise false.
	 */
	public boolean isFullHouse()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		if(myCheck.get(0).getType() == myCheck.get(1).getType() 
				&& myCheck.get(1).getType() == myCheck.get(2).getType()
				&& myCheck.get(3).getType() == myCheck.get(4).getType())
		{
			return true;
		}
		if(myCheck.get(0).getType() == myCheck.get(1).getType() 
				&& myCheck.get(2).getType() == myCheck.get(3).getType()
				&& myCheck.get(3).getType() == myCheck.get(4).getType())
		{
			return true;
		}

		return false;
	}

	/**
	 * This method checks to see if there is four cards in the
	 * Poker Hand of the same type. This can be checked by comparing
	 * the first four cards in the hand. 
	 * 
	 * This method also checks to make sure that there are no duplicate
	 * cards. I.e two cards that are same type of same suit.
	 * 
	 * @return True if there is 4 of a kind then true, otherwise false.
	 */

	public boolean isFourOfKind()
	{
		orderCards();
		Vector<Card> myCheck = getCards();
		
		for(int i = 0; i < myCheck.size() - 1; i++)
		{
			if(myCheck.get(i).getType() == myCheck.get(i+1).getType()
					&& myCheck.get(i).getSuit() == myCheck.get(i+1).getSuit())
			{
				return false;
			}
		}

		if(myCheck.get(0).getType() == myCheck.get(1).getType() 
				&& myCheck.get(1).getType() == myCheck.get(2).getType()
				&& myCheck.get(2).getType() == myCheck.get(3).getType())
		{
			return true;
		}

		return false;
	}

	/**
	 * This method is a combination of the isStraight() and isFlush() methods.
	 * It checks to see that the Poker Hand is both a straight and a flush.
	 * 
	 * @return True if straight flush, otherwise false.
	 */
	public boolean isStraightFlush()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		for(int i = 0; i < myCheck.size() - 1; i++)
		{
			if(myCheck.get(i).getType()+1 != myCheck.get(i+1).getType())
			{
				return false;
			}
			
			if(myCheck.get(i).getSuit() != myCheck.get(i+1).getSuit())
			{
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * This method checks to see that all the cards are of the same suit
	 * and that they each increment such that they are in order of
	 * Ten, Jack, King, Queen, Ace. 
	 * 
	 * @return true if royal flush, false otherwise
	 */

	public boolean isRoyalFlush()
	{
		orderCards();
		Vector<Card> myCheck = getCards();

		for(int i = 0; i < myCheck.size() - 1; i++)
		{
			if(myCheck.get(i).getType() != 10+i)
			{
				return false;
			}
			
			if(myCheck.get(i).getSuit() != myCheck.get(i+1).getSuit())
			{
				return false;
			}
		}
		
		return true;
	}
}