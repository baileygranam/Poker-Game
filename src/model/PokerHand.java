package model;

import java.util.Vector;

/**
 * Extension of hand model that is meant specifically for 5-card poker.
 * PokerHand can be classified into various rankings of poker hand.
 * 
 * @author Christopher Finkle
 * @author Tierney Irwin
 */

public class PokerHand extends Hand
{

	private int myNumberCards;
	private int myMaxNumberCards;
	private boolean myIsHighCard;
	private boolean myIsPair;
	private boolean myIsThreeOfKind;
	private boolean myIsTwoPair;
	private boolean myIsStraight;
	private boolean myIsFlush;
	private boolean myIsFullHouse;
	private boolean myIsFourOfKind;
	private boolean myIsStraightFlush;
	private boolean myIsRoyalFlush;
	
	public PokerHand(int maxNum)
	{
		super(maxNum);
		myMaxNumberCards=maxNum;
	}

	/**
	 * helper function flags all cards as relevant
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	private void allCardsRelevant()
	{
		for(Card c : myCards)
		{
			c.setRelevance(true);
		}
	}
	
	/**
	 * This function evaluates the hand for various properties and uses
	 * these properties to determine what ranking the hand should have.
	 * 
	 * pre: Hand's myCards must contain 5 cards.
	 * post: appropriate boolean variable denoting hand type is true.
	 * 
	 * @return the pokerHandRanking object that describes the hand.
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public PokerHandRanking determineRanking()
	{
		this.orderCards();
		
		//ensures no residual relevance from previous rankings.
		for(Card c: myCards)
		{
			c.setRelevance(false);
		}
		
		//if every card has same suit, is flush
		boolean tempFlush = true;
		for(Card c: myCards)
		{
			tempFlush = tempFlush && c.getSuit()==myCards.get(0).getSuit();
		}
		
		//We need to know if it has an ace because they cause exceptions for Straights.
		boolean tempHasAce = myCards.lastElement().getType()==CardType.ACE;
		boolean tempStraight = true;
		
		//index for straight check is off-by-one in normal case
		//but off-by-two in exceptional case w/ A and 2
		int exception = 1;
		if(myCards.firstElement().getType()==CardType.TWO && tempHasAce)
		{
			exception = 2;
		}
		
		//checks that each card's value is one less than its successor, allowance made for ace.
		for(int i=0; i<myCards.size()-exception; i++)
		{
			tempStraight = tempStraight && 
					myCards.get(i).getType().getType()+1==myCards.get(i+1).getType().getType();
		}
		
		//classifies all flush or straight hands based on previous evaluation
		if(tempStraight && tempFlush && tempHasAce && exception==1)
		{
			myIsRoyalFlush=true;
			this.allCardsRelevant();
			return PokerHandRanking.ROYAL_FLUSH;
		}
		else if(tempStraight && tempFlush)
		{
			myIsStraightFlush=true;
			this.allCardsRelevant();
			return PokerHandRanking.STRAIGHT_FLUSH;
		}
		else if(tempStraight){
			myIsStraight=true;
			this.allCardsRelevant();
			return PokerHandRanking.STRAIGHT;
		}
		else if(tempFlush){
			myIsFlush=true;
			this.allCardsRelevant();
			return PokerHandRanking.FLUSH;
		}
		
		//tests for four of a kind starting from index 0 or 1
		boolean tempFour;
		for(int i=0; i<=1; i++)
		{
			tempFour=true;
			CardType kind = myCards.get(i).getType();
			for(int j=1; j<4; j++)
			{
				tempFour = tempFour && myCards.get(i+j).getType()==kind;
			}
			if(tempFour)
			{
				for(int r=0; r<4; r++)
				{
					myCards.get(i+r).setRelevance(true);
				}
				myIsFourOfKind = true;
				return PokerHandRanking.FOUR_OF_KIND;
			}
		}
		
		//tests for three of a kind starting from index 0, 1, or 2
		boolean tempThree;
		for(int i=0; i<=2; i++)
		{
			tempThree = true;
			CardType kind = myCards.get(i).getType();
			for(int j=1; j<3; j++)
			{
				tempThree = tempThree && myCards.get(i+j).getType()==kind;
			}
			if(tempThree)
			{
				for(int r=0; r<3; r++)
				{
					myCards.get(i+r).setRelevance(true);
				}
				//if three of kind, possibility of full house. 
				//Checks remaining pair for match.
				if(myCards.get((i+3)%5).getType()==myCards.get((i+4)%5).getType())
				{
					myIsFullHouse = true;
					return PokerHandRanking.FULL_HOUSE;
				}
				myIsThreeOfKind = true;
				return PokerHandRanking.THREE_OF_KIND;
			}
		}
		
		//checks for pairs starting at index 0,1,2, or 3.
		boolean tempPair;
		for(int i=0; i<=3; i++)
		{
			tempPair = myCards.get(i).getType()==myCards.get(i+1).getType();
			if(tempPair)
			{
				myCards.get(i).setRelevance(true);
				myCards.get(i+1).setRelevance(true);
				//if one pair, possibility of two pair (full house already weeded out)
				//Checks remaining three cards for pairs.
				for(int j=0; j<=1; j++)
				{
					if(myCards.get((i+2+j)%5).getType()==myCards.get((i+3+j)%5).getType())
					{
						myCards.get(i+2+j).setRelevance(true);
						myCards.get(i+3+j).setRelevance(true);
						myIsTwoPair=true;
						return PokerHandRanking.TWO_PAIR;
					}
				}
				myIsPair=true;
				return PokerHandRanking.PAIR;
			}
		}
		//If no other hand was flagged, must be high card by default.
		myCards.lastElement().setRelevance(true);
		myIsHighCard = true;
		return PokerHandRanking.HIGH_CARD;
	}

	/**
	 * compares pokerHand to another one, taking into account 
	 * tie-breaker rules by comparing relevant cards to each other, highest first.
	 * 
	 * @param pokerHand: the pokerhand to which we wish to compare this.
	 * @return 1 if our hand wins, -1 if our hand loses, 0 in unlikely event of tie.
	 * 
	 * @author Christopher Finkle
	 * @author Tierney Irwin
	 */
	public int compareTo(PokerHand pokerHand)
	{
		if(this.getRanking()>pokerHand.getRanking())
		{
			return 1;
		}
		else if(this.getRanking()<pokerHand.getRanking())
		{
			return -1;
		}
		else
		{
			Vector<Card> r1 = new Vector<Card>();
			Vector<Card> r2 = new Vector<Card>();
			for(int i=4; i>=0; i--)
			{
				if(myCards.get(i).isRelevant())
				{
					r1.add(myCards.get(i));
				}
				if(pokerHand.getCards().get(i).isRelevant())
				{
					r2.add(pokerHand.getCards().get(i));
				}
			}
			for(int j=0; j<r1.size(); j++)
			{
				int compare = r1.get(j).compareTo(r2.get(j));
				if(compare!=0)
				{
					return compare;
				}
			}
		}
		return 0;
	}

	/**
	 * toString lists cards and gives ranking (I hope!)
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
		str = str + ". Ranking: "+this.getRanking();
		return str;
	}

	public int getRanking()
	{
		return this.determineRanking().getRank();
	}

	public int getNumberCards()
	{
		return myNumberCards;
	}

	public int getMaxNumberCards()
	{
		return myMaxNumberCards;
	}

	public boolean isHighCard()
	{
		this.determineRanking();
		return myIsHighCard;
	}

	public boolean isPair()
	{
		this.determineRanking();
		return myIsPair;
	}

	public boolean isTwoPair()
	{
		this.determineRanking();
		return myIsTwoPair;
	}

	public boolean isThreeOfKind()
	{
		this.determineRanking();
		return myIsThreeOfKind;
	}

	public boolean isStraight()
	{
		this.determineRanking();
		return myIsStraight;
	}

	public boolean isFlush()
	{
		this.determineRanking();
		return myIsFlush;
	}

	public boolean isFullHouse()
	{
		this.determineRanking();
		return myIsFullHouse;
	}

	public boolean isFourOfKind()
	{
		this.determineRanking();
		return myIsFourOfKind;
	}

	public boolean isStraightFlush()
	{
		this.determineRanking();
		return myIsStraightFlush;
	}

	public boolean isRoyalFlush()
	{
		this.determineRanking();
		return myIsRoyalFlush;
	}

}
